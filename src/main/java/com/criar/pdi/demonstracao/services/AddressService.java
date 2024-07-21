package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.Address.AddressCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Address.AddressDTO;
import com.criar.pdi.demonstracao.DTOs.Address.AddressUpdateDTO;
import com.criar.pdi.demonstracao.exceptions.Address.AddressDuplicateDataException.AddressDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Address.AddressGenericException.AddressGenericException;
import com.criar.pdi.demonstracao.exceptions.Address.AddressIdentifyException.AddressIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Address.AddressNotFoundException.AddressNotFoundException;
import com.criar.pdi.demonstracao.models.Address.Address;
import com.criar.pdi.demonstracao.repositories.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AddressService {

    @Autowired
    IAddressRepository iAddressRepository;
    public AddressCommonDTO getAddressByID(String addressID) {
        try {
            Address address = iAddressRepository.findById(Integer.valueOf(addressID)).orElseThrow();
            return address.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new AddressNotFoundException();
        } catch (NumberFormatException e) {
            throw new AddressIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AddressCommonDTO setAddress(AddressDTO addressDTO) {
        try {
            Address address = new Address(addressDTO);
            address.setInclusionDate();
            iAddressRepository.save(address);
            return address.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new AddressDuplicateDataException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public AddressCommonDTO updateAddress(AddressUpdateDTO addressUpdateDTO) {

        try {
            Address address = iAddressRepository.findById(Integer.valueOf(addressUpdateDTO.ID())).orElseThrow();
            address.update(addressUpdateDTO);
            iAddressRepository.saveAndFlush(address);
            return getAddressByID(addressUpdateDTO.ID());
        } catch (NoSuchElementException e) {
            throw new AddressNotFoundException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAddress(String addressID) {
        try {
            Address address = iAddressRepository.findById(Integer.valueOf(addressID)).orElseThrow();
            if (address.isInactive()) {
                throw new AddressGenericException("ESTE ENDEREÇO JA ESTA INATIVADO!!");
            }
            address.setExclusionDate();
            iAddressRepository.saveAndFlush(address);
        } catch (NoSuchElementException e) {
            throw new AddressNotFoundException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
