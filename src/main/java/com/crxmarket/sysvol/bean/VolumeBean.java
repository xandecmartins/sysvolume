package com.crxmarket.sysvol.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.crxmarket.sysvol.model.Volume;
import com.crxmarket.sysvol.service.VolumeService;

@ManagedBean
public class VolumeBean implements Serializable{

	@Inject
	VolumeService volumeService;
	
	private String data;

	private Volume result;
	
	private BarChartModel barModel;

	@PostConstruct
	public void init() {
		barModel = new BarChartModel();
        barModel.setTitle("Volume Analysis After Rain");
        barModel.setLegendPosition("ne");
        ((ChartModel)barModel).setSeriesColors("999999,0000FF");
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Columns");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quantity");
	}
	
	public String calc() {
		try {
		result = volumeService.calcVolume(getValues());
		} catch (IllegalArgumentException e){
			FacesContext.getCurrentInstance().addMessage("Invalid Value", new FacesMessage(e.getMessage()));
			return "";
		}
        ChartSeries stones = new ChartSeries("Stone Colmuns");
        
        int i=1;
        for (Integer stone : result.getStoneColumns()) {
        	stones.set(i++, stone);
        }
        barModel.addSeries(stones);

        ChartSeries waters = new ChartSeries("Water Colmuns");

        i=1;
        for (Integer stone : result.getWaterColumns()) {
        	waters.set(i++, stone);
		}

        barModel.addSeries(waters);

        barModel.setStacked(true);
        
        return "";
	}
	
	public String randColumns() {
		Random rand = new Random();
		StringBuilder strBuilder = new StringBuilder();
		int max = rand.nextInt(11);
		for (int i = 0; i < max; i++) {
			strBuilder.append(rand.nextInt(30)).append(",");
			
		}
		strBuilder.setLength(strBuilder.length() - 1);
		data = strBuilder.toString();
		return "";
	}
	
	private List<Integer> getValues(){
		String[] array = data.split(",");
		List<Integer> retVal = new ArrayList<>(data.length());
		for (String string : array) {
			try {
				retVal.add(Integer.valueOf(string.trim()));
			} catch(Exception e) {
				throw new IllegalArgumentException("The value "+string+" is not valid!");
			}
		}
		return retVal;
	}
	
	public String clear() {
		data = "";
		result = null;
		barModel = new BarChartModel();
		return "";
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

	public void setResult(Volume result) {
		this.result = result;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}
	
}
