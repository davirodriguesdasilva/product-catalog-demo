package br.com.catalog.product.api.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Schema(description = "Schema product filter input")
@Setter
@Getter
public class ProductFilterRequest {

    @Schema(description = "Category ID to filter.", example = "2")
    @Positive(message = "Category ID must be a positive number.")
    private Long categoryId;

    @Schema(description = "Minimum price (inclusive).", example = "100.00")
    @DecimalMin(value = "0.0", inclusive = true, message = "Minimum price must be 0 or higher.")
    @Digits(integer = 10, fraction = 2, message = "Minimum price must be a valid decimal number with up to 2 decimal places.")
    private BigDecimal minPrice;

    @Schema(description = "Maximum price (inclusive).", example = "500.00")
    @DecimalMin(value = "0.0", inclusive = true, message = "Maximum price must be 0 or higher.")
    @Digits(integer = 10, fraction = 2, message = "Maximum price must be a valid decimal number with up to 2 decimal places.")
    private BigDecimal maxPrice;

    @Schema(description = "Minimum rating (1‑5).", example = "3")
    @DecimalMin(value = "1.0", message = "Minimum rating must be at least 1.0.")
    @DecimalMax(value = "5.0", message = "Minimum rating must not exceed 5.0.")
    private Double minRating;

    @Schema(description = "Maximum rating (1‑5).", example = "5")
    @DecimalMin(value = "1.0", message = "Maximum rating must be at least 1.0.")
    @DecimalMax(value = "5.0", message = "Maximum rating must not exceed 5.0.")
    private Double maxRating;
}
