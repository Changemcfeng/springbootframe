package com.example.jwt.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.jwt.annotaion.LoginAnnotation;
import com.example.jwt.annotaion.OtherPermit;
import com.example.jwt.service.UserService;
import com.example.jwt.service.impl.GetToken;
import com.example.jwt.utils.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserContrller {
    @Autowired
    private UserService userService;

    @Resource(name = "GetToken")
    GetToken getToken;
    JSONObject jsonObject = new JSONObject();

    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json")
    public JSONObject register(@RequestBody User user) {
        int result = userService.saveUsers(user);
        if (result>0){
            jsonObject.put("status",true);
            jsonObject.put("message","新增成功");
            return jsonObject;
        }
        else {
            jsonObject.put("status",false);
            jsonObject.put("message","增加失败");
            return jsonObject;
        }

    }
    @LoginAnnotation
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json")
    public JSONObject userLogin(@RequestBody User user){
        log.info("username: "+user.getName()+"password:"+user.getPassword());
        int re = userService.findUsers(user);
        if (re>0){
            String token = getToken.token(user);
            jsonObject.put("users",user);
            jsonObject.put("message",token);
        }
        else {
            jsonObject.put("message","帐号或者密码错误");
        }
        return jsonObject;
    }
    @OtherPermit
    @RequestMapping(value = "/otherPage",method = RequestMethod.GET)
    public JSONObject otherPage(){
        //模仿下登陆后，传入token，token验证后返回有效信息
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message","验证成功");
        jsonObject.put("code",200);
        return jsonObject;
    }
}