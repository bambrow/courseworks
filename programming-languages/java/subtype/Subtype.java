package subtype;

class Video {
	int length;
	int resolution;
}

class Movie extends Video {
	String director;
}

public class Subtype {
	static void addLength(Video v) {
		v.length += 20;
	}
	public static void main(String[] args) {
		Movie m = new Movie();
		addLength(m);
		System.out.println(m.length);
	}
}
