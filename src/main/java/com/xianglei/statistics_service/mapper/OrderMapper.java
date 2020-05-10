package com.xianglei.statistics_service.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xianglei.statistics_service.domain.BsOrder;
import com.xianglei.statistics_service.domain.Park;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: Xianglei
 * @Company: xxx
 * @Date: 2020/4/17 13:57
 * com.xianglei.reserve_service.mapper
 * @Description:
 */
@Repository
public interface OrderMapper extends BaseMapper<BsOrder> {

    @Select(" SELECT COUNT(1) nums,b.`PARK_NAME` parkName FROM BS_ORDER a INNER JOIN BS_PARK  b ON a.`PARK_ID`=b.`FLOW_ID` GROUP BY a.`PARK_ID` ORDER BY nums")
    List<Park> findParkList();
}
