package com.juddar.strategy;

/**
 * @Description
 * @Author dasongju
 * @Date 2018/10/29 下午2:45
 */
@PriceRegion(max = 10000)
public class Orgnic implements CalPrice{

    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice;
    }
}
