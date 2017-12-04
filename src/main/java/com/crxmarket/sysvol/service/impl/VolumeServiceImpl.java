package com.crxmarket.sysvol.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.crxmarket.sysvol.model.Volume;
import com.crxmarket.sysvol.service.VolumeService;

@Stateless
public class VolumeServiceImpl implements VolumeService {
	
	public Volume calcVolume(List<Integer> columns) {
		validation(columns);
		
		Volume retVal = new Volume();
		retVal.setStoneColumns(columns);

		List<Integer> water = new ArrayList<>(columns.size());
		
		int[] maxArray = generateMaxArrayRightToLeft(columns);

		water.add(0);
		int volume = 0;
		int maxLeft = 0;

		for (int i = 0; i < columns.size() - 1; i++) {
			if (columns.get(i) > maxLeft) {
				maxLeft = columns.get(i);
			}

			int min = 0;

			if (maxArray[i + 1] < maxLeft) {
				min = maxArray[i + 1];
			} else {
				min = maxLeft;
			}

			if (columns.get(i + 1) < min) {
				int columnsWaterValue = min - columns.get(i + 1);
				water.add(columnsWaterValue);
				volume += columnsWaterValue;
			} else {
				water.add(0);
			}
		}

		retVal.setWaterColumns(water);
		retVal.setTotalValue(volume);

		return retVal;
	}

	/**
	 * Method responsible to calculate the array of max values from right to left.
	 * This array is used as reference in the calc method
	 * 
	 * @param columns
	 * @return array of references from right to left
	 */
	private int[] generateMaxArrayRightToLeft(List<Integer> columns) {
		int[] maxArray = new int[columns.size()];

		int max = 0;

		for (int i = columns.size() - 1; i > 0; i--) {
			if (columns.get(i) > max) {
				max = columns.get(i);
			}
			maxArray[i - 1] = max;

		}
		return maxArray;
	}
	
	private void validation(List<Integer> columns) {
		if(columns==null || columns.isEmpty()) {
			throw new IllegalArgumentException("The input data cannot be empty");
		}
			
		columns.stream().forEach(x -> { if(x<0) throw new IllegalArgumentException("The value "+x+" is not valid"); });
	}

}
