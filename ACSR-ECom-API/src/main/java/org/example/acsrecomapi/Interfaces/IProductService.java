package org.example.acsrecomapi.Interfaces;

import org.example.acsrecomapi.DTO.Product.AddProductRequest;
import org.example.acsrecomapi.Models.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<Products> getProduct(int page, int pageSize);
    Boolean addNewProduct(AddProductRequest addProductRequest);
}
