package com.lhj.miaosha.service;


import com.lhj.miaosha.dao.GoodsDao;
import com.lhj.miaosha.domain.MiaoshaGoods;
import com.lhj.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author ：LHJ
 * @date ：2019/7/25 18:50
 * @description：${description}
 */
@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;
    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }


    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public boolean reduceStock(GoodsVo goods) {
        MiaoshaGoods g = new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        return goodsDao.reduceStock(g) == 1 ? true : false;

    }
//    public void reduceStock(GoodsVo goods) {
//        MiaoshaGoods g = new MiaoshaGoods();
//        g.setGoodsId(goods.getId());
//        goodsDao.reduceStock(g);
//        System.out.println("减库存");
//    }
}
