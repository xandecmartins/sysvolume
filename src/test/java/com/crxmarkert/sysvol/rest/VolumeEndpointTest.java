package com.crxmarkert.sysvol.rest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.ws.rs.core.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.crxmarkert.sysvol.util.BuildUtils;
import com.crxmarket.sysvol.rest.VolumeEndpoint;
import com.crxmarket.sysvol.service.VolumeService;

import edu.emory.mathcs.backport.java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class VolumeEndpointTest {

	
	@Mock VolumeService volumeService;
	
	@InjectMocks VolumeEndpoint rest;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCalcOK(){
		when(volumeService.calcVolume(any(List.class))).thenReturn(BuildUtils.buildVolumeWater());
		Response result = rest.calcVolume(Arrays.asList(new Integer[]{5,4,5}));
		verify(volumeService).calcVolume(any(List.class));
		assertNotNull(result);
	}
}
