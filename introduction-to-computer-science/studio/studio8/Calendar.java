package studio8;

import java.util.HashSet;

public class Calendar {

	private HashSet<Appointment> set;
	
	public Calendar() {
		set = new HashSet<Appointment>();
	}
	
	public void add(Appointment appointment) {
		set.add(appointment);
	}
	
	private void schedule(Date date) {
		for (Appointment appointment : set) {
			if (date.equals(appointment.getDate())) {
				System.out.println(appointment);
			}
		}
	}
	
	public void schedule(int month, int day, int year) {
		Date date = new Date(month, day, year, true);
		schedule(date);
	}
	
	public int amWorking() {
		int count = 0;
		for (Appointment appointment : set) {
			if (appointment.getDate().amWorking() && appointment.getTime().amWorking()) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		
		Calendar calendar = new Calendar();
		Appointment a1 = new Appointment(new Date(10, 27, 2016, false), new Time(16, 30, true), "Coffee");
		Appointment a2 = new Appointment(new Date(10, 27, 2016, true), new Time(18, 30, false), "Midterm");
		Appointment a3 = new Appointment(new Date(10, 29, 2016, true), new Time(15, 30, false), "Another midterm");
		Appointment a4 = new Appointment(new Date(10, 28, 2016, true), new Time(15, 30, false), "Another midterm");
		Appointment a5 = new Appointment(new Date(10, 26, 2016, true), new Time(9, 30, false), "Another midterm");
		Appointment a6 = new Appointment(new Date(10, 28, 2016, true), new Time(19, 30, false), "Another midterm");
		calendar.add(a1);
		calendar.add(a2);
		calendar.add(a3);
		calendar.add(a4);
		calendar.add(a5);
		calendar.add(a6);
		calendar.schedule(10, 27, 2016);
		System.out.println("Working: " + calendar.amWorking());
		
	}

}
