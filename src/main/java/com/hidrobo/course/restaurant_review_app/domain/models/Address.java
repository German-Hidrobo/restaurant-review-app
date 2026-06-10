package com.hidrobo.course.restaurant_review_app.domain.models;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 10)
    private String streetNumber;
    
    @Column(nullable = false, length = 10)
    private String streetName;

    @Column(nullable = false, length = 10)
    private String unit;
    
    @Column(nullable = false, length = 55)
    private String city;
    
    @Column(nullable = false, length = 55)
    private String state;
    
    @Column(nullable = false, length = 10)
    private String postalCode;
    
    @Column(nullable = false, length = 55)
    private String country;

    @Override
    public String toString() {
        return "Address [id=" + id + ", streetNumber=" + streetNumber + ", streetName=" + streetName + ", city=" + city
                + ", state=" + state + ", postalCode=" + postalCode + ", country=" + country + "]";
    }


    

}
