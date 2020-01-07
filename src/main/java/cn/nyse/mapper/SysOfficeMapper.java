package cn.nyse.mapper;

import cn.nyse.entity.SysOffice;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysOfficeMapper extends Mapper<SysOffice> {
    @SelectProvider(type = SysOfficeProvider.class,method = "selectByCondition")
    List<SysOffice> selectByCondition(Map<String, Object> params);

    @Select("select so.*,sa.name areaName from  " +
            " sys_office so,sys_area sa " +
            " where " +
            " so.id=#{oid} " +
            " and " +
            " so.del_flag=0 " +
            " and " +
            " so.area_id=sa.id ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "areaName",property = "areaName"),
            @Result(column = "id",property = "wastes",many = @Many(select = "cn.nyse.mapper.WasteMapper.selectByOid"))
    })
    SysOffice selectByOid(long oid);

    @Delete("delete from office_waste where office_id=#{id}")
    int deleteOfficeWaste(long id);

    @InsertProvider(type = SysOfficeProvider.class,method = "insertBathOfficeWaste")
    int insertBathOfficeWaste(@Param("id") long id,@Param("wasteIds") long[] wasteIds);
}