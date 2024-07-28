package com.toy.project.product.output.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@Schema(description = "요청 응답결과 DTO")
public class ResponseDto<T> {
    @Schema(description = "응답코드", nullable = true, example = "200")
    private int status;

    @Schema(description = "응답메시지", nullable = true, example = "OK")
    private String message;

    @Schema(description = "응답데이터", nullable = true, example = "Object")
    private T data;

    public ResponseDto() {
        this.status = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
    }

    public ResponseDto(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public void initSuccess(T data) {
        this.status = HttpStatus.OK.value();
        this.message = HttpStatus.OK.getReasonPhrase();
        this.data = data;
    }

    public void initFail(T data, Exception e) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = e.toString();
        this.data = data;
    }
}
