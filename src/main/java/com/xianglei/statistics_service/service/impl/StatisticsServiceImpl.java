package com.xianglei.statistics_service.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xianglei.statistics_service.common.Tools;
import com.xianglei.statistics_service.domain.*;
import com.xianglei.statistics_service.mapper.OrderMapper;
import com.xianglei.statistics_service.mapper.ParkMapper;
import com.xianglei.statistics_service.mapper.UserMapper;
import com.xianglei.statistics_service.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.util.function.Tuple4;

import java.util.*;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    static final String[] times = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09"
            , "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
    private Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);
    @Autowired
    UserMapper userMapper;
    @Autowired
    ParkMapper parkMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public Map<String, Integer> getDashBoardCount() {
        Map<String, Integer> map = null;
        try {
            map = new HashMap<>();
            List<BsUser> bsUsers = userMapper.selectList(null);
            // 所有用户
            int allUser = bsUsers.size();
            // 会员数
            int vipUser = 0;
            for (BsUser bsUser : bsUsers) {
                if (bsUser.getVip() == "0") {
                    vipUser++;
                }
            }
            // 停车场数
            List<BsPark> bsParks = parkMapper.selectList(null);
            int allPark = bsParks.size();
            // 订单数量
            List<BsOrder> bsOrders = orderMapper.selectList(null);
            int allOrder = bsOrders.size();
            map.put("userCount", allUser);
            map.put("vipCount", vipUser);
            map.put("parkCount", allPark);
            map.put("orderCount", allOrder);
        } catch (Exception e) {
            logger.error("统计预览报错", e);
        }
        return map;
    }

    @Override
    public Map<String, Integer> getPieChartCount() {
        Map<String, Integer> map = null;
        try {
            map = new HashMap<>();
            // 男女
            Integer manCount = userMapper.selectCount(new QueryWrapper<BsUser>().eq("SEXY", 0));
            Integer womanCount = userMapper.selectCount(new QueryWrapper<BsUser>().eq("SEXY", 1));
            // 会员数
            Integer vipCount = userMapper.selectCount(new QueryWrapper<BsUser>().eq("VIP", 0));
            Integer noVipCount = userMapper.selectCount(new QueryWrapper<BsUser>().eq("VIP", 1));
            map.put("manCount", manCount);
            map.put("womanCount", womanCount);
            map.put("vipCount", vipCount);
            map.put("noVipCount", noVipCount);
        } catch (Exception e) {
            logger.error("饼状图数据报错", e);
        }
        return map;
    }

    @Override
    public Map<String, Integer> getParkUseCount() {
        Map<String, Integer> map = null;
        Date date = new Date();
        try {
            map = new HashMap<>();
            List<BsPark> bsParks = parkMapper.selectList(null);
            int inUseCount = 0;
            int noInUseCount = 0;
            int sum = 0;
            for (BsPark bsPark : bsParks) {
                Integer volume = bsPark.getVolume();
                sum = sum + volume;
            }
            List<BsOrder> bsOrders = orderMapper.selectList(null);
            for (BsOrder bsOrder : bsOrders) {
                Date startTime = bsOrder.getStartTime();
                Date leaveTime = bsOrder.getLeaveTime();
                if (date.after(startTime) && date.before(leaveTime)) {
                    inUseCount++;
                }
            }
            noInUseCount = sum - inUseCount > 0 ? sum - inUseCount : 0;
            map.put("inUseCount", inUseCount);
            map.put("noInUseCount", noInUseCount);
        } catch (Exception e) {
            logger.error("车位使用量获取错误", e);
        }
        return map;
    }

    @Override
    public List<Park> getParkTop10() {
        List<Park> list = null;
        try {
            List<Park> parkList = orderMapper.findParkList();
            int size = parkList.size();
            if (size > 10) {
                parkList.subList(0, 10);
            } else {
                // 补充
                for (int i = 0; i < 10 - size; i++) {
                    Park park = new Park();
                    park.setParkName("");
                    parkList.add(park);
                }
            }
            list = parkList;
        } catch (Exception e) {
            logger.error("饼状图数据报错", e);
        }
        return list;
    }

    @Override
    public List<Order> getOrderNumsEveryHour() {
        List<Order> list = new ArrayList<>(16);
        // 获取所有订单
        List<BsOrder> bsOrders = orderMapper.selectList(null);
        // 循环24次
        for (String time : times) {
            int nums = 0;
            Order order = new Order();
            order.setTime(time);
            // 循环所有订单
            for (BsOrder bsOrder : bsOrders) {
                Date createTime = bsOrder.getCreateTime();
                String format = DateUtil.format(createTime, "HH:mm:ss");
                if (format.indexOf(time) == 0) {
                    nums++;
                }
            }
            order.setNums(nums);
            list.add(order);
        }

        return list;
    }

    @Override
    public List<Last10DayOrder> getLast10DayOrderNums() {
        List<Last10DayOrder> list = new ArrayList<>();
        // 获取所有订单 按照时间倒叙
        List<BsOrder> bsOrders = orderMapper.selectList(new QueryWrapper<BsOrder>().orderByDesc("CREATE_TIME"));
        int size = bsOrders.size();
        if (size > 10) {
            bsOrders.subList(0, 10);
        } else {
            // 补充
            for (int i = 0; i < 10 - size; i++) {
                bsOrders.add(new BsOrder());
            }
        }
        Last10DayOrder last10DayOrder = null;
        int nan = 0;
        int nv = 0;
        int sum = 0;
        for (BsOrder bsOrder : bsOrders) {
            last10DayOrder = new Last10DayOrder();
            String userId = bsOrder.getUserId();
            BsUser bsUser = userMapper.selectOne(new QueryWrapper<BsUser>().eq("FLOW_ID", userId));
            if (Tools.isNotNull(bsUser)) {
                String sexy = bsUser.getSexy();
                if ("0".equals(sexy)) {
                    nan++;
                } else {
                    nv++;
                }
            }
            sum = nan + nv;
            last10DayOrder.setManOrder(nan);
            last10DayOrder.setWomanOrder(nv);
            last10DayOrder.setSumOrder(sum);
            list.add(last10DayOrder);
            nan = 0;
            nv = 0;
            sum = 0;
        }
        return list;
    }
}
