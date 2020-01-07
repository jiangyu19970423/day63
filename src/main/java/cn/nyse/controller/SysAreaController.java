package cn.nyse.controller;

import cn.nyse.entity.Result;
import cn.nyse.entity.SysArea;
import cn.nyse.service.SysAreaService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2020/01/03/16:25
 * @Description:
 */
@RestController
@RequestMapping("manager/area")
public class SysAreaController {

    @Autowired
    SysAreaService service;

    @RequestMapping("")
    public ModelAndView index() {
        return new ModelAndView("/area/area");
    }

    /**
     * Excel下载操作:
     * 1.设置响应头
     * 2.设置文件乱码处理
     * 3.获取响应数据流
     * 4.写出到页面
     */
    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=sysArea.xls");
        OutputStream outputStream = response.getOutputStream();
        outputStream = service.writeExcel(outputStream);//响应流数据已经有文件信息
        MultipartFile file;
    }

    @RequestMapping("upload")
    public Result upload(MultipartFile upFile) throws IOException {
        Result result = new Result();
        int i = service.readExcel(upFile.getInputStream());
        if (i > 0) {
            result.setMsg("导入数据成功");
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping("toList")
    public PageInfo<SysArea> toList(@RequestBody Map<String, Object> params) {
        return service.selectByPage(params);
    }

    @RequestMapping("selectAll")
    public List<SysArea> selectAll() {
        return service.selectAll();
    }

    @RequestMapping("toUpdate")
    public SysArea toUpdate(long id) {
        return service.selectByAid(id);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/area/save");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody SysArea area) {
        Result result = new Result();

        int i = service.updateParentIds(area);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("更新成功");
        }
        return result;
    }

    @RequestMapping("awesome")
    public ModelAndView awesome(){
        return new ModelAndView("/modules/font-awesome");
    }

    @RequestMapping("delete")
    public Result delete(Integer id) {
        Result result = new Result();
        SysArea sysArea = new SysArea();
        sysArea.setDelFlag("1");
        sysArea.setUpdateDate(new Date());
        sysArea.setId((long)id);
        int i=service.updateByPrimaryKeySelective(sysArea);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("删除成功");
        }
        return result;
    }


}
