package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.ProductInOrder.ProductInOrderCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInOrder.ProductInOrderDTO;
import com.criar.pdi.demonstracao.exceptions.ProductInOrder.ProductInOrderDuplicateDataException.ProductInOrderDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.ProductInOrder.ProductInOrderGenericException.ProductInOrderGenericException;
import com.criar.pdi.demonstracao.exceptions.ProductInOrder.ProductInOrderIdentifyException.ProductInOrderIdentifyException;
import com.criar.pdi.demonstracao.exceptions.ProductInOrder.ProductInOrderNotFoundException.ProductInOrderNotFoundException;
import com.criar.pdi.demonstracao.models.ProductInOrder.ProductInOrder;
import com.criar.pdi.demonstracao.repositories.IProductInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductInOrderService {
    @Autowired
    IProductInOrderRepository iProductInOrderRepository;
    public ProductInOrderCommonDTO getProductInOrderByID(String productInOrderID) {
        try {
            ProductInOrder productInOrder = iProductInOrderRepository.findById(Integer.valueOf(productInOrderID)).orElseThrow();
            return productInOrder.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new ProductInOrderNotFoundException();
        } catch (NumberFormatException e) {
            throw new ProductInOrderIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private Page<ProductInOrderCommonDTO> page(Page<ProductInOrder> productPage, Pageable pageable) {
        List<ProductInOrderCommonDTO> productInOrderCommonDTOList = productPage.getContent().stream()
                .map(ProductInOrder::getCommonDTO).toList();
        return new PageImpl<>(productInOrderCommonDTOList, pageable, productPage.getTotalElements());
    }

    public Page<ProductInOrderCommonDTO> getProductsInOrderByOrderID(Integer orderID, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductInOrderCommonDTO> productsPage = iProductInOrderRepository.findAllByOrderID(orderID, pageable);
        return productsPage;
    }

    public ProductInOrderCommonDTO setProductInOrder(ProductInOrderDTO productInOrderDTO) {
        try {
            ProductInOrder productInOrder = new ProductInOrder(productInOrderDTO);
            productInOrder.setInclusionDate();
            iProductInOrderRepository.save(productInOrder);
            return productInOrder.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new ProductInOrderDuplicateDataException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProductInOrder(String productInOrderID) {
        try {
            ProductInOrder productInOrder = iProductInOrderRepository.findById(Integer.valueOf(productInOrderID)).orElseThrow();
            if (productInOrder.isInactive()) {
                throw new ProductInOrderGenericException("O PRODUTO NESTE CARRINHO JA ESTA INATIVADO!!");
            }
            productInOrder.setExclusionDate();
            iProductInOrderRepository.saveAndFlush(productInOrder);
        } catch (NoSuchElementException e) {
            throw new ProductInOrderNotFoundException();
        } catch (NumberFormatException e) {
            throw new ProductInOrderGenericException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
