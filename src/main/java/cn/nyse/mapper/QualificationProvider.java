package cn.nyse.mapper;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/25/15:55
 * @Description:
 */
public class QualificationProvider {

    /**
     * start:'' ,  end:'' ,type:'', check:''
     *
     * @param params
     * @return
     */
    public String selectByCondition(Map<String, Object> params) {

        StringBuilder sb = new StringBuilder();
        sb.append("select qu.*,uu.name uploadUser, cu.name checkUser from qualification qu  left join sys_user uu  on qu.upload_user_id=uu.id left join sys_user cu on qu.check_user_id=cu.id where qu.del_flag=0 ");
        if (params.containsKey("type") && !StringUtils.isEmpty(params.get("type"))) {
            sb.append(" and qu.type=#{type}");
        }
        if (params.containsKey("check") && !StringUtils.isEmpty(params.get("check"))) {
            sb.append(" and qu.`check`=#{check}");
        }
        if (params.containsKey("start") && !StringUtils.isEmpty(params.get("start"))) {
            sb.append(" and qu.create_date>#{start} ");
        }
        if (params.containsKey("end") && !StringUtils.isEmpty(params.get("end"))) {
            sb.append(" and qu.create_date<#{end} ");
        }
        return sb.toString();
    }
}
