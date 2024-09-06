package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.CouponRedemptionDTO.CouponRedemptionCommonDTO;
import com.criar.pdi.demonstracao.DTOs.CouponRedemptionDTO.CouponRedemptionDTO;
import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Coupon.CouponIdentifyException.CouponIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Coupon.CouponNotFoundException.CouponNotFoundException;
import com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionDuplicateDataException.CouponRedemptionDuplicateDataException;
import com.criar.pdi.demonstracao.services.CouponManagementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon-management")
@Tag(name = "Management")
public class CouponManagementController {

    private final CouponManagementService couponManagementService;

    public CouponManagementController(CouponManagementService couponManagementService) {
        this.couponManagementService = couponManagementService;
    }

    @PostMapping
    public ResponseEntity<?> setCouponRedemption(@RequestBody @Valid CouponRedemptionDTO couponRedemptionDTO) {
        try {
            CouponRedemptionCommonDTO couponRedemptionCommonDTO = couponManagementService.setCouponRedemption(couponRedemptionDTO);
            return ResponseEntity.ok(new ResponseBody(200, couponRedemptionCommonDTO));
        } catch (CouponRedemptionDuplicateDataException e) {
            return ResponseEntity.status(409).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
        }
        catch (CouponNotFoundException e){
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (CouponIdentifyException e){
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(400).body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}
