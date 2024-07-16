package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.DTOs.Product.ProductDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreUpdateDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Product.ProductDuplicateDataException.ProductDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Product.ProductGenericException.ProductGenericException;
import com.criar.pdi.demonstracao.exceptions.Product.ProductIdentifyException.ProductIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Product.ProductNotFoundException.ProductNotFoundException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreDuplicateDataException.StoreDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreGenericException.StoreGenericException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreIdentifyException.StoreIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreNotFoundException.StoreNotFoundException;
import com.criar.pdi.demonstracao.models.Product.Product;
import com.criar.pdi.demonstracao.services.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try{
            Page<ProductDTO> pages = productService.getProducts(page, size);
            return ResponseEntity.ok(pages);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }

    @GetMapping("/{productID}")
    public ResponseEntity<ResponseBody> getProduct(@PathVariable @Valid String productID) {
        try{
            return ResponseEntity.ok(new ResponseBody(200, productService.getStoreByID(productID)));
        } catch (ProductNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.ok(responseBody);
        } catch (ProductIdentifyException e){
            return ResponseEntity.ok(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }

    @PostMapping
    public ResponseEntity<?> setProduct(@RequestBody @Valid ProductDTO productDTO){
        try{
            ProductDTO newProductDTO = productService.setProduct(productDTO);
            return ResponseEntity.ok(new ResponseBody(200, newProductDTO));
        }catch (ProductDuplicateDataException e){
            return ResponseEntity.ok(new ResponseBody(409, new MessageDTO(e.getMessage())));
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductDTO productUpdateDTO){
        try{
            ProductDTO productDTO = productService.updateProduct(productUpdateDTO);
            return ResponseEntity.ok(new ResponseBody(200, productDTO));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body("ERRO NA OPERACAO");
        }
    }
    @DeleteMapping("/{productID}")
    public ResponseEntity<ResponseBody> deleteLogicalProduct(
            @PathVariable String productID
    ){
        try{
            productService.deleteProduct(productID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("PRODUTO INATIVADO COM SUCESSO!!")));
        } catch (ProductGenericException | ProductNotFoundException e){
            return ResponseEntity.ok().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}
