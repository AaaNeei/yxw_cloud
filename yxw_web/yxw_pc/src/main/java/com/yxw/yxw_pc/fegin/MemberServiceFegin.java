package com.yxw.yxw_pc.fegin;


import com.yxw.api.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Author:阿倪
 * @Date: 2019/4/19 21:26
 * @Description:  member工程fegin实现分布式调用
 * @return:
 * @throws:
 */
@Component
@FeignClient("yxw-member")
public interface MemberServiceFegin extends MemberService {
}
