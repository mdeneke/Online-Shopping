import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductComponent } from './product.component';
import { ProductListComponent } from './productList/productList.component';
import { RouterModule, Routes } from '@angular/router';
import { ClubsComponent } from './productList/clubs/clubs.component';
import { JersyComponent } from './productList/jersy/jersy.component';
import { PlayersComponent } from './productList/players/players.component';
import { ListComponent } from './productList/list/list.component';
import { AddItemComponent } from './productList/addItem/addItem.component';
import { FormsModule } from '@angular/forms';

const routes: Routes = [
  {
    path: '',
    component: ProductComponent
  },

  {
    path: 'list',
    component: ProductListComponent
  },
  {
    path: 'home',
    component: JersyComponent
  },
  {
    path: 'clubs',
    component: ClubsComponent
  },
  {
    path: 'players',
    component: PlayersComponent
  },
  {
    path: 'add-item',
    component: AddItemComponent
  },

];

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    RouterModule.forChild(routes),
    FormsModule
  ],
  declarations: [ProductComponent, ProductListComponent, ClubsComponent,
    JersyComponent, PlayersComponent, ListComponent, AddItemComponent]
})
export class ProductModule { }
