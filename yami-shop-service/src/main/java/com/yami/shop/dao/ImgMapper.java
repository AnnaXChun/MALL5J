package com.yami.shop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yami.shop.bean.dto.ImageDto;
import com.yami.shop.bean.model.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImgMapper extends BaseMapper<Image> {

    //返回所有搜索历史
    List<ImageDto> selectAll(String userId);

}
