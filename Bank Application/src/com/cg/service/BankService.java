package com.cg.service;

import com.cg.exceptions.InsufficientBalanceException;
import com.cg.exceptions.InsufficientIntialBalanceException;
import com.cg.exceptions.InvalidAccountNoException;
import com.cg.model.Customer;

public interface BankService {
	Customer createAccount(int amt, int accno) throws InsufficientIntialBalanceException;
	int deposit(int accno, int amt) throws InvalidAccountNoException, Exception;
	int withdraw(int accno,int amt) throws InsufficientBalanceException, InvalidAccountNoException;
	boolean transfer_fund(int acc1,int acc2, int amt) throws InsufficientBalanceException,InvalidAccountNoException;
	int show_balance(int accno) throws InvalidAccountNoException;
}
