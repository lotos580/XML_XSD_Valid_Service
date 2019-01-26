package com.task.xmlvalid;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

public class XMLValidatorTest {

    private RestController restService;

    {
        try {
            restService = new RestController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void validTest() {

        MultipartFile xml = null;
        MultipartFile xsd = null;
        try {
            xml = new MockMultipartFile("xml.xml", new FileInputStream(new File("src/test/resources/xml.xml")));
            xsd = new MockMultipartFile("xsd.xsd", new FileInputStream(new File("src/test/resources/xsd.xsd")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String actual = null;
        String output = "VALID";
        try {
            actual = restService.validate(xml, xsd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals("Result not as expected", output, actual);
    }

    @Test
    public void invalidTest() {

        MultipartFile xml = null;
        MultipartFile xsd = null;
        try {
            xml = new MockMultipartFile("badxml.xml", new FileInputStream(new File("src/test/resources/badxml.xml")));
            xsd = new MockMultipartFile("xsd.xsd", new FileInputStream(new File("src/test/resources/xsd.xsd")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String actual = null;
        String output = "INVALID";
        try {
            actual = restService.validate(xml,xsd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals("Result not as expected", output, actual);
    }

}
