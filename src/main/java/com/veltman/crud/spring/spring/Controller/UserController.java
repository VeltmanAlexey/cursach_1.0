package com.veltman.crud.spring.spring.Controller;


import com.veltman.crud.spring.spring.Model.Role;
import com.veltman.crud.spring.spring.Model.User;
import com.veltman.crud.spring.spring.Model.UserInf;
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



    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    // admin
    @GetMapping("/admin")
    public String users(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("user", userServiceImp.getAllUsers());
        model.addAttribute("roles", userServiceImp.listRoles());
        model.addAttribute("userForCreate", new User());
        model.addAttribute("userForForm", new User());
        model.addAttribute("userInf", new UserInf());
        return "/index";
    }



    // admin
    @PostMapping("/admin")
    public String add(@ModelAttribute("user") User user, @ModelAttribute("userInf") UserInf userInf) {
        userServiceImp.saveUser(user, userInf);

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
    public String update(@ModelAttribute("users")User user,
                         @PathVariable("id") int id,
                         @RequestParam(value = "role") String role,
                         @ModelAttribute("userInf")UserInf userInf
                         ) {
        user.setRoles(userServiceImp.findRolesByName(role));
        userServiceImp.updateUser(user, id, userInf);
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
