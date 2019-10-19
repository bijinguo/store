package com.huawei.store.service;

import ch.qos.logback.core.pattern.FormatInfo;
import com.huawei.store.entity.Address;

import java.util.List;

public interface AddressService {


     void addAddress(Integer uid,String username,Address address);

     List<Address> showAllAddress(Integer uid);







}
