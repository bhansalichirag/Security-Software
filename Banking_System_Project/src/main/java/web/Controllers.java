package main.java.web;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

@Controller
public class Controllers {


	@RequestMapping(value= {"/","/login"}, method = RequestMethod.GET)
    public String welcome(ModelMap model){
        String name = (String) model.get("name");
        model.put("Login", name);
        return "Login";
    }
}