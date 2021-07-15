import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getItems(): Observable<any> {
    return this.http.get('http://localhost:8080/items');
  }

  getProducts(): Observable<any> {
    return this.http.get('http://localhost:8080/products');
  }

  createItem(item: any, productId: number): Observable<any> {
    return this.http.post('http://localhost:8080/items/' + productId, item);
  }

  uploadImage(formData: FormData, itemId: any): Observable<any> {
    console.log(itemId)
    return this.http.post('http://localhost:8080/items/upload/' + itemId, formData);
  }
}
