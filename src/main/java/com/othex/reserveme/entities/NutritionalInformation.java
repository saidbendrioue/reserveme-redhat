package com.othex.reserveme.entities;

import com.othex.reserveme.entities.commons.AbstractAuditingEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "nutritional_information")
public class NutritionalInformation extends AbstractAuditingEntity {

    @Column(name = "protein")
    private double protein;

    @Column(name = "fat")
    private double fat;

    @Column(name = "carbohydrates")
    private double carbohydrates;

    @Override
    public String toString() {
        return "NutritionalInformation{" +
                "id='" + id + '\'' +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbohydrates=" + carbohydrates +
                '}';
    }

}
