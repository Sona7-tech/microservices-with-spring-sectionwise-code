package com.bank.accounts.repository;

import com.bank.accounts.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Account, Long> {

    Account findByCustomerId(int customerId);

}
