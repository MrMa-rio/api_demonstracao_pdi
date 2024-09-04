package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.ProductInPromotion.ProductInPromotionCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInPromotion.ProductInPromotionDTO;
import com.criar.pdi.demonstracao.exceptions.Address.AddressIdentifyException.AddressIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Address.AddressNotFoundException.AddressNotFoundException;
import com.criar.pdi.demonstracao.exceptions.ProductInPromotion.ProductInPromotionGenericException.ProductInPromotionGenericException;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartDuplicateDataException.ProductInCartDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.ProductInPromotion.ProductInPromotionIdentifyException.ProductInPromotionIdentifyException;
import com.criar.pdi.demonstracao.exceptions.ProductInPromotion.ProductInPromotionNotFoundException.ProductInPromotionNotFoundException;
import com.criar.pdi.demonstracao.models.ProductInPromotion.ProductInPromotion;
import com.criar.pdi.demonstracao.repositories.IProductInPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductInPromotionService {

    @Autowired
    private IProductInPromotionRepository iProductInPromotionRepository;

    public ProductInPromotionCommonDTO getProductInPromotionByProductID(String productID) {
        try {
            ProductInPromotion productInPromotion = iProductInPromotionRepository.findByProductID(Integer.valueOf(productID)).orElseThrow();
            return productInPromotion.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new AddressNotFoundException();
        } catch (NumberFormatException e) {
            throw new AddressIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProductInPromotionCommonDTO setProductInPromotion(ProductInPromotionDTO productInPromotionDTO) {
        try {
            ProductInPromotion productInPromotion = new ProductInPromotion(productInPromotionDTO);
            productInPromotion.setInclusionDate();
            iProductInPromotionRepository.save(productInPromotion);
            return productInPromotion.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new ProductInCartDuplicateDataException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProductInPromotion(String productInPromotionID) {
        try {
            ProductInPromotion productInPromotion = iProductInPromotionRepository.findById(Integer.valueOf(productInPromotionID)).orElseThrow();
            if (productInPromotion.isInactive()) {
                throw new ProductInPromotionGenericException("O PRODUTO EM PROMOCAO JA ESTA INATIVADO!!");
            }
            productInPromotion.setExclusionDate();
            iProductInPromotionRepository.saveAndFlush(productInPromotion);
        } catch (NoSuchElementException e) {
            throw new ProductInPromotionNotFoundException();
        } catch (NumberFormatException e) {
            throw new ProductInPromotionIdentifyException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
