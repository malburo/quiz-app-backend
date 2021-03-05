package com.example.Quiz.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class AdminAPI {
    @Autowired
    AdminService adminService;
    

}
