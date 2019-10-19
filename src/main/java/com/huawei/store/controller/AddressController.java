package com.huawei.store.controller;

import com.huawei.store.entity.Address;
import com.huawei.store.service.AddressService;
import com.huawei.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController extends BaseControlller {

    @Autowired
    AddressService service;

    @PostMapping("saveAddress" )
    public JsonResult<Void> saveAddress(HttpSession session, Address address){

        Integer uid = getByUid(session);
        String username = getUsername(session);
        service.addAddress(uid,username,address);

       return new JsonResult<>(SUCCESS);


    }
    @PostMapping("show_address")
    public JsonResult<List> showAddress(HttpSession session){
        Integer uid = getByUid(session);



        List<Address> lists=new ArrayList<>();
        lists = service.showAllAddress(uid);


        return new JsonResult<>(SUCCESS,lists);
    }






}
