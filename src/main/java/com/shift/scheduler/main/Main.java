/**
* <h1>Automated Shift Scheduler</h1>
* <p>This project assigns shifts to employees so that each employee gets his salary equal or close to 
* his/her desired earnings. 
* The desired earnings and hourly earnings are assigned randomly. A total of 10 employees 
* and 40 shifts are generated.</p>
*
* @author  Deepak Srinivas Pradhan
*/
package com.shift.scheduler.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.shift.scheduler.model.Employee;
import com.shift.scheduler.model.Shift;
import com.shift.scheduler.algorithm.SchedulingAlgorithmImpl;
import com.shift.scheduler.algorithm.SchedulingAlgorithmI;

public class Main {
	public static final int duration = 2;

	public static void main(String[] args) {
		int totalEmployees = 10;
		int minDesiredEarnings = 30;
		int maxDesiredEarnings = 90;

		int minHourlyEarnings = 5;
		int maxHourlyEarnings = 10;

		int totalShifts = 40;

		Date startTime;
		long startTimeInMillis;
		long maxstartTimeInMillis;

		// generates 10 employees
		// assigns their desiredEarnings between 30 and 90
		List<Employee> employees = new ArrayList<Employee>();
		for (int i = 0; i < totalEmployees; i++) {
			employees.add(new Employee(i + 1, getRandomNumber(minDesiredEarnings, maxDesiredEarnings)));
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
		Calendar calendar;
		List<Shift> shifts = new ArrayList<Shift>();
		try {
			// given start time
			startTime = simpleDateFormat.parse("2020.08.01 00:00");
			startTimeInMillis = startTime.getTime();

			// given end time
			calendar = new GregorianCalendar(2020, 7, 5, 0, 0);
			calendar.add(Calendar.HOUR, -duration);
			maxstartTimeInMillis = calendar.getTimeInMillis();

			for (int i = 0; i < totalShifts; i++) {
				long start = getRandomLongNumber(startTimeInMillis, maxstartTimeInMillis);
				shifts.add(new Shift(i + 1, start,
						getEnd(start),
						getRandomNumber(minHourlyEarnings, maxHourlyEarnings)));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SchedulingAlgorithmI schedulingAlgorithm = new SchedulingAlgorithmImpl();
		List<Shift> assignedShifts = schedulingAlgorithm.scheduleShifts(employees, shifts, duration);
		for (int i = 0; i < assignedShifts.size(); i++) {
			System.out.println(assignedShifts.get(i));
		}
	}

	protected static int getRandomNumber(int min, int max) {
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNum;
	}

	protected static long getRandomLongNumber(long min, long max) {
		long randomNum = ThreadLocalRandom.current().nextLong(min, max + 1);
		return randomNum;
	}

	protected static long getEnd(long start) {
		Calendar endTime;
		endTime = Calendar.getInstance();
		endTime.setTimeInMillis(start);
		endTime.add(Calendar.HOUR, duration);
		return endTime.getTimeInMillis();
	}
}
