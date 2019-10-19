package com.huawei.store.controller;

import com.huawei.store.service.ex.InsertException;
import com.huawei.store.service.ex.ServiceException;
import com.huawei.store.service.ex.UsernameDuplicateException;
import com.huawei.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

public  class BaseControlller {
    public static final Integer SUCCESS=20;
    public static final Integer ADDRESS_MAX_COUNT=5;
    public static final Integer ERROR_USERNAME_REPETITION=30;

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public JsonResult handleException(Throwable e){
        JsonResult<Void> jr = new JsonResult<>();
            if (e instanceof UsernameDuplicateException){
                jr.setState(ERROR_USERNAME_REPETITION);
                jr.setMessage(e.getMessage());
            }else if (e instanceof InsertException){
                jr.setState(31);
                jr.setMessage(e.getMessage());
            }
        return jr;
    }

    public Integer  getByUid(HttpSession session){
        return  Integer.valueOf(session.getAttribute("uid").toString());

    }

    public String getUsername(HttpSession session){
        return session.getAttribute("username").toString();
    }




}
