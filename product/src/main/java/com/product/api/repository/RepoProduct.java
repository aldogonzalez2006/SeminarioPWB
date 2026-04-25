package com.product.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.product.api.entity.Product;

@Repository
public interface RepoProduct extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product ORDER BY product_id", nativeQuery = true)
    List<Product> getAll();

    @Query(value = "SELECT * FROM product WHERE status = 1 ORDER BY product", nativeQuery = true)
    List<Product> findActive();

    @Query(value = "SELECT * FROM product WHERE gtin = :gtin", nativeQuery = true)
    Product findByGtin(@Param("gtin") String gtin);

    @Query(value = "SELECT * FROM product WHERE category_id = :category_id AND status = 1", nativeQuery = true)
    List<Product> findByCategoryId(@Param("category_id") Integer category_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO product (gtin, product, description, price, stock, category_id, status) "
                 + "VALUES (:gtin, :product, :description, :price, :stock, :category_id, 1)", nativeQuery = true)
    void create(@Param("gtin") String gtin,
                @Param("product") String product,
                @Param("description") String description,
                @Param("price") Float price,
                @Param("stock") Integer stock,
                @Param("category_id") Integer category_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET gtin = :gtin, product = :product, description = :description, price = :price, stock = :stock, category_id = :category_id "
                 + "WHERE product_id = :product_id", nativeQuery = true)
    void update(@Param("product_id") Integer product_id,
                @Param("gtin") String gtin,
                @Param("product") String product,
                @Param("description") String description,
                @Param("price") Float price,
                @Param("stock") Integer stock,
                @Param("category_id") Integer category_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET stock = :stock WHERE product_id = :product_id", nativeQuery = true)
    void updateStock(@Param("product_id") Integer product_id, @Param("stock") Integer stock);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET status = 0 WHERE product_id = :product_id", nativeQuery = true)
    void disable(@Param("product_id") Integer product_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET status = 1 WHERE product_id = :product_id", nativeQuery = true)
    void enable(@Param("product_id") Integer product_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product SET status = :status WHERE product_id = :id", nativeQuery = true)
    void switchStatus(@Param("id") Integer id, @Param("status") Integer status);
}
