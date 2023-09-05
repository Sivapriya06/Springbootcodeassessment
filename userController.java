package com.example.springbootproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    public userRepository userrepository;
    @PostMapping("/add")
    public User addUser(@RequestBody User user)
    {
        User obj=new User();
        obj.setUserId(user.getUserId());
        obj.setUserName(user.getUserName());
        obj.setUserEmail(user.getUserEmail());
        obj.setGender(user.getGender());
        obj.setAge(user.getAge());
        obj.setNationality(user.getNationality());
        return obj;
    }
    @GetMapping("/viewAll")

    public @ResponseBody  Iterable<User> getAllUsers(){
        return userrepository.findAll();
    }
    @GetMapping("view/{id}")
    public Optional<User> getUser(@PathVariable Integer id)
    {
        return userrepository.findById(id);
    }

    @PutMapping("/edit/{id}")
    public String update(@RequestBody User updateUser,@PathVariable Integer id)
    {
        return userrepository.findById(id)
                .map(user -> {
                    user.setUserName(updateUser.getUserName());
                    user.setUserEmail(updateUser.getUserEmail());
                    user.setAge(updateUser.getAge());
                    user.setGender(updateUser.getGender());
                    user.setNationality(updateUser.getNationality());
                    userrepository.save(user);
                    return " User details have been successfully updated";
                }).orElseGet(()->{
                    return "Invalid user !!!!";
                });

    }
    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id)
    {
        userrepository.deleteById(id);
        return "User deleted !!!!";
    }

}




