package main.java.web;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

@Controller
public class Controllers {


    @RequestMapping(value="/opener", method = RequestMethod.GET)
    public String opener(ModelMap model){
        String name = (String) model.get("name");
        model.put("open", name);
        return "open";
    }
    @RequestMapping(value = "/reg_emp.jsp", method = RequestMethod.GET)
    public String redirect() {
       return "redirect:finalPage";
    }
}