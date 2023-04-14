package com.resteng.resteng.classes.bankAccount;


import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepo extends JpaRepository<BankAccount, Long> {

}
