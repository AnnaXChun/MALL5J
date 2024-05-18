package com.yami.shop.api.controller;

import com.yami.shop.common.response.ServerResponseEntity;
import com.yami.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalTime.now;

@RestController
public class SalesController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/sales/{id}")
    public ServerResponseEntity<List<Double>> sales(@PathVariable Long id){
        return ServerResponseEntity.success(orderService.getSales(id));
    }

}
