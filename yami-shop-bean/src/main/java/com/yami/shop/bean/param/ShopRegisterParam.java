package com.yami.shop.bean.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "设置商家信息")
public class ShopRegisterParam {
    @Schema(description = "密码" )
    private String password;

    @Schema(description = "邮箱" )
    private String email;

    @Schema(description = "用户名" )
    private String username;

    @Schema(description = "手机号" )
    private String mobile;

    @Schema(description = "座机号")
    private String tel;

    @Schema(description = "头像" )
    private String img;

    @Schema(description = "校验登陆注册验证码成功的标识" )
    private String checkRegisterSmsFlag;

    @Schema(description = "商家店铺名称")
    private String shopName;

    @Schema(description = "店铺公告")
    private String shopNotice;

    @Schema(description = "店铺所在省份")
    private String province;

    @Schema(description = "店铺所在城市")
    private String city;

    @Schema(description = "店铺所在区域")
    private String area;

    @Schema(description = "店铺详细地址")
    private String shopAddress;
}
