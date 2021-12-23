import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/* the story behind the project in it's protocols is about one company that established in our country because electric scooters
 * became popular in the country. Thats why we understood that we will make this project for one company, and the company
 * will use the program to it needs. This is the reason why we used static vectors.
 */

public class Company {
	private static Vector<Customer> customersOfCompany = new Vector<Customer>(); // this vector contains the customers of the company
	private static Vector<ElectricScooter> scootersOfCompany = new Vector<ElectricScooter>();// this vector contains the scooters of the company that we can sale
	private static Vector<Employee> employeesOfCompany = new Vector<Employee>(); // this vector contains the employees of the company
	private static Vector<ServiceCall> serviceCallsOfCompany = new Vector<ServiceCall>(); // this vector contains the service calls record of the company

	public Company (String ES, String customers, String employees, String serviceCalls) {
		createES(ES);
		createEmployees(employees);
		createCustomers(customers);
		createServiceCall(serviceCalls);
	}
	private void createES(String ES) {// this method reads a file of ElectricScooters and enter them to the company data
		BufferedReader inFile=null;
		try{
			FileReader fr = new FileReader (ES);
			inFile = new BufferedReader (fr);
			inFile.readLine();
			String line;
			while ((line=inFile.readLine())!=null) {
				Double App_Version=0.0;
				String[] input = line.trim().split("\\s+");
				int SN = Integer.parseInt(input[0]);
				double temp = Double.parseDouble(input[1]);
				int Price;
				if(temp%1 != 0) {
					Price=0;
				}else {
					Price = Integer.parseInt(input[1]);
				}
				String Model = input[2];
				int Max_Speed = Integer.parseInt(input[3]);
				if(input.length == 4) {
					try {
						ElectricScooter q= new  ElectricScooter(SN,Price,Model,Max_Speed);
						addElectricScooter(q);
					}catch(PriceException e) {
						System.err.println("Didnt add the last scooter");
					}
				}
				else if(input.length == 5) {
					App_Version = Double.parseDouble(input[4]);	
					try {
						QuickElectricScooter qu= new  QuickElectricScooter(SN,Price,Model,Max_Speed,App_Version);
						addElectricScooter(qu);
					}catch(PriceException e) {
						System.err.println("Didnt add the last scooter");
					}
				}
			}
		}
		catch (FileNotFoundException exception){
			System.err.println ("The file " + ES + " was not found.");
		}
		catch (IOException exception){
			System.err.println (exception);
		}
		finally{
			try {
				inFile.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void createEmployees(String Employees) {// this method reads a file of Employees and enters them to the company data
		BufferedReader inFile=null;
		try{
			FileReader fr = new FileReader (Employees);
			inFile = new BufferedReader (fr);
			inFile.readLine();
			String line;
			while ((line=inFile.readLine())!=null) {
				Double commRate=0.0;
				String[] input = line.trim().split("\\s+");
				int ID = Integer.parseInt(input[0]);
				String Name = input[2];
				int age = Integer.parseInt(input[3]);
				char gender = input[4].charAt(0);
				if(input.length == 6) {
					try {
						char shirtSize = input[5].charAt(0);
						TechnicalEmployee employee = new TechnicalEmployee(ID,Name,age,gender,shirtSize);
						addEmployee(employee);
					}
					catch (shirtSizeException exception){
						System.err.println("Didnt add the last Employee");	
					}
					catch (genderException exception){
						System.err.println("Didnt add the last Employee");
					}
				}else if(input.length == 7) {
					char shirtSize = input[6].charAt(0);
					commRate = Double.parseDouble(input[5]);
					try {
						SalesEmployee employee = new SalesEmployee(ID,Name,age,gender,shirtSize,commRate);
						addEmployee(employee);
					}
					catch (shirtSizeException exception){
						System.err.println("Didnt add the last Employee");
					}
					catch (genderException exception){
						System.err.println("Didnt add the last Employee");
					}
				}
			}
		}
		catch (FileNotFoundException exception){
			System.err.println ("The file " + Employees + " was not found.");
		}
		catch (IOException exception){
			System.err.println (exception);
		}
		finally{
			try {
				inFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void createCustomers(String Customers) {// this method reads a file of customers and enter them to the company data
		BufferedReader inFile=null;
		try {
			FileReader fr = new FileReader (Customers);
			inFile = new BufferedReader (fr);
			inFile.readLine();
			String line;
			while ((line=inFile.readLine())!=null) {
				boolean helmet = false;
				boolean lock = false;
				String[] input = line.trim().split("\\s+");
				int ID = Integer.parseInt(input[0]);
				String fname = input[1];
				int age = Integer.parseInt(input[2]);
				char gender = input[3].charAt(0);
				int helmet1 = Integer.parseInt(input[4]);
				int lock1 = Integer.parseInt(input[5]);
				if(helmet1 == 1)
					helmet = true;
				if(lock1 == 1)
					lock = true;
				if(input.length == 6) {
					try {
						Customer q= new  Customer(ID,fname,age,gender,helmet,lock);
						addCustomer(q);
					}
					catch (genderException exception){
						System.err.println("Didnt add the last Customer");
					}
				}
				else if(input.length == 7) {
					try {
						int SN = Integer.parseInt(input[6]);
						Customer c= new  Customer(ID,fname,age,gender,helmet,lock,getScooterBySN(SN));
						addCustomer(c);
						removeScooter(c.getScooter());
					}
					catch (genderException exception){
						System.err.println("Didnt add the last Customer");
					}
				}
			}
		}
		catch (FileNotFoundException exception){
			System.err.println ("The file " + Customers + " was not found.");
		}
		catch (IOException exception){
			System.err.println (exception);
		}
		finally{
			try {
				inFile.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void createServiceCall(String serviceCalls) { // this method reads a file of service calls and enter them to the company data
		BufferedReader inFile=null;
		try {
			FileReader fr = new FileReader (serviceCalls);
			inFile = new BufferedReader (fr);
			inFile.readLine();
			String line;
			while ((line=inFile.readLine())!=null) {
				String[] input = line.trim().split("\\s+"); // we separate each String and put them inside an array
				int customerID = Integer.parseInt(input[0]);
				int employeeID = Integer.parseInt(input[1]);
				String serviceType = input[2];
				try {
					ServiceCall s = new ServiceCall(getCustomerByID(customerID), getEmployeeByID(employeeID), 
							serviceType,getCustomerByID(customerID).getScooter());
					createOldServiceCall(s);
				}catch(RuntimeException e) {}// we didnt add this customer/employee/scooter we will not break the program but we will not make the service call
			}
		}
		catch (FileNotFoundException exception){
			System.err.println ("The file " + serviceCalls + " was not found.");
		}
		catch (IOException exception){
			System.err.println (exception);
		}
		finally{
			try {
				inFile.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private void createOldServiceCall(ServiceCall s) {// this method helps to update the data about the old service calls
		Customer c = s.getCustomer();
		Employee e = s.getEmployee();
		if(s.getServiceType().equals("Technical")) {
			double p = Math.random();
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
			c.setProfitFromLastCall(e.getCommRate());
			((TechnicalEmployee)e).updateSalary();
		}
		else if(s.getServiceType().equals("Sales")){
			boolean quickES = false;
			if(s.getCustomer().getScooter() instanceof QuickElectricScooter)
				quickES = true;
			c.setScooter(((SalesEmployee)e).sellScooter(c,quickES));
			c.setProfitFromLastCall(e.getCommRate()*c.getScooter().getPrice());
			((SalesEmployee)e).setSalary(c.getScooter().getPrice());
		}
		s.updateProfit();
		addServiceCall(s);
	}
	public void addElectricScooter(ElectricScooter es) {//this method add an ElectricScooer to the company data
		scootersOfCompany.add(es);
	}
	public void addCustomer(Customer c) {//this method add an Customer to the company data
		if(c.getScooter() == null) {
			Vector<Comparable> s = new Vector<Comparable>();
			for(int index = 0;index<scootersOfCompany.size();index++) {//change the vector of customer for a comparable one to use getMin
				s.add(scootersOfCompany.elementAt(index));
			}
			Comparable es =getMin(s);//we keep the scooter with the minimum price
			boolean quickES;
			if(es instanceof QuickElectricScooter) {
				quickES = true;	
			}else {
				quickES = false;
			}
			serviceForCustomer(c.getID(),"Sales",quickES);//make customer that have no scooter to buy the cheapest
		}
		c.enteringTheCompany();//when a customer join the company, he will pay 30$
		customersOfCompany.add(c);
	}
	public void addEmployee(Employee e) {//this method add an Employee to the company data
		employeesOfCompany.add(e);
		e.updateBonus();//when an employee join the company he will get cloth bonus
	}
	public void addServiceCall(ServiceCall s) {//this method add a service call to the company data
		serviceCallsOfCompany.add(s);
	}
	public Vector<Customer> getCustomersOfCompany(){//returns the customers of the company
		return this.customersOfCompany;
	}
	public Vector<ElectricScooter> getScootersOfCompany(){//returns the scooters of the company that are for sale
		return this.scootersOfCompany;
	}
	public Vector<Employee> getEmployeesOfCompany(){//returns the employees of the company 
		return this.employeesOfCompany;
	}
	public Vector<ServiceCall> getServiceCallsOfCompany(){//returns the service calls record of the company 
		return this.serviceCallsOfCompany;
	}
	public boolean serviceForCustomer(int customerID, String serviceType, boolean quickES) {//by this method all the service calls are made
		if(!serviceType.equals("Technical") || !serviceType.equals("Sales") || getCustomerByID(customerID) == null) {
			return false; // if the customer doesn't exist in the data of the company, or the service type is wrong then return false
		}
		int minCommrateEmployee = findMinCommrateEmployeeIndex(serviceType); // get the index of the employee with the minimum commrate
		Customer c = getCustomerByID(customerID); 
		Employee e = employeesOfCompany.elementAt(minCommrateEmployee); // get the employee with the minimum commrate from the vector
		if(serviceType.equals("Technical")) {
			ElectricScooter es = getCustomerByID(customerID).getScooter();
			ServiceCall s = new ServiceCall(c,e,serviceType,es); 
			((TechnicalEmployee)e).technicalService(es);// operate a technical service
			s.updateProfit();// set the profit from the service call
			c.setProfitFromLastCall(e.getCommRate());
			((TechnicalEmployee)e).updateSalary();// update the employees salary
			addServiceCall(s);
		}
		else if(serviceType.equals("Sales")) {
			ServiceCall s = new ServiceCall(c,e,serviceType,findMinPriceScooter(quickES));
			c.setScooter(((SalesEmployee)e).sellScooter(c,quickES));
			s.updateProfit();// set the profit from the service call
			c.setProfitFromLastCall(e.getCommRate()*c.getScooter().getPrice());
			((SalesEmployee)e).setSalary(c.getScooter().getPrice());// update the employees salary
			addServiceCall(s);
		}
		return true;
	}
	private int findMinCommrateEmployeeIndex(String serviceType) { /* this method finds the employee with the minimum commRate 
		from the kind of employee that we need */
		double[] arr =  helpFindFirst(serviceType);
		int minIndex = (int)arr[0]; 
		double min= arr[1]; 
		for (int index = 0; index < employeesOfCompany.size(); index++) {  
			if(serviceType.equals("Technical")) {
				if(employeesOfCompany.elementAt(index) instanceof TechnicalEmployee) {
					if(employeesOfCompany.elementAt(index).getCommRate() < min) {
						min = employeesOfCompany.elementAt(index).getCommRate();
						minIndex = index;
					}
				}
			}
			if(serviceType.equals("Sales")) {
				if(employeesOfCompany.elementAt(index) instanceof SalesEmployee) {
					if(employeesOfCompany.elementAt(index).getCommRate() < min) {
						min = employeesOfCompany.elementAt(index).getCommRate();
						minIndex = index;
					}
				}
			}
		}
		return minIndex;
	}
	private double[] helpFindFirst(String serviceType) {//we use this method to find the first employee from the kind that we want
		//(to find the one with the lowest commRate)
		double[] arr = new double[2];
		for (int index = 0; index < employeesOfCompany.size(); index++) {  
			if(serviceType.equals("Technical")) {
				if(employeesOfCompany.elementAt(index) instanceof TechnicalEmployee) {
					arr[0] = index;
					arr[1] = employeesOfCompany.elementAt(index).getCommRate();
					break;
				}
			}
			if(serviceType.equals("Sales")) {
				if(employeesOfCompany.elementAt(index) instanceof SalesEmployee) {
					arr[0] = index;
					arr[1] = employeesOfCompany.elementAt(index).getCommRate();
					break;
				}
			}
		} 
		return arr;
	}
	public ElectricScooter getScooterBySN(int SN) {//this method finds a scooter in the company data by its Serial Number
		for (int index = 0; index < scootersOfCompany.size(); index++) {  
			if(scootersOfCompany.elementAt(index).getSN() == SN) {
				return scootersOfCompany.elementAt(index);
			}
		}
		return null;
	}
	public Employee getEmployeeByID(int employeeID) {//this method finds a Employee in the company data by its ID
		for (int index = 0; index < employeesOfCompany.size(); index++) {  
			if(employeesOfCompany.elementAt(index).getID() == employeeID) {
				return employeesOfCompany.elementAt(index);
			}
		}
		return null;
	}
	public Customer getCustomerByID(int customerID) {//this method finds a Customer in the company data by its ID
		for (int index = 0; index < customersOfCompany.size(); index++) {  
			if(customersOfCompany.elementAt(index).getID() == customerID) {
				return customersOfCompany.elementAt(index);
			}
		}
		return null;
	}
	public static Customer getCustomerByScooterSN(int SN) {//this method finds a Customer in the company data by its scooter Serial Number
		for (int index = 0; index < customersOfCompany.size(); index++) {  
			if(customersOfCompany.elementAt(index).getScooter().getSN() == SN) {
				return customersOfCompany.elementAt(index);
			}
		}
		return null;
	}
	public static ElectricScooter findMinPriceScooter(boolean quickES) {//this method finds a scooter with the minimum price from the kind that the customer want
		int[] arr = helpfindMinPrice(quickES);
		int min = arr[0];
		int minIndex = arr[1];
		for (int index = 0; index < scootersOfCompany.size(); index++) { 
			if(quickES) {
				if(scootersOfCompany.elementAt(0) instanceof QuickElectricScooter &&
						scootersOfCompany.elementAt(0).getPrice()<min) {
					minIndex = index;
					min = scootersOfCompany.elementAt(0).getPrice();
				}
			}
			else {
				if(!(scootersOfCompany.elementAt(0) instanceof QuickElectricScooter) &&
						scootersOfCompany.elementAt(0).getPrice()<min) {
					minIndex = index;
					min = scootersOfCompany.elementAt(0).getPrice();
				}
			}
		}
		return scootersOfCompany.elementAt(minIndex);
	}
	private static int[] helpfindMinPrice(boolean quickES) {//we use this method to find the first scooter from the kind that we want
		//(to find the one with the lowest price)
		int[] arr = new int[2];
		for (int index = 0; index < scootersOfCompany.size(); index++) {  
			if(quickES) {
				if(scootersOfCompany.elementAt(index) instanceof QuickElectricScooter) {
					arr[0] = scootersOfCompany.elementAt(index).getPrice();
					arr[1] = index;
					break;
				}
			}
			else {
				if(!(scootersOfCompany.elementAt(index) instanceof QuickElectricScooter)) {
					arr[0] = scootersOfCompany.elementAt(index).getPrice();
					arr[1] = index;
					break;
				}
			}
		}
		return arr;
	}
	public static void removeScooter(ElectricScooter removeAble) {//this function remove a scooter from the vector of scooters
		for(int index=0;index<scootersOfCompany.size();index++) {
			if(removeAble.getSN()==scootersOfCompany.elementAt(index).getSN()) {
				scootersOfCompany.removeElementAt(index);
			}
		}
	}
	public double purchasedQuickScooterRatio() {//this method calculate the percent of Quick scooters from all the sales that had been in the company
		double numOfQuicks = 0;
		double numOfSales = 0;
		for (int index = 0; index < serviceCallsOfCompany.size(); index++) {  
			if(serviceCallsOfCompany.elementAt(index).getScooter() instanceof QuickElectricScooter) {
				numOfQuicks++;
			}if(serviceCallsOfCompany.elementAt(index).getEmployee() instanceof SalesEmployee) {
				numOfSales++;
			}
		}
		double ratio = numOfQuicks/numOfSales;
		return ratio;
	}
	public static double totalRevenues(Vector<profitable> profit) {//this function calculate the revenue from a profitable vector
		double revenue= 0;
		for (int index = 0; index < profit.size(); index++) {  
			revenue = revenue + profit.elementAt(index).getProfit();//update revenue
		}
		return revenue;
	}
	public static Comparable getMin(Vector<Comparable> comperable) {//this function calculate finds the minimum value from a comparable vector
		Comparable temp = comperable.elementAt(0);
		for (int index = 0; index < comperable.size()-1; index++) {  
			int minOfBoth = temp.compareTo(comperable.elementAt(index+1));
			if(minOfBoth == 1) {
				temp = comperable.elementAt(index+1);
			}
		}
		return temp;
	}

	public static int updatedRates(Vector<updateable> update) {//this function update values of objects that we can update and 
		//return the number of all the objects that updated
		int temp = 0;
		for (int index = 0; index < update.size(); index++) {  
			if(update.elementAt(index).update())
				temp++;
		}
		return temp;
	}
	public static int isPopular() {//this function finds the popular scooter in the company
		int numOfBasicScooters = 0;//the number of electric scooters
		int numOfQuickScooters = 0;//the number of quick electric scooters
		for (int index = 0; index < customersOfCompany.size(); index++) {  
			if(customersOfCompany.elementAt(index).getScooter() instanceof QuickElectricScooter) {
				numOfQuickScooters = numOfQuickScooters+1;//update the number of quick electric scooters
			}
			else {
				numOfBasicScooters = numOfBasicScooters+1;//update the number of electric scooters
			}			
		}
		if(numOfBasicScooters<numOfQuickScooters) {
			return 1;
		}
		else if(numOfBasicScooters>numOfQuickScooters)
			return -1;
		else 
			return 0;
	}
}