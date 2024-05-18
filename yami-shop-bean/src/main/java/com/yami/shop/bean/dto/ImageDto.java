package com.yami.shop.bean.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ImageDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String search_time;

    byte[] image_data;

    String file_name;

    String image_id;
}
