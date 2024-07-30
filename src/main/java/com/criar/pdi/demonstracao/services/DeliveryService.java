package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.Delivery.DeliveryCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Delivery.DeliveryDTO;
import com.criar.pdi.demonstracao.DTOs.Delivery.DeliveryUpdateDTO;
import com.criar.pdi.demonstracao.exceptions.Delivery.DeliveryDuplicateDataException.DeliveryDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Delivery.DeliveryGenericException.DeliveryGenericException;
import com.criar.pdi.demonstracao.exceptions.Delivery.DeliveryIdentifyException.DeliveryIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Delivery.DeliveryNotFoundException.DeliveryNotFoundException;
import com.criar.pdi.demonstracao.models.Delivery.Delivery;
import com.criar.pdi.demonstracao.repositories.IDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DeliveryService {
    @Autowired
    IDeliveryRepository iDeliveryRepository;

    public DeliveryCommonDTO getDeliveryByID(String deliveryID) {
        try {
            Delivery delivery = iDeliveryRepository.findById(Integer.valueOf(deliveryID)).orElseThrow();
            return delivery.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new DeliveryNotFoundException();
        } catch (NumberFormatException e) {
            throw new DeliveryIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DeliveryCommonDTO setDelivery(DeliveryDTO deliveryDTO) {
        try {
            Delivery delivery = new Delivery(deliveryDTO);
            delivery.setInclusionDate();
            iDeliveryRepository.save(delivery);
            return delivery.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new DeliveryDuplicateDataException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public DeliveryCommonDTO updateDelivery(DeliveryUpdateDTO deliveryUpdateDTO) {

        try {
            Delivery delivery = iDeliveryRepository.findById(Integer.valueOf(deliveryUpdateDTO.ID())).orElseThrow();
            delivery.update(deliveryUpdateDTO);
            iDeliveryRepository.saveAndFlush(delivery);
            return getDeliveryByID(deliveryUpdateDTO.ID());
        } catch (NoSuchElementException e) {
            throw new DeliveryNotFoundException();
        } catch (RuntimeException e) {

            throw new RuntimeException(e);
        }
    }

    public void deleteDelivery(String deliveryID) {
        try {
            Delivery delivery = iDeliveryRepository.findById(Integer.valueOf(deliveryID)).orElseThrow();
            if (delivery.isInactive()) {
                throw new DeliveryGenericException("ESTA ENTREGA JA ESTA INATIVADA!!");
            }
            delivery.setExclusionDate();
            iDeliveryRepository.saveAndFlush(delivery);
        } catch (NoSuchElementException e) {
            throw new DeliveryNotFoundException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
