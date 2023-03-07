package com.cybersoft.osahaneat.Service;

import com.cybersoft.osahaneat.Service.Imp.FileStorageServiceImp;
import com.cybersoft.osahaneat.Service.Imp.FoodServiceImp;
import com.cybersoft.osahaneat.dto.FoodDto;
import com.cybersoft.osahaneat.entity.CategoryRestaurant;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService implements FoodServiceImp {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FileStorageServiceImp fileStorageServiceImp;

    // Thêm menu kèm theo hình ảnh
    @Override
    public boolean addFood(FoodDto foodDto, MultipartFile file) {
        CategoryRestaurant categoryRestaurant = new CategoryRestaurant();
        categoryRestaurant.setId(foodDto.getCate_res_id());

        Food food = new Food();
        food.setName(foodDto.getName());
        food.setDesc(foodDto.getDesc());
        food.setPrice(foodDto.getPrice());
        food.setCategoryRestaurant(categoryRestaurant);

        try{
            fileStorageServiceImp.saveFile(file);   // Lưu hình ảnh lên ổ cứng
            food.setIntruction(file.getOriginalFilename()); // Set tên file hình ảnh vào đối tượng
            foodRepository.save(food);  // Lưu đối tượng vào database
            return true;
        } catch (Exception e){
            System.err.println("Error query database + " + e.getMessage());
            return false;
        }
    }

    @Override
    public FoodDto loadFood(String name) {  // Lấy danh sách food theo tên truyền vao
        try {
            Food food = foodRepository.findByName(name);

            FoodDto foodDto = new FoodDto();
            foodDto.setName(food.getName());
            foodDto.setDesc(foodDto.getDesc());
            foodDto.setPrice(food.getPrice());
            foodDto.setIntruction(food.getIntruction());
            foodDto.setCate_res_id(foodDto.getCate_res_id());

            return foodDto;
        } catch (Exception e){
            System.err.println("Error query Sql FindByName + " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<FoodDto> loadAllFood() {
        List<Food> foodList = new ArrayList<>();
        foodList = foodRepository.findAll();

        CategoryRestaurant categoryRestaurant = new CategoryRestaurant();

        List<FoodDto> foodDtoList = new ArrayList<>();

        for (Food item:foodList){
            FoodDto foodDto = new FoodDto();
            foodDto.setName(item.getName());
            foodDto.setDesc(item.getDesc());
            foodDto.setPrice(item.getPrice());
            foodDto.setIntruction(item.getIntruction());

            categoryRestaurant = item.getCategoryRestaurant();
            foodDto.setCate_res_id(categoryRestaurant.getId());

            foodDtoList.add(foodDto);
        }
        return foodDtoList;
    }
}
