package com.veltman.crud.spring.spring.Service;


import com.veltman.crud.spring.spring.Model.Role;
import com.veltman.crud.spring.spring.Model.User;
import com.veltman.crud.spring.spring.Model.UserInf;
import com.veltman.crud.spring.spring.Repository.RoleRepository;
import com.veltman.crud.spring.spring.Repository.UserRepository;
import com.veltman.crud.spring.spring.Tools.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService, UserServiceImp {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RandomString randomString;


    @Autowired
    @Lazy
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, RandomString randomString) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.randomString = randomString;
    }


    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user, UserInf userInf) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserInf(userInf);
        userInf.setId(user.getId());
        userRepository.save(user);
    }



    @Transactional
    public User updateUser(User user, int id, UserInf userInf) {
        User userFromDb = getUserById(user.getId());

        if (!userFromDb.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setUserInf(userInf);
        userRepository.save(user);
        return user;
    }

    public Set<Role> findRolesByName(String roleName) {
        Set<Role> roles = new HashSet<>();
        for (Role role : listRoles()) {
            if (roleName.contains(role.getName())) {
                roles.add(role);
            }
        }
        return roles;
    }

    public void saveRole(Role role) {
        role.setName("ROLE_" + randomString.getAlphaNumericString(15));
        roleRepository.save(role);
    }


    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found!", username));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}