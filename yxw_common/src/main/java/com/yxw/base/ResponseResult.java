package com.yxw.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author:阿倪
 * @Date: 2019/4/18 16:34
 * @Description: 游学网公共返回对象
 * @return:
 * @throws:
 */
@Getter
@Setter
public class ResponseResult implements Serializable {
    private static final long serialVersionUID = 987862342355698123L;
    /**
     * 返回状态码
     */
    private String resultCode;
    /**
     * 返回状态信息
     */
    private String resultMsg;
    /**
     * 返回数据
     */
    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(String resultCode, String resultMsg, Object data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "resultCode='" + resultCode + '\'' +
                ", resultMsg='" + resultMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
