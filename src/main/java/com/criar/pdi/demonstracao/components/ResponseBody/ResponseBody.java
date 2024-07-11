package com.criar.pdi.demonstracao.components.ResponseBody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@NoArgsConstructor
public class ResponseBody {
    private Integer status;
    private String message;

    public ResponseBody(Integer status, String message){
        this.message = message;
        this.status = status;
    }
}
