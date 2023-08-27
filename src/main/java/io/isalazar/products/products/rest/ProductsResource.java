package io.isalazar.products.products.rest;

import io.isalazar.products.products.model.ProductsDTO;
import io.isalazar.products.products.service.ProductsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/productss", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsResource {

    private final ProductsService productsService;

    public ProductsResource(final ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public ResponseEntity<List<ProductsDTO>> getAllProductss() {
        return ResponseEntity.ok(productsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsDTO> getProducts(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(productsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createProducts(@RequestBody @Valid final ProductsDTO productsDTO) {
        final Long createdId = productsService.create(productsDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateProducts(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final ProductsDTO productsDTO) {
        productsService.update(id, productsDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteProducts(@PathVariable(name = "id") final Long id) {
        productsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
