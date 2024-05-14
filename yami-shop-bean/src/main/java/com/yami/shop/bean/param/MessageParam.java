package com.yami.shop.bean.param;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalTime;

@Data
@TableName("tz_ws_message")
public class MessageParam {
    @TableId
    private Long Id;

    @NotBlank(message = "时间不能为空")
    private LocalTime Time;

    @NotBlank(message = "发送方不能为空")
    private Integer from;

    private String Message;

    @NotBlank(message = "用户ID不能为空")
    private Long userId;

    @NotBlank(message = "商户名称不能为空")
    private Long Shopname;

    @NotBlank(message = "商户ID不能为空")
    private Long Shopid;

    @NotBlank(message = "用户名不能为空")
    private String Username;

    @NotBlank(message = "邮箱不能为空")
    private String Email;
}
