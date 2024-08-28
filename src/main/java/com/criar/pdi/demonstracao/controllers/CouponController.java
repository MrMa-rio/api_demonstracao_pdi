package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Coupon.CouponDTO;
import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Coupon.CouponDuplicateDataException.CouponDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Coupon.CouponGenericException.CouponGenericException;
import com.criar.pdi.demonstracao.exceptions.Coupon.CouponIdentifyException.CouponIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Coupon.CouponNotFoundException.CouponNotFoundException;
import com.criar.pdi.demonstracao.services.CouponService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Coupons")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("/{couponID}")
    public ResponseEntity<?> getCoupon(@PathVariable String couponID) {
        try {
            return ResponseEntity.ok(new ResponseBody(200, couponService.getCouponByID(couponID)));
        } catch (CouponNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.status(404).body(responseBody);
        } catch (CouponIdentifyException e) {
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }

    @PostMapping
    public ResponseEntity<?> setCoupon(@RequestBody @Valid CouponDTO couponDTO) {
        try {
            CouponCommonDTO couponCommonDTO = couponService.setCoupon(couponDTO);
            return ResponseEntity.ok(new ResponseBody(200, couponCommonDTO));
        } catch (CouponDuplicateDataException e) {
            return ResponseEntity.status(409).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{couponID}")
    public ResponseEntity<ResponseBody> deleteLogicalCoupon(
            @PathVariable String couponID
    ) {
        try {
            couponService.deleteCoupon(couponID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("CUPOM INATIVADO COM SUCESSO!!")));
        } catch (CouponNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (CouponIdentifyException e) {
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        } catch (CouponGenericException e) {
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}
