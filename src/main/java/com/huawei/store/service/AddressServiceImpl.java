package com.huawei.store.service;

import com.huawei.store.entity.Address;
import com.huawei.store.mapper.AddressMapper;
import com.huawei.store.service.ex.InsertException;
import com.huawei.store.service.ex.UserNotFoundException;
import com.huawei.store.service.ex.mapper.AddressCountlimitException;
import com.sun.javafx.css.converters.InsetsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

import static com.huawei.store.controller.BaseControlller.ADDRESS_MAX_COUNT;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;


    @Override
    public void addAddress(Integer uid,String username,Address address) {
        Integer countAddress = getCountByUid(uid);

        if (countAddress>ADDRESS_MAX_COUNT){
          throw new AddressCountlimitException("保存地址数量达到上限！");


        }
        address.setUid(uid);
       int isDefault=countAddress==0? 1:0;
       address.setIsDefault(isDefault);
       address.setCreatedUser(username);
       address.setCreatedTime(new Date());
       address.setModifiedUser(username);
       address.setModifiedTime(new Date());
        System.out.println(address);

    saveAddress(address);


    }

    @Override
    public List<Address> showAllAddress(Integer uid) {
        if (uid==null||uid<0){
            throw new UserNotFoundException("用户登录失效，请重新登录！");
        }
        List<Address> lists = addressMapper.findByUid(uid);
       for (Address address:lists){
           address.setTel(null);

           address.setZip(null);
           address.setCreatedUser(null);
           address.setCreatedTime(null);
           address.setModifiedUser(null);
           address.setModifiedTime(null);
       }


        return lists;
    }

    private void saveAddress(Address address) {

        Integer integer = addressMapper.saveAddress(address);
        if (integer!=1){
            throw new InsertException("添加地址异常，请联系管理员");
        }


    }

    private Integer getCountByUid(Integer uid) {
        if(uid==null||uid<1){
            throw new IllegalArgumentException();
        }

        Integer count = addressMapper.getCountByUid(uid);



        return count;
    }


}
