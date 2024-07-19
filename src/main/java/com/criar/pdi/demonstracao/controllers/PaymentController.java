package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.DTOs.Payment.PaymentCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Payment.PaymentDTO;
import com.criar.pdi.demonstracao.DTOs.Payment.PaymentUpdateDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInCartDTO.ProductInCartCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInCartDTO.ProductInCartDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInCartDTO.ProductInCartUpdateDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Payment.PaymentDuplicateDataException.PaymentDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Payment.PaymentGenericException.PaymentGenericException;
import com.criar.pdi.demonstracao.exceptions.Payment.PaymentIdentifyException.PaymentIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Payment.PaymentNotFoundException.PaymentNotFoundException;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartDuplicateDataException.ProductInCartDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartGenericException.ProductInCartGenericException;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartIdentifyException.ProductInCartIdentifyException;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartNotFoundException.ProductInCartNotFoundException;
import com.criar.pdi.demonstracao.services.PaymentService;
import com.criar.pdi.demonstracao.services.ProductInCartService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{paymentID}")
    public ResponseEntity<?> getPayment(@PathVariable String paymentID){
        try{
            return ResponseEntity.ok(new ResponseBody(200, paymentService.getPaymentByID(paymentID)));
        } catch (PaymentNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.ok(responseBody);
        } catch (PaymentIdentifyException e){
            return ResponseEntity.ok(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }
//    @GetMapping("/cart/{paymentID}")
//    public ResponseEntity<?> getProductsInCartByCartID(
//            @PathVariable String paymentID
//    ){
//        try{
//            ArrayList<PaymentCommonDTO> pages = paymentService.getProductsInCartByCartID(paymentID);
//            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO(pages)));
//        }catch (RuntimeException e){
//            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
//        }
//    }
    @PostMapping
    public ResponseEntity<?> setPayment(@RequestBody @Valid PaymentDTO paymentDTO){
        try{
            PaymentCommonDTO paymentCommonDTO = paymentService.setPayment(paymentDTO);
            return ResponseEntity.ok(new ResponseBody(200, paymentCommonDTO));
        }catch (PaymentDuplicateDataException e){
            return ResponseEntity.ok(new ResponseBody(409, new MessageDTO(e.getMessage())));
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> updatePayment(@RequestBody @Valid PaymentUpdateDTO paymentUpdateDTO){
        try{
            PaymentCommonDTO paymentCommonDTO = paymentService.updatePayment(paymentUpdateDTO);
            return ResponseEntity.ok(new ResponseBody(200, paymentCommonDTO));
        } catch (PaymentNotFoundException e){
            return ResponseEntity.ok(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("ERRO NA OPERACAO");
        }
    }
    @DeleteMapping("/{paymentID}")
    public ResponseEntity<ResponseBody> deleteLogicalShoppingCart(
            @PathVariable String paymentID
    ){
        try{
            paymentService.deletePayment(paymentID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("PAGAMENTO INATIVADO COM SUCESSO!!")));
        } catch (PaymentNotFoundException e){
            return ResponseEntity.ok().body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (PaymentIdentifyException e){
            return ResponseEntity.ok(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
        catch (PaymentGenericException e){
            return ResponseEntity.ok().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}
