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
        return userDao.getAllUsers();
    }


    public void updateUser(int userId, String fullName, String email, String role) {
        userDao.updateUser(userId, fullName, email, role);
    }

    public UsersModel getUserById(int userId) {
        return userDao.getUserById(userId);
    }




}
