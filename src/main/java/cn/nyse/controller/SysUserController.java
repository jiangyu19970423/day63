package cn.nyse.controller;

import cn.nyse.entity.SysUser;
import cn.nyse.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2020/01/07/16:27
 * @Description:
 */
@RestController
@RequestMapping("manager/sysuser")
public class SysUserController {

    @Autowired
    SysUserService service;

    @RequestMapping("")
    public ModelAndView user(){
        return new ModelAndView("/user/user");
    }

    @RequestMapping("toList")
    public PageInfo<SysUser> toList(@RequestBody Map<String,Object> params){
        return service.selectByCondition(params);
    }


    /**
     * 根据传入的角色id查询已经分配该角色的用户信息
     * @param rid
     * @return
     */
    @RequestMapping("selectByRid")
    public List<SysUser> selectByRid(long rid){
        return service.selectByRid(rid);
    }


    @RequestMapping("selectNoRole")
    public List<SysUser> selectNoRole(long rid,long oid){
        return service.selectNoRole(rid,oid);
    }
}
