package com.example.contestbackend.controller;

import com.example.contestbackend.model.User;
import com.example.contestbackend.dto.UserDto;
import com.example.contestbackend.service.CsvExportService;
import com.example.contestbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/users")
public class UserController {
    private final UserService userService;

    private final CsvExportService csvExportService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUsers(id);
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
    }

    @GetMapping("/details")
    public User getUserDetails(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        return user;
    }

    @RequestMapping (path = "/export")
    public void getAllUsersInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"users.csv\"");
        csvExportService.writeUsersToCsv(servletResponse.getWriter());
    }

    @PutMapping("/{email}")
    public User updateUser(@PathVariable String email, @RequestBody UserDto userDto){
        return userService.updateUser(email, userDto);
    }

    @GetMapping("/roles/{roleId}")
    public List<String> getEmailsByRoleId(@PathVariable List<Integer> roleId){
        return userService.getEmailsByRoleId(roleId);
    }
}