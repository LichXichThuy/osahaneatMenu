package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.Service.Imp.FileStorageServiceImp;
import com.cybersoft.osahaneat.Service.Imp.FoodServiceImp;
import com.cybersoft.osahaneat.dto.FoodDto;
import com.cybersoft.osahaneat.payload.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    FileStorageServiceImp fileStorageServiceImp;

    @Autowired
    FoodServiceImp foodServiceImp;

    @PostMapping("")    // Thêm menu kèm theo hình ảnh
    public ResponseEntity<?> addMenu(
            FoodDto foodDto,    // Truyền đối tượng gồm name, description, price, cate_res_id
            MultipartFile file) // Truyền file hình ảnh
    {
        ResponseData responseData = new ResponseData();
        responseData.setData(foodServiceImp.addFood(foodDto, file));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/load")   // Lấy danh sách food theo tên truyền vao
    public ResponseEntity<?> loadMenu(@RequestParam String name){
        ResponseData responseData = new ResponseData();
        responseData.setData(foodServiceImp.loadFood(name));

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/load/all")    // Lấy danh sách toàn bộ food
    public ResponseEntity<?> loadAllMenu(){
        ResponseData responseData = new ResponseData();
        responseData.setData(foodServiceImp.loadAllFood());

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downMenu(@PathVariable String fileName){
        Resource file = fileStorageServiceImp.downloadFile(fileName);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(file);
    }
}
