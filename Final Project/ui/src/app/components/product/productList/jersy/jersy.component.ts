import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/product.service';

@Component({
  selector: 'app-jersy',
  templateUrl: './jersy.component.html',
  styleUrls: ['./jersy.component.css']
})
export class JersyComponent implements OnInit {

  items: any[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getItems().subscribe(items => this.items = items);
  }

}
