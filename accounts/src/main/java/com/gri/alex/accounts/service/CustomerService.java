package com.gri.alex.accounts.service;

import com.gri.alex.accounts.dto.CustomerDetailsDto;

public interface CustomerService {

  /**
   * @param mobileNumber - Input Mobile Number
   * @return Customer Details based on a given mobileNumber
   */
  CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
