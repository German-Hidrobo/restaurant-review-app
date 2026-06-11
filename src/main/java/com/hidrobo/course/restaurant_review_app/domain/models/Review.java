package com.hidrobo.course.restaurant_review_app.domain.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviews", uniqueConstraints = @UniqueConstraint(name = "unique_user_restaurant_constraint", columnNames = {"written_by", "restaurant_id" }))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private LocalDateTime datePosting;

    @Column(nullable = false)
    private LocalDateTime lastEdited;

    @OneToMany
    private List<Photo> photos;

    @ManyToOne
    @JoinColumn(name = "written_by")
    private User writtenBy;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "Review [id=" + id + ", content=" + content + ", rating=" + rating + ", datePosting=" + datePosting
                + ", lastEdited=" + lastEdited + "]";
    }

    @PrePersist
    private void createOn() {
        LocalDateTime now = LocalDateTime.now();
        this.datePosting = now;
        this.lastEdited = now;
    }

    @PreUpdate
    private void updateOn() {
        this.lastEdited = LocalDateTime.now();
    }

}
