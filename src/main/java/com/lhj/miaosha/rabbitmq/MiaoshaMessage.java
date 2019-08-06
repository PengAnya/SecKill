package com.lhj.miaosha.rabbitmq;

import com.lhj.miaosha.domain.MiaoshaUser;

/**
 * @author ：LHJ
 * @date ：2019/7/31 9:56
 * @description：${description}
 */
public class MiaoshaMessage {
    private MiaoshaUser user;
    private long goodsId;

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
