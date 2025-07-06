import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductFilterRequest } from '../../shared/interfaces/ProductFilterRequest.interface';
import { Observable } from 'rxjs';
import { Category } from '../../shared/interfaces/Category.interface';
import { Product } from '../../shared/interfaces/Product.interface';

@Injectable({
  providedIn: 'root'
})
export class ProductCatalogService {
  baseUrl = "/proxy/product/api";

  constructor(private http: HttpClient) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.baseUrl}/categories`);
  }

  getProductsWithFilter(filter: ProductFilterRequest, lastId?: number): Observable<Product[]> {
    let params = new HttpParams();

    if (this.isValidValue(filter.categoryId)) params = params.set('categoryId', filter.categoryId!.toString());
    if (this.isValidValue(filter.minPrice)) params = params.set('minPrice', filter.minPrice!.toString());
    if (this.isValidValue(filter.maxPrice)) params = params.set('maxPrice', filter.maxPrice!.toString());
    if (this.isValidValue(filter.minRating)) params = params.set('minRating', filter.minRating!.toString());
    if (this.isValidValue(filter.maxRating)) params = params.set('maxRating', filter.maxRating!.toString());
    if (lastId && lastId > 0) params = params.set('lastId', lastId.toString());

    return this.http.get<Product[]>(`${this.baseUrl}/products`, { params });
  }

  private isValidValue(value: any): boolean {
    return value != null && value !== '' && value !== 'null';
  }
}
