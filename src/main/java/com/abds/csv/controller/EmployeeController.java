package com.abds.csv.controller;

import com.abds.csv.entity.Employee;
import com.abds.csv.service.EmployeeService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    private static final String CSV_HEADER = "Email,FirstName,ID,LastName,Salary\n";

    @GetMapping("/csvexport")
    public void exportCSV(HttpServletResponse response)
            throws Exception {

        //set file name and content type
        String filename = "Employee-data.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");
        //create a csv writer
//        StatefulBeanToCsv<Employee> writer = new StatefulBeanToCsvBuilder<Employee>(response.getWriter())
//                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false)
//                .build();
        List<Employee> employees = employeeService.findAll();
//        //write all employees data to csv file
//        writer.write(employees);

        //Save to folder
        StringBuilder csvContent = new StringBuilder();
        csvContent.append(CSV_HEADER);
        for(Employee emp : employees){

            csvContent.append(emp.getEmail()).append(",")
                    .append(emp.getFirstName()).append(",")
                    .append(emp.getId()).append(",")
                    .append(emp.getLastName()).append(",")
                    .append(emp.getSalary()).append("\n");
        }

        String folderPath = "D:\\Workspace\\Microservices"; // Replace with your folder path
        String fileName = "output.csv";
        String filePath = folderPath + "/" + fileName;
        Files.createDirectories(Paths.get(folderPath));
        Files.write(Paths.get(filePath),csvContent.toString().getBytes());

    }


}
