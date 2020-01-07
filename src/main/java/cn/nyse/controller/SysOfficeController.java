package cn.nyse.controller;

import cn.nyse.entity.Result;
import cn.nyse.entity.SysOffice;
import cn.nyse.entity.Waste;
import cn.nyse.entity.WasteType;
import cn.nyse.service.SysOfficeService;
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
@RequestMapping("manager/office")
public class SysOfficeController {

    @Autowired
    SysOfficeService service;

    @Autowired
    WasteService wasteService;

    @Autowired
    WasteTypeService wasteTypeService;


    @RequestMapping("list")
    public List<SysOffice> list() {
        return service.selectAll();
    }

    @RequestMapping("")
    public ModelAndView index() {
        return new ModelAndView("/office/office");
    }

    @RequestMapping("toList")
    public PageInfo<SysOffice> toList(@RequestBody Map<String, Object> params) {
        return service.selectByCondition(params);
    }


    @RequestMapping("toUpdate")
    public SysOffice toUpdate(long oid) {
        return service.selectByOid(oid);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage(){
        return new ModelAndView("/office/update");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody SysOffice sysOffice){
        int update = service.update(sysOffice);
        Result result = new Result();
        if(update>0){
            result.setMsg("更新成功");
            result.setSuccess(true);
        }
        return result;
    }


    @RequestMapping("selectWaste")
    public List<Waste> selectWaste(long selected ){
        Waste waste = new Waste();
        waste.setParentId(selected);
        return wasteService.select(waste);
    }

    @RequestMapping("selectWasteType")
    public List<WasteType> selectWasteType(){
        return wasteTypeService.selectAll();
    }
}
