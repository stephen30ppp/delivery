package org.example.delivery.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.delivery.Entity.ShoppingCart;
import org.example.delivery.mapper.ShoppingCartMapper;
import org.example.delivery.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
