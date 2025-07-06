import { Component, Input, ChangeDetectionStrategy } from '@angular/core';
import { Product } from '../../interfaces/Product.interface';

@Component({
  selector: 'app-product-card',
  standalone: false,
  templateUrl: './product-card.html',
  styleUrl: './product-card.scss',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ProductCard {
  @Input() product!: Product;

  getStars() {
    const stars = [];
    const rating = this.product?.ratingAvg || 0;
    
    for (let i = 1; i <= 5; i++) {
      stars.push({
        filled: i <= rating
      });
    }
    
    return stars;
  }
}
