package spring.code2.Controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.code2.Product.ProductEntity;
import spring.code2.Repo.ProductRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final int PAGE_SIZE = 5;
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping
    public List<ProductEntity> getAllProducts(@RequestParam(defaultValue = "id") String sortBy,
                                              @RequestParam(defaultValue = "0") Integer pageNumber)
    {
//       list used only for content of page or use Page<ProductEntity>
//        return productRepository.findBy(Sort.by(
//                Sort.Order.desc(sortBy),
//                Sort.Order.asc("title")
//        ));

//    return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy, "title"));


        Pageable pageable = PageRequest.of(pageNumber,
                PAGE_SIZE,
                Sort.by(sortBy));


        return productRepository.findAll(pageable).getContent();
    }
}
