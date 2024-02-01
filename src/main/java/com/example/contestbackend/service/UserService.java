package com.example.contestbackend.service;

import com.example.contestbackend.dto.UserDto;
import com.example.contestbackend.model.User;
import com.example.contestbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final TitleService titleService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));
    }

    public User getUserByEmail(String email){return userRepository.findUserByEmail(email);}

    public User createUser(UserDto userDto) {
        User user = new User(
//            (userDto.getFirstname().substring(0,1).toUpperCase() + userDto.getFirstname().substring(1).toLowerCase()),
//            (userDto.getLastname().substring(0,1).toUpperCase() + userDto.getLastname().substring(1).toLowerCase()),
            userDto.getFirstname(),
            userDto.getLastname(),
            userDto.getEmail(),
            passwordEncoder.encode(userDto.getPassword()),
            userDto.getPhone(),
            userDto.getWantsToRate(),
            userDto.getEnabled(),
            titleService.getTitle(userDto.getTitle()),
            roleService.getRole(userDto.getRole())
        );
        user.setEnabled(false);
        try {
            return userRepository.save(user);
        } catch (DataAccessException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exists!");
        }
    }

    public void deleteUsers(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}