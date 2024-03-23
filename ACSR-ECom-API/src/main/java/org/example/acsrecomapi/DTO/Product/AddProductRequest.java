package org.example.acsrecomapi.DTO.Product;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class AddProductRequest {
    private String productName;
    private double price;
    private String description;
    @Transient
    private List<MultipartFile> imgFile;
    public AddProductRequest(String productName, double price, String description, List<MultipartFile> imgFile){
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.imgFile = imgFile;
    }
}
