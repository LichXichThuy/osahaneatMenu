package com.cybersoft.osahaneat.Service;

import com.cybersoft.osahaneat.Service.Imp.FileStorageServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService implements FileStorageServiceImp {

    @Value("${file.upload}")
    private String path;

    private Path root;

    @Override
    public boolean saveFile(MultipartFile file) {
        try {
            init();
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
            return true;
        } catch (Exception e){
            System.err.println("Error save file + " + e.getMessage());
            return false;
        }
    }

    private void init(){
        root = Paths.get(path);

        if (!Files.exists(root)){
            try {
                Files.createDirectories(root);
            } catch (Exception e){
                System.err.println("Error create root folder + " + e.getMessage());
            }
        }
    }

    public Resource downloadFile(String fileName){
        try {
            root = Paths.get(path);
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (Exception e) {
            System.err.println("Error upload file + " + e.getMessage());
            return null;
        }
    }
}
