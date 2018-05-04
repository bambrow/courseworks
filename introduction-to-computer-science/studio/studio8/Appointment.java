package studio8;

public class Appointment {

	private final Date date;
	private final Time time;
	private final String name;
	
	public Appointment(Date date, Time time, String name) {
		this.date = date;
		this.time = time;
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		Appointment other = (Appointment) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Appointment: " + date.toString() + " " + time.toString() + " " + name;
	}

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public String getName() {
		return name;
	}

	public static void main(String[] args) {

		Appointment a1 = new Appointment(new Date(10, 27, 2016, false), new Time(16, 30, true), "Coffee");
		Appointment a2 = new Appointment(new Date(10, 27, 2016, true), new Time(18, 30, false), "Midterm");
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a1.equals(a2));
		
	}

}
