package com.bank.loans.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.loans.model.Loan;

@Repository
public interface LoansRepository extends CrudRepository<Loan, Long> {


    List<Loan> findByCustomerIdOrderByStartDtDesc(int customerId);

}
