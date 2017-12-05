package com.crxmarkert.sysvol.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.crxmarket.sysvol.exception.ColumnInvalidException;
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
		Volume vol = volumeService.calcVolume("5,4,5");
		assertEquals(new Integer(1),vol.getTotalValue());
	}
	
	@Test
	public void testSucessWithWaterWithWhiteSpace() {
		Volume vol = volumeService.calcVolume("5 , 4, 5");
		assertEquals(new Integer(1),vol.getTotalValue());
	}
	
	@Test
	public void testSucessWithoutWater() {
		Volume vol = volumeService.calcVolume("5,5,5");
		assertEquals(new Integer(0),vol.getTotalValue());
	}
	
	@Test
	public void testSucesslZero() {
		Volume vol = volumeService.calcVolume("0,0,0");
		assertEquals(new Integer(0),vol.getTotalValue());
	}
	
	@Test(expected = ColumnInvalidException.class)
	public void testFailNegative() {
		volumeService.calcVolume("-5,-5,-5");
	}
	
	@Test(expected = ColumnInvalidException.class)
	public void testFailInvalidChar() {
		volumeService.calcVolume("5,4,a");
	}
	
	@Test(expected = ColumnInvalidException.class)
	public void testFailEmpty() {
		volumeService.calcVolume("");
	}
	
	@Test(expected = ColumnInvalidException.class)
	public void testFailNull() {
		volumeService.calcVolume(null);
	}
}
