package com.crxmarkert.sysvol.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.crxmarkert.sysvol.util.BuildUtils;
import com.crxmarkert.sysvol.util.ContextMocker;
import com.crxmarket.sysvol.bean.VolumeBean;
import com.crxmarket.sysvol.service.VolumeService;

@RunWith(MockitoJUnitRunner.class)
public class VolumeBeanTest {

	@Mock
	VolumeService volumeService;

	@InjectMocks
	VolumeBean bean;

	@Before
	public void init() {
		bean.init();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCalcOK() {
		when(volumeService.calcVolume(any(List.class))).thenReturn(
				BuildUtils.buildVolumeWater());
		bean.setData("5,4,5");
		bean.calc();
		verify(volumeService).calcVolume(any(List.class));
		assertEquals(bean.getResult().getTotalValue(), BuildUtils
				.buildVolumeWater().getTotalValue());
		assertNotNull(bean.getBarModel());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testClearOK() {
		when(volumeService.calcVolume(any(List.class))).thenReturn(
				BuildUtils.buildVolumeWater());
		bean.setData("5,4,5");
		bean.calc();
		bean.clear();
		assertNull(bean.getResult());
		assertEquals("", bean.getData());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testExceptionNumberFormat() {
		FacesContext context = ContextMocker.mockFacesContext();
		try {
			Map<String, Object> session = new HashMap<String, Object>();
			ExternalContext ext = mock(ExternalContext.class);
			when(ext.getSessionMap()).thenReturn(session);
			when(context.getExternalContext()).thenReturn(ext);

			doThrow(new NumberFormatException("For input string: a")).when(
					volumeService).calcVolume(any(List.class));
			bean.setData("5,4,a");
			bean.calc();
		} finally {
			context.release();
		}

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testExceptionEmpty() {
		FacesContext context = ContextMocker.mockFacesContext();
		try {
			Map<String, Object> session = new HashMap<String, Object>();
			ExternalContext ext = mock(ExternalContext.class);
			when(ext.getSessionMap()).thenReturn(session);
			when(context.getExternalContext()).thenReturn(ext);

			doThrow(new IllegalArgumentException()).when(
					volumeService).calcVolume(any(List.class));
			bean.setData("5,4,a");
			bean.calc();
		} finally {
			context.release();
		}

	}
}
