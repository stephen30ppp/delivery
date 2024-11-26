package org.example.delivery.dto;


import lombok.Data;
import org.example.delivery.Entity.Setmeal;
import org.example.delivery.Entity.SetmealDish;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
