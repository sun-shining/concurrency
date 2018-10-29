package com.juddar.strategy;

/**
 * @Description
 * @Author dasongju
 * @Date 2018/10/29 下午2:46
 */
@PriceRegion(min=10000,max = 20000)
public class Vip implements CalPrice {
    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice * 0.9;
    }

}
