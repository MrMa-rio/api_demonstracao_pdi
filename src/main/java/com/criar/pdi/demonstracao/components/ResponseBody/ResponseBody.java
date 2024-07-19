package com.criar.pdi.demonstracao.components.ResponseBody;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Getter
@Component
@NoArgsConstructor
public class ResponseBody {
    private Integer status;

    private Object response;

    public ResponseBody(Integer status, IGenericDTO IGenericDTO){
        this.response = IGenericDTO;
        this.status = status;
    }
    public ResponseBody(Integer status, ArrayList<Object> iGenericDTO){
        this.response = iGenericDTO;
        this.status = status;
    }
}
