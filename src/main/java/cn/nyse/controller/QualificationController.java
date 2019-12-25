package cn.nyse.controller;

import cn.nyse.entity.Demand;
import cn.nyse.entity.Qualification;
import cn.nyse.entity.Result;
import cn.nyse.service.QualificationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/25/15:43
 * @Description:
 */
@RestController
@RequestMapping("manager/qualification")
public class QualificationController {
    @Autowired
    QualificationService service;

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/qualification/index");
    }


    @RequestMapping("toList")
    public PageInfo<Qualification> toList(@RequestBody Map<String, Object> params) {
        return service.selectPage(params);
    }


    @RequestMapping("toUpdate")
    public Map<String, Object> toUpdate(Long id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("qualification", service.selectByPrimaryKey(id));
        map.put("oid", service.selectOfficeId(id));
        return map;
    }
    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage(){
        return new ModelAndView("/qualification/update");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Qualification qualification) {
        qualification.setUpdateDate(new Date());
        Result result = new Result();
        int i = service.updateByPrimaryKeySelective(qualification);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("更新成功");
        }
        return result;
    }
}
