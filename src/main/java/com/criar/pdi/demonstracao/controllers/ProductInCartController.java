package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInCartDTO.ProductInCartCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInCartDTO.ProductInCartDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInCartDTO.ProductInCartUpdateDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartDuplicateDataException.ProductInCartDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartGenericException.ProductInCartGenericException;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartIdentifyException.ProductInCartIdentifyException;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartNotFoundException.ProductInCartNotFoundException;
import com.criar.pdi.demonstracao.services.ProductInCartService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/product-in-cart")
public class ProductInCartController {
    @Autowired
    private ProductInCartService productInCartService;

    @GetMapping("/{productInCartID}")
    public ResponseEntity<?> getProductInCart(@PathVariable String productInCartID){
        try{
            return ResponseEntity.ok(new ResponseBody(200, productInCartService.getProductInCartByID(productInCartID)));
        } catch (ProductInCartNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.ok(responseBody);
        } catch (ProductInCartIdentifyException e){
            return ResponseEntity.ok(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }
    @GetMapping("/cart/{shoppingCartID}")
    public ResponseEntity<?> getProductsInCartByCartID(
            @PathVariable String shoppingCartID
    ){
        try{
            ArrayList<ProductInCartCommonDTO> pages = productInCartService.getProductsInCartByCartID(shoppingCartID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO(pages)));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
    @PostMapping
    public ResponseEntity<?> setProductInCart(@RequestBody @Valid ProductInCartDTO productInCartDTO){
        try{
            ProductInCartCommonDTO productInCartCommonDTO = productInCartService.setProductInCart(productInCartDTO);
            return ResponseEntity.ok(new ResponseBody(200, productInCartCommonDTO));
        }catch (ProductInCartDuplicateDataException e){
            return ResponseEntity.ok(new ResponseBody(409, new MessageDTO(e.getMessage())));
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> updateProductInCart(@RequestBody @Valid ProductInCartUpdateDTO productInCartUpdateDTO){
        try{
            ProductInCartCommonDTO productInCartCommonDTO = productInCartService.updateProductInCart(productInCartUpdateDTO);
            return ResponseEntity.ok(new ResponseBody(200, productInCartCommonDTO));
        } catch (ProductInCartNotFoundException e){
            return ResponseEntity.ok(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("ERRO NA OPERACAO");
        }
    }
    @DeleteMapping("/{productInCartID}")
    public ResponseEntity<ResponseBody> deleteLogicalShoppingCart(
            @PathVariable String productInCartID
    ){
        try{
            productInCartService.deleteProductInCart(productInCartID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("PRODUTO NO CARRINHO INATIVADO COM SUCESSO!!")));
        } catch (ProductInCartNotFoundException e){
            return ResponseEntity.ok().body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (ProductInCartIdentifyException e){
            return ResponseEntity.ok(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
        catch (ProductInCartGenericException e){
            return ResponseEntity.ok().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}
