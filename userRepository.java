package com.example.springbootproject;

import org.springframework.data.repository.CrudRepository;

public interface userRepository  extends CrudRepository<User,Integer> {

}
