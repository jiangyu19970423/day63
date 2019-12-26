package cn.nyse.controller;

import cn.nyse.entity.Examine;
import cn.nyse.service.ExamineService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/26/15:39
 * @Description:
 */
@RestController
@RequestMapping("manager/examine")
public class ExamineController {
    @Autowired
    ExamineService service;

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/examine/index");
    }


    @RequestMapping("toList")
    public PageInfo<Examine> toList(@RequestBody Map<String, Object> params) {
        return service.selectPage(params);
    }
}
