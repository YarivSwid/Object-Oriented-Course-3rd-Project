import java.util.Vector;

public class Customer implements Comparable<Customer>, profitable{
	private int ID;
	private String fname;
	private int age;
	private char gender;
	private boolean helmet;
	private boolean lock;
	private ElectricScooter scooter;
	private double profit = 0;
	private double profitFromLastCall = 0;
	private double lastCharge = 0;

	public Customer (int ID, String fname, int age, char gender, boolean helmet, boolean lock, ElectricScooter scooter) {//this constructor creates a customer
		if(gender!='u' && gender!='m' && gender!='f') //if the gender is not one of those we will throw an exception
			throw new genderException("your gender is : "+gender+". please enter one of these options: m/f/u ");
		this.ID = ID;
		this.fname = fname;
		this.age = age;
		this.gender = gender;
		this.helmet = helmet;
		this.lock = lock;
		this.scooter = scooter;
	}
	public Customer (int ID, String fname, int age, char gender,  boolean helmet, boolean lock) {//this constructor creates a customer without a scooter
		if(gender!='u' && gender!='m' && gender!='f') //if the gender is not one of those we will throw an exception
			throw new genderException("your gender is : "+gender+". please enter one of these options: m/f/u ");
		this.ID = ID;
		this.fname = fname;
		this.age = age;
		this.gender = gender;
		this.helmet = helmet;
		this.lock = lock;	
	}

	public static Customer MaxSpeedCompare(Vector<Customer> c, MaxSpeedComparator comp) {//this method returns the customer that has the scooter with the maximum speed
		Customer max = c.elementAt(0);
		for(int index =0;index < c.size();index++)
		{
			if(comp.compare(c.elementAt(index),max)>=0){
				max=c.elementAt(index);
			}
		}
		return max;	
	}
	public ElectricScooter getScooter() {//returns the scooter of the customer
		return this.scooter;
	}
	public void setScooter(ElectricScooter es) {//update the scooter of the customer
		this.scooter = es;
	}
	public void updateProfit() {// update the profit 
		this.profit = this.profit + this.profitFromLastCall ;
	}
	public double getProfit() {// returns the profit
		return this.profit;
	}
	public void setProfitFromLastCall(double payment) {// update the profit from the last call
		this.profitFromLastCall = this.lastCharge - payment;
		this.updateProfit();
	}
	public double getLastCharge() {// returns the last charge
		return this.lastCharge;
	}
	public void setLastCharge(int charge) {// update the last charge of the customer
		this.lastCharge = charge;
	}
	public int getID() {// returns the ID of the customer
		return this.ID;
	}
	public void enteringTheCompany() { // when a customer will enter the company he will pay 30$
		this.profit = 30;
	}
	public int compareTo(Customer other){//compare between to customers by the profit to the company 
		if( this.profit > other.profit )
			return 1;
		if( this.profit < other.profit )
			return -1;
		return 0;
	}
}
