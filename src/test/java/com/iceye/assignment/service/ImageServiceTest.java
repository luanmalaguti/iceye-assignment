package com.iceye.assignment.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Test
    public void convertTextToImageTest(){
        imageService.convertTextToImage("test");
        File file = new File(imageService.getTempDir() + "test.png");
        assertTrue(file.exists());
    }
}