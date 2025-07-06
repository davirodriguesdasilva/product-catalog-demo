export interface ProductFilterRequest {
  categoryId?: number | string;
  minPrice?: number | string;
  maxPrice?: number | string;
  minRating?: number | string;
  maxRating?: number | string;
}
