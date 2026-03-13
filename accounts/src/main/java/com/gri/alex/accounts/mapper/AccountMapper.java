package com.gri.alex.accounts.mapper;

import com.gri.alex.accounts.dto.AccountDto;
import com.gri.alex.accounts.entity.Account;

public class AccountMapper {

  public static AccountDto mapToAccountDto(Account accounts, AccountDto accountDto) {
    accountDto.setAccountNumber(accounts.getAccountNumber());
    accountDto.setAccountType(accounts.getAccountType());
    accountDto.setBranchAddress(accounts.getBranchAddress());
    return accountDto;
  }

  public static Account mapToAccount(AccountDto accountDto, Account accounts) {
    accounts.setAccountNumber(accountDto.getAccountNumber());
    accounts.setAccountType(accountDto.getAccountType());
    accounts.setBranchAddress(accountDto.getBranchAddress());
    return accounts;
  }

}