package com.example.adcash.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;


//  @ManyToOne
//  private Category category;
}
