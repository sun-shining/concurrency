package com.juddar.strategy;

/**
 * @Description
 * @Author dasongju
 * @Date 2018/10/29 下午2:47
 */
@PriceRegion(min=30000)
public class GoldVip implements CalPrice {
    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice * 0.7;
    }

}
