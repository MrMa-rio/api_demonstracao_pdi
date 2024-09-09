package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.ProductCoupon.ProductCouponDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInCart.ProductInCartCommonDTO;
import com.criar.pdi.demonstracao.components.ProductUtilities.ProductUtilities;
import com.criar.pdi.demonstracao.models.ProductsInCart.ProductInCart;
import com.criar.pdi.demonstracao.repositories.IProductInCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManagementService {

    private final ProductUtilities productUtilities;
    @Autowired
    private IProductInCartRepository iProductInCartRepository;

    public ProductManagementService(ProductUtilities productUtilities) {
        this.productUtilities = productUtilities;
    }

    public ProductInCartCommonDTO productWithinDiscount(ProductCouponDTO productCouponDTO) {

        Double price = productUtilities.calculatePrice(
                productCouponDTO.coupon().discountType(),
                productCouponDTO.product().price(),
                productCouponDTO.coupon().discountValue()
        );
        return new ProductInCartCommonDTO(
                productCouponDTO.product().ID(),
                productCouponDTO.product().productID(),
                productCouponDTO.product().quantity(),
                price,
                productCouponDTO.product().shoppingCartID(),
                productCouponDTO.product().inclusionDate(),
                productCouponDTO.product().updatedDate(),
                productCouponDTO.product().exclusionDate()
        );
    }

    public ProductInCartCommonDTO setProductWithDiscountInCart(ProductCouponDTO productCouponDTO) {
        ProductInCart productDTO = new ProductInCart(productWithinDiscount(productCouponDTO));
        iProductInCartRepository.saveAndFlush(productDTO);
        return productDTO.getCommonDTO();
    }
}
