package com.mickey.tech.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author suzhengxiao
 * @date 2020/3/7 17:22
 **/
@Data
public class UserInfoVO {


    @NotBlank(message="用户名不能为空")
    private String userName;

    @Email
    private String email;

    @Pattern(regexp = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$", message = "{identityId}不符合身份证格式")
    private String identityId;
}
