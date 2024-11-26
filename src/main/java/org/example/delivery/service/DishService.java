package org.example.delivery.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.delivery.Entity.Dish;
import org.example.delivery.dto.DishDto;

public interface DishService extends IService<Dish> {
    public void updateWithFlavor(DishDto dishDto);

    void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);
}
