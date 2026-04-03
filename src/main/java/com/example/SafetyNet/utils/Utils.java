package com.example.SafetyNet.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static int birthdayToAge(String birthdate) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate = LocalDate.parse(birthdate, formatter);
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
}
