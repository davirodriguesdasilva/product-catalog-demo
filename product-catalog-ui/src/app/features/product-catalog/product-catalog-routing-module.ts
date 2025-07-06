import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductCatalog } from './page/product-catalog';

const routes: Routes = [
    {
    path: '',
    pathMatch: 'full',
    component: ProductCatalog,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductCatalogRoutingModule { }
