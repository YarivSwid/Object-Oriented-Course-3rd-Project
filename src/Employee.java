
abstract class Employee implements Comparable<Employee>, updateable{
	protected int ID;//the ID of the employee
	protected String fName;//the name of the employee
	protected int age;//the age of the employee
	protected char gender;//the gender of the employee
	protected char shirtSize;//the shirt size of the employee
	protected double commRate;//the commrate of the employee
	protected double bonus;//the company will update the clothing bonus when employee join's the company
	protected double salary = 0;//the salary of the employee

	public Employee (int ID, String fName, int age, char gender, char shirtSize) {//this constructor creats an employee
		if(shirtSize!='s' && shirtSize!='m' && shirtSize!='l' && shirtSize!='x') //if the size is not one of those we will throw an exception
			throw new shirtSizeException("your shirt size is : "+shirtSize+". please enter one of these sizes: s/m/l/x ");
		if(gender!='u' && gender!='m' && gender!='f') //if the gender is not one of those we will throw an exception
			throw new genderException("your gender is : "+gender+". please enter one of these options: m/f/u ");
		this.ID=ID;
		this.fName=fName;
		this.age=age;
		this.shirtSize=shirtSize;
		this.gender=gender;
	}
	public double getCommRate() {//returns the commrate of the employee
		return this.commRate;
	}
	public double getSalary() {//returns the salary of the employee
		return this.salary;
	}
	public int getID() {//returns the ID of the employee
		return this.ID;
	}
	abstract public void updateBonus();//this method is abstract because we want the employees sons to run it over 

	public int compareTo(Employee other){//compares between the salary of this employee to another
		if( this.salary > other.salary )
			return 1;
		if( this.salary < other.salary )
			return -1;
		return 0;
	}
}
