package com.api.book.bootrestbook.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int authorId;
    @Column(name="first_name")
    private String firstName;
    private String lastName;
    private String language;
    @OneToOne(mappedBy = "author")
    //@JsonBackReference tells us that we donot need to do recursive call again to Book class
    @JsonBackReference
    private Book book;
    
    @Override
    public String toString() {
        return "Author [authorId=" + authorId + ", language=" + language + ", lastName=" + lastName + "]";
    }

    public Author() {
    }

    public Author(int authorId,String firstName, String lastName, String language) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.language = language;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public int getAuthorId() {
        return authorId;
    }
    
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

}
