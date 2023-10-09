package com.validator.fileValidator.controller;

import com.validator.fileValidator.model.Person;
import com.validator.fileValidator.model.SafetyIncident;
import com.validator.fileValidator.service.CSVValidatorService;
import com.validator.fileValidator.service.XLSXValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class FileValidatorController {

    private final CSVValidatorService csvValidatorService;
    private final XLSXValidatorService xlsxValidatorService;
    @Autowired
    public FileValidatorController(CSVValidatorService csvValidatorService, XLSXValidatorService xlsxValidatorService) {
        this.csvValidatorService = csvValidatorService;
        this.xlsxValidatorService = xlsxValidatorService;
    }

    @PostMapping("/csv")
    public boolean validateCSV(@RequestBody Person person) {
            return this.csvValidatorService.validateCSV(person);
    }

    @PostMapping("/xlsx")
    public boolean validateXLSX(@RequestBody SafetyIncident safetyIncident) {
        return this.xlsxValidatorService.validateXLSX(safetyIncident);
    }
}
