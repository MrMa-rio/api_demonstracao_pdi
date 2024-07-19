package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.Payment.PaymentCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Payment.PaymentDTO;
import com.criar.pdi.demonstracao.DTOs.Payment.PaymentUpdateDTO;
import com.criar.pdi.demonstracao.exceptions.Payment.PaymentDuplicateDataException.PaymentDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Payment.PaymentGenericException.PaymentGenericException;
import com.criar.pdi.demonstracao.exceptions.Payment.PaymentIdentifyException.PaymentIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Payment.PaymentNotFoundException.PaymentNotFoundException;
import com.criar.pdi.demonstracao.models.Payment.Payment;
import com.criar.pdi.demonstracao.repositories.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PaymentService {
    @Autowired
    IPaymentRepository iPaymentRepository;
    public PaymentCommonDTO getPaymentByID(String productInCartID) {
        try {
            Payment payment = iPaymentRepository.findById(Integer.valueOf(productInCartID)).orElseThrow();
            return payment.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new PaymentNotFoundException();
        } catch (NumberFormatException e) {
            throw new PaymentIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public ArrayList<ProductInCartCommonDTO> getProductsInCartByCartID(String shoppingCartID) {
//        ArrayList<ProductInCartCommonDTO> productInCartList = iProductInCartRepository.findAllByShoppingCartID(shoppingCartID);
//        return productInCartList;
//    }

    public PaymentCommonDTO setPayment(PaymentDTO paymentDTO) {
        try {
            Payment payment = new Payment(paymentDTO);
            payment.setInclusionDate();
            iPaymentRepository.save(payment);
            return payment.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new PaymentDuplicateDataException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public PaymentCommonDTO updatePayment(PaymentUpdateDTO paymentUpdateDTO) {
        try {
            Payment payment  = iPaymentRepository.findById(Integer.valueOf(paymentUpdateDTO.ID())).orElseThrow();
            payment.update(paymentUpdateDTO);
            iPaymentRepository.saveAndFlush(payment);
            return getPaymentByID(paymentUpdateDTO.ID());
        } catch (NoSuchElementException e) {
            throw new PaymentNotFoundException();
        } catch (NumberFormatException e) {
            throw new PaymentIdentifyException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePayment(String paymentID) {
        try {
            Payment payment = iPaymentRepository.findById(Integer.valueOf(paymentID)).orElseThrow();
            if (payment.isInactive()) {
                throw new PaymentGenericException("O PAGAMENTO JA ESTA INATIVADO!!");
            }
            payment.setExclusionDate();
            iPaymentRepository.saveAndFlush(payment);
        } catch (NoSuchElementException e) {
            throw new PaymentNotFoundException();
        } catch (NumberFormatException e) {
            throw new PaymentGenericException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
