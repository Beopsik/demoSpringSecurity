package com.example.demospringsecurity.common;

import com.example.demospringsecurity.account.Account;
import com.example.demospringsecurity.account.AccountService;
import com.example.demospringsecurity.book.Book;
import com.example.demospringsecurity.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataGenerator implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account account1 = createUser("beobsik");
        Account account2 = createUser("moon");
        createBook("spring", account1);
        createBook("hibernate", account2);
    }

    private Account createUser(String name){
        Account account = new Account();
        account.setUsername(name);
        account.setPassword("123");
        account.setRole("USER");

        return accountService.createNew(account);
    }

    private void createBook(String title, Account account) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(account);
        bookRepository.save(book);
    }
}
