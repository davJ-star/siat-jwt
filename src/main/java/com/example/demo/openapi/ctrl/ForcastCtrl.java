package com.example.demo.openapi.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.openapi.dto.ForcastRequestDTO;
import com.example.demo.openapi.dto.ForcastResponseDTO;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/forcast")
public class ForcastCtrl {

    @Value("${openApi.serviceKey}")
    private String serviceKey;

    @Value("${openApi.callBackUrls}")
    private String callBackUrls;

    @Value("${openApi.dataType}")
    private String dataType;


    @GetMapping("/getData")
    public ResponseEntity<List<ForcastResponseDTO>> getData(@Valid @RequestBody ForcastRequestDTO params) {
        System.out.println("debug >>>> endpoint : /forcast/getData");
        System.out.println("debug >>>> serviceKey : " + serviceKey);
        System.out.println("debug >>>> callBackUrls : " + callBackUrls);
        System.out.println("debug >>>> dataType : " + dataType);
        System.out.println("debug >>>> params : " + params);
        // 얘가 callbackurl로 요청해야한다. 그리고 해당 값을 responseDTO로 담아서 전달해줘야한다.
        // Service에서 처리하는게 좋지 않을까?
        return null;
    }
    
    
}
