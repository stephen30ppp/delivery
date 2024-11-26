package org.example.delivery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.delivery.Entity.DishFlavor;
import org.example.delivery.mapper.DishFlavorMapper;
import org.example.delivery.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>implements DishFlavorService {
}
