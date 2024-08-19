package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.ProductInCart.ProductInCartCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInCart.ProductInCartDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInCart.ProductInCartUpdateDTO;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartDuplicateDataException.ProductInCartDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartGenericException.ProductInCartGenericException;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartIdentifyException.ProductInCartIdentifyException;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartNotFoundException.ProductInCartNotFoundException;
import com.criar.pdi.demonstracao.models.ProductsInCart.ProductInCart;
import com.criar.pdi.demonstracao.repositories.IProductInCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class ProductInCartService {
    @Autowired
    IProductInCartRepository iProductInCartRepository;
    public ProductInCartCommonDTO getProductInCartByID(String productInCartID) {
        try {
            ProductInCart productInCart = iProductInCartRepository.findById(Integer.valueOf(productInCartID)).orElseThrow();
            return productInCart.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new ProductInCartNotFoundException();
        } catch (NumberFormatException e) {
            throw new ProductInCartIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ProductInCartCommonDTO> getProductsInCartByCartID(String shoppingCartID) {
        ArrayList<ProductInCartCommonDTO> productInCartList = iProductInCartRepository.findAllByShoppingCartID(shoppingCartID);
        return productInCartList;
    }

    public ProductInCartCommonDTO setProductInCart(ProductInCartDTO productInCartDTO) {
        try {
            ProductInCart productInCart = new ProductInCart(productInCartDTO);
            productInCart.setInclusionDate();
            iProductInCartRepository.save(productInCart);
            return productInCart.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new ProductInCartDuplicateDataException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductInCartCommonDTO updateProductInCart(ProductInCartUpdateDTO productInCartUpdateDTO) {
        try {
            ProductInCart productInCart = iProductInCartRepository.findById(Integer.valueOf(productInCartUpdateDTO.ID())).orElseThrow();
            productInCart.update(productInCartUpdateDTO);
            iProductInCartRepository.saveAndFlush(productInCart);
            return getProductInCartByID(productInCartUpdateDTO.ID());
        } catch (NoSuchElementException e) {
            throw new ProductInCartNotFoundException();
        } catch (NumberFormatException e) {
            throw new ProductInCartIdentifyException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProductInCart(String productInCartID) {
        try {
            ProductInCart productInCart = iProductInCartRepository.findById(Integer.valueOf(productInCartID)).orElseThrow();
            if (productInCart.isInactive()) {
                throw new ProductInCartGenericException("O PRODUTO NESTE CARRINHO JA ESTA INATIVADO!!");
            }
            productInCart.setExclusionDate();
            iProductInCartRepository.saveAndFlush(productInCart);
        } catch (NoSuchElementException e) {
            throw new ProductInCartNotFoundException();
        } catch (NumberFormatException e) {
            throw new ProductInCartGenericException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
