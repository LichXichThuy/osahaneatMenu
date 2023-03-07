package com.cybersoft.osahaneat.Service.Imp;

import com.cybersoft.osahaneat.dto.FoodDto;
import com.cybersoft.osahaneat.entity.Food;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodServiceImp {
    boolean addFood(FoodDto foodDto, MultipartFile file);

    FoodDto loadFood(String name);
    List<FoodDto> loadAllFood();
}
