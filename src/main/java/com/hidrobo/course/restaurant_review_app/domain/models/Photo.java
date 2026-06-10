package com.hidrobo.course.restaurant_review_app.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "photos")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class Photo {

        @Id
        // @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @Column(nullable = false, unique = true)
        private String filename;
        
        @Column(nullable = false)
        private LocalDateTime uploadDate;

        @PrePersist
        private void uploadOn(){
            this.uploadDate = LocalDateTime.now();
        }

        @Override
        public String toString() {
            return "Photo [id=" + id + ", filename=" + filename + ", uploadDate=" + uploadDate + "]";
        }


        
}
