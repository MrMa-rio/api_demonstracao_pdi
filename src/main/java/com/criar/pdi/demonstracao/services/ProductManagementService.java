package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.ProductInCart.ProductInCartCommonDTO;
import com.criar.pdi.demonstracao.components.ProductUtilities.ProductUtilities;
import com.criar.pdi.demonstracao.models.Coupon.CouponDiscountType;
import org.springframework.stereotype.Service;

@Service
public class ProductManagementService {

    private final ProductUtilities productUtilities;

    public ProductManagementService(ProductUtilities productUtilities) {
        this.productUtilities = productUtilities;
    }

    public ProductInCartCommonDTO productWithinDiscount(CouponDiscountType discountType, ProductInCartCommonDTO productDTO){

        if(discountType == CouponDiscountType.FIXED){

            return new ProductInCartCommonDTO(
                    productDTO.ID(),
                    productDTO.productID(),
                    productDTO.quantity(),
                    productDTO.price(),
                    productDTO.shoppingCartID(),
                    productDTO.inclusionDate(),
                    productDTO.updatedDate(),
                    productDTO.exclusionDate()
            );
        }
        if(discountType == CouponDiscountType.PERCENTAGE){
            return new ProductInCartCommonDTO(
                    productDTO.ID(),
                    productDTO.productID(),
                    productDTO.quantity(),
                    productDTO.price(),
                    productDTO.shoppingCartID(),
                    productDTO.inclusionDate(),
                    productDTO.updatedDate(),
                    productDTO.exclusionDate()
            );
        }
        return null;
    }
}
