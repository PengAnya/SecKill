package com.lhj.miaosha.service;

import com.lhj.miaosha.domain.MiaoshaUser;
import com.lhj.miaosha.domain.OrderInfo;
import com.lhj.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：LHJ
 * @date ：2019/7/27 16:38
 * @description：${description}
 */
@Service
public class MiaoshaService {
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;
    //保证这三个操作，减库存 下订单 写入秒杀订单是一个事物
    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
        //减库存
        //boolean success = goodsService.reduceStock(goods);
        boolean success = goodsService.reduceStock(goods);

        if (success){
            //下订单 写入秒杀订单
            return orderService.createOrder(user, goods);
        }else {
//            setGoodsOver(goods.getId());
            return null;
        }
    }
}

//    @Transactional
//    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) {
//        //减库存
//        //boolean success = goodsService.reduceStock(goods);
//        goodsService.reduceStock(goods);
//
//        //if (success){
//        //下订单 写入秒杀订单
//        return orderService.createOrder(user, goods);
////        }else {
////            setGoodsOver(goods.getId());
////            return null;
////        }
//    }
//}