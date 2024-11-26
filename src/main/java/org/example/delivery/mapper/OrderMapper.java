package org.example.delivery.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.delivery.Entity.Orders;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}