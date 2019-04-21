package com.yxw.yxw_pc.fegin;

import com.yxw.aip.service.PayCallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 16:10
 * @Description:
 * @return:
 * @throws:
 */
@Component
@FeignClient("yxw-pay")
public interface PayCallBackServiceFegin extends PayCallBackService {
}
