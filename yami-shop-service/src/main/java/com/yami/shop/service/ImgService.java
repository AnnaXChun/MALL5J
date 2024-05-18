package com.yami.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yami.shop.bean.dto.ImageDto;
import com.yami.shop.bean.model.Image;
import com.yami.shop.bean.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ImgService extends IService<Image> {

    //存储图片到历史记录
    void saveImage(Image img);

    //根据图片id获取图片信息
    Image getImageById(String imgId);

    //根据图片id删除图片
    void removeImgById(String imgId);

    //分页获取商品搜索历史
    List<Image> getHistory(String userId);

}
