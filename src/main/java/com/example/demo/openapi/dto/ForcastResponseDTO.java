package com.example.demo.openapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForcastResponseDTO {
    // @NotNull(message = "해변 번호가 없습니다.")
    // @NotNull(message = "해변 번호가 없습니다. ) 1")
    @JsonProperty("beachNum")
    private String beachNum;
    // @NotNull(message = "발표일자가 없습니다.")
    // @NotNull(message = "발표일자가 없습니다. ) 20220622")
    @JsonProperty("baseDate")
    private String baseDate;
    // @NotNull(message = "발표시각이 없습니다.")
    // @NotNull(message = "발표시각이 없습니다. ) 1230")
    @JsonProperty("baseTime")
    private String baseTime;
    @JsonProperty("category")
    private String category;
    @JsonProperty("fcstDate")
    private String fcstDate;
    @JsonProperty("fcstTime")
    private String fcstTime;
    @JsonProperty("fcstValue")
    private String fcstValue;
    @JsonProperty("nx")
    private String nx;
    @JsonProperty("ny")
    private String ny;   
}
