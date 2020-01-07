package cn.nyse.service.impl;

import cn.nyse.entity.WasteType;
import cn.nyse.service.IService;
import cn.nyse.service.WasteTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2020/01/06/19:06
 * @Description:
 */
@Service
@Transactional
public class WasteTypeServiceImpl extends ServiceImpl<WasteType> implements WasteTypeService {
}
