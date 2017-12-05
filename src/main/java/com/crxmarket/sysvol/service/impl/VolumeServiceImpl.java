package com.crxmarket.sysvol.service.impl;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.Stateless;

import com.crxmarket.sysvol.model.Volume;
import com.crxmarket.sysvol.exception.ColumnInvalidException;
import com.crxmarket.sysvol.service.VolumeService;

@Stateless
public class VolumeServiceImpl implements VolumeService {

	/**
	 * Method responsible to calculate the volume of water stored among the
	 * columns
	 */
	public Volume calcVolume(String data) {

		List<Integer> columns = evaluateAndConvertInput(data);

		Volume retVal = new Volume();
		retVal.setStoneColumns(columns);

		List<Integer> water = new ArrayList<>(columns.size());

		int[] maxArray = generateMaxArrayRightToLeft(columns);

		// First column never stores water
		water.add(0);

		int volume = 0;
		int maxLeft = 0;

		for (int i = 0; i < columns.size() - 1; i++) {
			// Define the max wall on left
			maxLeft = max(maxLeft, columns.get(i));

			int maxRight = maxArray[i + 1];

			// Define the minimum reference between maxLeft and maxRight
			// reference
			int min = min(maxRight, maxLeft);

			// Comparing if there is difference between the next column and the
			// minimum reference
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
	 * Method responsible to calculate the array of max values from right to
	 * left. This array is used as reference in the calc method
	 * 
	 * @param columns
	 * @return array of references from right to left
	 */
	private int[] generateMaxArrayRightToLeft(List<Integer> columns) {
		int[] maxArray = new int[columns.size()];

		int max = 0;

		for (int i = columns.size() - 1; i > 0; i--) {
			max = max(columns.get(i), max);
			maxArray[i - 1] = max;

		}
		return maxArray;
	}

	private List<Integer> evaluateAndConvertInput(String data) {
		if (data == null || data.trim().isEmpty()) {
			throw new ColumnInvalidException(
					"The list of columns cannot be empty");
		}

		List<Integer> columns = null;
		try {
			columns = Stream.of(data.split("\\s*,\\s*")).map(Integer::parseInt)
					.collect(Collectors.toList());

			columns.stream().forEach(
					x -> {
						if (x < 0)
							throw new ColumnInvalidException("The value " + x
									+ " is not valid");
					});
		} catch (NumberFormatException e) {
			throw new ColumnInvalidException("The value "
					+ e.getMessage().split(":")[1] + " is not valid");
		}

		return columns;
	}

}
