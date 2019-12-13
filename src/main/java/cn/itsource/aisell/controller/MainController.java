package cn.itsource.aisell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author myllxy
 * @create 2019-12-14 6:47
 */
@Controller
@RequestMapping("/main")
public class MainController {
    @RequestMapping()
    String showHomepage() {
        return "main";
    }

}
