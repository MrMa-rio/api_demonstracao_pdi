package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInPromotion.ProductInPromotionCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInPromotion.ProductInPromotionDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.ProductInPromotion.ProductInPromotionDuplicateDataException.ProductInPromotionDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.ProductInPromotion.ProductInPromotionGenericException.ProductInPromotionGenericException;
import com.criar.pdi.demonstracao.exceptions.ProductInPromotion.ProductInPromotionIdentifyException.ProductInPromotionIdentifyException;
import com.criar.pdi.demonstracao.exceptions.ProductInPromotion.ProductInPromotionNotFoundException.ProductInPromotionNotFoundException;
import com.criar.pdi.demonstracao.services.ProductInPromotionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-in-promotion")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Products In Promotion")
public class ProductInPromotionController {

    private final ProductInPromotionService productInPromotionService;

    public ProductInPromotionController(ProductInPromotionService productInPromotionService) {
        this.productInPromotionService = productInPromotionService;
    }

    @GetMapping("/{productID}")
    public ResponseEntity<?> getProductInPromotionByProductID(@PathVariable String productID) {
        try {
            return ResponseEntity.ok(new ResponseBody(200, productInPromotionService.getProductInPromotionByProductID(productID)));
        } catch (ProductInPromotionNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.status(404).body(responseBody);
        } catch (ProductInPromotionIdentifyException e) {
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }

    @PostMapping
    public ResponseEntity<?> setProductInPromotion(@RequestBody @Valid ProductInPromotionDTO productInPromotionDTO) {
        try {
            ProductInPromotionCommonDTO productInPromotionCommonDTO = productInPromotionService.setProductInPromotion(productInPromotionDTO);
            return ResponseEntity.ok(new ResponseBody(200, productInPromotionCommonDTO));
        } catch (ProductInPromotionDuplicateDataException e) {
            return ResponseEntity.status(409).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{productInPromotionID}")
    public ResponseEntity<ResponseBody> deleteLogicalShoppingPromotion(
            @PathVariable String productInPromotionID
    ) {
        try {
            productInPromotionService.deleteProductInPromotion(productInPromotionID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("PRODUTO EM PROMOCAO INATIVADO COM SUCESSO!!")));
        } catch (ProductInPromotionNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (ProductInPromotionIdentifyException e) {
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        } catch (ProductInPromotionGenericException e) {
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }

}
