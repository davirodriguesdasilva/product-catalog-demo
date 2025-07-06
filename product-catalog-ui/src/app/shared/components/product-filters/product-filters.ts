import { Component, EventEmitter, Input, Output, OnDestroy } from '@angular/core';
import { Category } from '../../interfaces/Category.interface';
import { ProductFilterRequest } from '../../interfaces/ProductFilterRequest.interface';
import { Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-product-filters',
  standalone: false,
  templateUrl: './product-filters.html',
  styleUrl: './product-filters.scss'
})
export class ProductFilters implements OnDestroy {
  @Input() categories: Category[] = [];
  @Output() filtersChange = new EventEmitter<ProductFilterRequest>();

  filters: ProductFilterRequest = {};
  private filterSubject = new Subject<ProductFilterRequest>();
  private destroy$ = new Subject<void>();

  constructor() {
    this.filterSubject.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      takeUntil(this.destroy$)
    ).subscribe(filters => this.filtersChange.emit(filters));
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  onFiltersChange() {
    this.filterSubject.next({ ...this.filters });
  }

  clearFilters() {
    this.filters = {};
    this.onFiltersChange();
  }
}
