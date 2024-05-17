package com.yami.shop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yami.shop.bean.param.MessageParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsMessageMapper extends BaseMapper<MessageParam> {
    public void saveMsg(MessageParam msgParamArray);
    public List<String> GetByUserId(String userId);
    public List<MessageParam> GetByUserIdAndShopId(String userId, Long shopId);
}
