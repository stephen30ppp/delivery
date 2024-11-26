package org.example.delivery.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.delivery.Entity.Category;
import org.example.delivery.Entity.Dish;
import org.example.delivery.Entity.Setmeal;
import org.example.delivery.common.CustomerException;
import org.example.delivery.mapper.CategoryMapper;
import org.example.delivery.service.CategoryService;
import org.example.delivery.service.DishService;
import org.example.delivery.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category>implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish>dishLambdaQueryWrapper=new LambdaQueryWrapper<>();
        //query based on id
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        long count = dishService.count(dishLambdaQueryWrapper);
        if (count>0){
            //alredy connect pull a exception
             throw new CustomerException("当前分类下关联了菜品，不能删除");
        }
        LambdaQueryWrapper<Setmeal>setmealLambdaQueryWrapper=new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        long count2 = setmealService.count();
        if (count2>0){
            throw new CustomerException("当前分类关联了套餐，不能删除");
        }
        super.removeById(id);
    }
}
