package com.validator.fileValidator.service;

import com.validator.fileValidator.model.Person;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CSVValidatorService {
    private final HashSet<String> jobTitles = new HashSet<>();

    public CSVValidatorService(){
        this.jobTitles.add("haematologist");
        this.jobTitles.add("phytotherapist");
        this.jobTitles.add("building surveyor");
        this.jobTitles.add("insurance account manager");
        this.jobTitles.add("educational psychologist");
    }

    public boolean validateCSV(Person person) {

        return validateEmail(person.getEmail()) &&
                validateDateOfBirth(person.getDateOfBirth()) &&
                validateJobTitle(person.getJobTitle());
    }

    public boolean validateEmail(String email) {
        if (validateBlank(email)) {
            return false;
        }
        String emailRegularExpression = "^[A-Za-z0-9]+([._-][A-Za-z0-9]+)*@[A-Za-z0-9]+([.-][A-Za-z0-9]+)*\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegularExpression);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validateDateOfBirth(String dateOfBirth){
        if (validateBlank(dateOfBirth)) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(dateOfBirth);
            Date minDate = dateFormat.parse("1980-01-01");
            return date.after(minDate);
        } catch (ParseException e) {
            System.out.println("Fecha sin formato");
            //Pendiente exepción
            return false;
        }
    }

    public boolean validateJobTitle(String jobTitle){
        if (validateBlank(jobTitle)){
            return false;
        }
        return this.jobTitles.contains(jobTitle.toLowerCase());
    }
    public boolean validateBlank(String textToValidate){
        return textToValidate == null || textToValidate.trim().isEmpty();
    }
}
