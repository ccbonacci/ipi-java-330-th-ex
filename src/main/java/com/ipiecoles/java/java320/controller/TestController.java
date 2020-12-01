package com.ipiecoles.java.java320.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping(value = "/test") // Equivalent à @RequestMapping(value..., method = RequestMethod.GET)
    public String test(final ModelMap model) {
        model.put("nom", "IPI");
        model.put("message", "How are <strong> you </strong>");
        return "test";
    }


}
