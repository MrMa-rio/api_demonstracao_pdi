package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Delivery.DeliveryCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Delivery.DeliveryDTO;
import com.criar.pdi.demonstracao.DTOs.Delivery.DeliveryUpdateDTO;
import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Delivery.DeliveryDuplicateDataException.DeliveryDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Delivery.DeliveryGenericException.DeliveryGenericException;
import com.criar.pdi.demonstracao.exceptions.Delivery.DeliveryIdentifyException.DeliveryIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Delivery.DeliveryNotFoundException.DeliveryNotFoundException;
import com.criar.pdi.demonstracao.services.DeliveryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Delivery")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/{deliveryID}")
    public ResponseEntity<?> getDelivery(@PathVariable String deliveryID){
        try{
            return ResponseEntity.ok(new ResponseBody(200, deliveryService.getDeliveryByID(deliveryID)));
        } catch (DeliveryNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.status(404).body(responseBody);
        } catch (DeliveryIdentifyException e){
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }
    @PostMapping
    public ResponseEntity<?> setDelivery(@RequestBody @Valid DeliveryDTO deliveryDTO){
        try{
            DeliveryCommonDTO deliveryCommonDTO = deliveryService.setDelivery(deliveryDTO);
            return ResponseEntity.ok(new ResponseBody(200, deliveryCommonDTO));
        }catch (DeliveryDuplicateDataException e){
            return ResponseEntity.status(409).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> updateDelivery(@RequestBody @Valid DeliveryUpdateDTO deliveryUpdateDTO){
        try{
            DeliveryCommonDTO deliveryCommonDTO = deliveryService.updateDelivery(deliveryUpdateDTO);
            return ResponseEntity.ok(new ResponseBody(200, deliveryCommonDTO));
        } catch (DeliveryNotFoundException e){
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (DeliveryIdentifyException e){
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
        catch (RuntimeException e){
            return ResponseEntity.badRequest().body("ERRO NA OPERACAO");
        }
    }
    @DeleteMapping("/{deliveryID}")
    public ResponseEntity<ResponseBody> deleteLogicalAddress(
            @PathVariable String deliveryID
    ){
        try{
            deliveryService.deleteDelivery(deliveryID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("ENTREGA INATIVADA COM SUCESSO!!")));
        } catch (DeliveryNotFoundException e){
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (DeliveryIdentifyException e){
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
        catch (DeliveryGenericException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}
