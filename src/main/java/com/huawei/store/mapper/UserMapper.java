package com.huawei.store.mapper;

import com.huawei.store.entity.User;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Date;

public interface UserMapper {
    Integer addNewUser(User user);


    User findByUid(Integer uid);
    User findByUsername(String username);
    Integer updatePassword(
            @Param("uid")Integer uid,
            @Param("password")String password,
            @Param("modifiedUser")String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime
                           );
    Integer updateAvatar(@Param("uid")Integer uid,@Param("avatar")String avatar, @Param("modifiedUser")String modifiedUser,
                         @Param("modifiedTime") Date modifiedTime);

    Integer updateInfo(User user);
}
