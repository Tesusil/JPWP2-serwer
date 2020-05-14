package com.tesusil.jezyki.wysokiego.poziomu.product

import com.tesusil.jezyki.wysokiego.poziomu.category.CategoryRepository
import com.tesusil.jezyki.wysokiego.poziomu.category.CategoryException
import com.tesusil.jezyki.wysokiego.poziomu.product.response.ProductsAndCategoryResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product/")
class ProductController(val repository: ProductRepository,
                        val categoryRepository: CategoryRepository) {

    @GetMapping("{typeId}/")
    fun getProductsByTypeId(@PathVariable(name = "typeId") id: Long): ProductsAndCategoryResponse {
        val optional = categoryRepository.findById(id);
        if (optional.isPresent) {
            val list = repository.findAll().asSequence().filter { it.category.id == id }.toList()
            val category = categoryRepository.findById(id).get();
            return ProductsAndCategoryResponse(category, list)
        }
        throw CategoryException.NotFound(id);
    }

    @GetMapping("details/{productId}/")
    fun getProductDetailsById(@PathVariable productId: Long): Product {
        val optional = repository.findById(productId)
        if (optional.isPresent) {
            return optional.get()
        }
        throw ProductException.NotFound(productId)
    }

    @PostMapping("newProduct/")
    fun newProduct(@RequestBody product: Product) {
        val amountOfMatchProducts = getAmountOfMatchProducts(product)
        if (amountOfMatchProducts > 0) {
            throw ProductException.ProductAlreadyExist(product)
        }
        repository.save(product)
    }

    @PutMapping("updateProduct/")
    fun updateProduct(@RequestBody product: Product) {
        if (getAmountOfMatchProducts(product) == 1) {
            repository.save(product)
        } else if (getAmountOfMatchProducts(product) == 0) {
            throw ProductException.NotFound(product.id)
        }
    }

    private fun getAmountOfMatchProducts(product: Product): Int {
        return repository.findAll().asSequence().filter { it.name == product.name }.count()
    }
}