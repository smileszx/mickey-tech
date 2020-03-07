package com.mickey.tech.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Swagger Bean测试
 * @author suzhengxiao
 * @date 2020/1/10 16:23
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class SwaggerVO {

    @ApiModelProperty(value="ID",dataType="String",name="ID",example="20200110123456")
    String id;

    @ApiModelProperty(value="编码",dataType="String",name="code",example="001")
    @NotBlank(message = "编码不能为空")
    String code;

    @ApiModelProperty(value="名称",dataType="String",name="name",example="xiaomi")
    @NotBlank(message = "名称不能为空")
    @Email
    String name;

}
