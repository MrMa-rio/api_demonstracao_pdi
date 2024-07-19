package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.ShoppingCart.ShoppingCartDTO;
import com.criar.pdi.demonstracao.exceptions.ShoppingCart.ShoppingCartDuplicateDataException.ShoppingCartDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.ShoppingCart.ShoppingCartGenericException.ShoppingCartGenericException;
import com.criar.pdi.demonstracao.exceptions.ShoppingCart.ShoppingCartIdentifyException.ShoppingCartIdentifyException;
import com.criar.pdi.demonstracao.exceptions.ShoppingCart.ShoppingCartNotFoundException.ShoppingCartNotFoundException;
import com.criar.pdi.demonstracao.models.ShoppingCart.ShoppingCart;
import com.criar.pdi.demonstracao.DTOs.ShoppingCart.ShoppingCartCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ShoppingCart.ShoppingCartUpdateDTO;
import com.criar.pdi.demonstracao.repositories.IShoppingCartRepository;
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
public class ShoppingCartService {
    @Autowired
    IShoppingCartRepository iShoppingCartRepository;

    public Page<ShoppingCartCommonDTO> getShoppingCarts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ShoppingCart> shoppingCartPage = iShoppingCartRepository.findAll(pageable);
        List<ShoppingCartCommonDTO> shoppingCartCommonDTOList = shoppingCartPage.getContent().stream()
                .map(ShoppingCart::getCommonDTO).toList();
        return new PageImpl<>(shoppingCartCommonDTOList, pageable, shoppingCartPage.getTotalElements());
    }

    public ShoppingCartCommonDTO getShoppingCartByID(String shoppingCartID) {
        try {
            ShoppingCart shoppingCart = iShoppingCartRepository.findById(Integer.valueOf(shoppingCartID)).orElseThrow();
            return shoppingCart.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new ShoppingCartNotFoundException();
        } catch (NumberFormatException e) {
            throw new ShoppingCartIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ShoppingCartCommonDTO setShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        try {
            ShoppingCart shoppingCart = new ShoppingCart(shoppingCartDTO);
            shoppingCart.setInclusionDate();
            iShoppingCartRepository.save(shoppingCart);
            return shoppingCart.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new ShoppingCartDuplicateDataException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ShoppingCartCommonDTO updateShoppingCart(ShoppingCartUpdateDTO shoppingCartUpdateDTO) {
        try {
            ShoppingCart shoppingCart = iShoppingCartRepository.findById(Integer.valueOf(shoppingCartUpdateDTO.ID())).orElseThrow();
            shoppingCart.update(shoppingCartUpdateDTO);
            iShoppingCartRepository.saveAndFlush(shoppingCart);
            return getShoppingCartByID(shoppingCartUpdateDTO.ID());
        } catch (NoSuchElementException e) {
            throw new ShoppingCartNotFoundException();
        } catch (NumberFormatException e) {
            throw new ShoppingCartIdentifyException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteShoppingCart(String shoppingCartID) {
        try {
            ShoppingCart shoppingCart = iShoppingCartRepository.findById(Integer.valueOf(shoppingCartID)).orElseThrow();
            if (shoppingCart.isInactive()) {
                throw new ShoppingCartGenericException("ESTE CARRINHO DE COMPRAS JA ESTA INATIVADO!!");
            }
            shoppingCart.setExclusionDate();
            iShoppingCartRepository.saveAndFlush(shoppingCart);
        } catch (NoSuchElementException e) {
            throw new ShoppingCartNotFoundException();
        } catch (NumberFormatException e) {
            throw new ShoppingCartIdentifyException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
