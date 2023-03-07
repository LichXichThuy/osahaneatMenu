package com.cybersoft.osahaneat.Service;

import com.cybersoft.osahaneat.Service.Imp.LoginServiceImp;
import com.cybersoft.osahaneat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginServiceImp {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean login(String username, String password) {
        return userRepository.findByEmailAndPassword(username, password).size() > 0;
    }
}
