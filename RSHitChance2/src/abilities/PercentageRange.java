package abilities;

import main.DamageMode;

//todo: make flyweight for percentage range
public class PercentageRange {
	//1.0 means 100% ability damage
	private double min;
	private double max;
	
	PercentageRange(double min100, double max100) {
		this.min = min100/100;
		this.max = max100/100;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}
	
	public double getAverage() {
		return (min+max)/2;
	}
	
	public double getMultiplier(DamageMode mode) {
		switch(mode) {
		case MAX:
			return getMax();
		case MIN:
			return getMin();
		case AVE:
			return getAverage();
		default:
			System.out.println("PercentageRange: unrecognized mode! " + mode);
			return 0;
		}
	}
	
	
	
	
	//for use in hash maps
	@Override
	public boolean equals(Object o) {
		if(o instanceof PercentageRange) {
			PercentageRange pr2 = (PercentageRange) o;
			return this.min==pr2.min && this.max == pr2.max;
		}else {
			return false;
		}
	}
	
	//for use in hash maps
	//copied from java's point2d class
	@Override
	public int hashCode() {
		long bits = Double.doubleToLongBits(min);
	    bits ^= Double.doubleToLongBits(max) * 31;
	    return (((int) bits) ^ ((int) (bits >> 32)));
	}

	
}
