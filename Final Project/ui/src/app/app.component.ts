import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  products: any = [];

  constructor(private http: HttpClient) {

  }
  ngOnInit(): void {
    this.http.get("http://localhost:8080/products").subscribe(res => this.products = res);
  }


}
