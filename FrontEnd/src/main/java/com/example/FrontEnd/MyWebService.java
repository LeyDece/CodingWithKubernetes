package com.example.FrontEnd;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyWebService {

    @Value("${backEndURL}")
    String backEndURL;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String hello(){
		try{
            RestTemplate restTemplate = new RestTemplate();
            String s = restTemplate.getForObject(backEndURL, String.class);
            return "Retrieving every user from backend  : " + s;
        }catch (Exception e){
            return "Error : " + e.toString();
        }
    }

}
