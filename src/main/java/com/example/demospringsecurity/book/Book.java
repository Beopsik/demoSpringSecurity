package com.example.demospringsecurity.book;

import com.example.demospringsecurity.account.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Book {

    @Id @GeneratedValue
    private Integer id;

    private String title;

    @ManyToOne
    private Account author;
}
