package com.exercise.product.category;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.*;

import java.sql.Timestamp;

@Entity
@Table(name = "category")
@SQLDelete(sql = "UPDATE table_product SET deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @Column(name = "createdAt")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
