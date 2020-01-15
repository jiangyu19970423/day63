package cn.nyse.controller;

import cn.nyse.entity.Result;
import cn.nyse.entity.SysLog;
import cn.nyse.entity.Waste;
import cn.nyse.entity.WasteType;
import cn.nyse.service.SysLogService;
import cn.nyse.service.WasteService;
import cn.nyse.service.WasteTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

//替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/syslog")
public class SysLogController {

    @Autowired
    SysLogService service;





    @RequestMapping("")
    public ModelAndView toIndex(){
        return new ModelAndView("/log/log");
    }

    @RequestMapping("selectPage")
    public PageInfo<SysLog> selectPage(@RequestBody Map<String,Object> params){
        return service.selectByCondition(params);
    }

    @RequestMapping("toDetail")
    public SysLog toDetail(Integer id){
        return service.selectByPrimaryKey(id);
    }

    @RequestMapping("toDetailPage")
    public ModelAndView toDetailPage(){
        return new ModelAndView("/log/log-detail");
    }




}
