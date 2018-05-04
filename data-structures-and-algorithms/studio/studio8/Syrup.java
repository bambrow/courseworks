package studio8;

public class Syrup {

	public double density;
	public String brand;

	public Syrup(String brand, double density) {

		this.density = density;
		this.brand = brand;
	}

	public int hashCode(){
		int hash = ((int) (density * 1000) % 71);
		for (int i = 0; i < brand.length(); ++i) {
			hash = hash * 17 + (int) brand.charAt(i) * 31;
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
		Syrup other = (Syrup) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (Double.doubleToLongBits(density) != Double
				.doubleToLongBits(other.density))
			return false;
		return true;
	}



}

