package cn.nyse.controller;

import cn.nyse.entity.Result;
import cn.nyse.entity.SysUser;
import cn.nyse.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2020/01/07/14:46
 * @Description:
 */
@Controller
public class MainController {


    @Autowired
    SysUserService sysUserService;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "/login";
    }



    @RequestMapping("doLogin")
    @ResponseBody
    public Result doLogin(@RequestBody Map<String, Object> params, HttpSession session) {
        //获取session的vcode
        String vcode = (String) session.getAttribute("vcode");
        Result result = new Result();
        if (vcode.equals(params.get("code"))) {//校验验证码是否正确
            SysUser sysUser = new SysUser();
            sysUser.setUsername((String) params.get("username"));
            sysUser.setPassword((String) params.get("password"));
            SysUser checkUser = sysUserService.checkSysUser(sysUser);
            if (checkUser != null) {
                result.setSuccess(true);
                result.setMsg("登录成功");
                result.setObj(checkUser);
                session.setAttribute("userInfo", checkUser);
            }
        }

        return result;
    }

    @RequestMapping("logout")
    @ResponseBody
    public Result logout(HttpSession session){
        Result result = new Result();
        session.invalidate();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("index")
    public String index(){
        return "/index";
    }
}
