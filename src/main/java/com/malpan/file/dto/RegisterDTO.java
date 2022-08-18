package com.malpan.file.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description="注册DTO")
@Data
public class RegisterDTO {
    @Schema(description="用户名")
    private String username;
    @Schema(description="MailBox")
    private String mailbox;
    @Schema(description="密码")
    private String password;
    @Schema(description = "VerifyCode")
    private String VerifyCode;
}