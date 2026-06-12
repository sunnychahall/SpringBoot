package spring.code2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.code2.Product.ProductEntity;
import spring.code2.Repo.ProductRepository;
import java.util.List;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
class Code2ApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testRepository(){

        ProductEntity productEntity = ProductEntity.builder()
                .sku("P1099")
                .title("S24")
                .price(BigDecimal.valueOf(50000))
                .quantity(1)
                .build();

       ProductEntity savedEntity =  productRepository.save(productEntity);
       System.out.println(savedEntity);

    }

    @Test
    void getRepository()
    {
//        List<ProductEntity> entityList = productRepository.findByTitle("Pixel 10");
//        System.out.println(entityList);
// 		List<ProductEntity> entities = productRepositor y.findByCreatedAtAfter(
//				LocalDateTime.of(2025, 1, 1, 0, 0, 0 ));
//		List<ProductEntity> entities = productRepository.findByQuantityGreaterThanOrPriceLessThan(20, BigDecimal.valueOf(54999.99));
        List<ProductEntity> entities = productRepository.findByTitleContainingIgnoreCase("S");
        System.out.println(entities);
    }


    @Test
    void getSingleFromRepository() {
        Optional<ProductEntity> productEntity = productRepository
                .findByTitleAndPrice("Pixel 10", BigDecimal.valueOf(74999.99));
        productEntity.ifPresent(System.out::println);
    }


    }


