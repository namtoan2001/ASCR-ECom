package org.example.acsrecomapi.Services;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.example.acsrecomapi.DTO.Product.AddProductRequest;
import org.example.acsrecomapi.Interfaces.IProductService;
import org.example.acsrecomapi.Models.ProductImages;
import org.example.acsrecomapi.Models.Products;
import org.example.acsrecomapi.Repositories.ProductImageRepository;
import org.example.acsrecomapi.Repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public  class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }
    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;
    @Override
    public List<Products> getProduct(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Products> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
    }
    @Override
    @Transactional
    public Boolean addNewProduct(AddProductRequest request) {
        Products product = new Products();
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setStatus(true);
        productRepository.save(product);
        if(product.getProductId() > 0){
            try {
                Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", cloudName,
                        "api_key", apiKey,
                        "api_secret", apiSecret));
                UUID uuidFolder = UUID.randomUUID();
                for (MultipartFile file : request.getImgFile()) {
                    UUID uuidImgName = UUID.randomUUID();
                    String imgName = file.getOriginalFilename();
                    assert imgName != null;
                    String img = imgName.substring(0, imgName.lastIndexOf('.'));
                    String url = String.format("ProductImage/%s/%s", uuidFolder, uuidImgName);
                    ProductImages productImg = new ProductImages();
                    productImg.setProducts(product);
                    productImg.setImageLink("https://res.cloudinary.com/dpwifnuax/image/upload/"+url);
                    productImageRepository.save(productImg);
                    if(file.getSize() >= 10485760){
                        throw new Exception("File co dung luong > 10MB!");
                    }
                    else {
                        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                                "public_id", url,
                                "overwrite", true,
                                "quality", "auto:low"));
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}