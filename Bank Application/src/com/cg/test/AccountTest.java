package com.cg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.exceptions.InsufficientBalanceException;
import com.cg.exceptions.InsufficientIntialBalanceException;
import com.cg.exceptions.InvalidAccountNoException;
import com.cg.model.Customer;
import com.cg.repo.BankRepo;
import com.cg.service.BankService;
import com.cg.service.BankServiceImpl;

public class AccountTest {

	@Mock
	BankRepo repo;
	
	BankService service;
	
	Customer cust;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		service = new BankServiceImpl(repo);
		cust = new Customer();
		cust.setAcc_no(1004);
		cust.setBalance(5000);
	}
	
	/* create account
	 * 1. when the amount is less than 500, system should throw exception
	 * 2. when the valid info is passed account should be created successfully.
	 * 
	 */

	
	@Test(expected = com.cg.exceptions.InsufficientIntialBalanceException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientIntialBalanceException {
		service.createAccount(400, 1001);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientIntialBalanceException
	{
		when(repo.saveAccount(cust)).thenReturn(true);
		assertEquals(cust, service.createAccount(5000, 1004));
	}
	
	/*
	 * deposit
	 * 1. when amt is <= 0 then system should throw exception
	 * 2. when account number is invalid system should throw InvalidAccountNoException
	 * 3. when valid info is passed system should return new balance
	 */
	
	@Test(expected = java.lang.Exception.class)
	public void whenamtislessthan0systemshouldthrowexception() throws Exception {
		service.deposit(1004, 0);
	}
	
	@Test(expected = com.cg.exceptions.InvalidAccountNoException.class)
	public void wheninvalidaccnoispassedsystemshouldthrowexception() throws InvalidAccountNoException,Exception {
		service.deposit(52, 452);
	}
	
	@Test
	public void whenvalidinfoispassedsystemshouldreturnnewbalance() throws Exception {
		
		when(repo.searchCust(1004)).thenReturn(cust);
		assertEquals(7000, service.deposit(1004, 2000));
		
	}
	
	/*
	 * withdraw
	 * 1. when amt > bal then system should throw InsufficientBalanceException
	 * 2. when account number is invalid system should throw InvalidAccountNoException
	 * 3. when valid info is passed system should return new balance
	 */
	
	@Test(expected = com.cg.exceptions.InsufficientBalanceException.class)
	public void whenamtislessthanbalancesystemshouldthrowexception() throws InsufficientBalanceException, InvalidAccountNoException {
		
		when(repo.searchCust(1004)).thenReturn(cust);
		service.withdraw(1004, 100000);
	}
	
	@Test(expected = com.cg.exceptions.InvalidAccountNoException.class)
	public void wheninvalidaccnoispassedforwithdrawalsystemshouldthrowexception() throws InvalidAccountNoException,InsufficientBalanceException {
		service.withdraw(41, 2000);
	}
	
	@Test
	public void whenvalidinfoispassedforwithdrawsystemshouldreturnnewbalance() throws InsufficientBalanceException, InvalidAccountNoException  {
		
		when(repo.searchCust(1004)).thenReturn(cust);
		assertEquals(3000, service.withdraw(1004, 2000));
	}
	
	/*
	 * transfer fund
	 * 1. when amt > bal then system should throw InsufficientBalanceException
	 * 2. when account number is invalid system should throw InvalidAccountNoException
	 * 3. when valid info is passed system should return true
	 */
	
	@Test(expected = com.cg.exceptions.InsufficientBalanceException.class)
	public void whenamtislessthanbalanceincust_fromsystemshouldthrowexception() throws InsufficientBalanceException, InvalidAccountNoException {
		
		Customer cust2 = new Customer();
		cust2.setAcc_no(1005);
		cust2.setBalance(3000);
		
		when(repo.searchCust(1004)).thenReturn(cust);
		when(repo.searchCust(1005)).thenReturn(cust2);
		
		service.transfer_fund(1004, 1005, 1000000);
	}
	
	@Test(expected = com.cg.exceptions.InvalidAccountNoException.class)
	public void wheninvalidaccnoispassedforcustsystemshouldthrowexception() throws InvalidAccountNoException,InsufficientBalanceException {
		service.transfer_fund(102, 15, 200);
	}
	
	@Test
	public void whenvalidinfoispassedsystemshouldreturntrue() throws InvalidAccountNoException,InsufficientBalanceException {
		
		Customer cust2 = new Customer();
		cust2.setAcc_no(1005);
		cust2.setBalance(3000);
		
		when(repo.searchCust(1004)).thenReturn(cust);
		when(repo.searchCust(1005)).thenReturn(cust2);
		
		assertTrue(service.transfer_fund(1004, 1005, 2000));
	}
	
	/*
	 * show balance
	 * 1. when invalid accno is passed system should throw InvalidAccountNoException
	 * 2. when valid info is passed system should return balance
	 */
	
	@Test(expected = com.cg.exceptions.InvalidAccountNoException.class)
	public void wheninvalidaccnumberispassedsystemshouldthrowexception() throws InvalidAccountNoException {
		service.show_balance(10);
	}
	
	@Test
	public void whenvalidinfoispassedforshowbalsystemshouldreturnnewbalance() throws InvalidAccountNoException {
		
		when(repo.searchCust(1004)).thenReturn(cust);
		
		assertEquals(5000, service.show_balance(1004));
		
	}
}
