package com.yxw.yxw_pay.mapper;

import com.yxw.entity.PaymentInfo;
import org.apache.ibatis.annotations.*;

/**
 * @Author:阿倪
 * @Date: 2019/4/21 12:19
 * @Description:
 * @return:
 * @throws:
 */
public interface PaymentInfoMapper {

    @Select("select * from payment_info where  id=#{id}")
    public PaymentInfo getPaymentInfo(@Param("id") Long id);

    @Insert("insert into payment_info ( id,userid,typeid,orderid,platformorderid,ordername,price,source,state,created,updated) value(null,#{userId},#{typeId},#{orderId},#{platformorderId},#{orderName},#{price},#{source},#{state},#{created},#{updated})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 添加该行，product中的id将被自动添加
    public Integer savePaymentType(PaymentInfo paymentInfo);

    @Select("select * from payment_info where  orderid=#{orderId}")
    public PaymentInfo getByOrderIdPayInfo(@Param("orderId") String orderId);

    @Update("update payment_info set state =#{state},payMessage=#{payMessage},platformorderId=#{platformorderId},ordername=#{orderName},updated=#{updated} where orderId=#{orderId} ")
    public Integer updatePayInfo(PaymentInfo paymentInfo);
}
