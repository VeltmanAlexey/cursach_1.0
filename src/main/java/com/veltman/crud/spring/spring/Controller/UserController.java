package com.veltman.crud.spring.spring.Controller;


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

    // buh
    @GetMapping("/buh")
    public String usersForBuh(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("user", userServiceImp.getAllUsers());
        model.addAttribute("roles", userServiceImp.listRoles());
        return "/buh";
    }

    // buh
    @GetMapping("/buh/new")
    public String addUserForBuh(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("roles", userServiceImp.listRoles());
        model.addAttribute("user", new User());
        return "new";
    }

    // buh
    @PostMapping("/buh")
    public String addForBuh(@ModelAttribute("user") User user) {
        userServiceImp.saveUser(user);
        return "redirect:/buh";
    }

    // buh
    @GetMapping("buh/edit/{id}")
    public String editForBuh(@PathVariable("id") int id, Model model) {
        model.addAttribute("userOne", userServiceImp.getUserById(id));
        return "edit";
    }

    // buh
    @PatchMapping("buh/{id}")
    public String updateForBuh(@ModelAttribute("users")User user, @PathVariable("id") int id, @RequestParam(value = "role") String role) {
        user.setRoles(userServiceImp.findRolesByName(role));
        userServiceImp.updateUser(user, id);
        return "redirect:/buh";
    }

    // buh
    @DeleteMapping("buh/{id}")
    public String deleteForBuh(@PathVariable("id") int id) {
        userServiceImp.deleteUserById(id);
        return "redirect:/buh";
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////

    // owner
    @GetMapping("/owner")
    public String usersForOwner(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("user", userServiceImp.getAllUsers());
        model.addAttribute("roles", userServiceImp.listRoles());
        return "/owner";
    }

    // owner
    @GetMapping("/owner/new")
    public String addUserForOwner(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("roles", userServiceImp.listRoles());
        model.addAttribute("user", new User());
        return "new";
    }

    // owner
    @PostMapping("/owner")
    public String addForOwner(@ModelAttribute("user") User user) {
        userServiceImp.saveUser(user);
        return "redirect:/owner";
    }

    // owner work
    @GetMapping("owner/edit/{id}")
    public String editForOwner(@PathVariable("id") int id, Model model) {
        model.addAttribute("userOne", userServiceImp.getUserById(id));
        return "edit";
    }

    // owner
    @PatchMapping("owner/{id}")
    public String updateForOwner(@ModelAttribute("users")User user, @PathVariable("id") int id, @RequestParam(value = "role") String role) {
        user.setRoles(userServiceImp.findRolesByName(role));
        userServiceImp.updateUser(user, id);
        return "redirect:/owner";
    }

    // owner
    @DeleteMapping("owner/{id}")
    public String deleteForOwner(@PathVariable("id") int id) {
        userServiceImp.deleteUserById(id);
        return "redirect:/owner";
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////

    // executiveDirector
    @GetMapping("/executiveDirector")
    public String usersForExecutiveDirector(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("user", userServiceImp.getAllUsers());
        model.addAttribute("roles", userServiceImp.listRoles());
        return "/executiveDirector";
    }

    // executiveDirector
    @GetMapping("/executiveDirector/new")
    public String addUserForExecutiveDirector(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("roles", userServiceImp.listRoles());
        model.addAttribute("user", new User());
        return "new";
    }

    // executiveDirector
    @PostMapping("/executiveDirector")
    public String addForExecutiveDirector(@ModelAttribute("user") User user) {
        userServiceImp.saveUser(user);
        return "redirect:/executiveDirector";
    }

    // executiveDirector
    @GetMapping("executiveDirector/edit/{id}")
    public String editForExecutiveDirector(@PathVariable("id") int id, Model model) {
        model.addAttribute("userOne", userServiceImp.getUserById(id));
        return "edit";
    }

    // executiveDirector
    @PatchMapping("executiveDirector/{id}")
    public String updateForExecutiveDirector(@ModelAttribute("users")User user, @PathVariable("id") int id, @RequestParam(value = "role") String role) {
        user.setRoles(userServiceImp.findRolesByName(role));
        userServiceImp.updateUser(user, id);
        return "redirect:/executiveDirector";
    }

    // executiveDirector
    @DeleteMapping("executiveDirector/{id}")
    public String deleteForExecutiveDirector(@PathVariable("id") int id) {
        userServiceImp.deleteUserById(id);
        return "redirect:/executiveDirector";
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    // HR
    @GetMapping("/hr")
    public String usersForHr(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("user", userServiceImp.getAllUsers());
        model.addAttribute("roles", userServiceImp.listRoles());
        return "/hr";
    }

    // HR
    @GetMapping("/hr/new")
    public String addUserForHr(Model model, Principal principal) {
        model.addAttribute("userForBar", userServiceImp.findByUsername(principal.getName()));
        model.addAttribute("roles", userServiceImp.listRoles());
        model.addAttribute("user", new User());
        return "new";
    }

    // HR
    @PostMapping("/hr")
    public String addForHr(@ModelAttribute("user") User user) {
        userServiceImp.saveUser(user);
        return "redirect:/hr";
    }

    // HR
    @GetMapping("hr/edit/{id}")
    public String editForHr(@PathVariable("id") int id, Model model) {
        model.addAttribute("userOne", userServiceImp.getUserById(id));
        return "edit";
    }

    // HR
    @PatchMapping("hr/{id}")
    public String updateForHr(@ModelAttribute("users")User user, @PathVariable("id") int id, @RequestParam(value = "role") String role) {
        user.setRoles(userServiceImp.findRolesByName(role));
        userServiceImp.updateUser(user, id);
        return "redirect:/hr";
    }

    // HR
    @DeleteMapping("hr/{id}")
    public String deleteForHr(@PathVariable("id") int id) {
        userServiceImp.deleteUserById(id);
        return "redirect:/hr";
    }
}
