package com.bhrasta.bag.service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhrasta.bag.service.Entities.Bags;

@Repository
public interface BagsRepository extends JpaRepository<Bags, String>{

}
