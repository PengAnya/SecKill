package com.lhj.miaosha.exception;

import com.lhj.miaosha.result.CodeMsg;

/**
 * @author ：LHJ
 * @date ：2019/7/25 22:40
 * @description：自定义全局异常类
 */
public class GlobalException extends RuntimeException {

    private static final long servialVersionUID = 1L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}

