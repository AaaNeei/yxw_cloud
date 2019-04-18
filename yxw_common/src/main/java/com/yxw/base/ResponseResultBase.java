package com.yxw.base;

/**
 * @Author:阿倪
 * @Date: 2019/4/18 16:40
 * @Description: 返回对象包装类
 * @return:
 * @throws:
 */
public class ResponseResultBase {


    public ResponseResult setErrorResult() {
        return setResult(ResultBase.YXW_RESULT_ERROR_CODE, ResultBase.YXW_RESULT_ERROR_MSG, null);
    }

    public ResponseResult setErrorResult(String msg) {
        return setResult(ResultBase.YXW_RESULT_ERROR_CODE, msg, null);
    }

    /**
     * 返回成功但没有data数据
     *
     * @return
     */
    public ResponseResult setSuccessResult() {
        return setResult(ResultBase.YXW_RESULT_SUCCESS_CODE, ResultBase.YXW_RESULT_SUCCESS_MSG, null);
    }

    public ResponseResult setSuccessResult(String msg) {
        return setResult(ResultBase.YXW_RESULT_SUCCESS_CODE, msg, null);
    }

    /**
     * 返回成功有data数据
     *
     * @param data
     * @return
     */
    public ResponseResult setSuccessResult(Object data) {
        return setResult(ResultBase.YXW_RESULT_SUCCESS_CODE, ResultBase.YXW_RESULT_SUCCESS_MSG, data);
    }

    public ResponseResult setSuccessResult(String msg, Object data) {
        return setResult(ResultBase.YXW_RESULT_SUCCESS_CODE, msg, data);
    }


    /**
     * 设置返回结果
     *
     * @param resultCode
     * @param resultMsg
     * @param data
     * @return
     */
    public ResponseResult setResult(String resultCode, String resultMsg, Object data) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setResultCode(resultCode);
        responseResult.setResultMsg(resultMsg);
        if (data != null) {
            responseResult.setData(data);
        }
        return responseResult;
    }

}
