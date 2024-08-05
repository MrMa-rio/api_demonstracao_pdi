package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.DTOs.ShoppingCart.ShoppingCartCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ShoppingCart.ShoppingCartDTO;
import com.criar.pdi.demonstracao.DTOs.ShoppingCart.ShoppingCartUpdateDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.ShoppingCart.ShoppingCartDuplicateDataException.ShoppingCartDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.ShoppingCart.ShoppingCartGenericException.ShoppingCartGenericException;
import com.criar.pdi.demonstracao.exceptions.ShoppingCart.ShoppingCartIdentifyException.ShoppingCartIdentifyException;
import com.criar.pdi.demonstracao.exceptions.ShoppingCart.ShoppingCartNotFoundException.ShoppingCartNotFoundException;
import com.criar.pdi.demonstracao.services.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-cart")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Shopping Cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{shoppingCartID}")
    @Operation(description = "Pega um Carrinho atraves do ID")
    public ResponseEntity<?> getShoppingCart(@PathVariable String shoppingCartID){
        try{
            return ResponseEntity.ok(new ResponseBody(200, shoppingCartService.getShoppingCartByID(shoppingCartID)));
        } catch (ShoppingCartNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.status(404).body(responseBody);
        } catch (ShoppingCartIdentifyException e){
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }
    @GetMapping
    @Operation(description = "Pega uma lista paginada de Carrinhos")
    public ResponseEntity<?> getShoppingCarts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try{
            Page<ShoppingCartCommonDTO> pages = shoppingCartService.getShoppingCarts(page, size);
            return ResponseEntity.ok(pages);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
    @PostMapping
    @Operation(description = "Cria um novo Carrinho")
    public ResponseEntity<?> setShoppingCart(@RequestBody @Valid ShoppingCartDTO shoppingCartDTO){
        try{
            ShoppingCartCommonDTO shoppingCartCommonDTO = shoppingCartService.setShoppingCart(shoppingCartDTO);
            return ResponseEntity.ok(new ResponseBody(200, shoppingCartCommonDTO));
        }catch (ShoppingCartDuplicateDataException e){
            return ResponseEntity.status(409).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    @PutMapping
    @Transactional
    @Operation(description = "Atualiza um Carrinho")
    public ResponseEntity<?> updateShoppingCart(@RequestBody @Valid ShoppingCartUpdateDTO shoppingCartUpdateDTO){
        try{
            ShoppingCartCommonDTO shoppingCartCommonDTO = shoppingCartService.updateShoppingCart(shoppingCartUpdateDTO);
            return ResponseEntity.ok(new ResponseBody(200, shoppingCartCommonDTO));
        } catch (ShoppingCartNotFoundException e){
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("ERRO NA OPERACAO");
        }
    }
    @DeleteMapping("/{shoppingCartID}")
    @Operation(description = "Inativa um Carrinho atraves do ID")
    public ResponseEntity<ResponseBody> deleteLogicalShoppingCart(
            @PathVariable String shoppingCartID
    ){
        try{
            shoppingCartService.deleteShoppingCart(shoppingCartID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("CARRINHO DE COMPRAS INATIVADO COM SUCESSO!!")));
        } catch (ShoppingCartNotFoundException e){
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (ShoppingCartIdentifyException e){
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
        catch (ShoppingCartGenericException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}
