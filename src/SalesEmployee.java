
public class SalesEmployee extends Employee {
	private int currentNumOfSales = 0; // we will keep the current number of sales 
	private int oldNumOfSales = 0; // we will keep the old number of sales

	public SalesEmployee (int ID, String fName, int age, char gender, char shirtSize, double commRate) {//this constructor create sales employee
		super(ID,fName,age,gender,shirtSize);
		this.commRate=commRate;
	}
	public void updateBonus() {//this method update the employees cloth bonus when he join to the company
		if(shirtSize == 's')
			this.bonus = 10;
		else if(shirtSize == 'm')
			this.bonus = 15;
		else if(shirtSize == 'l')
			this.bonus = 20;
		else 
			this.bonus = 25;
	}
	public ElectricScooter sellScooter(Customer c,boolean quickES) {// this function simulate a sale in progress
		ElectricScooter forSale = Company.findMinPriceScooter(quickES); // we get the minimum price scooter
		currentNumOfSales = currentNumOfSales +	forSale.getPrice(); // we update the current number of sales
		c.setLastCharge(forSale.getPrice()); // the employee charge the customer
		Company.removeScooter(forSale); 
		return forSale;

	}
	public void setSalary(int price) {//this method update the salary of the employee
		this.salary = salary + commRate*price;	
	}
	public boolean update() {//this method update the commrate of the sales employee
		if(currentNumOfSales >= 2*oldNumOfSales && commRate < 0.3) {
			oldNumOfSales = currentNumOfSales;
			this.commRate = this.commRate+(this.commRate*0.02);
			if(this.commRate >= 0.3)
				this.commRate = 0.3;
			return true;
		}
		return false;
	}
}
