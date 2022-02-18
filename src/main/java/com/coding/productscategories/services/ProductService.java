package com.coding.productscategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coding.productscategories.models.Category;
import com.coding.productscategories.models.Product;
import com.coding.productscategories.repositories.CategoryRepository;
import com.coding.productscategories.repositories.ProductRepository;

@Service
public class ProductService {
	
	
	private final ProductRepository proRep;
	private final CategoryRepository catRep;
	
	public ProductService(ProductRepository productRepository, CategoryRepository catRep) {
		this.proRep=productRepository;
		this.catRep=catRep;
	}
	
//	CREATE
	public Product createProduct(Product pro) {
		return proRep.save(pro);
	}
	
	
//	READ ONE BY ID
	public Product findOne(Long id) {
		Optional<Product> optionalProduct=proRep.findById(id);
		
		
		if(optionalProduct.isPresent()) {
			return optionalProduct.get();
		}
		else return null;
	}
	
//	FIND THIS PRODUCTS CATEGORIES
	public List<Category> findThisCategories(Long id){
		Product thisProduct=this.findOne(id);
		if (thisProduct !=null) {
			return catRep.findAllByProducts(thisProduct);
		}
		else return null;
	}
	
//	FIND ALL CATEGORIES NOT FOR THIS PRODUCT
	
	public List<Category> findNotCategories(Long id){
		Product thisProduct=this.findOne(id);
		if (thisProduct !=null) {
			return catRep.findByProductsNotContains(thisProduct);
		}
		else return null;
	}

	
	
//	UPDATE PRODUCT WITH NEW CATETGORY
	
	public Product updateCategory(Product product) {
		Optional<Product> thisProduct=proRep.findById(product.getId());
		if (thisProduct.isPresent()) {
			Product update=thisProduct.get();
			for (Category thisCategory: product.getCategories()) {
				update.getCategories().add(thisCategory);
			}
//			update.getCategories().add(product.getCategories().get(0));
			proRep.save(update);
			return update;
		}	
		else return null;
	}
	
	
	
	
	
	
	
	
	
	
	
}
