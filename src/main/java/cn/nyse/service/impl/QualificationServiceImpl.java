package cn.nyse.service.impl;

import cn.nyse.entity.Qualification;
import cn.nyse.mapper.QualificationMapper;
import cn.nyse.service.QualificationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2019/12/25/16:10
 * @Description:
 */
@Service
@Transactional
public class QualificationServiceImpl extends ServiceImpl<Qualification> implements QualificationService {
    @Autowired
    QualificationMapper qualificationMapper;

    @Override
    public PageInfo<Qualification> selectPage(Map<String, Object> params) {
        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<Qualification> list = qualificationMapper.selectByCondition(params);
        return new PageInfo<>(list);
    }

    @Override
    public long selectOfficeId(long oid) {
        return qualificationMapper.selectOfficeId(oid);
    }
}
