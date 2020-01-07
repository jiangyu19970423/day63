package cn.nyse.mapper;

import cn.nyse.entity.SysArea;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

public class SysAreaProvider {


    /**
     * 批量插入
     */
    public String insertBath(@Param("areas") List<SysArea> areas) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `guguanjia`.`sys_area`( `parent_id`, `parent_ids`, `code`, `name`, `type`, `create_by`, " +
                "`create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `icon`) VALUES ");
        for (int i = 0; i < areas.size(); i++) {
            sb.append("(");

            sb.append("#{areas[" + i + "].parentId}, #{areas[" + i + "].parentIds}, #{areas[" + i + "].code}, #{areas[" + i + "].name}, " +
                    "#{areas[" + i + "].type}, #{areas[" + i + "].createBy}, #{areas[" + i + "].createDate}, #{areas[" + i + "].updateBy}, #{areas[" + i + "].updateDate}, #{areas[" + i + "].remarks}, #{areas[" + i + "].delFlag}, #{areas[" + i + "].icon}");

            sb.append("),");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();

    }



}
