package org.example.delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.delivery.Entity.Setmeal;
import org.example.delivery.Entity.SetmealDish;
import org.example.delivery.common.CustomerException;
import org.example.delivery.dto.SetmealDto;
import org.example.delivery.mapper.SetmealMapper;
import org.example.delivery.service.SetmealDishService;
import org.example.delivery.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceimpl extends ServiceImpl<SetmealMapper, Setmeal>implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishServiceService;
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
              this.save(setmealDto);
        List<SetmealDish> setmealDishes=setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishServiceService.saveBatch(setmealDishes);
    }

    @Transactional
    public void removeDish(List<Long> ids) {
        //select count(*) from setmeal where id in (1,2,3) and status=1
        //查询套餐条件
        LambdaQueryWrapper<Setmeal>queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);
        Long count=this.count(queryWrapper);
        if (count>0){
            throw new CustomerException("套餐正在售卖中，不能删除");
        }
        this.removeByIds(ids);
        LambdaQueryWrapper<SetmealDish>lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);


        setmealDishServiceService.remove(lambdaQueryWrapper);

    }
}
