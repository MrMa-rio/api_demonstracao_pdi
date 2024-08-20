package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreSearchDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreUpdateDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Store.StoreDuplicateDataException.StoreDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreGenericException.StoreGenericException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreIdentifyException.StoreIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreNotFoundException.StoreNotFoundException;
import com.criar.pdi.demonstracao.services.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/{storeID}")
    @Operation(description = "Pega uma Loja atraves do ID")
    public ResponseEntity<?> getStore(@PathVariable String storeID) {
        try {
            return ResponseEntity.ok(new ResponseBody(200, storeService.getStoreByID(storeID)));
        } catch (StoreNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.status(404).body(responseBody);
        } catch (StoreIdentifyException e) {
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }

    @GetMapping("/{storeID}/products")
    @Operation(description = "Pega uma lista de produtos atraves do  ID da Loja")
    public ResponseEntity<?> getProductsByStoreID(@PathVariable String storeID,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        try {
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO(storeService.getProductsByStoreByID(storeID, page, size))));
        } catch (StoreNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.status(404).body(responseBody);
        } catch (StoreIdentifyException e) {
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }


    @GetMapping
    @Operation(description = "Pega uma lista paginada de Lojas")
    public ResponseEntity<?> getStores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<StoreCommonDTO> pages = storeService.getStores(page, size);
            return ResponseEntity.ok(pages);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }

    @GetMapping("/search")
    @Operation(description = "Pega uma lista paginada de Lojas")
    @Transactional
    public ResponseEntity<?> getStores(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) String owner,
            @RequestParam(defaultValue = "", required = false) String cnpj,
            @RequestParam(defaultValue = "", required = false) String description,
            @RequestParam(defaultValue = "", required = false) String address,
            @RequestParam(defaultValue = "", required = false) String region
    ) {
        try {
            List<StoreCommonDTO> pages = storeService.getStoresByParams(new StoreSearchDTO(name, owner, description, address, region, cnpj));
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO(pages))); //TODO: Padronizar retorno
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }

    @PostMapping
    @Operation(description = "Cria uma nova Loja")
    public ResponseEntity<?> setStore(@RequestBody @Valid StoreDTO storeDTO) {
        try {
            StoreCommonDTO storeCommonDTO = storeService.setStore(storeDTO);
            return ResponseEntity.ok(new ResponseBody(200, storeCommonDTO));
        } catch (StoreDuplicateDataException e) {
            return ResponseEntity.status(409).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    @Transactional
    @Operation(description = "Atualiza uma Loja")
    public ResponseEntity<?> updateStore(@RequestBody @Valid StoreUpdateDTO storeUpdateDTO) {
        try {
            StoreCommonDTO storeCommonDTO = storeService.updateStore(storeUpdateDTO);
            return ResponseEntity.ok(new ResponseBody(200, storeCommonDTO));
        } catch (StoreNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("ERRO NA OPERACAO");
        }
    }

    @DeleteMapping("/{storeID}")
    @Operation(description = "Inativa uma Loja atraves do ID")
    public ResponseEntity<ResponseBody> deleteLogicalStore(
            @PathVariable String storeID
    ) {
        try {
            storeService.deleteStore(storeID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("LOJA INATIVADA COM SUCESSO!!")));
        } catch (StoreNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (StoreGenericException e) {
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}

