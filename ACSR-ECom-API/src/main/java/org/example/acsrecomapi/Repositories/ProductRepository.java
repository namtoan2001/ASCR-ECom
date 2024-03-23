package org.example.acsrecomapi.Repositories;

import org.example.acsrecomapi.Models.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Products, Integer>{
    Page<Products> findAll(Pageable pageable);
}
