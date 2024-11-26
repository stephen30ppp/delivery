package org.example.delivery.dto;


import lombok.Data;
import org.example.delivery.Entity.Dish;
import org.example.delivery.Entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
