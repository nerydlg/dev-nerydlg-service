package dev.nerydlg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name="n_blog")
@AllArgsConstructor
@NoArgsConstructor
public class NBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "publication_date")
    private Timestamp publicationDate;
    
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "lang_code")
    private String langCode;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private NUser user;
    
    @ManyToOne
    @JoinColumn(name = "domain_id")
    private NDomain domain;
}