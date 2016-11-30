package com.cg.service;

import com.cg.exceptions.InsufficientBalanceException;
import com.cg.exceptions.InsufficientIntialBalanceException;
import com.cg.exceptions.InvalidAccountNoException;
import com.cg.model.Customer;
import com.cg.repo.BankRepo;

public class BankServiceImpl implements BankService {
	
	BankRepo repo;
	
	public BankServiceImpl(BankRepo repo) {
		this.repo = repo;
	}

	@Override
	public Customer createAccount(int amt, int accno) throws InsufficientIntialBalanceException {
		if(amt < 500)
			throw new InsufficientIntialBalanceException();
		
		Customer cust = new Customer();
		cust.setAcc_no(accno);
		cust.setBalance(amt);
		
		if(repo.saveAccount(cust)){
			return cust;
		}
		
		return null;
	}

	@Override
	public int deposit(int accno, int amt) throws InvalidAccountNoException, Exception {
		
		if(amt<=0)
			throw new Exception("Amount should be greater than zero");
		
		if(Integer.toString(accno).length()<4)
			throw new InvalidAccountNoException();
		
		Customer cust = repo.searchCust(accno);
		
		if(cust!=null){
			int bal = cust.getBalance();
			cust.setBalance(bal+amt);
			return cust.getBalance();
		}
		
		return 0;
	}

	@Override
	public int withdraw(int accno, int amt) throws InsufficientBalanceException, InvalidAccountNoException {
		
		if(Integer.toString(accno).length()<4)
			throw new InvalidAccountNoException();
		
		Customer cust = repo.searchCust(accno);
		
		if(cust!=null){
			int bal = cust.getBalance();
			
			if(amt>bal)
				throw new InsufficientBalanceException();
			
			cust.setBalance(bal-amt);
			return cust.getBalance();
		}
		
		
		return 0;
	}

	@Override
	public boolean transfer_fund(int acc1, int acc2, int amt)
			throws InsufficientBalanceException, InvalidAccountNoException {
		if(Integer.toString(acc1).length()<4)
			throw new InvalidAccountNoException();
		
		if(Integer.toString(acc2).length()<4)
			throw new InvalidAccountNoException();
		
		Customer cust1 = repo.searchCust(acc1);
		Customer cust2 = repo.searchCust(acc2);
		
		if((cust1!=null)&&(cust2!=null)){
			int bal1 = cust1.getBalance();
			int bal2 = cust2.getBalance();
			if(amt>bal1)
				throw new InsufficientBalanceException();
			
			cust1.setBalance(bal1-amt);
			cust2.setBalance(bal2+amt);
			
			if(bal1+bal2==cust1.getBalance()+cust2.getBalance())
				return true;
		}	
			
		return false;
	}

	@Override
	public int show_balance(int accno) throws InvalidAccountNoException {
		
		if(Integer.toString(accno).length()<4)
			throw new InvalidAccountNoException();
		
		Customer cust = repo.searchCust(accno);
		
		if(cust!=null){
			int bal = cust.getBalance();
			return bal;
		}
		return 0;
	}
	
}
