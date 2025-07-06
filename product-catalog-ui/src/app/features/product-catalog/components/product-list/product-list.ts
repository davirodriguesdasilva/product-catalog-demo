import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';
import { ProductCatalogService } from '../../product-catalog.service';
import { Product } from '../../../../shared/interfaces/Product.interface';
import { Category } from '../../../../shared/interfaces/Category.interface';
import { ProductFilterRequest } from '../../../../shared/interfaces/ProductFilterRequest.interface';
import { Subject } from 'rxjs';
import { takeUntil, finalize } from 'rxjs/operators';

@Component({
  selector: 'app-product-list',
  standalone: false,
  templateUrl: './product-list.html',
  styleUrl: './product-list.scss'
})
export class ProductList implements OnInit, OnDestroy {
  products: Product[] = [];
  categories: Category[] = [];
  isLoading = false;
  hasMoreProducts = true;
  currentFilters: ProductFilterRequest = {};
  private lastProductId = 0;
  private destroy$ = new Subject<void>();

  constructor(private productCatalogService: ProductCatalogService) { }

  ngOnInit() {
    this.loadCategories();
    this.loadProducts();
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  @HostListener('window:scroll')
  onScroll() {
    const { pageYOffset, innerHeight } = window;
    const { scrollHeight } = document.documentElement;

    if (pageYOffset + innerHeight > scrollHeight - 200 && !this.isLoading && this.hasMoreProducts) {
      this.loadProducts();
    }
  }

  loadCategories() {
    this.productCatalogService.getCategories()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (categories: Category[]) => this.categories = categories,
        error: error => console.error('Erro ao carregar categorias:', error)
      });
  }

  loadProducts(reset = false) {
    if (this.isLoading) return;

    this.isLoading = true;
    if (reset) {
      this.products = [];
      this.lastProductId = 0;
      this.hasMoreProducts = true;
    }

    this.productCatalogService.getProductsWithFilter(this.currentFilters, this.lastProductId)
      .pipe(takeUntil(this.destroy$), finalize(() => this.isLoading = false))
      .subscribe({
        next: (newProducts: Product[]) => {
          if (newProducts.length === 0) {
            this.hasMoreProducts = false;
          } else {
            this.products = [...this.products, ...newProducts];
            this.lastProductId = newProducts[newProducts.length - 1].id;
          }
        },
        error: error => console.error('Erro ao carregar produtos:', error)
      });
  }

  onFiltersChange(filters: ProductFilterRequest) {
    this.currentFilters = filters;
    this.loadProducts(true);
  }

  trackByProductId = (index: number, product: Product) => product.id;
}
