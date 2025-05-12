package com.example.demo.openapi.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.openapi.dto.ForcastRequestDTO;
import com.example.demo.openapi.dto.ForcastResponseDTO;
import com.example.demo.openapi.service.ForcastService;

import jakarta.validation.Valid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/forcast")
public class ForcastCtrl {

    @Value("${openApi.serviceKey}")
    private String serviceKey;

    @Value("${openApi.callBackUrl}")
    private String callBackUrl;

    @Value("${openApi.dataType}")
    private String dataType;


    @Autowired
    private ForcastService service;


    @PostMapping("/getData")
    public ResponseEntity<List<ForcastResponseDTO>> getData(@RequestBody ForcastRequestDTO params) {
        System.out.println("debug >>>> endpoint : /forcast/getData");
        System.out.println("debug >>>> serviceKey : " + serviceKey);
        System.out.println("debug >>>> callBackUrls : " + callBackUrl);
        System.out.println("debug >>>> dataType : " + dataType);
        System.out.println("debug >>>> params : " + params);
        // 얘가 callbackurl로 요청해야한다. 그리고 해당 값을 responseDTO로 담아서 전달해줘야한다.
        // http://apis.data.go.kr/1360000/BeachInfoservice/getUltraSrtFcstBeach?beachNum=1&base_date=20220622&base_time=1230

        String requestURL = callBackUrl
                        +"?serviceKey="+serviceKey
                        +"&beach_num="+ params.getBeach_num()
                        +"&base_date="+ params.getBase_date()
                        +"&base_time="+ params.getBase_time()
                        +"&dataType="+ dataType;
        System.out.println("debug >>>>>> requestURL: " + requestURL);                        
        // Service에서 처리하는게 좋지 않을까?
        System.out.println("debug >>>> url : " + requestURL);
        /////////
        HttpURLConnection http   = null ; 
        InputStream       stream = null ; 
        String            result = null ;

        List<ForcastResponseDTO>  list  = null ;
        try {
            URL url = new URL(requestURL);
            http = (HttpURLConnection)url.openConnection();
            System.out.println("http connection = " + http ); 
            int code = http.getResponseCode() ; 
            System.out.println("http response code  = " + code );  
            if( code == 200 ) {
                stream = http.getInputStream() ; // 응답데이터를 받는 코드다. (xml)
                result = readString(stream) ;
                System.out.println("result = " + result); 
		
                // 서비스 구현
                list = service.parsingJson(result); // 
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        ///////// 
        return ResponseEntity.ok().body(list);
    }
    public String readString(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        String input = null  ;
        StringBuilder result = new StringBuilder();
        while( (input = br.readLine() ) != null ) {
            result.append(input+"\n\r");
        }
        br.close();   
        return result.toString() ; 
    }
    
}
