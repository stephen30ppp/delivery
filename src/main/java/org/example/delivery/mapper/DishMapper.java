package org.example.delivery.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.delivery.Entity.Dish;
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
