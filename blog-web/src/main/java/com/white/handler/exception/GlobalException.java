package com.white.handler.exception;

import com.white.domain.Result;
import com.white.domain.ResultInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e){
        e.printStackTrace();
        return Result.error().codeAndMessage(ResultInfo.GLOBAL_ERROR);
    }

    @ExceptionHandler(MyRuntimeException.class)
    @ResponseBody
    public Result myRuntimeException(MyRuntimeException e){
        e.printStackTrace();
        return Result.error().codeAndMessage(e.getCode(),e.getMessage());
    }
}
