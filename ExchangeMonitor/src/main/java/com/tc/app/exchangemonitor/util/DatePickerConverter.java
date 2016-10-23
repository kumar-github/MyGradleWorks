package com.tc.app.exchangemonitor.util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.util.StringConverter;

public class DatePickerConverter extends StringConverter<LocalDate>
{
	// Default Date Pattern
	private String pattern = "MM/dd/yyyy";
	private DateTimeFormatter dateTimeFormatter;

	public DatePickerConverter()
	{
		dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
	}

	public DatePickerConverter(String pattern)
	{
		this.pattern = pattern;
		dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
	}

	// Change String to LocalDate
	public LocalDate fromString(String text)
	{
		LocalDate date = null;

		if (text != null && !text.trim().isEmpty())
		{
			date = LocalDate.parse(text, dateTimeFormatter);
		}

		return date;
	}

	// Change LocalDate to String
	public String toString(LocalDate date)
	{
		String text = null;

		if (date != null)
		{
			text = dateTimeFormatter.format(date);
		}

		return text;
	}
}