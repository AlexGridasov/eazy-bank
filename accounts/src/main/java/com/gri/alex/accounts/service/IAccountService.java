package com.gri.alex.accounts.service;

import com.gri.alex.accounts.dto.CustomerDto;

public interface IAccountService {

  /**
   *
   * @param customerDto - CustomerDto Object
   */
  void createAccount(CustomerDto customerDto);
}
