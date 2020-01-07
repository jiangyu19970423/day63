package cn.nyse.controller;

import cn.nyse.entity.WorkOrder;
import cn.nyse.service.WorkOrderService;
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
 * @Date: 2019/12/27/15:58
 * @Description:
 */
@RestController
@RequestMapping("manager/admin/work")
public class WorkOrderController {

    @Autowired
    WorkOrderService workOrderService;

    @RequestMapping("")
    public ModelAndView index() {
        return new ModelAndView("/work/admin/work");
    }

    @RequestMapping("toList")
    public PageInfo<WorkOrder> toList(@RequestBody Map<String, Object> params) {
        return workOrderService.selectPage(params);
    }

    @RequestMapping("selectByOid")
    public Map<String,Object> selectByOid(long oid){
        return workOrderService.selectByOid(oid);
    }

    @RequestMapping("toDetail")
    public ModelAndView toDetail(){
        return new ModelAndView("/work/work-detail");
    }
}
