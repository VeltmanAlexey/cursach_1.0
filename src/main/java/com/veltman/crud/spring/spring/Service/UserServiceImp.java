package com.veltman.crud.spring.spring.Service;

import com.veltman.crud.spring.spring.Model.Role;
import com.veltman.crud.spring.spring.Model.User;
import com.veltman.crud.spring.spring.Model.UserInf;


import java.util.List;
import java.util.Set;

public interface UserServiceImp {

    Set<Role> findRolesByName(String roleName);

    User updateUser(User user, int id, UserInf userInf);

    List<Role> listRoles();

    User getUserById(int id);

    List<User> getAllUsers();

    void saveUser(User user, UserInf userInf);

    void deleteUserById(int id);

    User findByUsername(String username);

    void saveRole(Role role);


}
