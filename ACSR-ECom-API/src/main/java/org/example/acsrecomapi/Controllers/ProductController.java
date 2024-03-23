package org.example.acsrecomapi.Controllers;

import org.example.acsrecomapi.Common.Constant;
import org.example.acsrecomapi.DTO.Product.AddProductRequest;
import org.example.acsrecomapi.Models.Products;
import org.example.acsrecomapi.Services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = Constant.HOST, allowCredentials = "true")
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getData")
    public List<Products> getAllData(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int pageSize) throws Exception {
        var result = productService.getProduct(page, pageSize);
        if(result.isEmpty()){
            throw new Exception("Not Found!");
        }
        return result;
    }
    @PostMapping(path ="/addNewProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean addNewProduct(@ModelAttribute("request")@Validated AddProductRequest request){
        return productService.addNewProduct(request);
    }
}
