
public class TechnicalEmployee extends Employee{
	private double oldSalary = 0;// we will save the old salary to check if we need to update the employee's salary 
	
	public TechnicalEmployee (int ID, String fName, int age, char gender, char shirtSize) {
		super(ID,fName,age,gender,shirtSize);
		this.commRate = 5;
	}
	public void updateBonus() { //this method update the employees cloth bonus when he join to the company
		if(shirtSize == 's')
			this.bonus = 30;
		else if(shirtSize == 'm' || shirtSize == 'l')
			this.bonus = 35;
		else 
			this.bonus = 40;	
	}

	public void technicalService (ElectricScooter es) { // this method will simulate a technical service
		if(es instanceof QuickElectricScooter) {
			System.out.println("Thank you for coming to us");
			System.out.println("the model of your scooter is: " + es.getModel());
			System.out.println("your max speed is: " + es.getMaxSpeed());
			System.out.println("Hope not to see you again.");
		}
		else 
			System.out.println("The model of your scooter is: " +es.getModel()+", your max speed is: " + es.getMaxSpeed());
		double p = Math.random();
		Customer c = Company.getCustomerByScooterSN(es.getSN());
		if(p<0.3) {
			c.setLastCharge(10);
		}
		else if(0.3<=p && p<0.5) {
			c.setLastCharge(30);
		}
		else if(0.5<=p && p<0.9) {
			c.setLastCharge(80);
		}
		else {
			c.setLastCharge(120);
		}
		c.setProfitFromLastCall(commRate);
	}
	public void updateSalary() {// when a customer uses technical service we add to his salary his commRate
		this.salary = this.salary + commRate;
	}
	public boolean update() {//this method update the commrate of the technical employee
		if(salary >= 1.5 * oldSalary) {
			this.oldSalary = this.salary;
			this.commRate = this.commRate + 2;
			return true;
		}
		return false;
	}
}
