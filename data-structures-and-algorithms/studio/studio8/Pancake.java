package studio8;


public class Pancake {


	public int radius;
	public boolean wheat;

	public Pancake(int radius, boolean wheat) {

		this.radius = radius;
		this.wheat = wheat;
	}

	@Override
	public int hashCode(){
		int hash = 31 * radius;
		if (wheat){
			hash = hash + 17;
		}
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pancake other = (Pancake) obj;
		if (radius != other.radius)
			return false;
		if (wheat != other.wheat)
			return false;
		return true;
	}

}
