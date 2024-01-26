package com.bandup.api.controller;

import com.bandup.api.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/aws-s3")
@RequiredArgsConstructor
public class AwsS3Controller {
    private final AwsS3Service awsS3Service;

    @GetMapping("/get")
    public String getPreSignedGetUrl(@RequestParam(value = "key", required = true) String key) {
        return awsS3Service.getPreSignedGetUrl("bandup", key);
    }
    @GetMapping("/put")
    public String getPreSignedPutUrl(
            @RequestParam(value = "key", required = true) String key,
            @RequestParam(value = "contentType", required = true) String contentType
    ) {
        return awsS3Service.getPreSignedPutUrl("bandup", key, contentType);
    }
}
