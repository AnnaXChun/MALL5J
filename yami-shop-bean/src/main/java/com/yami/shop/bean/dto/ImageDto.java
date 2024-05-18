package com.yami.shop.bean.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@TableName("history")
public class ImageDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("search_time")
    LocalDateTime search_time;
    @TableField("image_data")
    byte[] image_data;
    @TableField("file_name")
    String file_name;
    @TableField("image_id")
    String image_id;
}
