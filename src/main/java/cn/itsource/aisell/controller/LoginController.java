package cn.itsource.aisell.controller;

import cn.itsource.aisell.common.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author myllxy
 * @create 2019-12-14 20:35
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password) {
        //1.拿到当前用户
        Subject subject = SecurityUtils.getSubject();
        try {
            //2.封装令牌，进行登录
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误！！！");
            e.printStackTrace();
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误！！！");
            e.printStackTrace();
        } catch (AuthenticationException e) {
            System.out.println("神迷错误！！！！");
            e.printStackTrace();
        }
        return new JsonResult();
    }

    //登出跳到登录页面
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/s/login.jsp";
    }
}
