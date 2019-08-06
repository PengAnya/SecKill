package com.lhj.miaosha.redis;

/**
 * @author ：LHJ
 * @date ：2019/8/1 9:05
 * @description：${description}
 */
public class AccessKey extends BasePrefix{

    private AccessKey( int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }

}
