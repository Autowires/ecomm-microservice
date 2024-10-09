package com.microservices.product.service.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.product.service.dto.ProductForm;
import com.microservices.product.service.dto.ProductInfoResponse;
import com.microservices.product.service.entity.Product;
import com.microservices.product.service.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
    private static final Logger log = Logger.getLogger(ProductController.class.getName());

	@GetMapping
	public List<Product> productsView(@RequestParam(required = false) String category,
			@RequestParam(required = false) String search) {
		return productService.getProducts(category, search);
	}

	@GetMapping("/{productId}")
	public ProductInfoResponse product(@PathVariable long productId) {
		return productService.getProducInfo(productId);
	}

	@GetMapping("/categories")
	public List<String> displayCategories() {
		return productService.getCategories();

	}

	@GetMapping("/recentAdds")
	public List<Product> recentAddedProducts() {
		return productService.recentAdds();
	}
	/**
	 * This method is used to add a product in the database
	 * 
	 * @param retailerId - This is the id of the retailer
	 * @param form       - This is the ProductForm object which contains the
	 *                    information of the product
	 * @return - A string value which indicates if the product is added or not
	 */
   
	@PostMapping("/addproduct/{retailerId}")
	public String addProduct(@PathVariable long retailerId, @RequestBody ProductForm form) {
		log.info("Logging for add product");
		return productService.addProduct(retailerId, form);
	}
	/**
	 * This method is used to update a product in the database
	 * 
	 * @param retailerId - This is the id of the retailer
	 * @param productId  - This is the id of the product
	 * @param form       - This is the ProductForm object which contains the updated
	 *                    information of the product
	 * @return - A string value which indicates if the product is updated or not
	 */
	@PutMapping("/updateProduct/{retailerId}/{productId}")
	public String updateProduct(@PathVariable long retailerId, @PathVariable Long productId,
			@RequestBody ProductForm form) {
		log.info("Logging for update product");
		return productService.updateProduct(retailerId, productId, form);
	}
	/**
	 * This method is used to delete a product from the database
	 * 
	 * @param retailerId - This is the id of the retailer
	 * @param productId  - This is the id of the product
	 * @return - String message indicating whether the deletion was successful or
	 *         not
	 */

	@DeleteMapping("/deleteProduct/{retailerId}/{productId}")
	public String deleteProduct(@PathVariable long retailerId, @PathVariable Long productId) {
		log.info("Logging for delete product");
		return productService.deleteProduct(retailerId, productId);
	}

	@GetMapping("/getproductbyid/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable Long productId) {
		Product product = productService.findProductById(productId);
		return ResponseEntity.ok(product);
	}
	/**
	 * This method is used to get all the products of a retailer
	 * 
	 * @param retailerId - This is the id of the retailer
	 * @return - A list of Product objects which are associated with the given
	 *         retailerId
	 */
	@GetMapping("/getAllProducts/{retailerId}")
	public List<Product> getAllProducts(@PathVariable Long retailerId) {
		log.info("Logging for get all product");
		return productService.getAllProducts(retailerId);
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
