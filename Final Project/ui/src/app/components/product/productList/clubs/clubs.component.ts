import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/product.service';

@Component({
  selector: 'app-clubs',
  templateUrl: './clubs.component.html',
  styleUrls: ['./clubs.component.css']
})
export class ClubsComponent implements OnInit {

  items: any[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getItems().subscribe((items: any[]) => {
      console.log(items)
      this.items = items.filter(f => f?.product?.name == "Club");
    });
  }
}
