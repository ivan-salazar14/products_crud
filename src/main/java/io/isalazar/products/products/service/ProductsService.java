package io.isalazar.products.products.service;

import io.isalazar.products.products.domain.Products;
import io.isalazar.products.products.model.ProductsDTO;
import io.isalazar.products.products.repos.ProductsRepository;
import io.isalazar.products.products.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsService(final ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<ProductsDTO> findAll() {
        final List<Products> productss = productsRepository.findAll(Sort.by("id"));
        return productss.stream()
                .map(products -> mapToDTO(products, new ProductsDTO()))
                .toList();
    }

    public ProductsDTO get(final Long id) {
        return productsRepository.findById(id)
                .map(products -> mapToDTO(products, new ProductsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProductsDTO productsDTO) {
        final Products products = new Products();
        mapToEntity(productsDTO, products);
        return productsRepository.save(products).getId();
    }

    public void update(final Long id, final ProductsDTO productsDTO) {
        final Products products = productsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productsDTO, products);
        productsRepository.save(products);
    }

    public void delete(final Long id) {
        productsRepository.deleteById(id);
    }

    private ProductsDTO mapToDTO(final Products products, final ProductsDTO productsDTO) {
        productsDTO.setId(products.getId());
        productsDTO.setName(products.getName());
        productsDTO.setPrice(products.getPrice());
        productsDTO.setStock(products.getStock());
        return productsDTO;
    }

    private Products mapToEntity(final ProductsDTO productsDTO, final Products products) {
        products.setName(productsDTO.getName());
        products.setPrice(productsDTO.getPrice());
        products.setStock(productsDTO.getStock());
        return products;
    }

}
