package com.resteng.resteng.classes.account;

import org.springframework.data.jpa.repository.JpaRepository;

 interface AccountRep extends JpaRepository<Account,Long>{
    
}
