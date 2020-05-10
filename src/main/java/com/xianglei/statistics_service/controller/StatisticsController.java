package com.xianglei.statistics_service.controller;

import com.xianglei.statistics_service.common.BaseJson;
import com.xianglei.statistics_service.common.Tools;
import com.xianglei.statistics_service.domain.Last10DayOrder;
import com.xianglei.statistics_service.domain.Order;
import com.xianglei.statistics_service.domain.Park;
import com.xianglei.statistics_service.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/back")
public class StatisticsController {


    @Autowired
    private StatisticsService statisticsService;

    /**
     * 获取数据预览
     * 总用户数
     * 会员数
     * 停车场数
     * 订单数
     *
     * @return
     */
    @RequestMapping("/dashboard")
    public BaseJson getDashBoardCount() {
        BaseJson baseJson = new BaseJson(false);
        Map<String, Integer> map = statisticsService.getDashBoardCount();
        if (Tools.isNotNull(map)) {
            baseJson.setData(map);
            baseJson.setCode(200);
            baseJson.setStatus(true);
            baseJson.setMessage("获取真实成功");
        } else {
            map = new HashMap<>();
            map.put("userCount", 18);
            map.put("vipCount", 14);
            map.put("parkCount", 21);
            map.put("orderCount", 74);
            baseJson.setData(map);
            baseJson.setCode(200);
            baseJson.setStatus(true);
            baseJson.setMessage("获取模拟值成功");
        }
        return baseJson;
    }

    /**
     * 男女分布饼状图
     *
     * @return
     */
    @RequestMapping("/getPieChart")
    public BaseJson getPieChartCount() {
        BaseJson baseJson = new BaseJson(false);
        Map<String, Integer> map = statisticsService.getPieChartCount();
        if (Tools.isNotNull(map)) {
            baseJson.setData(map);
            baseJson.setCode(200);
            baseJson.setStatus(true);
            baseJson.setMessage("获取真实成功");
        } else {
            map = new HashMap<>();
            map.put("manCount", 20);
            map.put("womanCount", 17);
            map.put("vipCount", 15);
            map.put("noVipCount", 22);
            baseJson.setData(map);
            baseJson.setCode(200);
            baseJson.setStatus(true);
            baseJson.setMessage("获取模拟值成功");
        }
        return baseJson;
    }

    /**
     * 停车场销量前十 柱状图
     *
     * @return
     */
    @RequestMapping("/getParkUseCount")
    public BaseJson getParkUseCount() {
        BaseJson baseJson = new BaseJson(false);
        Map<String, Integer> map = statisticsService.getParkUseCount();
        if (Tools.isNotNull(map)) {
            baseJson.setData(map);
            baseJson.setCode(200);
            baseJson.setStatus(true);
            baseJson.setMessage("获取真实成功");
        } else {
            map = new HashMap<>();
            map.put("inUseCount", 18);
            map.put("noInUseCount", 74);
            baseJson.setData(map);
            baseJson.setCode(200);
            baseJson.setStatus(true);
            baseJson.setMessage("获取模拟值成功");
        }
        return baseJson;
    }

    /**
     * 停车场销量前十 柱状图
     *
     * @return
     */
    @RequestMapping("/getParkTop10")
    public BaseJson getParkTop10() {
        BaseJson baseJson = new BaseJson(false);
        List<Park> map = statisticsService.getParkTop10();
        if (Tools.isNotNull(map)) {
            baseJson.setData(map);
            baseJson.setCode(200);
            baseJson.setStatus(true);
            baseJson.setMessage("获取真实成功");
        }
        return baseJson;
    }

    /**
     * 各个时段下单量
     *
     * @return
     */
    @RequestMapping("/getOrderNumsEveryHour")
    public BaseJson getOrderNumsEveryHour() {
        BaseJson baseJson = new BaseJson(false);
        List<Order> map = statisticsService.getOrderNumsEveryHour();
        if (Tools.isNotNull(map)) {
            baseJson.setData(map);
            baseJson.setCode(200);
            baseJson.setStatus(true);
            baseJson.setMessage("获取真实成功");
        }
        return baseJson;
    }

    /**
     * 最近十天男女总订单量
     *
     * @return
     */
    @RequestMapping("/getLast10DayOrderNums")
    public BaseJson getLast10DayOrderNums() {
        BaseJson baseJson = new BaseJson(false);
        List<Last10DayOrder> last10DayOrderNums = statisticsService.getLast10DayOrderNums();
        if (Tools.isNotNull(last10DayOrderNums)) {
            baseJson.setData(last10DayOrderNums);
            baseJson.setCode(200);
            baseJson.setStatus(true);
            baseJson.setMessage("获取真实成功");
        }
        return baseJson;
    }

}
