package com.coding.productscategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coding.productscategories.models.Category;
import com.coding.productscategories.models.Product;
import com.coding.productscategories.repositories.CategoryRepository;
import com.coding.productscategories.repositories.ProductRepository;

@Service
public class CategoryService {

	private final CategoryRepository catRep;
	private final ProductRepository proRep;
	public CategoryService(CategoryRepository categoryRepository, ProductRepository proRep) {
		this.catRep=categoryRepository;
		this.proRep=proRep;
	}
	
	
//	CREATE
	public Category createCategory(Category cat) {
		return catRep.save(cat);
	}
	
	
//	READ ONE BY ID
	public Category findOne(Long id) {
		Optional<Category> optionalCategory=catRep.findById(id);
		
		if (optionalCategory.isPresent()) {
			return optionalCategory.get();
		}
		else return null;
	} 
	

//	FIND THIS CATEGORIES PRODUCTS
	public List<Product> findThisProducts(Long id){
		Category thisCategory=this.findOne(id);
		if(thisCategory !=null) {
			return proRep.findAllByCategories(thisCategory);
		}
		else return null;
	}
	
	
//	FIND ALL PRODUCTS NOT FOR THIS CATEGORY
	public List<Product> findNotProducts(Long id){
		Category thisCategory=this.findOne(id);
		if (thisCategory !=null) {
			return proRep.findByCategoriesNotContains(thisCategory);
		}
		else return null;
	}
	
//	UPDATE CATEGORY WITH NEW PRODUCTS
	public Category updateProduct(Category category) {
		Optional<Category> thisCategory=catRep.findById(category.getId());
		if(thisCategory.isPresent()) {
			Category update=thisCategory.get();
			for (Product thisProduct:category.getProducts()) {
				update.getProducts().add(thisProduct);
			}
			catRep.save(update);
			return update;
		}
		else return null;
	}
	
	
	
	
	
}
