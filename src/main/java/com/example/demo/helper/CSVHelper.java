package com.example.demo.helper;

import com.example.demo.model.Apartment;
import com.example.demo.model.Contact;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ApartmentRepository;
import com.example.demo.service.ApartmentService;
import com.example.demo.service.CustomerService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVHelper {
    @Autowired
    static CustomerRepository customerRepository;

    @Autowired
    static ApartmentRepository apartmentRepository;
    public static String TYPE = "text/csv";

    @Autowired
    ApartmentService apartmentService;

    @Autowired
    CustomerService customerService;
    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Customer> addCustomer(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Customer> tutorials = new ArrayList<Customer>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                    Customer customer = new Customer(
                            csvRecord.get(0),
                            csvRecord.get("FirstName"),
                            csvRecord.get("LastName"),
                            csvRecord.get("Address"),
                            Integer.parseInt(csvRecord.get("Age")),
                            csvRecord.get("Status")
                    );

                    tutorials.add(customer);
            }

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public List<Contact> addContact(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Contact> list = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

//             for (CSVRecord csvRecord : csvRecords) {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust the format according to your CSV
//                Date StartDate = null;
//                Date EndDate = null;
//                try {
//                    StartDate = dateFormat.parse(csvRecord.get("StartDate"));
//                } catch (ParseException e) {
//                    e.printStackTrace(); // Handle the exception appropriately
//                }
//                try {
//                    EndDate = dateFormat.parse(csvRecord.get("EndDate"));
//                } catch (ParseException e) {
//                    e.printStackTrace(); // Handle the exception appropriately
//                }
//
//                String apartmentID = csvRecord.get("Apartment");
//                String customerID = csvRecord.get("CustomerID");
//                Customer customer = customerService.findById(csvRecord.get(CONTRACTHEADER[1]));
//                Customer customer = new Customer();
//
//                if (result.isPresent()){
//                    customer = result.get();
//                }
//                Apartment apartment = apartmentService.findById(csvRecord.get(CONTRACTHEADER[2]));
//                Apartment apartment = new Apartment();
//                if(result2.isPresent()){
//                    apartment = result2.get();
//                }

//                Contact contact1 = new Contact(
//                        csvRecord.get(0),
//                        customer,
//                        apartment,
//                        StartDate,
//                        EndDate
//                );
//                Contact contact = Contact.builder()
//                        .id(csvRecord.get(0))
//                        .customer(customer)
//                        .apartment(apartment)
//                        .StartDate(LocalDate.parse(csvRecord.get("StartDate")))
//                        .EndDate(LocalDate.parse(csvRecord.get("EndDate"))).build();
//
//
//                list.add(contact);
//            }

            for (CSVRecord csvRecord : csvRecords) {
                String apartmentID = csvRecord.get("Apartment");
                String customerID = csvRecord.get("CustomerID");
                LocalDate StartDate = LocalDate.parse(csvRecord.get("StartDate"));
                LocalDate EndDate = LocalDate.parse(csvRecord.get("EndDate"));

                try {
                    Customer customer = customerService.findById(customerID);
                    Apartment apartment = apartmentService.findById(apartmentID);

                    if (customer != null && apartment != null) {
                        Contact contact = Contact.builder()
                                .id(csvRecord.get(0))
                                .customer(customer)
                                .apartment(apartment)
                                .StartDate(StartDate)
                                .EndDate(EndDate)
                                .build();

                        list.add(contact);
                    } else {
                        System.out.println("Customer or apartment not found for IDs: " + customerID + ", " + apartmentID);
                    }
                } catch (Exception e) {
                    System.out.println("Error processing CSV record: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            return list;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<Apartment> addApartment(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Apartment> list = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Apartment apartment = new Apartment(
                        csvRecord.get(0),
                        csvRecord.get("Address"),
                        Integer.parseInt(csvRecord.get("RentalPrice")),
                        Integer.parseInt(csvRecord.get("NumberOfRoom"))
                );
                list.add(apartment);
            }

            return list;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
