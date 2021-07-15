import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/product.service';

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css']
})
export class PlayersComponent implements OnInit {

  items: any[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getItems().subscribe((items: any[]) => {
      console.log(items)
      this.items = items.filter(f => f?.product?.name == "Player");
    });
  }
}
