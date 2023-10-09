package com.validator.fileValidator.service;

import com.validator.fileValidator.model.SafetyIncident;
import org.springframework.stereotype.Service;

import java.util.HashSet;
@Service
public class XLSXValidatorService {
    private final HashSet<String> reportTypes = new HashSet<>();

    public XLSXValidatorService(){
        this.reportTypes.add("near miss");
        this.reportTypes.add("lost time");
        this.reportTypes.add("first aid");
    }

    public boolean validateXLSX(SafetyIncident safetyIncident) {
        return validateInjuryLocation(safetyIncident.getInjuryLocation()) &&
                validateReportType(safetyIncident.getReportType());
    }

    public boolean validateInjuryLocation(String injuryLocation){
        if (validateBlank(injuryLocation)){
            return false;
        }
        return !injuryLocation.equals("N/A");
    }

    public boolean validateReportType(String reportType){
        if (validateBlank(reportType)){
            return false;
        }
        return this.reportTypes.contains(reportType.toLowerCase());
    }

    public boolean validateBlank(String textToValidate){
        return textToValidate == null || textToValidate.trim().isEmpty();
    }

}
