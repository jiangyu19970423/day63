package cn.nyse.controller;

import cn.nyse.entity.SysOffice;
import cn.nyse.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/office")
public class SysOfficeController {

    @Autowired
    SysOfficeService service;


    @RequestMapping("list")
    public List<SysOffice> list (){
        return service.selectAll();
    }


}
