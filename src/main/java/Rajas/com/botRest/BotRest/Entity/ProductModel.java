package Rajas.com.botRest.BotRest.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EnableJpaRepositories
@Table(name="products")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProdId",nullable = false)
    private long prodId;

    @ManyToOne
    @JoinColumn(name = "CatId")
    private CategoryModel categoryModel;
    //
    @ManyToOne
    @JoinColumn(name = "hsnCode")
    private HSNCode hsnCode;

    @Column(name = "Name")
    private String name;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "Price")
    private int price;

    @Column(name = "photo_path")
    private String photoPath;

     @Column(name = "suggestion")
    private long suggestedProductId;

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public HSNCode getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(HSNCode hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public long getSuggestedProductId() {
        return suggestedProductId;
    }

    public void setSuggestedProductId(long suggestedProductId) {
        this.suggestedProductId = suggestedProductId;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "prodId=" + prodId +
                ", categoryModel=" + categoryModel +
                ", hsnCode=" + hsnCode +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", photoPath='" + photoPath + '\'' +
                ", suggestedProductId=" + suggestedProductId +
                '}';
    }

    //    @ManyToMany(mappedBy = "products")
//    private List<Cart> cart =new ArrayList<Cart>();

//    @ManyToMany(mappedBy = "products")
//    private List<Cart>carts =new ArrayList<Cart>();

}

