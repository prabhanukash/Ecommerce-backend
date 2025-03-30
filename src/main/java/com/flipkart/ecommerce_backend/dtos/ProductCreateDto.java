package com.flipkart.ecommerce_backend.dtos;


import com.flipkart.ecommerce_backend.models.ProductStatus;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Data Transfer Object for creating a new base Product.
 * Assumes a default variant/SKU might be generated by the service layer,
 * or variants are added via separate API calls.
 */
public record ProductCreateDto(

        @NotBlank(message = "Product name cannot be blank")
        @Size(max = 255, message = "Product name cannot exceed 255 characters")
        String name,

        @Size(max = 500, message = "Short description cannot exceed 500 characters")
        String shortDescription,

        String longDescription,

        @NotNull(message = "Base variantPrice cannot be null")
        @DecimalMin(value = "0.01", message = "Base variantPrice must be positive")
        @Digits(integer=10, fraction=2, message = "Base variantPrice format invalid (e.g., 1234567890.99)")
        BigDecimal basePrice,

        @NotBlank(message = "Category cannot be blank")
        @Size(max = 100)
        String category,

        @NotBlank(message = "Brand cannot be blank")
        @Size(max = 100)
        String brand,

        @Size(max = 1024, message = "Image URL too long")
        String imageUrl,

        ProductStatus status,

        @NotBlank(message = "SKU cannot be blank")
        @Size(max = 100)
        String sku,

        @DecimalMin(value = "0.01", message = "Variant price must be positive")
        @Digits(integer=10, fraction=2)
        BigDecimal variantPrice,

        @Min(value = 0, message = "Initial quantity cannot be negative")
        Integer initialQuantity,

        @Size(max = 50)
        String color,

        @Size(max = 50)
        String size,

        @Size(max = 1024)
        String variantImageUrl
) { }
