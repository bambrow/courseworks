package studio8;

import java.util.HashSet;
import java.util.LinkedList;

public class Date implements Working{
	
	private final int month;
	private final int day;
	private final int year;
	private final boolean isHoliday;
	
	public Date(int month, int day, int year, boolean isHoliday) {
		this.month = month;
		this.day = day;
		this.year = year;
		this.isHoliday = isHoliday;
	}
	
	public boolean isEarlierThan(Date that) {
		if (this.year < that.year) {
			return true;
		} else if (this.year == that.year && this.month < that.month) {
			return true;
		} else if (this.year == that.year && this.month == that.month && this.day < that.day) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean amWorking() {
		if (day % 2 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return month + "/" + day + "/" + year;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Date other = (Date) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getYear() {
		return year;
	}

	public boolean isHoliday() {
		return isHoliday;
	}

	public static void main(String[] args) {
		
		Date d1 = new Date(10, 27, 2016, false);
		System.out.println(d1);
		Date d2 = new Date(10, 27, 2016, true);
		System.out.println(d2);
		System.out.println(d1.equals(d2));
		Date d3 = new Date(5, 5, 2016, false);
		Date d4 = new Date(6, 6, 2016, true);
		Date d5 = new Date(7, 7, 2016, true);
		Date d6 = new Date(9, 9, 2016, false);
		
		LinkedList<Date> list = new LinkedList<Date>();
		list.add(d1);
		list.add(d2);
		list.add(d3);
		list.add(d4);
		list.add(d5);
		list.add(d6);
		System.out.println(list);
		list.clear();
		list.add(d1);
		list.add(d2);
		list.add(d1);
		System.out.println(list);
		
		HashSet<Date> set = new HashSet<Date>();
		set.add(d1);
		set.add(d2);
		set.add(d1);
		System.out.println(set);
	
		System.out.println("d3 earlier than d4? " + d3.isEarlierThan(d4));
		System.out.println("d5 earlier than d4? " + d5.isEarlierThan(d4));
		
	}

}
