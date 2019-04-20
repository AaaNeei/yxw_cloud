package com.yxw.api.util;


import com.yxw.base.ResponseResultBase;
import com.yxw.entity.Student;
import com.yxw.util.MD5Util;
import com.yxw.util.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author:阿倪
 * @Date: 2019/3/23 9:58
 * @Description:
 * @return:
 * @throws:
 */
@Slf4j
@Service
public class EncodeAndDecode extends ResponseResultBase {

    /**
     * @param request redisType redisKeyName
     * @return 获取公钥
     */
    public String  getPublicKey(HttpServletRequest request, String redisType) {
        String publicKey = "";
        try {
            //获取公钥
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            redisUtil.setString(redisType + request.getSession().getId(), RSAUtils.getPrivateKey(keyMap));
        } catch (Exception e) {

        }
        return publicKey;
    }

    public void delPublicKey(HttpServletRequest request, String redisType) {
        try {
            redisUtil.delKey(redisType + request.getSession().getId());
        } catch (Exception e) {

        }

    }

    /**
     * 对前端password解密 在后端进行md5加盐加密
     *
     * @param student
     * @param request
     * @param redisType
     * @return
     */
    public Student decodePassword(Student student, HttpServletRequest request, String redisType) {
        //获取私钥
        String privateKey = redisUtil.getString(redisType + request.getSession().getId());
        //获取之后及时删除缓存
        redisUtil.delKey(redisType + request.getSession().getId());
        String password = student.getStuPassword();
        if (StringUtils.isEmpty(password)) {
            return null;
        }
        password = password.replaceAll("%2B", "+");
        //解密后的密码
        String decodePassword = "";
        try {
            byte[] decryptByPrivateKey = RSAUtils.decryptByPrivateKey(Base64Utils.decode(password.getBytes()), privateKey);
            decodePassword = new String(decryptByPrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("异常信息:{}", e.getMessage());
            //后期要全局日志补货
        }
        //后端用MD5再次 加盐加密 存储数据库
        String MD5Password = MD5Util.MD5(decodePassword);
        student.setStuPassword(MD5Password);
        return student;
    }

}
