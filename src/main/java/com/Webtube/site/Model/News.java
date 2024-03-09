package com.Webtube.site.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table(name = "News")
public class News {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "title")
    private String title;

    // Getter and setter for thumbnailUrl
    @Setter
    @Getter
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String thumbnailUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;


    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "category")
    private String category;

    // Custom formatter for date/time display
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm a");


    // Getters for formatted createdAt and updatedAt timestamps
    public String getFormattedCreatedAt() {
        return createdAt.format(formatter);
    }

    public String getFormattedUpdatedAt() {
        return updatedAt.format(formatter);
    }




    // Getters and setters

}
