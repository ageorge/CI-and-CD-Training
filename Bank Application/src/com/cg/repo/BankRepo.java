package com.cg.repo;

import com.cg.exceptions.InvalidAccountNoException;
import com.cg.model.Customer;

public interface BankRepo {
	boolean saveAccount(Customer cust);
	Customer searchCust(int accno) throws InvalidAccountNoException;
}