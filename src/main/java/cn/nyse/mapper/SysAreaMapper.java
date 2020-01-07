package cn.nyse.mapper;

import cn.nyse.entity.SysArea;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysAreaMapper extends Mapper<SysArea> {
    @InsertProvider(type = SysAreaProvider.class,method = "insertBath")
    int insertBath(@Param("areas") List<SysArea> areas);

    /**
     * 根据父区域id查找所有区域
     * @return
     */
    @Select("select sub.*,parent.name parentName " +
            "from " +
            "sys_area sub,sys_area parent " +
            "where " +
            "sub.parent_ids like concat('%',#{aid},'%') " +
            "and " +
            "sub.parent_id=parent.id " +
            "and " +
            "sub.del_flag=0")
    List<SysArea> selectByPid(Long aid);

    @Select("select  " +
            " sub.*,parent.name parentName " +
            "from " +
            " sys_area sub,sys_area parent " +
            "where " +
            " sub.parent_id=parent.id " +
            "and " +
            " sub.id=#{id}")
    SysArea selectByAid(Long id);

    @Update("update " +
            " sys_area " +
            "set " +
            " parent_ids=REPLACE(parent_ids,#{oldParentIds},#{parentIds}) " +
            "where " +
            " parent_ids like concat('%,',#{id},',%') ")
    int updateParentIds(SysArea sysArea);

}
