import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ImgFallbackModule } from 'ngx-img-fallback';
import { ProductCard } from './components/product-card/product-card';
import { ProductFilters } from './components/product-filters/product-filters';

const declarationExport = [
  ProductCard,
  ProductFilters
];

const importExport = [
  FormsModule,
  ImgFallbackModule
];

@NgModule({
  declarations: [...declarationExport],
  imports: [CommonModule, ...importExport],
  exports: [...declarationExport, ...importExport],
})
export class SharedModule { }
