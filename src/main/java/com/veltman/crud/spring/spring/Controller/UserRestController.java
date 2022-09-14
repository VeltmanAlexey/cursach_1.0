package com.veltman.crud.spring.spring.Controller;


import com.veltman.crud.spring.spring.Model.Role;
import com.veltman.crud.spring.spring.Model.User;
import com.veltman.crud.spring.spring.Service.UserService;
import com.veltman.crud.spring.spring.Service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class UserRestController {


    private final UserServiceImp userServiceImp;

    @Autowired()
    public UserRestController(UserService userServiceImp) {
        this.userServiceImp = userServiceImp;
    }


    @PostMapping("/create")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        userServiceImp.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userServiceImp.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        return new ResponseEntity<>(userServiceImp.updateUser(user, user.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        if (userServiceImp.getUserById(id) == null) {
            return "User with id " + id + " not exist";
        } else {
            userServiceImp.deleteUserById(id);
            return "User with id " + id + " was deleted!";
        }
    }

    @GetMapping( "/getRoles")
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(userServiceImp.listRoles(), HttpStatus.OK);
    }
}
