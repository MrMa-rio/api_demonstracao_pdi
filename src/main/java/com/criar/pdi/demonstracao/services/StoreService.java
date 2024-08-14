package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.Store.StoreCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreUpdateDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserCommonDTO;
import com.criar.pdi.demonstracao.exceptions.Store.StoreDuplicateDataException.StoreDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreGenericException.StoreGenericException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreIdentifyException.StoreIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreNotFoundException.StoreNotFoundException;
import com.criar.pdi.demonstracao.models.Store.Store;
import com.criar.pdi.demonstracao.models.User.User;
import com.criar.pdi.demonstracao.models.User.UserAccessLevel;
import com.criar.pdi.demonstracao.repositories.IStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StoreService {

    @Autowired
    IStoreRepository iStoreRepository;

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

    public Page<StoreCommonDTO> getStores(int page, int size, String name, String ownerID, String region, String cnpj) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Store> storePage  = getStoresByParams(name, ownerID, region, cnpj, pageable);
        if(storePage.isEmpty())  storePage = iStoreRepository.findAll(pageable);
        return page(storePage, pageable);
    }

    private Page<Store> getStoresByParams(String name, String ownerID, String region, String cnpj, Pageable pageable){
        Page<Store> page = Page.empty();
        if(!name.isEmpty()){
            page = iStoreRepository.findAllByNameContains(name, pageable); //refatorar
        }

        return page;
    }

    public Page<StoreCommonDTO> getStoresByName(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Store> storePage = iStoreRepository.findAllByNameContains(name, pageable); //refatorar
        return page(storePage, pageable);
    }

    public Page<StoreCommonDTO> getStoresByCnpj(int page, int size, String cnpj) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Store> storePage = iStoreRepository.findAllByCnpjContains(cnpj, pageable); //refatorar
        return page(storePage, pageable);
    }

    public Page<StoreCommonDTO> getStoresByOwnerID(int page, int size, String ownerID) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Store> storePage = iStoreRepository.findAllByOwnerID(ownerID, pageable); //refatorar
        return page(storePage, pageable);
    }

    public Page<StoreCommonDTO> getStoresByRegion(int page, int size, String region) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Store> storePage = iStoreRepository.findAllByRegion(region, pageable); //refatorar
        return page(storePage, pageable);
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
}
