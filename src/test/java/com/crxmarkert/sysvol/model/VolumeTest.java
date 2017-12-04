package com.crxmarkert.sysvol.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.crxmarket.sysvol.model.Volume;
import com.crxmarket.sysvol.service.VolumeService;
import com.crxmarket.sysvol.service.impl.VolumeServiceImpl;

public class VolumeTest {

	private VolumeService volumeService;

	@Before
	public void init() {
		volumeService = new VolumeServiceImpl();
	}

	@Test
	public void testSucessWithWater() {
		Integer[] array = { 5, 4, 5 };
		Integer[] arrayWater = { 0, 1, 0 };
		Volume vol = volumeService.calcVolume(Arrays.asList(array));
		assertEquals(new Integer(1),vol.getTotalValue());
		assertEquals(Arrays.asList(array),vol.getStoneColumns());
		assertEquals(Arrays.asList(arrayWater),vol.getWaterColumns());
		
	}

	
}
