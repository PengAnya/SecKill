package com.lhj.miaosha.access;

import com.lhj.miaosha.domain.MiaoshaUser;

/**
 * @author ：LHJ
 * @date ：2019/8/1 10:51
 * @description：${description}
 */
public class UserContext {

    private static ThreadLocal<MiaoshaUser> userThreadLocal = new ThreadLocal<MiaoshaUser>();

    public static void setUser(MiaoshaUser user){
        userThreadLocal.set(user);
    }
    public static MiaoshaUser gerUser(){
        return userThreadLocal.get();
    }
}
