package com.yami.shop.bean.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalTime;

@Data
@TableName("tz_ws_message")
public class MessageParam {
    @TableId
    private Long Id;

    @NotBlank(message = "时间不能为空")
    @JsonProperty("Time")
    @TableField("Time")
    private String Time;

    @NotBlank(message = "发送方不能为空")
    @TableField("from")
    @JsonProperty("From")
    private Integer From;
    @JsonProperty("Message")
    @TableField("message")
    private String Message;

    @NotBlank(message = "用户ID不能为空")
    @JsonProperty("userId")
    @TableField("userId")
    private String userId;

    @NotBlank(message = "商户名称不能为空")
    @JsonProperty("Shopname")
    private String  Shopname;

    @NotBlank(message = "商户ID不能为空")
    @JsonProperty("Shopid")
    @TableField("Shopid")
    private Long Shopid;

    @NotBlank(message = "用户名不能为空")
    @JsonProperty("Username")
    @TableField("Username")
    private String Username;

//    @NotBlank(message = "邮箱不能为空")
    @JsonProperty("Email")
    @TableField("Email")
    private String Email;
}
