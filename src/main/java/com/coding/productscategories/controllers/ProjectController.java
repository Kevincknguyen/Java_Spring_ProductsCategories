package com.coding.productscategories.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coding.productscategories.models.Category;
import com.coding.productscategories.models.Product;
import com.coding.productscategories.services.CategoryService;
import com.coding.productscategories.services.ProductService;

@Controller
public class ProjectController {
	
	
	@Autowired
	ProductService prodSer;
	@Autowired
	CategoryService catSer;
	
	
//	CREATE HOME
	@GetMapping("/")
	public String home (Model model, 
			@ModelAttribute("product") Product product, 
			@ModelAttribute("category") Category category) {
		
		return "Index.jsp";
	}
	
	
	
//	PRODUCT HOME
	@GetMapping("/product/{id}")
	public String product(@PathVariable("id")Long id, 
			Model model,
			HttpSession session, 
			@ModelAttribute("product") Product product,
			@ModelAttribute("category") Category category) {
		
		Product thisProduct=prodSer.findOne(id);
		if (thisProduct!=null) {
			List<Category> thisCategories= prodSer.findThisCategories(id);
			List<Category> notCategories=prodSer.findNotCategories(id);
			model.addAttribute("product",thisProduct);
			model.addAttribute("thisCategories", thisCategories);
			model.addAttribute("notCategories", notCategories);
			return "Product.jsp";
		}
		else return "redirect:/";
		
	}
	
//	Category HOME
	@GetMapping("/category/{id}")
	public String category(@PathVariable("id")Long id, 
			Model model,
			HttpSession session, 
			@ModelAttribute("product") Product product,
			@ModelAttribute("category") Category category) {
		
		Category thisCategory=catSer.findOne(id);
		if (thisCategory!=null) {
			List<Product> thisProducts= catSer.findThisProducts(id);
			List<Product> notProducts=catSer.findNotProducts(id);
			model.addAttribute("category",thisCategory);
			model.addAttribute("thisProducts", thisProducts);
			model.addAttribute("notProducts", notProducts);
			return "Category.jsp";
		}
		else return "redirect:/";
		
	}
	
	
	
	
	
	
	
	
//	NEW PRODUCT API
	@PostMapping("/newProduct")
	public String newProduct (Model model,
			@Valid @ModelAttribute("product") Product product, BindingResult result,
			@ModelAttribute("category") Category category
			) {
		
		if (result.hasErrors()) {
			return "/Index.jsp";
		}
		prodSer.createProduct(product);
		return "redirect:/";
	}
	
	
	
	
//	NEW CATRGORY API
	@PostMapping("/newCategory")
	public String newCategory(Model model,
			@Valid @ModelAttribute("category") Category category,BindingResult result,
			@ModelAttribute("product") Product product) {
		
		if (result.hasErrors()){
			return "/";
		}
		
		catSer.createCategory(category);
		return "redirect:/";
		
		
		
	
		
	}
	
	
	
//		ADD CATEGORYTOPRODUCT
	@RequestMapping(value="/addCategoryToProduct/{id}",method=RequestMethod.PUT)
	public String addCategorytoProduct(
			@PathVariable ("id")Long id,
			@Valid @ModelAttribute("product")Product product,
			BindingResult result) {
		
		System.out.println(product);
		System.out.println("------>"+product.getCategories());
		if (result.hasErrors()) {
			return "Product.jsp";
		} else {
			prodSer.updateCategory(product);
			return "redirect:/";
		}
		
		
		
	}
	
	
//	ADD PRODUCT TO CATEGORY
	@RequestMapping(value="/addProductToCategory/{id}",method=RequestMethod.PUT)
	public String addProducttoCategory(
			@PathVariable ("id")Long id,
			@Valid @ModelAttribute("category")Category category,
			BindingResult result) {
		
		if (result.hasErrors()) {
			return "Category.jsp";
		} else {
			catSer.updateProduct(category);
			return "redirect:/";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
