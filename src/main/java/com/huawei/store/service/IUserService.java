package com.huawei.store.service;

import com.huawei.store.entity.User;
import com.huawei.store.service.ex.PasswordNotMatchException;
import com.huawei.store.service.ex.UserNotFoundException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import java.util.Date;

public interface IUserService {


    void addUser(User user);
    User login(String username,String password)throws UserNotFoundException, PasswordNotMatchException;
    Integer changePassword(Integer uid,String oldPassword, String password, String modifiedUser);
    void changeAvatar(Integer uid,String avatar,String modifiedUser) throws FileUploadException;
    void changeInfo(Integer uid,String username,User user);


    User findByUid(Integer uid);


}
