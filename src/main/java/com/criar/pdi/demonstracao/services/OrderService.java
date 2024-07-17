package com.criar.pdi.demonstracao.services;


import com.criar.pdi.demonstracao.DTOs.Order.OrderCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Order.OrderDTO;
import com.criar.pdi.demonstracao.DTOs.Order.OrderUpdateDTO;
import com.criar.pdi.demonstracao.DTOs.Product.ProductCommonDTO;
import com.criar.pdi.demonstracao.exceptions.Order.OrderDuplicateDataException.OrderDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Order.OrderIdentifyException.OrderIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Order.OrderNotFoundException.OrderNotFoundException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreGenericException.StoreGenericException;
import com.criar.pdi.demonstracao.models.Order.Order;
import com.criar.pdi.demonstracao.models.Product.Product;
import com.criar.pdi.demonstracao.repositories.IOrderRepository;
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
public class OrderService {
    @Autowired
    IOrderRepository iOrderRepository;

    public Page<OrderCommonDTO> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> productPage = iOrderRepository.findAll(pageable);
        List<OrderCommonDTO> orderCommonDTOList = productPage.getContent().stream()
                .map(Order::getCommonDTO).toList();
        return new PageImpl<>(orderCommonDTOList, pageable, productPage.getTotalElements());
    }

    public OrderCommonDTO getOrderByID(String orderID) {
        try {
            Order order = iOrderRepository.findById(Integer.valueOf(orderID)).orElseThrow();
            return order.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new OrderNotFoundException();
        } catch (NumberFormatException e) {
            throw new OrderIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public OrderCommonDTO setOrder(OrderDTO orderDTO) {
        try {
            Order order = new Order(orderDTO);
            order.setInclusionDate();
            iOrderRepository.save(order);
            return order.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new OrderDuplicateDataException();
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public OrderCommonDTO updateOrder(OrderUpdateDTO orderUpdateDTO) {
        try {
            Order order = iOrderRepository.findById(Integer.valueOf(orderUpdateDTO.ID())).orElseThrow();
            order.update(orderUpdateDTO);
            iOrderRepository.saveAndFlush(order);
            return getOrderByID(orderUpdateDTO.ID());
        } catch (NoSuchElementException e) {
            throw new OrderNotFoundException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOrder(String orderID) {
        try{
            Order order = iOrderRepository.findById(Integer.valueOf(orderID)).orElseThrow();
            if(order.isInactive()){
                throw new StoreGenericException("ESTE PEDIDO JA ESTA INATIVADO!!");
            }
            order.setExclusionDate();
            iOrderRepository.saveAndFlush(order);
        } catch (NoSuchElementException e) {
            throw new OrderNotFoundException();
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
