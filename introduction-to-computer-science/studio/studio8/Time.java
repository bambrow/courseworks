package studio8;

public class Time implements Working{
	
	private final int hour;
	private final int minute;
	private final boolean is12Format;
	
	public Time(int hour, int minute, boolean is12Format) {
		this.hour = hour;
		this.minute = minute;
		this.is12Format = is12Format;
	}
	
	public boolean isEarlierThan(Time that) {
		if (this.hour < that.hour) {
			return true;
		} else if (this.hour == that.hour && this.minute < that.minute) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean amWorking() {
		if (hour >= 9 && hour <= 17) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		if (is12Format) {
			if (hour <= 12) {
				return hour + ":" + minute + " AM";
			} else {
				return (hour - 12) + ":" + minute + " PM";
			}
		} else {
			return hour + ":" + minute;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hour;
		result = prime * result + minute;
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
		Time other = (Time) obj;
		if (hour != other.hour)
			return false;
		if (minute != other.minute)
			return false;
		return true;
	}
	
	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public boolean isIs12Format() {
		return is12Format;
	}

	public static void main(String[] args) {
		
		Time t1 = new Time(16, 30, true);
		Time t2 = new Time(16, 30, false);
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t1.equals(t2));
		
		Time t3 = new Time(17, 30, true);
		Time t4 = new Time(18, 30, false);
		System.out.println("t2 earlier than t3? " + t2.isEarlierThan(t3));
		System.out.println("t4 earlier than t3? " + t4.isEarlierThan(t3));
		
	}

}
