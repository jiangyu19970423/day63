package cn.nyse.controller;


import cn.nyse.entity.Demand;
import cn.nyse.entity.Result;
import cn.nyse.service.DemandService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/24/17:11
 * @Description:
 */
@RestController
@RequestMapping("manager/demand")
public class DemandController {
    @Autowired
    DemandService service;

    @RequestMapping("index")
    public ModelAndView toIndex() {
        return new ModelAndView("/demand/index");
    }

    @RequestMapping("toList")
    public PageInfo<Demand> toList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        return service.selectPage(pageNum, pageSize);
    }

    /**
     * 到更新页面的查找demand对象功能
     *
     * @param id
     * @return
     */
    @RequestMapping("toUpdate")
    public Demand toUpdate(Integer id) {
        return service.selectByPrimaryKey(id);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/demand/update");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Demand demand) {
        demand.setCreateDate(new Date());
        Result result = new Result();
        int i = service.updateByPrimaryKeySelective(demand);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("更新成功");
        }
        return result;
    }

    @RequestMapping("toDetail")
    public Demand toDetail(Integer id){
        return service.selectByPrimaryKey(id);
    }

    @RequestMapping("toDetailPage")
    public ModelAndView toDetailPage(){
        return new ModelAndView("/demand/detail");
    }
}
