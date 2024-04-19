package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.projection.CategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepo extends JpaRepository<Category, UUID> {
    @Query(value = """
  select c.id as id, c.name as name from category c
""", nativeQuery = true)
    List<CategoryProjection> getCategories();
}
