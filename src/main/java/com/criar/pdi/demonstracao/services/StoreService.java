package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.Product.ProductCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreSearchDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreUpdateDTO;
import com.criar.pdi.demonstracao.exceptions.Store.StoreDuplicateDataException.StoreDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreGenericException.StoreGenericException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreIdentifyException.StoreIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreNotFoundException.StoreNotFoundException;
import com.criar.pdi.demonstracao.models.Product.Product;
import com.criar.pdi.demonstracao.models.Store.Store;
import com.criar.pdi.demonstracao.repositories.IProductRepository;
import com.criar.pdi.demonstracao.repositories.IStoreRepository;
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
import java.util.Scanner;

@Service
public class StoreService {

    @Autowired
    private IStoreRepository iStoreRepository;
    @Autowired
    private IProductRepository iProductRepository;

    public StoreCommonDTO getStoreByID(String storeID) {
        try {
            Store store = iStoreRepository.findById(Integer.valueOf(storeID)).orElseThrow();
            return store.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new StoreNotFoundException();
        } catch (NumberFormatException e) {
            throw new StoreIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Page<StoreCommonDTO> page(Page<Store> storePage, Pageable pageable) {
        List<StoreCommonDTO> storeCommonDTOList = storePage.getContent().stream()
                .map(Store::getCommonDTO).toList();
        return new PageImpl<>(storeCommonDTOList, pageable, storePage.getTotalElements());
    }

    private Page<ProductCommonDTO> pageOfProduct(Page<Product> storePage, Pageable pageable) {
        List<ProductCommonDTO> productCommonDTOList = storePage.getContent().stream()
                .map(Product::getCommonDTO).toList();
        return new PageImpl<>(productCommonDTOList, pageable, storePage.getTotalElements());
    }

    public Page<StoreCommonDTO> getStores(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Store> storePage  = iStoreRepository.findAll(pageable);
        return page(storePage, pageable);
    }

    public List<StoreCommonDTO> getStoresByParams(StoreSearchDTO storeSearchDTO) {

        List<Store> storePage = iStoreRepository.searchStoresByParams(
                storeSearchDTO.name(),
                storeSearchDTO.ownerID(),
                storeSearchDTO.description(),
                storeSearchDTO.address(),
                storeSearchDTO.region(),
                storeSearchDTO.cnpj());
        return storePage.stream()
                .map(Store::getCommonDTO).toList(); // TODO: Retornar uma lista paginada;
    }

    public StoreCommonDTO setStore(StoreDTO storeDTO) {
        try {
            Store store = new Store(storeDTO);
            store.setInclusionDate();
            iStoreRepository.save(store);
            return store.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new StoreDuplicateDataException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public StoreCommonDTO updateStore(StoreUpdateDTO storeUpdateDTO) {
        try {
            Store store = iStoreRepository.findById(Integer.valueOf(storeUpdateDTO.ID())).orElseThrow();
            store.update(storeUpdateDTO);
            iStoreRepository.saveAndFlush(store);
            return getStoreByID(storeUpdateDTO.ID());
        } catch (NoSuchElementException e) {
            throw new StoreNotFoundException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStore(String storeID) {
        try {
            Store store = iStoreRepository.findById(Integer.valueOf(storeID)).orElseThrow();
            if (store.isInactive()) {
                throw new StoreGenericException("ESTA LOJA JA ESTA INATIVADA!!");
            }
            store.setExclusionDate();
            iStoreRepository.saveAndFlush(store);
        } catch (NoSuchElementException e) {
            throw new StoreNotFoundException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public Page<ProductCommonDTO> getProductsByStoreByID(String storeID, int page, int size) {
        try{
            Pageable pageable = PageRequest.of(page, size);
            Page<Product> productPage  = iProductRepository.findAllByStore(storeID, pageable);
            return pageOfProduct(productPage, pageable);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
