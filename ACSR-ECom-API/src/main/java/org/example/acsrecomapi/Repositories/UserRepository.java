package org.example.acsrecomapi.Repositories;

import org.example.acsrecomapi.Models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer> {
    Users findByUserId(long userId);
    Users findByUserName(String userName);
    Users findByUserNameAndNumberPhoneAndEmail(String userName, String numberPhone, String email);
}
