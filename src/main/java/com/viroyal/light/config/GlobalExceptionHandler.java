package com.viroyal.light.config;

import com.alibaba.fastjson.JSON;
import com.viroyal.light.common.utils.BaseConstant;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    public String beanValidation(BindException exception){
        BindingResult result = exception.getBindingResult();
        List<ObjectError> list = result.getAllErrors();
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        for (ObjectError error : list) {
            resultMap.put(BaseConstant.CODE, BaseConstant.ERROR_CODE);
            resultMap.put(BaseConstant.MESSAGE, error.getDefaultMessage());
        }
        return JSON.toJSONString(resultMap);
    }
}
