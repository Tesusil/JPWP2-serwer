package com.tesusil.jezyki.wysokiego.poziomu.category

import com.tesusil.jezyki.wysokiego.poziomu.category.request.NewCategoryRequest
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/category/")
class CategoryController(private val repository: CategoryRepository) {

    @GetMapping("")
    fun getAll(): List<Category> {
        return repository.findAll()
    }

    @GetMapping("/{id}/")
    fun getCategoryById(@PathVariable id: Long): Category {
        if(isCategoryExist(id)) {
            return repository.findById(id).get()
        } else {
            throw CategoryException.NotFound(id)
        }
    }

    @GetMapping("main")
    fun getMainCategories(): List<Category> {
        return repository.findAll().asSequence().filter { it.parentId == null }.toList()
    }

    @GetMapping("/{id}/children")
    fun getCategoryChildrenById(@PathVariable id: Long): List<Category> {
        if(isCategoryExist(id)) {
            return if (repository.findById(id).get().hasChildren) repository.findAll().asSequence().filter { it.parentId == id }.toList() else Collections.emptyList()
        } else {
            throw CategoryException.NotFound(id)
        }
    }

    @PutMapping("")
    fun updateCategory(@RequestBody category: Category) {
        if(isCategoryExist(category.id)) {
            repository.save(category)
        } else {
            throw CategoryException.NotFound(category.id)
        }
    }

    @PostMapping("")
    fun newCategory(@RequestBody body: NewCategoryRequest) {
        val objectToSave = categoryFromNewCategory(body)
        repository.save(objectToSave)
        if (body.parentId != null && repository.findById(body.parentId).isPresent) {
            updateIsParentField(true, body.parentId)
        }
    }

    @DeleteMapping("{id}/")
    fun deleteCategory(@PathVariable id: Long) {
        if (isCategoryExist(id)) {
            val parentId = repository.findById(id).get().parentId
            repository.deleteById(id)
            if (parentId != null) {
                if (categoryChildrenAmount(parentId) == 0) {
                    updateIsParentField(false, parentId)
                }
            }
        } else {
            throw CategoryException.NotFound(id)
        }

    }

    private fun updateIsParentField(isParent: Boolean, categoryId: Long) {
        val objectToUpdate = repository.findById(categoryId).get()
        objectToUpdate.hasChildren = isParent
        repository.save(objectToUpdate)
    }

    private fun categoryChildrenAmount(categoryId: Long): Int {
        return repository.findAll().asSequence().filter { it.parentId == categoryId }.toList().size
    }

    private fun isCategoryExist(categoryId: Long) : Boolean = repository.findById(categoryId).isPresent
}