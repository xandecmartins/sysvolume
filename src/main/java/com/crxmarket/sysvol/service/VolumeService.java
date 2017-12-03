package com.crxmarket.sysvol.service;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;

import com.crxmarket.sysvol.model.Volume;

@Stateless
public class VolumeService {

	public Volume calcVolume(List<Integer> columns) {
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
				int columnsWaterValue = min - columns.get(i+1);
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
	
	public static void main(String[] args) throws java.lang.Exception {
		Integer[] array = { 3, 2, 4, 1, 2 };
		//Integer[] array = { 10, 2, 5, 1, 10 };

		for (int i : array) {
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();

		}

		

		Volume vol = new VolumeService().calcVolume(Arrays.asList(array));
		
		vol.getWaterColumns().stream().forEach(x -> System.out.println(x));
		
		System.out.println("\n-----------------------\n");
		
		System.out.println(vol.getTotalValue());
	}
}
