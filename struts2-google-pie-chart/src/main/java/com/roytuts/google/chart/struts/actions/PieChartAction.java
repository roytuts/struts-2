package com.roytuts.google.chart.struts.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.roytuts.google.chart.data.PieChartData;
import com.roytuts.google.chart.data.PieChartData.KeyValue;

public class PieChartAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String pieChartData;
	private List<KeyValue> pieDataList;

	public PieChartAction() {
		this.pieDataList = PieChartData.getPieDataList();
	}

	public String getPieChartData() {
		if (pieChartData == null || pieChartData.trim().length() <= 0) {
			populateData();
		}
		return pieChartData;
	}

	private void populateData() {
		StringBuilder stringBuilder = new StringBuilder();
		for (KeyValue pieData : pieDataList) {
			stringBuilder.append("[");
			stringBuilder.append("'");
			stringBuilder.append(pieData.getKey());
			stringBuilder.append("'");
			stringBuilder.append(",");
			stringBuilder.append(pieData.getValue());
			stringBuilder.append("]");
			stringBuilder.append(",");
		}
		pieChartData = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

}
