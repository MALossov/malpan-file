package com.malpan.file.controller;

import com.malpan.file.common.RestResult;
import com.malpan.file.service.MailService;
import com.malpan.file.util.CodeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Timer;
import java.util.TimerTask;

@Tag(name="VerifyCode",description = "发送验证码的接口")
@Slf4j
@RestController
public class CodeController {

    @Resource
    MailService mailComp;
    private Timer timer;

    @Operation(summary = "发送验证码",description = "发送验证码的接口",tags = {"verify code"})
    @GetMapping("/verifycode")
    private RestResult IdentifyCodeSend(HttpSession httpSession, @Parameter(description = "接收验证码的邮箱") String email){
        String verifyCode= CodeUtil.CodeGenerate(8);

        httpSession.setAttribute("verifyCode",verifyCode);
        httpSession.setAttribute("verifyMail",email);
        try{
            final Timer timer= new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    httpSession.removeAttribute("verifyCode");
                    httpSession.removeAttribute("verifyMail");
                    timer.cancel();
                }
            },5*60*1000);
        }catch (Exception e){
            return RestResult.fail().message("verifyCode out dated");
        }

        mailComp.SimpleMailSend("验证码","验证码："+verifyCode,email);
        return RestResult.success().message("verifyCode sended successfully.");
    }
}
