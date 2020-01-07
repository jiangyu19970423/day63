package cn.nyse.service.impl;

import cn.nyse.entity.Waste;
import cn.nyse.service.WasteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2020/01/06/19:44
 * @Description:
 */
@Service
@Transactional
public class WasteServiceImpl extends ServiceImpl<Waste> implements WasteService {
}
