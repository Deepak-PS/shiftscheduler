package com.shift.scheduler.algorithm;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.shift.scheduler.model.Employee;
import com.shift.scheduler.model.Shift;

@FunctionalInterface
interface Overlap {
	int checkOverlap(final List<Shift> shifts, final int id, final int i);
}

public class SchedulingAlgorithm {

	public List<Shift> scheduleShifts(List<Employee> employees, List<Shift> shifts, int shiftDuration) {

		for (int i = 0; i < shifts.size(); i++) {
			Collections.sort(employees);
			for (int j = 0; j < employees.size(); j++) {
				if (!checkOverlap(shifts, employees.get(j).getEmpId(), shifts.get(i).getStartTime(), shifts.get(i).getEndTime())) {
					int updatedEarings = employees.get(j).getActualEarnings()
							+ shifts.get(i).getHourlyEarnings() * shiftDuration;
					employees.get(j).setActualEarnings(updatedEarings);
					shifts.get(i).setAssignedEmployee(employees.get(j));
					break;
				}
			}
		}
		return shifts;
	}

	protected boolean checkOverlap(final List<Shift> shifts, final int id, final long st, long lt) {
		List<Shift> alreadyAssigned = shifts.stream().filter(s -> {
			return s.getAssignedEmployee() != null && s.getAssignedEmployee().getEmpId() == id
					&& ((s.getStartTime() <= st
							&& st <= s.getEndTime())
							|| (s.getStartTime() <= lt
									&& lt <= s.getEndTime()));
		}).collect(Collectors.toList());
		if (!alreadyAssigned.isEmpty()) {
			return true;
		}
		return false;
	}
}