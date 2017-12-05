package com.crxmarket.sysvol.bean;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.crxmarket.sysvol.exception.ColumnInvalidException;
import com.crxmarket.sysvol.model.Volume;
import com.crxmarket.sysvol.service.VolumeService;

@ManagedBean
public class VolumeBean implements Serializable {

	private static final long serialVersionUID = 7800120856479751146L;

	@Inject
	private VolumeService volumeService;

	private String data;

	private Volume result;

	private BarChartModel barModel;

	@PostConstruct
	public void init() {
		configureChart();
	}

	public void calc() {
		try {
			result = volumeService.calcVolume(data);
			updateChart();
		} catch (ColumnInvalidException e) {
			FacesContext.getCurrentInstance().addMessage("Invalid Value",
					new FacesMessage(e.getMessage()));
		}
	}

	public void clear() {
		data = "";
		result = null;
	}

	private void updateChart() {
		AtomicInteger ordinal = new AtomicInteger();

		ordinal.set(1);
		ChartSeries stones = new ChartSeries("Stone Colmuns");
		result.getStoneColumns().stream()
				.forEach(x -> stones.set(ordinal.getAndIncrement(), x));
		barModel.addSeries(stones);

		ordinal.set(1);
		ChartSeries waters = new ChartSeries("Water Colmuns");
		result.getWaterColumns().stream()
				.forEach(x -> waters.set(ordinal.getAndIncrement(), x));
		barModel.addSeries(waters);
	}

	public void configureChart() {
		barModel = new BarChartModel();
		barModel.setTitle("Volume Analysis After Rain");
		barModel.setLegendPosition("ne");
		barModel.setStacked(true);
		barModel.setSeriesColors("999999,0000FF");
		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Columns");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantity");
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Volume getResult() {
		return result;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}
}
