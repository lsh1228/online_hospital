package com.recommend.service;

import com.recommend.bean.Role;
import com.recommend.bean.User;
import com.recommend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private HttpSession session;

    private Map<String, String> loginSession = new HashMap<>();

    public boolean isLogin(User user) {

        if (user.getName() == null || !loginSession.containsKey(user.getName())) {
            return false;
        }
        String sessionId = session.getId();
        if (loginSession.get(user.getName()).endsWith(sessionId)) {
            return true;
        }
        return false;

    }
    public boolean login(User user) {

        if (user == null) {
            return false;
        }

        String password = userRepository.getUserPassword(user.getName());
        if (password != null) {
            if (password.equals(user.getPassword())) {
                String sessionId = session.getId();
                loginSession.put(user.getName(), sessionId);
                return true;
            }
        }
        return false;
    }

    public boolean register(User user) {

        if (user == null) {
            return false;
        }
        if (user.getRole() == null) {
            user.setRole(Role.PATIENT);
        }

        System.out.println("cccc");
        System.out.println(user.toString());

        if (user.getName() != null && user.getPassword() != null) {
            return userRepository.createUser(user);
        }

        return false;
    }

    public User getUser(String userName) {

        if (userName == null) {
            return null;
        }
        return userRepository.getUser(userName);
    }

    public boolean delUser(String userName) {

        if (userName == null) {
            return false;
        }

        return userRepository.delete(userName);
    }
}
