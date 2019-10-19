package com.huawei.store.user;

import com.huawei.store.StoreApplication;
import com.huawei.store.entity.User;
import com.huawei.store.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void testUser(){
        User user=new User();
        user.setUsername("XIN");
        user.setPassword("0990");
        user.setEmail("9080@COM");

        Integer integer = userMapper.addNewUser(user);
        System.out.println(integer);


    }


}
