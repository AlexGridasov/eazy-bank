package com.gri.alex.accounts.service.impl;

import com.gri.alex.accounts.dto.AccountDto;
import com.gri.alex.accounts.dto.CardDto;
import com.gri.alex.accounts.dto.CustomerDetailsDto;
import com.gri.alex.accounts.dto.LoanDto;
import com.gri.alex.accounts.entity.Account;
import com.gri.alex.accounts.entity.Customer;
import com.gri.alex.accounts.exception.ResourceNotFoundException;
import com.gri.alex.accounts.mapper.AccountMapper;
import com.gri.alex.accounts.mapper.CustomerMapper;
import com.gri.alex.accounts.repository.AccountRepository;
import com.gri.alex.accounts.repository.CustomerRepository;
import com.gri.alex.accounts.service.CustomerService;
import com.gri.alex.accounts.service.client.CardsFeignClient;
import com.gri.alex.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private AccountRepository accountRepository;
  private CustomerRepository customerRepository;
  private CardsFeignClient cardsFeignClient;
  private LoansFeignClient loansFeignClient;

  /**
   * @param mobileNumber - Input Mobile Number
   * @return Customer Details based on a given mobileNumber
   */
  @Override
  public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
    Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
    );
    Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
        () -> new ResourceNotFoundException("Account", "customerId",
            customer.getCustomerId().toString())
    );

    CustomerDetailsDto customerDto =
        CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
    customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

    ResponseEntity<LoanDto> loansResponse =
        loansFeignClient.fetchLoanDetails(mobileNumber);
    customerDto.setLoanDto(loansResponse.getBody());

    ResponseEntity<CardDto> cardsResponse =
        cardsFeignClient.fetchCardDetails(mobileNumber);
    customerDto.setCardDto(cardsResponse.getBody());

    return customerDto;
  }
}
