
public class QuickElectricScooter extends ElectricScooter {
	private double appVersion; // Quick electric scooter has appVersion

	public QuickElectricScooter (int serialNumber, int price, String model, int maxSpeed, double appVersion) { // this constructor create a quick scooter
		super(serialNumber,price,model,maxSpeed);
		this.appVersion=appVersion;
	}
	public boolean update() { // this method update the scooter price if it is popular 
		int scooter = Company.isPopular();
		if(scooter == 1) {
			this.price = (int)(1.1*this.price);
			return true;	
		}
		return false;
	}
}
