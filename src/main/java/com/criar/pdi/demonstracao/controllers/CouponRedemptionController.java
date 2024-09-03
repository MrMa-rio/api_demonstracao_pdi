package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponOfUserDTO;
import com.criar.pdi.demonstracao.DTOs.CouponRedemptionDTO.CouponRedemptionCommonDTO;
import com.criar.pdi.demonstracao.DTOs.CouponRedemptionDTO.CouponRedemptionDTO;
import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionDuplicateDataException.CouponRedemptionDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionGenericException.CouponRedemptionGenericException;
import com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionIdentifyException.CouponRedemptionIdentifyException;
import com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionNotFoundException.CouponRedemptionNotFoundException;
import com.criar.pdi.demonstracao.services.CouponRedemptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon-redemption")
@Tag(name = "Auth")
public class CouponRedemptionController {
    @Autowired
    private CouponRedemptionService couponRedemptionService;

    @GetMapping("/{couponRedemptionID}")
    public ResponseEntity<?> getCouponRedemption(@PathVariable String couponRedemptionID) {
        try {
            return ResponseEntity.ok(new ResponseBody(200, couponRedemptionService.getCouponRedemptionByID(couponRedemptionID)));
        } catch (CouponRedemptionNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.status(404).body(responseBody);
        } catch (CouponRedemptionIdentifyException e) {
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }

    @GetMapping("/{userID}/{couponID}")
    public ResponseEntity<?> getCouponRedemptionByUser(
            @PathVariable String userID,
            @PathVariable String couponID,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<CouponRedemptionCommonDTO> pages = couponRedemptionService.getCouponRedemptionByUserIdAndCouponId(new CouponOfUserDTO(userID, couponID), page, size);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO(pages)));
        } catch (CouponRedemptionNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.status(404).body(responseBody);
        } catch (CouponRedemptionIdentifyException e) {
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }

    @PostMapping
    public ResponseEntity<?> setCoupon(@RequestBody @Valid CouponRedemptionDTO couponRedemptionDTO) {
        try {
            CouponRedemptionCommonDTO couponRedemptionCommonDTO = couponRedemptionService.setCouponRedemption(couponRedemptionDTO);
            return ResponseEntity.ok(new ResponseBody(200, couponRedemptionCommonDTO));
        } catch (CouponRedemptionDuplicateDataException e) {
            return ResponseEntity.status(409).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{couponRedemptionID}")
    public ResponseEntity<ResponseBody> deleteLogicalCoupon(
            @PathVariable String couponRedemptionID
    ) {
        try {
            couponRedemptionService.deleteCouponRedemption(couponRedemptionID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("CUPOM INATIVADO COM SUCESSO!!")));
        } catch (CouponRedemptionNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (CouponRedemptionIdentifyException e) {
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        } catch (CouponRedemptionGenericException e) {
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}
