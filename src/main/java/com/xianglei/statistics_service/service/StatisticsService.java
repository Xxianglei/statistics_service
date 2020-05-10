package com.xianglei.statistics_service.service;

import com.xianglei.statistics_service.domain.Last10DayOrder;
import com.xianglei.statistics_service.domain.Order;
import com.xianglei.statistics_service.domain.Park;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
    /**
     * 获取预览值
     * @return
     */
    Map<String, Integer> getDashBoardCount();

    /**
     * 饼图
     * @return
     */
    Map<String, Integer> getPieChartCount();

    /**
     * 商圈车位使用量和剩余量
     * @return
     */
    Map<String, Integer> getParkUseCount();

    /**
     * 获取总销量前10
     * @return
     */
    List<Park>getParkTop10();

    /**
     * 获取每个时段的下单总量
     * @return
     */
    List<Order> getOrderNumsEveryHour();

    /**
     * 获取最近10天男女总下单量
     * @return
     */
    List<Last10DayOrder> getLast10DayOrderNums();

}
