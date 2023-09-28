package com.sembmarine.usermanagement.repo;

import org.springframework.data.repository.CrudRepository;

import com.sembmarine.usermanagement.entity.Role;

public interface UserManagementRep extends CrudRepository<Role, String> {

}
