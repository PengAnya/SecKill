package com.lhj.miaosha.controller;

import com.lhj.miaosha.domain.MiaoshaOrder;
import com.lhj.miaosha.domain.MiaoshaUser;
import com.lhj.miaosha.domain.OrderInfo;
import com.lhj.miaosha.redis.RedisService;
import com.lhj.miaosha.result.CodeMsg;
import com.lhj.miaosha.result.Result;
import com.lhj.miaosha.service.GoodsService;
import com.lhj.miaosha.service.MiaoshaService;
import com.lhj.miaosha.service.MiaoshaUserService;
import com.lhj.miaosha.service.OrderService;
import com.lhj.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：LHJ
 * @date ：2019/7/26 18:00
 * @description：${description}
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;


    @RequestMapping(value = "/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public Result<OrderInfo> miaosha(Model model, MiaoshaUser user,
                                  @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if(user == null){
            return Result.error(CodeMsg.SESSION_ERROR);//return "login";
        }

        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if(stock <=0){
            return Result.error(CodeMsg.MIAOSHA_OVER);
            //model.addAttribute("errmsg",CodeMsg.MIAOSHA_OVER.getMsg());
            //return Result.error(CodeMsg.MIAOSHA_OVER);//return "miaosha_fail";
        }

        //判断是否重复秒杀到
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if(order != null){
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
            //model.addAttribute("errmsg",CodeMsg.REPEATE_MIAOSHA.getMsg());
            //return "miaosha_fail";
        }

        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        //model.addAttribute("orderInfo",orderInfo);
        //model.addAttribute("goods",goods);
        //return "order_detail";
        return Result.success(orderInfo);
    }



}
