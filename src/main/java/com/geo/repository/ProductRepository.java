package com.geo.repository;

import com.geo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
select product
from Product product
where product.id in (
select purchase.productId
from Purchase purchase
where purchase.userId = :userId
)
""")
    List<Product> findProductsByWebsiteUser(Long userId);
}
