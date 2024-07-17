package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import com.criar.pdi.demonstracao.DTOs.Product.ProductCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Product.ProductDTO;
import com.criar.pdi.demonstracao.DTOs.Product.ProductUpdateDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreUpdateDTO;
import com.criar.pdi.demonstracao.exceptions.Product.ProductIdentifyException.ProductIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Product.ProductNotFoundException.ProductNotFoundException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreDuplicateDataException.StoreDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreGenericException.StoreGenericException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreIdentifyException.StoreIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreNotFoundException.StoreNotFoundException;
import com.criar.pdi.demonstracao.models.Product.Product;
import com.criar.pdi.demonstracao.models.Store.Store;
import com.criar.pdi.demonstracao.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    @Autowired
    IProductRepository iProductRepository;
    public Page<ProductCommonDTO> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = iProductRepository.findAll(pageable);
        List<ProductCommonDTO> storeCommonDTOList = productPage.getContent().stream()
                .map(Product::getCommonDTO).toList();
        return new PageImpl<>(storeCommonDTOList, pageable, productPage.getTotalElements());
    }

    public ProductCommonDTO getStoreByID(String productID) {
        try {
            Product product = iProductRepository.findById(Integer.valueOf(productID)).orElseThrow();
            return product.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new ProductNotFoundException();
        } catch (NumberFormatException e) {
            throw new ProductIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProductCommonDTO setProduct(ProductDTO productDTO) {
        try {
            Product product = new Product(productDTO);
            product.setInclusionDate();
            iProductRepository.save(product);
            return product.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new StoreDuplicateDataException();
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductCommonDTO updateProduct(ProductUpdateDTO productUpdateDTO) {
        try {
            Product product = iProductRepository.findById(Integer.valueOf(productUpdateDTO.ID())).orElseThrow();
            product.update(productUpdateDTO);
            iProductRepository.saveAndFlush(product);
            return getStoreByID(productUpdateDTO.ID());
        } catch (NoSuchElementException e) {
            throw new StoreNotFoundException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteProduct(String productID){
        try{
            Product product = iProductRepository.findById(Integer.valueOf(productID)).orElseThrow();
            if(product.isInactive()){
                throw new StoreGenericException("ESTE PRODUTO JA ESTA INATIVADO!!");
            }
            product.setExclusionDate();
            iProductRepository.saveAndFlush(product);
        } catch (NoSuchElementException e) {
            throw new StoreNotFoundException();
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
