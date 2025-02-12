package com.Ecommerce.Ecommerce_Website.service.adminCategory.adminProduct;

import com.Ecommerce.Ecommerce_Website.dto.ProductDto;
import com.Ecommerce.Ecommerce_Website.entity.Category;
import com.Ecommerce.Ecommerce_Website.entity.Product;
import com.Ecommerce.Ecommerce_Website.repo.CategoryRepository;
import com.Ecommerce.Ecommerce_Website.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;


    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());


        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow();


        product.setCategory(category);

       return productRepository.save(product).getDto();
    }

    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());

    }
}
