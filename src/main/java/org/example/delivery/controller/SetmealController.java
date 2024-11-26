package org.example.delivery.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.delivery.Entity.Category;
import org.example.delivery.Entity.Setmeal;
import org.example.delivery.common.R;
import org.example.delivery.dto.SetmealDto;
import org.example.delivery.service.CategoryService;
import org.example.delivery.service.SetmealDishService;
import org.example.delivery.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SetmealService setmealService;
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        log.info("套餐信息:{}",setmealDto);
        setmealService.saveWithDish(setmealDto);

        return R.success("保存成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page<Setmeal> pageInfo=new Page<>(page,pageSize);
        Page<SetmealDto> dtoPage=new Page<>();
        LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(name!=null,Setmeal::getName,name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        List<SetmealDto>list=null;
        setmealService.page(pageInfo,queryWrapper);
        BeanUtils.copyProperties(pageInfo,dtoPage,"records");
        List<Setmeal> records=pageInfo.getRecords();
        records.stream().map((item)->{
            SetmealDto setmealDto=new SetmealDto();
            Long categoryId=item.getCategoryId();
            Category category=categoryService.getById(categoryId);
            if (category!=null){
                String categoryName=category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());

        dtoPage.setRecords(list);
        return R.success(pageInfo);
    }
    @DeleteMapping
    public R<String>delete(@RequestParam List<Long> ids){
        log.info("ids:{}",ids);
        setmealService.removeDish(ids);
        return R.success("套餐数据删除成功");
    }

}
