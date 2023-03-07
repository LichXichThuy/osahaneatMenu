package com.cybersoft.osahaneat.Service.Imp;

import com.cybersoft.osahaneat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface LoginServiceImp {
    boolean login(String username, String password);
}
