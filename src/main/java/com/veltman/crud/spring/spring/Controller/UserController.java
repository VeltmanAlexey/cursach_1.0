package com.veltman.crud.spring.spring.Controller;


import com.veltman.crud.spring.spring.Model.Role;
import com.veltman.crud.spring.spring.Model.User;
import com.veltman.crud.spring.spring.Service.UserService;
import com.veltman.crud.spring.spring.Service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    private final UserServiceImp userServiceImp;

    @Autowired()
    public UserController(UserService userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    // worker
    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("ROLE_ADMIN",userServiceImp.findRolesByName("ROLE_ADMIN"));
        User user = userServiceImp.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "/user";
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    // admin
    @GetMapping("/admin")
    public String users(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("user", userServiceImp.getAllUsers());
        model.addAttribute("roles", userServiceImp.listRoles());
        return "/index";
    }

    // admin
    @GetMapping("/admin/new")
    public String addUser(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("roles", userServiceImp.listRoles());
        model.addAttribute("user", new User());
        return "new";
    }

    // admin
    @PostMapping("/admin")
    public String add(@ModelAttribute("user") User user) {
        userServiceImp.saveUser(user);
        return "redirect:/admin";
    }

    // admin
    @GetMapping("admin/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("userOne", userServiceImp.getUserById(id));
        return "edit";
    }

    // admin
    @PatchMapping("admin/{id}")
    public String update(@ModelAttribute("users")User user, @PathVariable("id") int id, @RequestParam(value = "role") String role) {
        user.setRoles(userServiceImp.findRolesByName(role));
        userServiceImp.updateUser(user, id);
        return "redirect:/admin";
    }

    // admin
    @DeleteMapping("admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userServiceImp.deleteUserById(id);
        return "redirect:/admin";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/admin/newRole")
    public String addRoleGet(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("role", new Role());
        return "newRole";
    }

    // admin
    @PostMapping("/admin/newRole")
    public String addRolePost(@ModelAttribute("role") Role role) {
        userServiceImp.saveRole(role);
        return "redirect:/admin";
    }



}
