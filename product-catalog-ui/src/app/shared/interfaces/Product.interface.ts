import { Category } from './Category.interface';

export interface Product {
  id: number;
  category: Category;
  name: string;
  imageUrl?: string;
  price: number;
  description?: string;
  ratingAvg?: number;
  ratingCount?: number;
  createdAt?: Date;
  updatedAt?: Date;
}
