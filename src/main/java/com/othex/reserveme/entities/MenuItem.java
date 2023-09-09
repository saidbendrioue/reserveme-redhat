package com.othex.reserveme.entities;

import com.othex.reserveme.entities.commons.AbstractAuditingEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "menu_items")
public class MenuItem extends AbstractAuditingEntity {

    @Column(name = "dish_name")
    private String dishName;

    @Column(name = "categoriy")
    private String categoriy;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price")
    private double price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nutritional_info_id")
    private NutritionalInformation nutritionalInformation;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Override
    public String toString() {
        return "MenuItem{" +
                "id='" + id + '\'' +
                ", dishName='" + dishName + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", nutritionalInformation=" + nutritionalInformation +
                ", menu=" + menu +
                '}';
    }

}
