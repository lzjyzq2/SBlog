package cn.settile.sblog.controller;

import cn.settile.sblog.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    ThemeService themeService;

    @RequestMapping("/{name}")
    public String index(Model model, @PathVariable String name){
        model.addAttribute("name",name);
        return themeService.render("index");
    }
    //TODO 主页实现
}
