package com.tencent.wxcloudrun.util;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@ApiModel(value = "返回结果处理类",description = "返回结果封装类")
public class ResultVo<T> implements Serializable {

    private int code;

    private String message;

    private T date;

    public static ResultVo success(){
        return ResultVo.builder().code(200).message("OK").build();
    }
    public static ResultVo success(Object date){
        return ResultVo.builder().code(200).message("OK").date(date).build();
    }
    public static ResultVo fail(String message){
        return ResultVo.builder().code(500).message(message).build();
    }
}
