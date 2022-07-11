package com.bank.accounts.controller;

import com.bank.accounts.model.Account;
import com.bank.accounts.model.Customer;
import com.bank.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {


    @Autowired
    private AccountsRepository accountsRepository;

    @PostMapping (value = "/myAccount")
    public Account getAccountDetails(@RequestBody Customer customer) {

        Account accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        if (accounts != null) {
            return accounts;
        } else {
            return null;
        }

    }

}
