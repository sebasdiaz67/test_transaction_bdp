package com.pichincha.backend.test.repository;

import com.pichincha.backend.test.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
