package com.ipiecoles.java.java320.controller;


import com.ipiecoles.java.java320.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilController {

    @Autowired
    private EmployeRepository employeRepository;

    @GetMapping(value = "/")
    public String accueil(final ModelMap model){
        model.put("nombreEmployes", employeRepository.count());
        return "accueil";
    }
//    @GetMapping(value = "/")
//    public String menu(final ModelMap model){
//        model.put("nombreEmployes", employeRepository.count());
//        return "fragments/header";
//    }

}
