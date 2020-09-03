package com.shift.scheduler.model;

public class Shift {
	private int shiftId;
	private long startTime;
	private long endTime;
	private int hourlyEarnings;
	Employee assignedEmployee; 
	
	public Shift(int shiftId, long startTime, long endTime, int hourlyEarnings) {
		this.shiftId = shiftId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.hourlyEarnings = hourlyEarnings;
	}
	
	public int getShiftId() {
		return shiftId;
	}
	
	public long getStartTime() {
		return startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public int getHourlyEarnings() {
		return hourlyEarnings;
	}
	public Employee getAssignedEmployee() {
		return assignedEmployee;
	}
	
	public void setAssignedEmployee(Employee assignedEmployee) {
		this.assignedEmployee = assignedEmployee;
	}
	
	public String toString() {
		return "Shift [ID: "+shiftId+", Start Time: "+startTime+", End Time: "+endTime+", Hourly Earnings: "+hourlyEarnings+", Assigned Employee:"+assignedEmployee+"]";
	}
}

