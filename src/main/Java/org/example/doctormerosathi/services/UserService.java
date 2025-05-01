package org.example.doctormerosathi.services;

import org.example.doctormerosathi.dao.userdao;
import org.example.doctormerosathi.model.UsersModel;
import java.util.List;

public class UserService {
    private final userdao userDao;

    public UserService() {
        this.userDao = new userdao();
    }

    public List<UsersModel> getAllUsers() {
        List<UsersModel> users = userDao.getAllUsers();
        return users;
    }
}