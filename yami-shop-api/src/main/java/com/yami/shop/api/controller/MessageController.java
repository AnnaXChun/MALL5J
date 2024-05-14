package com.yami.shop.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yami.shop.bean.param.MessageParam;
import com.yami.shop.common.response.ServerResponseEntity;
import com.yami.shop.dao.WsMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private WsMessageMapper wsMessageMapper;
    @PostMapping("/save")
    public ServerResponseEntity<String> SaveMessage(@RequestBody List<MessageParam> messageParam) {
        //TODO 保存一轮的消息记录
        messageParam.forEach(message -> {
            wsMessageMapper.insert(message);
        });
        return ServerResponseEntity.success("保存消息记录成功");
    }
}
