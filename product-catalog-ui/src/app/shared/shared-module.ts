import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductCard } from './components/product-card/product-card';
import { ProductFilters } from './components/product-filters/product-filters';

const declarationExport = [
  ProductCard,
  ProductFilters
];

const importExport = [FormsModule];

@NgModule({
  declarations: [...declarationExport],
  imports: [CommonModule, ...importExport],
  exports: [...declarationExport, ...importExport],
})
export class SharedModule { }
