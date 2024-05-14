package com.yami.shop.api.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
@RequestMapping("/Img")
public class ImgDetectController {

    public static final  String PAYMENT_URL = "http://localhost:8090";

    @Autowired
    private RestTemplate restTemplate;

    @Data
    public static class PythonResponse {
        private String image;
        private int id;
        private String name;
    }

    @GetMapping("/test")
    public String getPayment() {
        return restTemplate.getForObject(PAYMENT_URL + "/test", String.class);
    }

    @PostMapping("/detect")
    public ResponseEntity<PythonResponse> getDetectImg(@RequestParam("file") MultipartFile file) throws IOException {
        ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };


        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("file", fileResource);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        String pythonServiceUrl = PAYMENT_URL+"/api/img";
        ResponseEntity<PythonResponse> response = restTemplate.postForEntity(pythonServiceUrl, requestEntity,PythonResponse.class);
        System.out.println(response);
        if (response.getStatusCode().is2xxSuccessful()) {

            saveImage(response.getBody().getImage().getBytes(),file.getName());
            return response;
        } else {
            System.out.println("Failed to call Python API");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //存储图片
    private void saveImage(byte[] imageData,String fileName){
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yami_shop", "root","123456")){
            String sql = "INSERT INTO history(file_name,image_data) VALUES(?,?)";
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1,fileName);
                statement.setBytes(2,imageData);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
/*
* 建表语句
* CREATE TABLE History(
	id INT PRIMARY KEY AUTO_INCREMENT,
	file_name VARCHAR(255) NOT NULL,
	image_data LONGBLOB NOT NULL
);
*/
}
