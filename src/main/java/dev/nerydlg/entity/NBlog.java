package dev.nerydlg.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "summary")
    private String summary;
    
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

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NBlogTags> blogTags = new ArrayList<>();

    @Transient
    public List<NTag> getTags() {
        return blogTags.stream().map(NBlogTags::getTag).toList();
    }

    @Transient
    public void setTags(List<NTag> tags) {
        for(NTag tag : tags) {
            NBlogTags blogTags = new NBlogTags();
            blogTags.setTag(tag);
            blogTags.setBlog(this);
            this.blogTags.add(blogTags);
        }
    }

}