package com.example.demo.ctrl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.apache.ibatis.javassist.tools.reflect.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.JpaSampleRepository;
import com.example.demo.domain.dto.SampleResponseDTO;
import com.example.demo.domain.entity.JpaSampleEntity;
import com.example.demo.util.JwtProvider;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/jpa")
public class JpaCtrl {
    // 원래 service가 필요한데 그냥 바로 넘긴다고 생각해보자. 
    @Autowired
    JpaSampleRepository jpaSampleRepository;
     @Autowired
    private JwtProvider jwtProvider; // @Autowired를 사용하여 JwtProvider를 주입받는다.


    @PostMapping("/sign-up")
    // dto를 만들지 않아도 된다.
    // 그리고 Object를 떠올려라. -> 타입캐스팅과 다양성을 위해서.
    public String signUp(@RequestBody Map<String, String> params) { // 원래는 requestDTO를 걸어주는게 맞긴하다.
        //TODO: process POST request
        System.out.println("debug >> signUp(ctrl) endpoint hit");

        
        // jpaSampleRepository.findById(params.); 

        JpaSampleEntity entity = JpaSampleEntity.builder()
                                    .userId(params.get("userId")) // params.get()
                                    .passwd(params.get("passwd"))
                                    .name(params.get("name"))
                                    .build();     
                                    
                                    
        // 일단 바로 저장할려면 entity랑 repository에서 저장하는 것을 필요로 한다.(또는 delete)s                            
        jpaSampleRepository.save(entity); // entity를 넣어줘야한다. 회원가입에 대한 정보를 체크하고 넘겨줘야한다.ㄴ


        return null;
    }
    
    // 일단 지금까지 배웠던걸 토대로 체크한다.

    @PostMapping("/sign-in")
    // dto를 만들지 않아도 된다.
    // 그리고 Object를 떠올려라. -> 타입캐스팅과 다양성을 위해서.
    public String signIn(@RequestBody Map<String, String> params) { // 원래는 requestDTO를 걸어주는게 맞긴하다.
        //TODO: process POST request
        System.out.println("debug >> signUp(ctrl) endpoint hit");
        String accToken = jwtProvider.generateAccToken(params.get("userId")); // JWT Access Token 생성
        String reToken = jwtProvider.generateReToken(params.get("userId")); // JWT Refresh Token 생성
        // 아이디와 패스워드
        jpaSampleRepository.findByUserIdAndPasswd(params.get("userId"), params.get("passwd"))
                                .orElseThrow(()->new RuntimeException("not found"));
        // 데이터 유효성을 체크한뒤 setter를 쓰는건 괜찮다. 
        
        // jpaSampleRepository.findById(params.); 

        JpaSampleEntity entity = JpaSampleEntity.builder()
                                    .userId(params.get("userId")) // params.get()
                                    .passwd(params.get("passwd"))
                                    .name(params.get("name"))
                                    .build();     
                                    
        // 변경되었을때 jpa 자동으로 변경되도록해야한다.
        entity.setRefreshToken(reToken);
        // 일단 에러나느 문제....
                                    
        // 일단 바로 저장할려면 entity랑 repository에서 저장하는 것을 필요로 한다.(또는 delete)s                            
        jpaSampleRepository.save(entity); // entity를 넣어줘야한다. 회원가입에 대한 정보를 체크하고 넘겨줘야한다.ㄴ


        return null;
        /*
         * 
         * return Map.of(
                "accessToken", accToken,
              "refreshToken", reToken
            );
         */
    }

    @PutMapping("/update")
    // Dirty checking(변경 감지)
    // -> jpql
    // -> dynamic insert등을 해보기
    @Transactional
    public String update(@RequestBody Map<String, String> params) { // 원래는 requestDTO를 걸어주는게 맞긴하다.
        //TODO: process POST request
        System.out.println("debug >> update(ctrl) endpoint hit");

        // entity 정보를 가져온다. -> 없는 경우 .orElseThrow(() -> new RuntimeException("not found")); // 근데 해당 코드로 하면ㄷ안됨...
        // 일단 user_id는 일단 기존에 있는걸로 진행한다.
        // JpaSampleEntity entity = jpaSampleRepository.findById(params.get("user_id"))
        //                     .orElseThrow(() -> new RuntimeException("not found"));
        // // 아래는 있는 경우에만 실행된다. ->  
        // entity.setPasswd(params.get("user_passwd"));
        // entity.setName(params.get("user_name"));
        ////////////// 아래를 진행하지 않았다면, 

        //jpaSampleRepository.save(entity);


        jpaSampleRepository.updateRow(params.get("user_id"), params.get("user_passwd"), params.get("user_name"));



        // // 모든 컬럼에 대해 컬럼이 변경된다. 
        // JpaSampleEntity entity1 = JpaSampleEntity.builder()
        //                             .userId(params.get("user_id"))
        //                             .passwd(params.get("user_passwd"))
        //                             .name(params.get("user_name"))
        //                             .build();
        
        // jpaSampleRepository.save(entity1);


        

        return null;
        /*
         * 
         * return Map.of(
                "accessToken", accToken,
              "refreshToken", reToken
            );
         */
    }
    
    // 우선 지금 pathvariable로 가져온다.
    @DeleteMapping("/delete/{userId}")
    public String delete(@PathVariable("userId") String id) { // 원래는 requestDTO를 걸어주는게 맞긴하다.
        //TODO: process POST request
        System.out.println("debug >> delete(ctrl) endpoint hit");

        // // 우선 해당하는 값을 가져온다. 
        // JpaSampleEntity entity = jpaSampleRepository.findById(id)
        //     .orElseThrow(() -> new RuntimeException("not found"));

        
        // // 여기서 일단 삭제하기. 
        // // 해당값은 일단 repository에서 삭제하는게 맞을듯함.
        // jpaSampleRepository.delete(entity);

        // 아래와 같이 진행하셨다.
        jpaSampleRepository.deleteById(id);

        // // 모든 컬럼에 대해 컬럼이 변경된다. 
        // JpaSampleEntity entity1 = JpaSampleEntity.builder()
        //                             .userId(params.get("user_id"))
        //                             .passwd(params.get("user_passwd"))
        //                             .name(params.get("user_name"))
        //                             .build();
        
        // jpaSampleRepository.save(entity1);


        

        return null;
        /*
         * 
         * return Map.of(
                "accessToken", accToken,
              "refreshToken", reToken
            );
         */
    }
// 우선 지금 pathvariable로 가져온다.
    @GetMapping("/list")
    public ResponseEntity<List<SampleResponseDTO>>  list() { // 원래는 requestDTO를 걸어주는게 맞긴하다.
        //TODO: process POST request
        System.out.println("debug >> list(ctrl) endpoint hit");

        // // 우선 해당하는 값을 가져온다. 

        // 일단 전체를 가져오면될 것 같다.
        // 하나도 없는 경우도 체크가 필요하긴 할 듯한데..?
        List<JpaSampleEntity> lst = jpaSampleRepository.findAll();
        
        // List<SampleResponseDTO> dtoList = lst.stream()
        //                             .map(SampleResponseDTO::new) // 변환 생성자 사용
        //                             .collect(Collectors.toList());

        List<SampleResponseDTO> dtoList = lst.stream()
                                                .map(entity -> SampleResponseDTO.builder()
                                                    .userId(entity.getUserId())
                                                    .passwd(entity.getPasswd())
                                                    .name(entity.getName())
                                                    .refreshToken(entity.getRefreshToken())
                                                    .build())
                                                .collect(Collectors.toList());


        // JpaSampleEntity entity = jpaSampleRepository.findById(id)
        //     .orElseThrow(() -> new RuntimeException("not found"));

        
        // // 여기서 일단 삭제하기. 
        // // 해당값은 일단 repository에서 삭제하는게 맞을듯함.
        // jpaSampleRepository.delete(entity);

        // 아래와 같이 진행하셨다.
        // jpaSampleRepository.deleteById(id);

        // // 모든 컬럼에 대해 컬럼이 변경된다. 
        // JpaSampleEntity entity1 = JpaSampleEntity.builder()
        //                             .userId(params.get("user_id"))
        //                             .passwd(params.get("user_passwd"))
        //                             .name(params.get("user_name"))
        //                             .build();
        
        // jpaSampleRepository.save(entity1);


        
        // 일단 반환하는 코드랑 리스트 비어있을때 체크     
        return new ResponseEntity<List<SampleResponseDTO>>(dtoList, HttpStatus.ACCEPTED);
        
    }






    
    // 일단 지금까지 배웠던걸 토대로 체크한다.




}
