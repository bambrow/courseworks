package studio7;

public class Account {

	// an account has-a balance

	private int balance;

	public Account() {
		this.balance = 0;  // a new account w/o funds to start
	}

	/**
	 * 
	 * @param initialFunds initial money in this account, in dollars
	 */
	public Account(int initialFunds) {
		this.balance = initialFunds;
	}

	/**
	 * Make a deposit
	 * @param howMuch the amount of the deposit in dollars
	 * @return the balance after the money is deposited
	 */
	public double deposit(int howMuch) {
		this.balance += howMuch;  // add the funds to 
		return this.balance;
	}

	/**
	 * Withdraw some money
	 * @param howMuch money to be withdraw, in dollars
	 * @return the balance after the money is withdrawn
	 */
	public double withdraw(int howMuch) {
		if (howMuch > this.balance) {
			return this.balance;
		}
		else 
			//
			// delegate to deposit by passing the negative amount of money to be added
			//
			return deposit(-howMuch);
	}
	
	/**
	 * 
	 * @return current balance of this account
	 */
	public double getBalance() {
		// sleezzy but it works
		return withdraw(0);
	}
	
	public String toString() {
		if (this.balance == 1) {
			return "Account with " + this.balance+ " buck";
		}
		else return "Account with " + this.balance+ " bucks";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Account a1 = new Account();
		a1.deposit(1);
		System.out.println("a1 has " + a1.getBalance());
		a1.deposit(99);
		System.out.println("a1 has " + a1);

	}

}
