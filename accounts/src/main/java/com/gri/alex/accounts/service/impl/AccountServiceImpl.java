package com.gri.alex.accounts.service.impl;

import com.gri.alex.accounts.constants.AccountConstants;
import com.gri.alex.accounts.dto.AccountDto;
import com.gri.alex.accounts.dto.CustomerDto;
import com.gri.alex.accounts.entity.Account;
import com.gri.alex.accounts.entity.Customer;
import com.gri.alex.accounts.exception.CustomerAlreadyExistsException;
import com.gri.alex.accounts.exception.ResourceNotFoundException;
import com.gri.alex.accounts.mapper.AccountMapper;
import com.gri.alex.accounts.mapper.CustomerMapper;
import com.gri.alex.accounts.repository.AccountsRepository;
import com.gri.alex.accounts.repository.CustomerRepository;
import com.gri.alex.accounts.service.IAccountService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

  private AccountsRepository accountsRepository;
  private CustomerRepository customerRepository;

  /**
   * @param customerDto - CustomerDto Object
   */
  @Override
  public void createAccount(CustomerDto customerDto) {
    Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
    Optional<Customer> optionalCustomer =
        customerRepository.findByMobileNumber(customerDto.getMobileNumber());
    if (optionalCustomer.isPresent()) {
      throw new CustomerAlreadyExistsException(
          "Customer already registered with given mobileNumber "
              + customerDto.getMobileNumber());
    }
    // todo: temporary workaround
    customer.setCreatedAt(LocalDateTime.now());
    customer.setCreatedBy("Anonymous");

    Customer savedCustomer = customerRepository.save(customer);
    accountsRepository.save(createNewAccount(savedCustomer));
  }

  /**
   * @param customer - Customer Object
   * @return the new account details
   */
  private Account createNewAccount(Customer customer) {
    Account newAccount = new Account();
    newAccount.setCustomerId(customer.getCustomerId());
    long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

    newAccount.setAccountNumber(randomAccNumber);
    newAccount.setAccountType(AccountConstants.SAVINGS);
    newAccount.setBranchAddress(AccountConstants.ADDRESS);
    // todo: temporary workaround
    newAccount.setCreatedAt(LocalDateTime.now());
    newAccount.setCreatedBy("Anonymous");

    return newAccount;
  }

  /**
   * @param mobileNumber - Input Mobile Number
   * @return Accounts Details based on a given mobileNumber
   */
  @Override
  public CustomerDto fetchAccount(String mobileNumber) {
    Customer customer = customerRepository
        .findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

    Account account = accountsRepository
        .findByCustomerId(customer.getCustomerId())
        .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId",
            customer.getCustomerId().toString()));

    CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
    customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

    return customerDto;
  }
}
