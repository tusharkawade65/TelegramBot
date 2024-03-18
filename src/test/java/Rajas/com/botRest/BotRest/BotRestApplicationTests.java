package Rajas.com.botRest.BotRest;

import Rajas.com.botRest.BotRest.Repository.CartRepository;
import Rajas.com.botRest.BotRest.Repository.CategoryRepository;
import Rajas.com.botRest.BotRest.Repository.ProductRepository;
import Rajas.com.botRest.BotRest.Service.CartService;
import Rajas.com.botRest.BotRest.Service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class BotRestApplicationTests {

	@Autowired
	CategoryRepository cr;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ItemService itemService;
	@Autowired
	CartService cartService;
	@Autowired
	CartRepository cartRepository;
	@Test
	 void testItemService(){
		long uuid =1249750239;
		cartService.displayCart(uuid,cartRepository,productRepository);
	}

	@Test
	 void testGetProductsListByCategory(){
		List<String> products =itemService.getProductsListByCategory(productRepository,1);
		Assert.notEmpty(products);
	}

}
