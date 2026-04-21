package com.gri.alex.accounts.repository;

import com.gri.alex.accounts.entity.Account;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  Optional<Account> findByCustomerId(Long customerId);

  @Transactional
  @Modifying
  void deleteByCustomerId(Long customerId);

}
