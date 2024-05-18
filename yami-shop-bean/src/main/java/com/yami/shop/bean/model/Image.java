package com.yami.shop.bean.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@TableName("history")
public class Image implements Serializable {

    //用户id
    private String user_id;

    //图片id
    private Integer image_id;

    //图片识别的名称
    private String file_name;

    //图片的url
    private byte[] image_data;

    //搜索的时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime search_time;

}
