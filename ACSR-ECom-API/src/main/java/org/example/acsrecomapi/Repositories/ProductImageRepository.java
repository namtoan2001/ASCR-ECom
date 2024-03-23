package org.example.acsrecomapi.Repositories;

import org.example.acsrecomapi.Models.ProductImages;
import org.example.acsrecomapi.Models.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends CrudRepository<ProductImages, Integer> {

}
