package com.yami.shop.api.controller;


import com.yami.shop.bean.model.SysUser;
import com.yami.shop.bean.model.User;
import com.yami.shop.bean.param.MessageParam;
import com.yami.shop.common.response.ServerResponseEntity;
import com.yami.shop.dao.SysUserMapper;
import com.yami.shop.dao.UserMapper;
import com.yami.shop.dao.WsMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/msg")
public class MSGController {
    @Autowired
    private WsMessageMapper wsMessageMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserMapper userMapper;

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
    @GetMapping("/GetShopList")
    public ServerResponseEntity<List<SysUser>> GetShopList() {
        List<SysUser> shopidlist =  sysUserMapper.selectList(null);
        return ServerResponseEntity.success(shopidlist);
    }
    @GetMapping("/GetUserList")
    public ServerResponseEntity<List<User>> GetUserList() {
        List<User> Useridlist =  userMapper.selectList(null);
        return ServerResponseEntity.success(Useridlist);
    }
}
