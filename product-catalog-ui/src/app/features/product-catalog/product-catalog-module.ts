import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ProductCatalogRoutingModule } from './product-catalog-routing-module';
import { ProductCatalog } from './page/product-catalog';
import { ProductList } from './components/product-list/product-list';
import { SharedModule } from '../../shared/shared-module';

@NgModule({
  declarations: [
    ProductCatalog,
    ProductList
  ],
  imports: [
    CommonModule,
    FormsModule,
    ProductCatalogRoutingModule,
    SharedModule
  ]
})
export class ProductCatalogModule { }
