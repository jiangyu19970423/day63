package cn.nyse.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2020/01/07/14:52
 * @Description:
 */

/**
 * 生成验证码的servlet:
 * 1.引入kaptcha依赖
 * 2.编写servlet，继承KaptchaServlet
 */
@WebServlet(urlPatterns = "/code.jpg",initParams = {
        @WebInitParam(name="kaptcha.image.width",value = "120"),
        @WebInitParam(name="kaptcha.image.height",value = "32"),
        @WebInitParam(name="kaptcha.textproducer.char.length",value = "4"),
        @WebInitParam(name="kaptcha.textproducer.font.color",value = "red"),
        @WebInitParam(name="kaptcha.textproducer.char.string",value = "1234567890"),
        @WebInitParam(name="kaptcha.background.clear.from",value = "blue"),
        @WebInitParam(name="kaptcha.background.clear.to",value = "yellow"),
        @WebInitParam(name="kaptcha.session.key",value = "vcode"),
        @WebInitParam(name="kaptcha.textproducer.font.size",value = "28"),
})
public class CodeServlet extends KaptchaServlet {
}
