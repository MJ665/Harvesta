package com.courseweb.web.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.courseweb.web.Model.Admin;
import com.courseweb.web.Model.Crops;
import com.courseweb.web.Model.User;
import com.courseweb.web.Services.Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class Controller {

    @Autowired
    private Service service;
    
    @PostMapping("/adduser")
    public String addUser(@RequestBody User user){
        return service.addUser(user);
    }

    @PostMapping("/setPrice")
    public String setCropsPrice(@RequestBody List<Crops> list) throws JsonProcessingException{
        int index=0;
        while(list.size()!=index){
            service.setCropPrice(list.get(index));
            index++;
        }
        service.emailData();
        return "Successfully updated prices";
    }
    
    @PostMapping("/viewAdmin")
    public String viewAdmin(@RequestBody Admin admin){
        return service.viewAdmin(admin);
    }

    @GetMapping("/viewCrops")
    public List<Crops> viewCrops(){
        return service.viewCrops();
    }

    // @PostMapping("/send")
    // public String sendEmail() throws JsonProcessingException {
    //     return service.emailData();
    // }
}
