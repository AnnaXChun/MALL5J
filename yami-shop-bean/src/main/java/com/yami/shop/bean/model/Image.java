package com.yami.shop.bean.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@TableName("history")
public class Image{

    //用户id
    @TableField("userId")
    private String userId;

    //图片id
    @TableField("imageId")
    private Integer imageId;

    //图片识别的名称
    @TableField("name")
    private String name;

    //图片的url
    @TableField("data")

    private byte[] data;

    //搜索的时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("time")
    private LocalDateTime time;

}
