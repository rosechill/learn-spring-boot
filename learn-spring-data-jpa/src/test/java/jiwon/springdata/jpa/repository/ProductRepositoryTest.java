package jiwon.springdata.jpa.repository;

import jakarta.persistence.criteria.Predicate;
import jiwon.springdata.jpa.entity.Category;
import jiwon.springdata.jpa.entity.Product;
import jiwon.springdata.jpa.model.ProductPrice;
import jiwon.springdata.jpa.model.SimpleProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.support.TransactionOperations;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionOperations transactionOperations;

    @Test
    void createProduct() {
        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        {
            Product product = new Product();
            product.setName("Apple Iphone 14 Pro Max");
            product.setPrice(25_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }

        {
            Product product = new Product();
            product.setName("Apple Iphone 13 Pro Max");
            product.setPrice(18_000_000L);
            product.setCategory(category);
            productRepository.save(product);
        }
    }

    @Test
    void findByCategoryName() {
        List<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH");
        assertEquals(2, products.size());
        assertEquals("Apple Iphone 14 Pro Max", products.get(0).getName());
        assertEquals("Apple Iphone 13 Pro Max", products.get(1).getName());
    }

    @Test
    void sort() {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        List<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", sort);
        assertEquals(2, products.size());
        assertEquals("Apple Iphone 13 Pro Max", products.get(0).getName());
        assertEquals("Apple Iphone 14 Pro Max", products.get(1).getName());
    }

    @Test
    void pageable() {
        // page 0
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
        Page<Product> products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);
        assertEquals(1, products.getContent().size());
        assertEquals(0, products.getNumber());
        assertEquals(2, products.getTotalElements());
        assertEquals(2, products.getTotalPages());
        assertEquals("Apple Iphone 13 Pro Max", products.getContent().get(0).getName());

        // page 1
        pageable = PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")));
        products = productRepository.findAllByCategory_Name("GADGET MURAH", pageable);
        assertEquals(1, products.getContent().size());
        assertEquals(1, products.getNumber());
        assertEquals(2, products.getTotalElements());
        assertEquals(2, products.getTotalPages());
        assertEquals("Apple Iphone 14 Pro Max", products.getContent().get(0).getName());
    }

    @Test
    void testCount() {
        Long count = productRepository.count();
        assertEquals(2L, count);

        count = productRepository.countByCategory_Name("GADGET MURAH");
        assertEquals(2L, count);

        count = productRepository.countByCategory_Name("gak ada");
        assertEquals(0L, count);
    }

    @Test
    void exists() {
        boolean exists = productRepository.existsByName("Apple Iphone 14 Pro Max");
        assertTrue(exists);

        exists = productRepository.existsByName("Apple Iphone 1555 Pro Max");
        assertFalse(exists);
    }

    // without transaction
    @Test
    void deleteOld() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findById(1L).orElse(null);
            assertNotNull(category);

            Product product = new Product();
            product.setName("Samsung Galaxy S9");
            product.setPrice(25_000_000L);
            product.setCategory(category);
            productRepository.save(product);

            int delete = productRepository.deleteByName("Samsung Galaxy S9");
            assertEquals(1, delete);

            delete = productRepository.deleteByName("Samsung Galaxy S9");
            assertEquals(0, delete);
        });
    }

    @Test
    void deleteNew() {
        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        Product product = new Product();
        product.setName("Samsung Galaxy S9");
        product.setPrice(25_000_000L);
        product.setCategory(category);
        productRepository.save(product); // transaksi 1

        int delete = productRepository.deleteByName("Samsung Galaxy S9"); // transaksi 2
        assertEquals(1, delete);

        delete = productRepository.deleteByName("Samsung Galaxy S9"); // transaksi 3
        assertEquals(0, delete);
    }

    @Test
    void searchProduct() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Product> products = productRepository.searchProductUsingName("Apple Iphone 14 Pro Max", pageable);
        assertEquals(1, products.size());
        assertEquals("Apple Iphone 14 Pro Max", products.get(0).getName());
    }

    @Test
    void searchProductLike() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")));
        Page<Product> products = productRepository.searchProduct("%Iphone%", pageable);
        assertEquals(1, products.getContent().size());
        assertEquals(2, products.getTotalPages());
        assertEquals(2, products.getTotalElements());

        products = productRepository.searchProduct("%GADGET%", pageable);
        assertEquals(1, products.getContent().size());
    }

    @Test
    void modifiying() {
        int total = productRepository.deleteProductUsingNmae("salah");
        assertEquals(0, total);

        total = productRepository.updateProductPriceToZero(1L);
        assertEquals(1, total);

        Product product = productRepository.findById(1L).orElse(null);
        assertNotNull(product);
        assertEquals(0L, product.getPrice());
    }

    @Test
    void stream() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Category category = categoryRepository.findById(1L).orElse(null);
            assertNotNull(category);
            Stream<Product> stream = productRepository.streamAllByCategory(category);
            stream.forEach(product -> {
                System.out.println(product.getId() + " : " + product.getName());
            });
        });
    }

    @Test
    void slice() {
        Pageable firstPage = PageRequest.of(0, 1);

        Category category = categoryRepository.findById(1L).orElse(null);
        assertNotNull(category);

        Slice<Product> slice = productRepository.findAllByCategory(category, firstPage);
        // tampilakn konten product

        while (slice.hasNext()) {
            slice = productRepository.findAllByCategory(category, slice.nextPageable());
            // tampilkan konten product
        }
    }

    @Test
    void lock1() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            try {
                Product product = productRepository.findFirstByIdEquals(1L).orElse(null);
                assertNotNull(product);
                product.setPrice(30_000_000L);

                Thread.sleep(20_000L);
                productRepository.save(product);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void lock2() {
        transactionOperations.executeWithoutResult(transactionStatus -> {
            Product product = productRepository.findFirstByIdEquals(1L).orElse(null);
            assertNotNull(product);
            product.setPrice(10_000_000L);

            productRepository.save(product);

        });
    }

    @Test
    void specification() {
        Specification<Product> specification = (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaQuery.where(
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("name"), "Apple Iphone 14 Pro Max"),
                            criteriaBuilder.like(root.get("name"), "Apple Iphone 13 Pro Max")
                    )
            ).getRestriction();
        };
        List<Product> products = productRepository.findAll(specification);
        assertEquals(2, products.size());
    }

    @Test
    void projection() {
        List<SimpleProduct> simpleProducts = productRepository.findAllByNameLike("%Apple%", SimpleProduct.class);
        assertEquals(2, simpleProducts.size());

        List<ProductPrice> productPrices = productRepository.findAllByNameLike("%Apple%", ProductPrice.class);
        assertEquals(2, productPrices.size());
    }
}