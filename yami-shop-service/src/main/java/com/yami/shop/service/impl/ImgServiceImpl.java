package com.yami.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yami.shop.bean.dto.ImageDto;
import com.yami.shop.bean.model.Image;
import com.yami.shop.dao.ImgMapper;
import com.yami.shop.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Image> implements ImgService{

    @Autowired
    private ImgMapper imageMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveImage(Image img){
        imageMapper.insert(img);
    }

    @Override
    public Image getImageById(String imgId) {
        return imageMapper.selectById(imgId);
    }

    @Override
    public void removeImgById(String imgId){
        imageMapper.deleteById(imgId);
    }

    @Override
    public List<ImageDto> getHistory(String userId){
        return imageMapper.selectAll(userId);
    }



}
