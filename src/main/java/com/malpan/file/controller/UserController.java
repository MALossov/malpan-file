package com.malpan.file.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malpan.file.common.RestResult;
import com.malpan.file.dto.RegisterDTO;
import com.malpan.file.model.User;
import com.malpan.file.service.MailService;
import com.malpan.file.service.UserService;
import com.malpan.file.util.JWTUtil;
import com.malpan.file.vo.LoginVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "user", description = "该接口为用户接口，主要做用户登录，注册和校验token")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    JWTUtil jwtUtil;

    /**
     * 成功响应测试
     */
    @GetMapping(value="/test1")
    @ResponseBody
    public RestResult test1(){
        return RestResult.success();
    }

    /**
     * 失败响应测试
     */
    @GetMapping(value="/test2")
    @ResponseBody
    public RestResult test2(){
        return RestResult.fail();
    }
    
    /**
    * 空指针异常响应测试
    */
    @GetMapping(value="/test3")
    @ResponseBody
    public RestResult test3(){
        String s = null;
        int i = s.length();
        return RestResult.success();
    }

    @Operation(summary = "用户注册", description = "注册账号", tags = {"user"})
    @PostMapping(value = "/register")
    @ResponseBody
    public RestResult<String> register(HttpSession httpSession,@RequestBody RegisterDTO registerDTO) {
        RestResult<String> restResult = null;
        User user = new User();
        if(!(registerDTO.getVerifyCode().equals(httpSession.getAttribute("verifyCode"))&&
         registerDTO.getMailbox().equals(httpSession.getAttribute("verifyMail")))){
            return RestResult.fail().message("验证码错误");
        }
        user.setUsername(registerDTO.getUsername());
        user.setMailbox(registerDTO.getMailbox());
        user.setPassword(registerDTO.getPassword());

        restResult = userService.registerUser(user);

        return restResult;
    }

    @Operation(summary = "用户登录", description = "用户登录认证后才能进入系统", tags = {"user"})
    @GetMapping(value = "/login")
    @ResponseBody
    public RestResult<LoginVO> userLogin(String mailbox, String password) {
        RestResult<LoginVO> restResult = new RestResult<LoginVO>();
        LoginVO loginVO = new LoginVO();
        User user = new User();
        user.setMailbox(mailbox);
        user.setPassword(password);
        RestResult<User> loginResult = userService.login(user);

        if (!loginResult.getSuccess()) {
            return RestResult.fail().message("登录失败！");
        }

        loginVO.setUsername(loginResult.getData().getUsername());
        String jwt = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jwt = jwtUtil.createJWT(objectMapper.writeValueAsString(loginResult.getData()));
        } catch (Exception e) {
            return RestResult.fail().message("登录失败！");
        }
        loginVO.setToken(jwt);

        return RestResult.success().data(loginVO);
    }

    @Operation(summary = "检查用户登录信息", description = "验证token的有效性", tags = {"user"})
    @GetMapping("/checkuserlogininfo")
    @ResponseBody
    public RestResult<User> checkToken(@RequestHeader("token") String token) {
        RestResult<User> restResult = new RestResult<User>();
        User tokenUserInfo = null;
        try {

            Claims c = jwtUtil.parseJWT(token);
            String subject = c.getSubject();
            ObjectMapper objectMapper = new ObjectMapper();
            tokenUserInfo = objectMapper.readValue(subject, User.class);

        } catch (Exception e) {
            log.error("解码异常");
            return RestResult.fail().message("认证失败");

        }

        if (tokenUserInfo != null) {

            return RestResult.success().data(tokenUserInfo);

        } else {
            return RestResult.fail().message("用户暂未登录");
        }
    }
}