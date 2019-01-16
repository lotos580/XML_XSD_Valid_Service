package com.task.xmlvalid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.InputStream;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    /**
     * Ожидаем POST запрос
     * @param xml XML файл, получаемый из POST запроса
     * @param xsd XSD файл, получаемый из POST запроса
     * @return возвращает 'VALID' или 'INVALID' в случае успеха или неуспеха
     */

    @PostMapping("/validate")
    public String validate(@RequestParam("xml") MultipartFile xml,
                           @RequestParam("xsd") MultipartFile xsd) {

        try {

            //Ищем и создаем экземпляр фабрики для XML Schema
            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            InputStream inpStream1 = xsd.getInputStream();
            StreamSource streamSrc = new StreamSource(inpStream1);

            Schema sch = factory.newSchema(streamSrc); //Компилируем созданную схему
            
            //Создаем валидатор для схемы
            Validator validator = sch.newValidator();
            InputStream inpStream2 = xml.getInputStream();
            
            Source src = new StreamSource(inpStream2); //Делаем разбор проверяемого документа
            validator.validate(src); //Производим валидацию документа
            return "VALID";

        } catch (Exception e) {
            return "INVALID";
        }
    }
}
