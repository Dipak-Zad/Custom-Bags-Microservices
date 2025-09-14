package com.bhrasta.user.service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhrasta.user.service.Entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

}
