package com.yami.shop.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yami.shop.bean.param.MessageParam;
import com.yami.shop.common.response.ServerResponseEntity;
import com.yami.shop.dao.WsMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msg")
public class MSGController {
    @Autowired
    private WsMessageMapper wsMessageMapper;
    @PostMapping("/save")
    //保存聊天记录
    public ServerResponseEntity<String> saveMsg(@RequestBody MessageParam[] msgParamArray) {
        for (MessageParam msgParam : msgParamArray) {
            wsMessageMapper.saveMsg(msgParam);
        }
        return ServerResponseEntity.success("储存聊天记录成功");
    }
//    根据用户id和商家id获取聊天记录
    @PostMapping("/GetByUserIdAndShopId")
    public ServerResponseEntity<List<MessageParam>> GetByUserIdAndShopId(@RequestBody MessageParam param) {
        return ServerResponseEntity.success(wsMessageMapper.GetByUserIdAndShopId(param.getUserId(),param.getShopid()));
    }
//    根据用户id获取对话过的商家
    @PostMapping("/GetShopList")
    public ServerResponseEntity<List<String>> GetShopList(@RequestBody MessageParam msgParamArray) {
        return ServerResponseEntity.success(wsMessageMapper.GetByUserId(msgParamArray.getUserId()));
    }
}
