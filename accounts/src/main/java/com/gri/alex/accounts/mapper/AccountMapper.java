package com.gri.alex.accounts.mapper;

import com.gri.alex.accounts.dto.AccountsDto;
import com.gri.alex.accounts.entity.Account;

public class AccountMapper {

  public static AccountsDto mapToAccountDto(Account accounts, AccountsDto accountsDto) {
    accountsDto.setAccountNumber(accounts.getAccountNumber());
    accountsDto.setAccountType(accounts.getAccountType());
    accountsDto.setBranchAddress(accounts.getBranchAddress());
    return accountsDto;
  }

  public static Account mapToAccount(AccountsDto accountsDto, Account accounts) {
    accounts.setAccountNumber(accountsDto.getAccountNumber());
    accounts.setAccountType(accountsDto.getAccountType());
    accounts.setBranchAddress(accountsDto.getBranchAddress());
    return accounts;
  }

}