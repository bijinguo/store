package com.huawei.store.service;

import com.huawei.store.entity.User;
import com.huawei.store.mapper.UserMapper;
import com.huawei.store.service.ex.*;
import jdk.nashorn.internal.ir.IfNode;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.Format;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void addUser(User user) throws UserNotFoundException,InsertException {
        String username = user.getUsername();
        if (userMapper.findByUsername(username)!=null){
            throw new  UsernameDuplicateException("用户名已存在，请重新register");
        }

        String salt=UUID.randomUUID().toString();
        user.setSalt(salt);
        String uuidPwd=getUUIDPwd(user.getPassword(),salt);
        user.setPassword(uuidPwd);
        user.setIsDelete(0);
        user.setCreatedTime(new Date());
        user.setCreatedUser(username);





        Integer integer = userMapper.addNewUser(user);
        if (!integer.equals(1)){
            throw new InsertException("register exception, have a try please");
        }


    }

    @Override
    public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
        User user = userMapper.findByUsername(username);
        if (user==null){
            throw new UserNotFoundException("用户不存在");
        }
        if (user.getIsDelete().equals(1)){
            throw new UserNotFoundException("USER IS NOT EXIST");
        }

        String salt = user.getSalt();
        String uuidPwd = getUUIDPwd(password, salt);


        if (!uuidPwd.equals(user.getPassword())) {
            throw new PasswordNotMatchException("password error");
        }
        user.setSalt(null);
        user.setPassword(null);
        user.setIsDelete(null);



        return user;
    }

    @Override
    public Integer changePassword(Integer uid,String oldPassword, String password, String modifiedUser)
                throws UserNotFoundException,PasswordNotMatchException, UpdateException {

        User user=userMapper.findByUid(uid);

        if (user==null){
            throw new UserNotFoundException("用户不存在");
        }
        if (user.getIsDelete().equals(1)) {

            throw new UserNotFoundException("用户不存在，可能以删除");
        }






        String salt = user.getSalt();
        String oldpwd = user.getPassword();
        if (!getUUIDPwd(oldPassword, salt).equals(oldpwd)){
            throw new PasswordNotMatchException("原始密码错误，修改异常！");

        }


        String uuidPwd = getUUIDPwd(password, salt);


        Integer integer = userMapper.updatePassword(uid, uuidPwd, modifiedUser, new Date());

        if (!integer.equals(1)){
            throw new UpdateException("修改密码异常");
        }
        return integer;
    }

    @Override
    public void  changeAvatar(Integer uid, String avatar, String modifiedUser) throws FileUploadException {
        User user=userMapper.findByUid(uid);

        if (user==null){
            throw new UserNotFoundException("用户不存在");
        }
        if (user.getIsDelete().equals(1)) {

            throw new UserNotFoundException("用户不存在，可能以删除");
        }

        Integer integer = userMapper.updateAvatar(uid, avatar, modifiedUser, new Date());
        if (!integer.equals(1)){
            throw new FileUploadException("文件上传失败");
        }


    }


    @Override
    public void changeInfo(Integer uid,String username,User user)throws UserNotFoundException,UpdateException {
        User oldUser = userMapper.findByUid(uid);
        if (oldUser==null){
            throw new UserNotFoundException("用户不存在");
        }
        if (oldUser.getIsDelete().equals(1)) {

            throw new UserNotFoundException("用户不存在，可能以删除");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());




        Integer integer = userMapper.updateInfo(user);
        if (!integer.equals(1)){
            throw new UpdateException("修改用户信息异常");
        }



    }

    @Override
    public User findByUid(Integer uid) {

        User user = userMapper.findByUid(uid);
        if (user==null){
            throw new UserNotFoundException("用户不存在");
        }
        if (user.getIsDelete().equals(1)) {

            throw new UserNotFoundException("用户不存在，可能以删除");
        }

        //将user中不相关的属性设为null
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPhone(user.getPhone());
        user1.setEmail(user.getEmail());
        user1.setGender(user.getGender());


        return user1;
    }

    private String getUUIDPwd(String password,String salt) {
        String s=salt+password+salt;

        for (int i = 0; i < 3; i++) {
            s= DigestUtils.md5DigestAsHex(s.getBytes());
        }

        return s;


    }
}
