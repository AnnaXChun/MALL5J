package com.yami.shop.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yami.shop.bean.model.ShopDetail;
import com.yami.shop.bean.param.ShopRegisterParam;
import com.yami.shop.common.exception.YamiShopBindException;
import com.yami.shop.security.common.bo.UserInfoInTokenBO;
import com.yami.shop.security.common.enums.SysTypeEnum;
import com.yami.shop.security.common.manager.PasswordManager;
import com.yami.shop.security.common.manager.TokenStore;
import com.yami.shop.security.common.vo.TokenInfoVO;
import com.yami.shop.service.ShopDetailService;
import com.yami.shop.sys.dao.SysUserRoleMapper;
import com.yami.shop.sys.model.SysUser;
import com.yami.shop.sys.service.SysUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import com.yami.shop.common.response.ServerResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@RestController
@Tag(name = "商家注册相关接口")
@AllArgsConstructor
public class ShopRegisterController {
    private final SysUserService sysUserService;

    private final ShopDetailService shopDetailService;

    private final PasswordEncoder passwordEncoder;

    private final TokenStore tokenStore;

    private final PasswordManager passwordManager;

    private final SysUserRoleMapper sysUserRoleMapper;

    @PostMapping("/shopRegister")
    @Operation(summary = "注册" , description = "商家注册或绑定手机号接口")
    public ServerResponseEntity<TokenInfoVO> register(@Valid @RequestBody ShopRegisterParam shopRegisterParam) {
        // 正在进行申请注册
        if (sysUserService.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, shopRegisterParam.getUsername())) > 0) {
            // 该用户名已注册，无法重新注册
            throw new YamiShopBindException("该用户名已注册，无法重新注册");
        }
        Date now = new Date();
        SysUser sysUser = new SysUser();
        sysUser.setCreateTime(now);
        sysUser.setStatus(1);
        sysUser.setRoleIdList(new ArrayList<>());
        sysUser.getRoleIdList().add(3L);
        System.out.println(Arrays.toString(sysUser.getRoleIdList().toArray(new Long[0])));
        sysUser.setUsername(shopRegisterParam.getUsername());
        sysUser.setEmail(shopRegisterParam.getEmail());
        String decryptPassword = passwordManager.decryptPassword(shopRegisterParam.getPassword());
        sysUser.setPassword(passwordEncoder.encode(decryptPassword));
        sysUserService.save(sysUser);

        ShopDetail shopDetail = new ShopDetail();
        shopDetail.setShopId(sysUser.getShopId());
        shopDetail.setUserId(String.valueOf(sysUser.getUserId()));
        shopDetail.setShopName(shopRegisterParam.getShopName());
        shopDetail.setProvince(shopRegisterParam.getProvince());
        shopDetail.setCity(shopRegisterParam.getCity());
        shopDetail.setArea(shopRegisterParam.getArea());
        shopDetail.setShopAddress(shopRegisterParam.getShopAddress());
        shopDetail.setShopNotice(shopRegisterParam.getShopNotice());
        shopDetail.setMobile(shopRegisterParam.getMobile());
        shopDetail.setTel(shopRegisterParam.getTel());
        shopDetailService.save(shopDetail);

        sysUser.setShopId(shopDetail.getShopId());
        sysUserService.updateById(sysUser);

        sysUserRoleMapper.insertUserAndUserRole(sysUser.getUserId(),sysUser.getRoleIdList());

        // 2. 登录
        UserInfoInTokenBO userInfoInTokenBO = new UserInfoInTokenBO();
        userInfoInTokenBO.setUserId(String.valueOf(sysUser.getUserId()));
        userInfoInTokenBO.setSysType(SysTypeEnum.SHOP.value());
        userInfoInTokenBO.setIsAdmin(0);
        userInfoInTokenBO.setEnabled(true);
        return ServerResponseEntity.success(tokenStore.storeAndGetVo(userInfoInTokenBO));
    }
}
