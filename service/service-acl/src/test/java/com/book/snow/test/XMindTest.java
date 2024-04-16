package com.book.snow.test;


import com.book.snow.ApplicationTests;
import com.book.snow.model.XMind.XMind;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootTest(classes = ApplicationTests.class)
@RunWith(SpringRunner.class)
public class XMindTest {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Test
    public void testXMind(){

        XMind xMind = new XMind();


        String url = "http://www.akaywj.cloudns.be:5001/gen_mind";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.postForEntity(url, xMind, String.class);
        System.out.println(resp);
    }

}
