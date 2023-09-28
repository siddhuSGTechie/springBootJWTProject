package com.sembmarine.usermanagement.repo;

import org.springframework.data.repository.CrudRepository;

import com.sembmarine.usermanagement.entity.UserDemo;

public interface UserRepo extends CrudRepository<UserDemo, String> {

}
