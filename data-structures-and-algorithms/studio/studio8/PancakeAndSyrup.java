package studio8;

public class PancakeAndSyrup {

	public Pancake pancake;
	public Syrup syrup;

	public  PancakeAndSyrup(Pancake pancake, Syrup syrup) {

		this.pancake = pancake;
		this.syrup = syrup;
	}
	
	/**
	 * Essentially this is the implementation automatically generated
	 * by Java if you do Source--> Generate hashCode() equals()
	 * It's been cleaned up a bit to make it more clear.
	 */
	public int hashCode(){
		return pancake.hashCode() * 31 + syrup.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PancakeAndSyrup other = (PancakeAndSyrup) obj;
		if (pancake == null) {
			if (other.pancake != null)
				return false;
		} else if (!pancake.equals(other.pancake))
			return false;
		if (syrup == null) {
			if (other.syrup != null)
				return false;
		} else if (!syrup.equals(other.syrup))
			return false;
		return true;
	}
}
