package abilities;

import java.util.HashSet;
import java.util.Set;

public class PercentageRangeFlyweight {
	
	private static Set<PercentageRange> cachedRanges;
	
	public static PercentageRange getPercentageRange(double min,double max) {
		if(cachedRanges==null) {
			cachedRanges = new HashSet<>();///////use tree set?
		}
		
		
		PercentageRange fromStorage = getRangeFromStorage(min,max);
		if(fromStorage==null) {
			PercentageRange newRange = new PercentageRange(min,max);
			cachedRanges.add(newRange);
			return newRange;
		}else {
			return fromStorage;
		}
		
		
		
	}

	//returns null if a range with the given values is not there
	private static PercentageRange getRangeFromStorage(double min, double max) {
		for(PercentageRange pr:cachedRanges) {
			if(pr.getMax()==max && pr.getMin()==min) {
				return pr;
			}
		}
		return null;
	}
}
