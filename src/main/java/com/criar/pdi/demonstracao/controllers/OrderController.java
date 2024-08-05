package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.DTOs.Order.OrderCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Order.OrderDTO;
import com.criar.pdi.demonstracao.DTOs.Order.OrderUpdateDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Order.OrderDuplicateDataException.OrderDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Order.OrderGenericException.OrderGenericException;
import com.criar.pdi.demonstracao.exceptions.Order.OrderIdentifyException.OrderIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Order.OrderNotFoundException.OrderNotFoundException;
import com.criar.pdi.demonstracao.services.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try{
            Page<OrderCommonDTO> pages = orderService.getOrders(page, size);
            return ResponseEntity.ok(pages);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }

    @GetMapping("/{orderID}")
    public ResponseEntity<ResponseBody> getOrder(@PathVariable @Valid String orderID) {
        try{
            return ResponseEntity.ok(new ResponseBody(200, orderService.getOrderByID(orderID)));
        } catch (OrderNotFoundException e){
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (OrderIdentifyException e){
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }

    @PostMapping
    public ResponseEntity<ResponseBody> setOrder(@RequestBody @Valid OrderDTO orderDTO){
        try{
            OrderCommonDTO orderCommonDTO = orderService.setOrder(orderDTO);
            return ResponseEntity.ok(new ResponseBody(200, orderCommonDTO));
        }catch (OrderDuplicateDataException e){
            return ResponseEntity.status(409).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
        }
//        catch (RuntimeException e){
//            throw new RuntimeException(e);
//        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> updateOrder(@RequestBody @Valid OrderUpdateDTO orderUpdateDTO){
        try{
            OrderCommonDTO orderCommonDTO = orderService.updateOrder(orderUpdateDTO);
            return ResponseEntity.ok(new ResponseBody(200, orderCommonDTO));
        } catch (OrderNotFoundException e){
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("ERRO NA OPERACAO");
        }
    }
    @DeleteMapping("/{orderID}")
    public ResponseEntity<ResponseBody> deleteLogicalOrder(
            @PathVariable String orderID
    ){
        try{
            orderService.deleteOrder(orderID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("PRODUTO INATIVADO COM SUCESSO!!")));
        } catch (OrderGenericException | OrderNotFoundException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}
