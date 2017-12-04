package com.crxmarkert.sysvol.bean;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.crxmarket.sysvol.bean.VolumeBean;
import com.crxmarket.sysvol.service.VolumeService;


public class VolumeBeanTest {

	@InjectMocks
	private VolumeBean bean;
	
	@Mock
	private VolumeService volumeService;
	
	
	@Test
	public void testCalcOK(){
		//when(volumeService.calcVolume(any(List.class))).thenReturn(BuildUtils.buildVolumeWater());
		//String bean.calc()
	}
}
