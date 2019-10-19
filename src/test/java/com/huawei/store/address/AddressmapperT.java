package com.huawei.store.address;

import com.huawei.store.entity.Address;
import com.huawei.store.mapper.AddressMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.ListIterator;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressmapperT {
    @Autowired
    AddressMapper mapper;

    @Test
    public void testAddAddress(){

        Address address=new Address();
        address.setUid(8);
        address.setName("meiemei");
        address.setProvinceName("BEIJING");
        address.setProvinceCode(010);
        address.setAddress("海淀区中关村普天大厦");
        Integer row=mapper.saveAddress(address);
        System.err.println("row="+row);


    }
    @Test
    public void testSHOWaDDR(){
        List<Address> addresses = mapper.findByUid(6);
//        ListIterator<Address> addressListIterator = addresses.listIterator();
//      while (addressListIterator.hasNext()){
//
//      }
        System.out.println(addresses.toString());


    }

}
