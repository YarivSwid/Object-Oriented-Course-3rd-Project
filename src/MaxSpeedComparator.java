import java.util.Comparator;

public class MaxSpeedComparator implements Comparator{//this comparator compares between the max speed of two scooters
	                                                  //that belongs that belongs to the customers

	public int compare(Object o1, Object o2){
		return ((Customer)o1).getScooter().getMaxSpeed() - ((Customer)o2).getScooter().getMaxSpeed();
	}
}
