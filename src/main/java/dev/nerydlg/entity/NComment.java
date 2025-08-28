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
@Table(name="n_comment")
@AllArgsConstructor
@NoArgsConstructor
public class NComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "blog_id")
    private NBlog blog;
    
    @Column(name = "author")
    private String author;
    
    @Column(name = "content")
    private String content;
    
    @Column(name = "created_at")
    private Timestamp createdAt;
}