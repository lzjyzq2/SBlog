package cn.settile.sblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/{name}")
    public String index(Model model, @PathVariable String name){
        model.addAttribute("name",name);
        return "themes/SBlog/index";
    }
}
