package com.juddar.strategy;

/**
 * @Description
 * @Author dasongju
 * @Date 2018/10/29 下午2:46
 */
@PriceRegion(min=20000,max=30000)
public class SuperVip implements CalPrice {
    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice * 0.8;
    }

}
