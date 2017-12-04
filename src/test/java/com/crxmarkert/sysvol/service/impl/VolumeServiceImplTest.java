package com.crxmarkert.sysvol.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.crxmarket.sysvol.model.Volume;
import com.crxmarket.sysvol.service.VolumeService;
import com.crxmarket.sysvol.service.impl.VolumeServiceImpl;

public class VolumeServiceImplTest {
	
	private VolumeService volumeService;

	@Before
	public void init() {
		volumeService = new VolumeServiceImpl();
	}

	@Test
	public void testSucessWithWater() {
		Integer[] array = { 5, 4, 5 };
		Volume vol = volumeService.calcVolume(Arrays.asList(array));
		assertEquals(new Integer(1),vol.getTotalValue());
	}
	
	@Test
	public void testSucessWithoutWater() {
		Integer[] array = { 5, 5, 5 };
		Volume vol = volumeService.calcVolume(Arrays.asList(array));
		assertEquals(new Integer(0),vol.getTotalValue());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailNegative() {
		Integer[] array = { -5, -5, -5 };
		volumeService.calcVolume(Arrays.asList(array));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailEmpty() {
		volumeService.calcVolume(new ArrayList<Integer>());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFailNull() {
		volumeService.calcVolume(null);
	}
	
	@Test
	public void testFailZero() {
		Integer[] array = { 0, 0, 0 };
		Volume vol = volumeService.calcVolume(Arrays.asList(array));
		assertEquals(new Integer(0),vol.getTotalValue());
	}

}
