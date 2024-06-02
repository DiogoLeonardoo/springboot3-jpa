package com.educandoweb.course.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    @ManyToMany

    //Associacao muitos para muitos com a tabela tb_category e a tabela tb_product_category
    //A tabela tb_product_category é a tabela intermediaria entre as tabelas tb_product e tb_category
    //A tabela tb_product_category é criada automaticamente pelo JPA
    //A tabela tb_product_category é criada com as chaves estrangeiras product_id e category_id
    //A anotacao @JoinTable é usada para definir o nome da tabela intermediaria e as chaves estrangeiras
    //A anotacao @JoinColumn é usada para definir o nome da chave estrangeira
    //A anotacao @InverseJoinColumn é usada para definir o nome da outra chave estrangeira
    //A anotacao @JoinTable é usada para definir o nome da tabela intermediaria e as chaves estrangeiras
    //A anotacao @JoinColumn é usada para definir o nome da chave estrangeira
    //A anotacao @InverseJoinColumn é usada para definir o nome da outra chave estrangeira


    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }


    //Equals e HashCode para comparar os objetos pelo id

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
