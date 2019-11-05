package com.dxc.reports.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class InputValidator {

	public Boolean emailValidator(String input) {
		
		if (input.equals("") || input == null)
			return true;
		else {
			String emails[] = input.split(",");
			for (String email : emails) {
				email=email.toLowerCase();
				if (!email.matches(
						"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@dxc.com")
						|| email.equals(""))
					return false;

			}
		}
		return true;
	}

	public Boolean dateValidator(String input) {

		
		if (input.equals("") || input == null) {
			return false;
		} else {
			String dateDivision[] = input.split("-");
			if (dateDivision.length != 3) {
				return false;
			} else {

				if (!input.matches("((19|20)\\d\\d-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]))"))
					return false;
				else {
					YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(dateDivision[0]),
							Integer.parseInt(dateDivision[1]));
					if (yearMonthObject.lengthOfMonth() >= Integer.parseInt(dateDivision[2]))
						return true;
					return false;
				}
			}
		}

	}
	
	public Boolean dateCheck(String startDate,String endDate)
	{
		Date conStartDate;
		Date conEndDate;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			conStartDate=sdf1.parse(startDate);
			conEndDate=sdf1.parse(endDate);
			if(conStartDate.after(conEndDate))
				return false;
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
}
