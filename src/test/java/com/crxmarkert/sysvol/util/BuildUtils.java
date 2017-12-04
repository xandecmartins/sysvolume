package com.crxmarkert.sysvol.util;

import com.crxmarket.sysvol.model.Volume;

import edu.emory.mathcs.backport.java.util.Arrays;

public class BuildUtils {

	
	public static Volume buildVolumeWater(){
		Integer[] array = { 5, 4, 5 };
		Integer[] arrayw = { 0, 1, 0 };
		
		Volume retVal = new Volume();
		retVal.setStoneColumns(Arrays.asList(array));
		retVal.setWaterColumns(Arrays.asList(arrayw));
		retVal.setTotalValue(1);
		return retVal;
	}
}
