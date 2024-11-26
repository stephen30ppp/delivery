package org.example.delivery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.delivery.Entity.Setmeal;
import org.example.delivery.dto.SetmealDto;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);
    public void removeDish(List<Long>ids);
}
