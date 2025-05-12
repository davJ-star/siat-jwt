package com.example.demo.openapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.openapi.dto.ForcastResponseDTO;
import com.example.demo.util.ForcastItems;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ForcastService {

    public List<ForcastResponseDTO> parsingJson(String str){
        System.out.println("debug >> ForcastService parsingJson");
        
        ObjectMapper mapper = new ObjectMapper();
        List<ForcastResponseDTO> list = null;

        try {
            // 반환받는것 ForcastResponseDTO 여러개를 받아야되는데, 지금 이렇게 하면 한개만 돌려준다.
            ForcastItems items = mapper.readValue(str, ForcastItems.class);
            list = items.getItems();
            System.out.println("debug >> list size " + list.size());

            list.stream()
                    .forEach(System.out::println)
                    
                    ;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return list;
    }
    
}
