package com.gri.alex.loans.repository;

import com.gri.alex.loans.entity.Loan;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

  Optional<Loan> findByMobileNumber(String mobileNumber);

  Optional<Loan> findByLoanNumber(String loanNumber);

}
