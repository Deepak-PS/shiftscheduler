package com.shift.scheduler.algorithm;

import java.util.List;

import com.shift.scheduler.model.Employee;
import com.shift.scheduler.model.Shift;

public interface SchedulingAlgorithmI {
	
	public List<Shift> scheduleShifts(List<Employee> employees, List<Shift> shifts, int shiftDuration);
}
