package com.yami.shop.api.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/Img")
public class ImgDetectController {
    @PostMapping("/detect")
    public ResponseEntity<byte[]> getDetectImg(@RequestParam("file") MultipartFile file) throws IOException {
        //  准备文件资源
        ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

// 构造请求体
        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("file", fileResource);

//  设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

//创建 HttpEntity 对象
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

// 发送 HTTP 请求
        RestTemplate restTemplate = new RestTemplate();
        String pythonServiceUrl = "http://localhost:8888/api/img";
        ResponseEntity<byte[]> response = restTemplate.postForEntity(pythonServiceUrl, requestEntity,byte[].class);

// 处理响应
        if (response.getStatusCode().is2xxSuccessful()) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setContentType(MediaType.IMAGE_JPEG); // 假设图片是JPEG格式，根据实际情况调整
            return new ResponseEntity<>(response.getBody(), responseHeaders, HttpStatus.OK);
        } else {
            System.out.println("Failed to call Python API");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
