package com.cybersoft.osahaneat.Service.Imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageServiceImp {
    boolean saveFile(MultipartFile file);
    Resource downloadFile(String fileName);
}
