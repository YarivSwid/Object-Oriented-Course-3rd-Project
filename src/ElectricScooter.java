
public class ElectricScooter implements Comparable<ElectricScooter>,updateable{
	protected int price; // scooter price
	protected int serialNumber; // scooter serial number
	protected String model; // scooter model
	protected int maxSpeed; // scooter max speed

	public ElectricScooter (int serialNumber, int price, String model, int maxSpeed) { // this constructor create a scooter
		if(price < 0) // if the price is negative we will throw exception
			throw new PriceException("the price is : " + price + ". please enter an integer bigger than 0 ");
		this.maxSpeed=maxSpeed; 
		this.model=model;
		this.price=price;
		this.serialNumber=serialNumber;
	}
	public String getModel() {//returns the model of the scooter
		return this.model;
	} 
	public int getSN() {//returns the Serial Number of the scooter
		return this.serialNumber;
	}
	public int getPrice() {//returns the price of the scooter
		return this.price;
	}
	public int getMaxSpeed() {//returns the maximum speed of the scooter
		return this.maxSpeed;
	}
	public int compareTo(ElectricScooter other) { // this method compare two scooters by their price 
		if( this.price > other.price )
			return 1;
		if( this.price < other.price )
			return -1;
		return 0;
	}
	public boolean update() { // this method update the scooter price if it is popular 
		int scooter = Company.isPopular();
		if(scooter == (-1)) {
			this.price = (int)(this.price + 0.1*this.price);
			return true;	
		}
		return false;
	}
}
