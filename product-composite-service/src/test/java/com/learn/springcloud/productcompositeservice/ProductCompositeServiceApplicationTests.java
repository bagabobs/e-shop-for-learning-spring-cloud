package com.learn.springcloud.productcompositeservice;

import com.learn.springcloud.core.product.domain.Product;
import com.learn.springcloud.core.recommendation.domain.Recommendation;
import com.learn.springcloud.core.review.domain.Review;
import com.learn.springcloud.productcompositeservice.application.port.in.FindProductUseCase;
import com.learn.springcloud.util.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.mockito.Mockito.when;
import static java.util.Collections.singletonList;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductCompositeServiceApplicationTests {
	private static final int PRODUCT_ID_OK = 1;
	private static final int PRODUCT_ID_NOT_FOUND = 2;
	private static final int PRODUCT_ID_INVALID = 3;

	@MockBean
	private FindProductUseCase findProductUseCase;

	@Autowired
	private WebTestClient client;

	@BeforeEach
	void setUp() throws Exception {
		when(findProductUseCase.getProduct(PRODUCT_ID_OK)).thenReturn(new Product(PRODUCT_ID_OK, "name", 1, "mock-address"));
		when(findProductUseCase.getReviewsProduct(PRODUCT_ID_OK)).thenReturn(singletonList(new Review(PRODUCT_ID_OK, 1, "author", "subject", "content", "mock address")));
		when(findProductUseCase.getRecomendationsProduct(PRODUCT_ID_OK)).thenReturn(singletonList(new Recommendation(PRODUCT_ID_OK, 1, "author", 1, "content", "mock address")));

		when(findProductUseCase.getProduct(PRODUCT_ID_NOT_FOUND)).thenThrow(new ProductNotFoundException());
	}

	@Test
	void contextLoads() {
	}

}
