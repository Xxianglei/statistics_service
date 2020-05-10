package com.xianglei.statistics_service.domain;

/**
 * @Auther: Xianglei
 * @Company: xxx
 * @Date: 2020/5/10 16:59
 * com.xianglei.statistics_service.domain
 * @Description:
 */
public class Last10DayOrder {
    int manOrder;
    int womanOrder;
    int sumOrder;

    public int getManOrder() {
        return manOrder;
    }

    public void setManOrder(int manOrder) {
        this.manOrder = manOrder;
    }

    public int getWomanOrder() {
        return womanOrder;
    }

    public void setWomanOrder(int womanOrder) {
        this.womanOrder = womanOrder;
    }

    public int getSumOrder() {
        return sumOrder;
    }

    public void setSumOrder(int sumOrder) {
        this.sumOrder = sumOrder;
    }
}
