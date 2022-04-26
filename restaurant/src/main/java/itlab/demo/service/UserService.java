package itlab.demo.service;


import itlab.demo.model.entity.Role;
import itlab.demo.model.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();

    User authUser(User user);
}
