package com.yxw.yxw_pay.pay.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 12:58
 * @Description:
 * @return:
 * @throws:
 */
public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092800612294";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPRXnBUTpT3fMYTqMnqoSrHn6yGl8a7Jy5Qugwatlv8s7fAxmz1dXCNqACCt191nrzoho05GT+ukYv4Lb7MKFEOnkSWuMHbBg8GYOnyNdbPmxqVEiqhssHjIQi3FplUnu2qIAEK2xVzeDNLPuuGbB2OEgucFTAVXRU9dJpF2vqDMBmR7s3PNZt0axUPyS8cfQZYumnIqsWPHtZgkQGto8GNKcM0OKhIqU4yeLaf6UhgZAwX97KIWnrMKQGiIpth4XkCZg2rrfw5z+pRhtc4XiIfG1AGQJ/qTrQdC4SujIQPLr2QFMrGydgnWdh3TM+XeuAfi7nWSbB2iLvcP0NSt8tAgMBAAECggEAZUsqhQon4em3GIVohv/hIpbB3qwUd5W4TKdIQAsbRjH2HugzQNk7cz9pq2gBJxn//carHqAn44zRWMJYvImqSvHIbbjCcJn1HHCxi2vNDt64igijA6WfX+vx9C+03ZQOkZraP5hv1M5lVMqr+nCBOWQbbHHRCdahE/xk0hZUHrno//NM3YHI49cGqveXJ8yAUZIHygWy0WIXNCQ/qPcG4FAJCiHOb/3ggrsk0pjT5niRIDLjKFMnFVQj5Y2JE9nBrJHk4I38M+03wBGP2ayJAkdyPWJPbXVduS73EQDx8kIhZoHoF+UXJ5nyCV/OuMvUATWwoC57qKje3+R1leRcYQKBgQDgnzJUI5ZTDU7dvls3qeWUvjB3GiimvHYVaAn08tidr46Z3MCQpOe4b4I8Kw1AmeuYaEbronNcOoL8eBAe/GiDFVPWXv3O1HqVp7YzJao0rjl8q5w4R3kyBNu1Ml0f5C8tpwreOKkLcdlAC2Xx8Uwibkk0jbgKsWHvsg+eT+AqZQKBgQCjSRF0C4IwlOp2v9h6RbHqAVelKi0c5ztJeMNgvszxeB0rDKIFm+q+X4AhQ5Wmfwqm9qgKEkp9tkYe4wLiHzKYGJ67bPGTiPuFEILRoNW2U2CqtN8yRzu5ZfWzBuq0Q0pjsn8eOmFGM1M92xad+rHKPJc8L75tEiEvLLh/E1/xKQKBgQDFoYwol38l56miVF/xo9xJeOUvhaaKvXqv7vADwr9wTgyLt7z+BvmSPxo5Ui/AzSyK1gTAQhh550lkVUbp3G8gFl3DWaS6nByXHA6lBdixrTELTfqPUeOCbN/al0t0SdJwum5Hg1bIqQza2qyBqSqxiJOEV8S8M8HVobc4NVMoOQKBgBB/q5HML+V0xpQlIlyIekeoxxBbSJ+pBaaQ2z0FSnQDrP7SkfR7IqcFw06ZI5/lKlBgj+4klv5I0qEAUyRB/ry7kB5kDazYpIF3f6Bq3e1EU3osOHk+FVzQXpkuU7zPJ88XFB0bzQRo3zpaztg7oodKxAVC1bzzngLERAgjDwixAoGACmpsnGrN7+Qt69VrxoijKg7oNZb4sL2+Xpzy+PxmbFC+iV4otgIYTilyGckw50e0taVsaqqMUrP11kQ3/SYmitAwWJdfbL5lH6w5deRyGV+UNod6FYqc9UvRI82ZH/Rhn57pkzFg5nnj7eRb3Q4nLWmz34EOFshW+2hJc3YIkVw=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtVDcK/vxvgkH3R+q5SztsDe6trv+TMLq9ePeO4gSMnJjsE/LZxUsG1axmO4pWFaJ+PIiIrAYoGapkSRjMd1G3hOe6ID07q14Y/t+Cii4TV8hrwPU+iQpfQUdNrrmFnu02ZVUoownSJt31Fk75glYZ7VVW4T4sx0Bbj1XOuAXjbKcI2MGuc7yKwYpU4S0J+tJtp+ADX6S26sgv5kwHuR3hKMt42WWq6x4xXJp5XjXEiocMx/dpAf6XTrdbLlvfAoNpT2ypt1n0tUOeuxh1jmK7q+4MANlwiC9L27pxaXAjgAjxKktKIzhgDrCg1XFSlyMNS3LqAtJ9n5bJPo4s8ms6QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://yxw.tunnel.qydev.com/pay/asynCallBack";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://yxw.tunnel.qydev.com/pay/synCallBack";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
