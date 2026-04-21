package com.product.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.product.api.entity.ProductImage;

@Repository
public interface RepoProductImage extends JpaRepository<ProductImage, Integer> {

    @Query(value = "SELECT * FROM product_image", nativeQuery = true)
    List<ProductImage> getAll();

    @Query(value = "SELECT * FROM product_image WHERE status = 1", nativeQuery = true)
    List<ProductImage> findActive();

    @Query(value = "SELECT * FROM product_image WHERE product_id = :productId AND status = 1", nativeQuery = true)
    List<ProductImage> findByProductIdAndStatus(@Param("productId") Integer productId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO product_image (product_id, image, status) VALUES (:product_id, :image, 1)", nativeQuery = true)
    void create(@Param("product_id") Integer product_id, @Param("image") String image);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_image SET image = :image WHERE product_image_id = :product_image_id", nativeQuery = true)
    void update(@Param("product_image_id") Integer product_image_id, @Param("image") String image);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_image SET status = 0 WHERE product_image_id = :product_image_id", nativeQuery = true)
    void disable(@Param("product_image_id") Integer product_image_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_image SET status = 1 WHERE product_image_id = :product_image_id", nativeQuery = true)
    void enable(@Param("product_image_id") Integer product_image_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE product_image SET status = :status WHERE product_image_id = :id", nativeQuery = true)
    void switchStatus(@Param("id") Integer id, @Param("status") Integer status);
}
