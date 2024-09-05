package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInOrder.ProductInOrderCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInOrder.ProductInOrderDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.ProductInOrder.ProductInOrderDuplicateDataException.ProductInOrderDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.ProductInOrder.ProductInOrderGenericException.ProductInOrderGenericException;
import com.criar.pdi.demonstracao.exceptions.ProductInOrder.ProductInOrderIdentifyException.ProductInOrderIdentifyException;
import com.criar.pdi.demonstracao.exceptions.ProductInOrder.ProductInOrderNotFoundException.ProductInOrderNotFoundException;
import com.criar.pdi.demonstracao.services.ProductInOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/product-in-order")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Product In Order")
public class ProductInOrderController {
    @Autowired
    private ProductInOrderService productInOrderService;

    @GetMapping("/{productInOrderID}")
    public ResponseEntity<?> getProductInOrderById(@PathVariable String productInOrderID){
        try{
            return ResponseEntity.ok(new ResponseBody(200, productInOrderService.getProductInOrderByID(productInOrderID)));
        } catch (ProductInOrderNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.status(404).body(responseBody);
        } catch (ProductInOrderIdentifyException e){
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }
    @GetMapping("/order/{orderID}")
    public ResponseEntity<?> getProductsInOrderByOrderID(
            @PathVariable Integer orderID,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try{
            Page<ProductInOrderCommonDTO> pages = productInOrderService.getProductsInOrderByOrderID(orderID, page, size);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO(pages)));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
    @PostMapping
    public ResponseEntity<?> setProductInOrder(@RequestBody @Valid ProductInOrderDTO productInOrderDTO){
        try{
            ProductInOrderCommonDTO productInOrderCommonDTO = productInOrderService.setProductInOrder(productInOrderDTO);
            return ResponseEntity.ok(new ResponseBody(200, productInOrderCommonDTO));
        }catch (ProductInOrderDuplicateDataException e){
            return ResponseEntity.status(409).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/{productInOrderID}")
    public ResponseEntity<ResponseBody> deleteLogicalProductInOrder(
            @PathVariable String productInOrderID
    ){
        try{
            productInOrderService.deleteProductInOrder(productInOrderID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("PRODUTO NO PEDIDO INATIVADO COM SUCESSO!!")));
        } catch (ProductInOrderNotFoundException e){
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (ProductInOrderIdentifyException e){
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
        catch (ProductInOrderGenericException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}

