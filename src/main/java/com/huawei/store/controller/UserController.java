package com.huawei.store.controller;

import com.huawei.store.controller.ex.FileEmptyException;
import com.huawei.store.controller.ex.FileSizeException;
import com.huawei.store.controller.ex.FileTypeException;
import com.huawei.store.entity.User;
import com.huawei.store.service.IUserService;
import com.huawei.store.service.ex.UpdateException;
import com.huawei.store.service.ex.UserNotFoundException;
import com.huawei.store.service.ex.UsernameDuplicateException;
import com.huawei.store.util.JsonResult;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController extends BaseControlller {
  public static final  List<String > AVATAR_TYPE=new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/jpeg");
    }
    @Autowired
    IUserService service;
    @PostMapping("reg")
    public JsonResult<Void> regUser( User user){
        service.addUser(user);
        return new JsonResult<>(SUCCESS);


    }
    @PostMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession httpSession){

        User user=service.login(username, password);
        Integer  uid = user.getUid();
        httpSession.setAttribute("uid",uid);
        httpSession.setAttribute("username", username);


        return  new JsonResult<User>(SUCCESS,user);


    }
    @PostMapping("change_password")
    public JsonResult<Void> changeP(@RequestParam("oldPassword") String oldPassword,
                                    @RequestParam("newPassword") String newPassword,HttpSession session){

        Integer uid = getByUid(session);
        if (uid==null){
            throw new UserNotFoundException("登录失效，请重新登录！");

        }
        User user=service.findByUid(uid);






        Integer row = service.changePassword(uid,oldPassword, newPassword, user.getUsername());
        if (!row.equals(1)){
            throw new UpdateException("密码更新失败");
        }

        return new JsonResult<>(SUCCESS);

    }
    @PostMapping("get_by_uid")
    public JsonResult<User> getUserInfoByUid(HttpSession session){
        Integer uid=getByUid(session);
        User user=service.findByUid(uid);


        return new JsonResult<User>(SUCCESS,user);


    }
    @PostMapping("change_info")
    public JsonResult<Void> changeInfo(HttpSession session,User user){
        if (user==null){
            throw new UserNotFoundException("更新数据不能为空");
        }
        Integer uid=getByUid(session);
       String username=user.getUsername();


        service.changeInfo(uid, username, user);

       return new JsonResult<>(SUCCESS);
    }
    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file")MultipartFile file, HttpServletRequest httpServletRequest,HttpSession httpSession){
    long size=10*1024*1024;
        if (file.isEmpty()){
            throw new FileEmptyException();
        }
        if (file.getSize()>size){
            throw new FileSizeException("上传文件过大，超出范围");

        }


        if(!AVATAR_TYPE.contains( file.getContentType())){

            throw new FileTypeException("上传文件格式错误！");
        }

        String originalf = file.getOriginalFilename();
        int i = originalf.lastIndexOf(".");
        String  suffix="";
        if (i>=0){
           suffix = originalf.substring(i);
        }
        String  f=UUID.randomUUID().toString()+suffix;

        //生成目标文件
        ServletContext context = httpServletRequest.getServletContext();
        String contextPath=context.getRealPath("upload");

        File dest=new File(contextPath);
        if (!dest.exists()){
            dest.mkdirs();
        }
        File parent=new File(dest, f);

        try {
            file.transferTo(parent);


        } catch (IOException e) {
            e.printStackTrace();
        }
        String avatar="/upload/"+f;
        Integer uid = Integer.valueOf(httpSession.getAttribute("uid").toString());

        String username = getUsername(httpSession);


        try {
            service.changeAvatar(uid, avatar,username );
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        JsonResult<String> jr=new JsonResult();
            jr.setData(avatar);
            jr.setState(SUCCESS);

        return jr;
    }



}
