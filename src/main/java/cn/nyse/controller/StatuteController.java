package cn.nyse.controller;

import cn.nyse.entity.Result;
import cn.nyse.entity.Statute;
import cn.nyse.service.StatuteService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/30/16:06
 * @Description:
 */
@RestController
@RequestMapping("manager/statute")
public class StatuteController {

    @Autowired
    StatuteService service;

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/statute/index");
    }

    @RequestMapping("toList")
    public PageInfo<Statute> toList(@RequestBody Map<String, Object> params) {
        return service.selectByCondition(params);
    }

    @RequestMapping("toUpdate")
    public Statute toUpdate(Long id) {
        return service.selectByPrimaryKey(id);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/statute/update");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Statute statute) {
        Result result = new Result();
        int i = service.updateByPrimaryKeySelective(statute);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("更新成功");
        }
        return result;
    }

    @RequestMapping("delete")
    public void delete(Integer id) {
        int i = service.deleteByPrimaryKey(id);
    }

    @RequestMapping("insert")
    public Result insert(@RequestBody Statute statute){
        statute.setCreateDate(new Date());
        statute.setPubDate(new Date());
        statute.setUpdateDate(new Date());
        statute.setDelFlag("0");
        Result result = new Result();
        int i = service.insertSelective(statute);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("保存成功");
        }
        return result;
    }
}
