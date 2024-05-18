package com.yami.shop.bean.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

@Data
@TableName("history")
public class Image implements Serializable {

    //用户id

    private String user_id;

    //图片id
    private String image_id;

    //图片识别的名称
    private String file_name;

    //图片的url
    private byte[] image_data;

    //搜索的时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date search_time;


}
