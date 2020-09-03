package com.shift.scheduler.model;

public class Employee implements Comparable<Employee> {
	private int empId;
	private int desiredEarnings;
	private int actualEarnings = 0;

	public Employee(int empId, int desiredEarnings) {
		this.empId = empId;
		this.desiredEarnings = desiredEarnings;
	}
	
	public int getDesiredEarnings() {
		return desiredEarnings;
	}
	
	public int getEmpId() {
		return empId;
	}
	
	public int getActualEarnings() {
		return actualEarnings;
	}

	public void setActualEarnings(int actualEarnings) {
		this.actualEarnings = actualEarnings;
	}
	
	public String toString() {
		return "Employee [ID: "+empId+", Desired Earnings: "+desiredEarnings+", Actual Earnings: "+actualEarnings+"]";
	}
	
	// to sort according to the difference between desired earnings and actual earning in descending order
	@Override
    public int compareTo(Employee e) {
         return this.getDesiredEarnings() - this.getActualEarnings() > e.getDesiredEarnings() - e.getActualEarnings() ? -1: this.getDesiredEarnings() - this.getActualEarnings() < e.getDesiredEarnings() - e.getActualEarnings() ? 1 : 0;
	}
}