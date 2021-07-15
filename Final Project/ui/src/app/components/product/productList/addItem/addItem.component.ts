import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/product.service';

@Component({
  selector: 'app-addItem',
  templateUrl: './addItem.component.html',
  styleUrls: ['./addItem.component.css']
})
export class AddItemComponent implements OnInit {

  products: any[] = [];
  productId: number = 0;
  item: any = {};
  fileName = '';
  formData: any;
  success = '';
  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit() {
    this.productService.getProducts().subscribe(products => this.products = products)
  }
  save() {
    this.productService.createItem(this.item, this.productId).subscribe(item => {
      console.log(item?.id)
      this.productService.uploadImage(this.formData, item?.id).subscribe(res => {

      });
      this.item = {};
      if (this.productId == 1) {
        this.router.navigate(['products/clubs'])
      }
      if (this.productId == 2) {
        this.router.navigate(['products/players'])
      }
    })
  }

  onFileSelected(event: any) {

    const file: File = event.target.files[0];

    if (file) {

      this.fileName = file.name;

      const formData = new FormData();

      formData.append("multipartFile", file);
      this.formData = formData;
    }
  }

}
