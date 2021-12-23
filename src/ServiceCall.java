
public class ServiceCall implements Comparable<ServiceCall>,profitable{
	private Customer c; 
	private Employee e;
	private String serviceType;
	private ElectricScooter es;
	private double profit = 0;

	public ServiceCall (Customer c, Employee e, String serviceType, ElectricScooter es) { // this constructor apply a service call 
		this.c = c;
		this.e = e;
		this.serviceType = serviceType;
		this.es = es;
	}
	public void updateProfit() { // we set the profit from each service call 
		if(serviceType.equals("Sales"))
			this.profit = es.getPrice() - es.getPrice()*e.getCommRate();
		if(serviceType.equals("Technical"))
			this.profit = c.getLastCharge() - e.getCommRate();
	}
	public double getProfit() { // this method return the profit
		return this.profit;
	}
	public ElectricScooter getScooter() { // this method return the scooter
		return this.es;
	}
	public Employee getEmployee() { // this method return the employee
		return this.e;
	}
	public Customer getCustomer() { // this method return the customer
		return this.c;
	}
	public String getServiceType() { // this method returns the service type
		return this.serviceType;
	}
	public int compareTo(ServiceCall other){ // this method compare two service calls by their profit
		if( this.profit > other.profit )
			return 1;
		if( this.profit < other.profit )
			return -1;
		return 0;
	}
}
