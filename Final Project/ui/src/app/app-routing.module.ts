import { Routes, RouterModule } from '@angular/router';
import { ShellComponent } from './components/layout/shell/shell.component';
import { NgModule } from '@angular/core';

const routes: Routes = [

  {
    path: '',
    component: ShellComponent,

    children: [
      {
        path: '',
        redirectTo: 'products/home',
        pathMatch: 'full'
      },
      {
        path: 'products',
        loadChildren: () =>
          import("./components/product/product.module").then(p => p.ProductModule), // lazy loading
      },

    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
