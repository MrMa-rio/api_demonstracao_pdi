package com.criar.pdi.demonstracao.controllers;


import com.criar.pdi.demonstracao.DTOs.Address.AddressCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Address.AddressDTO;
import com.criar.pdi.demonstracao.DTOs.Address.AddressUpdateDTO;
import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Address.AddressDuplicateDataException.AddressDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Address.AddressGenericException.AddressGenericException;
import com.criar.pdi.demonstracao.exceptions.Address.AddressIdentifyException.AddressIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Address.AddressNotFoundException.AddressNotFoundException;
import com.criar.pdi.demonstracao.services.AddressService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/{addressID}")
    public ResponseEntity<?> getAddress(@PathVariable String addressID){
        try{
            return ResponseEntity.ok(new ResponseBody(200, addressService.getAddressByID(addressID)));
        } catch (AddressNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.ok(responseBody);
        } catch (AddressIdentifyException e){
            return ResponseEntity.ok(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }
    @PostMapping
    public ResponseEntity<?> setAddress(@RequestBody @Valid AddressDTO addressDTO){
        try{
            AddressCommonDTO addressCommonDTO = addressService.setAddress(addressDTO);
            return ResponseEntity.ok(new ResponseBody(200, addressCommonDTO));
        }catch (AddressDuplicateDataException e){
            return ResponseEntity.ok(new ResponseBody(409, new MessageDTO(e.getMessage())));
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> updateAddress(@RequestBody @Valid AddressUpdateDTO addressUpdateDTO){
        try{
            AddressCommonDTO addressCommonDTO = addressService.updateAddress(addressUpdateDTO);
            return ResponseEntity.ok(new ResponseBody(200, addressCommonDTO));
        } catch (AddressNotFoundException e){
            return ResponseEntity.ok(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("ERRO NA OPERACAO");
        }
    }
    @DeleteMapping("/{addressID}")
    public ResponseEntity<ResponseBody> deleteLogicalAddress(
            @PathVariable String addressID
    ){
        try{
            addressService.deleteAddress(addressID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("ENDEREÇO INATIVADO COM SUCESSO!!")));
        } catch (AddressNotFoundException e){
            return ResponseEntity.ok().body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (AddressIdentifyException e){
            return ResponseEntity.ok(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
        catch (AddressGenericException e){
            return ResponseEntity.ok().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}
