package com.gri.alex.loans.service.impl;

import com.gri.alex.loans.constants.LoanConstants;
import com.gri.alex.loans.dto.LoanDto;
import com.gri.alex.loans.entity.Loan;
import com.gri.alex.loans.exception.LoanAlreadyExistsException;
import com.gri.alex.loans.exception.ResourceNotFoundException;
import com.gri.alex.loans.mapper.LoanMapper;
import com.gri.alex.loans.repository.LoanRepository;
import com.gri.alex.loans.service.LoanService;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

  private LoanRepository loanRepository;

  /**
   * @param mobileNumber - Mobile Number of the Customer
   */
  @Override
  public void createLoan(String mobileNumber) {
    Optional<Loan> optionalLoans = loanRepository.findByMobileNumber(mobileNumber);
    if (optionalLoans.isPresent()) {
      throw new LoanAlreadyExistsException(
          "Loan already registered with given mobileNumber " + mobileNumber);
    }
    loanRepository.save(createNewLoan(mobileNumber));
  }

  /**
   * @param mobileNumber - Mobile Number of the Customer
   * @return the new loan details
   */
  private Loan createNewLoan(String mobileNumber) {
    Loan newLoan = new Loan();
    long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
    newLoan.setLoanNumber(Long.toString(randomLoanNumber));
    newLoan.setMobileNumber(mobileNumber);
    newLoan.setLoanType(LoanConstants.HOME_LOAN);
    newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
    newLoan.setAmountPaid(0);
    newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);

    return newLoan;
  }

  /**
   * @param mobileNumber - Input mobile Number
   * @return Loan Details based on a given mobileNumber
   */
  @Override
  public LoanDto fetchLoan(String mobileNumber) {
    Loan loan = loanRepository
        .findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));

    return LoanMapper.mapToLoanDto(loan, new LoanDto());
  }

  /**
   * @param loanDto - LoansDto Object
   * @return boolean indicating if the update of loan details is successful or not
   */
  @Override
  public boolean updateLoan(LoanDto loanDto) {
    Loan loan = loanRepository
        .findByLoanNumber(loanDto.getLoanNumber())
        .orElseThrow(
            () -> new ResourceNotFoundException("Loan", "LoanNumber", loanDto.getLoanNumber()));
    LoanMapper.mapToLoan(loanDto, loan);
    loanRepository.save(loan);

    return true;
  }

  /**
   * @param mobileNumber - Input MobileNumber
   * @return boolean indicating if the delete of loan details is successful or not
   */
  @Override
  public boolean deleteLoan(String mobileNumber) {
    Loan loan = loanRepository
        .findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
    loanRepository.deleteById(loan.getLoanId());

    return true;
  }

}
