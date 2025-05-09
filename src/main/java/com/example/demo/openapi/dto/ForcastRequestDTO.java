package com.example.demo.openapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data // ?? 왜 사용하는거지?
public class ForcastRequestDTO {
    // @NotNull(message = "해변 번호가 없습니다.")
    @NotNull(message = "해변 번호가 없습니다.")
    private String beach_num;
    // @NotNull(message = "발표일자가 없습니다.")
    @NotNull(message = "발표일자가 없습니다.")
    private String baes_date;
    // @NotNull(message = "발표시각이 없습니다.")
    @NotNull(message = "발표시각이 없습니다.")
    private String base_time;

    
}
