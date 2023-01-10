/**
 * @author Bharat Pathak
 */

import com.google.gson.Gson;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class AddressBook {

    public static final int ADD = 1;
    public static final int EDIT = 2;
    public static final int DELETE = 3;
    public static final int VIEW = 4;
    public static final int SEARCH_PERSON = 5;
    public static final int VIEW_PERSON = 6;
    public static final int COUNT_PERSON = 7;
    public static final int SORT_PERSON = 8;
    public static final int EXIT = 0;
    public static final int BY_CITY = 1;
    public static final int BY_STATE = 2;
    public static final int BY_NAME = 3;
    public static final int BY_ZIP = 4;
    public static final String MAP = "map";

    Map<String, ArrayList<Address>> map = new HashMap<>();
    Map<String, List<Address>> viewMap;
    Map<String, Long> countMap;
    ArrayList<Address> list;
    List<String[]> fileData;
    List<Address> file;
    Scanner sc = new Scanner(System.in);

    // Here we are create a function to add details in the arraylist by using user input :-
    public void addDetails() {
        Address address = new Address();

        System.out.println("Enter First Name : ");
        address.setFirstName(sc.nextLine());

        System.out.println("Enter Last Name : ");
        address.setLastName(sc.nextLine());

        System.out.println("Enter Address : ");
        address.setAddress(sc.nextLine());

        System.out.println("Enter City Name : ");
        address.setCity(sc.nextLine());

        System.out.println("Enter State Name : ");
        address.setState(sc.nextLine());

        System.out.println("Enter Zip Code : ");
        address.setZip(sc.nextLine());

        System.out.println("Enter Phone Number : ");
        address.setPhoneNumber(sc.nextLine());

        // Here we are check the duplicate phone number in the arraylist :-
        if (duplicateCheck(address.getPhoneNumber())) {
            System.out.println("Given Phone Number is Already Exists!");
            return;
        }

        System.out.println("Enter Email Id : ");
        address.setEmail(sc.nextLine());

        // Here we are adding address in the arraylist :-
        list.add(address);
        System.out.println(address);
        System.out.println("Contact Added Successfully.");
    }

    // Here we are create a function to edit details from the arraylist by using user input :-
    public void editDetails(String editByPhoneNumber, String type) {
        boolean flag = false;
        if (list.isEmpty() && map.isEmpty()) {
            System.out.println("There Is No Contact Available");
        } else {
            for (Address address : list) {
                if ((address.getPhoneNumber().equals(editByPhoneNumber))) {

                    System.out.print("1.Edit First Name  2.Edit Last Name  " +
                            "3.Edit Address  4.Edit City Name  " +
                            "5.Edit State Name  6.Edit Zip Code  " +
                            "7.Edit Phone Number  \n8.Edit Email Id  0.Exit\n->");

                    int option = sc.nextInt();
                    sc.nextLine();

                    switch (option) {
                        case 1:
                            System.out.println("Enter New First Name : ");
                            address.setFirstName(sc.nextLine());
                            break;
                        case 2:
                            System.out.println("Enter New Last Name : ");
                            address.setLastName(sc.nextLine());
                            break;
                        case 3:
                            System.out.println("Enter New Address : ");
                            address.setAddress(sc.nextLine());
                            break;
                        case 4:
                            System.out.println("Enter New City Name : ");
                            address.setCity(sc.nextLine());
                            break;
                        case 5:
                            System.out.println("Enter New State Name : ");
                            address.setState(sc.nextLine());
                            break;
                        case 6:
                            System.out.println("Enter New Zip Code : ");
                            address.setZip(sc.nextLine());
                            break;
                        case 7:
                            System.out.println("Enter New Phone Number : ");
                            String editPhoneNumber = sc.nextLine();
                            // Here we are check the duplicate phone number in the arraylist :-
                            if (duplicateCheck(address.getPhoneNumber())) {
                                System.out.println("Given Phone Number is Already Exists!");
                                return;
                            } else {
                                address.setPhoneNumber(editPhoneNumber);
                            }
                            break;
                        case 8:
                            System.out.println("Enter New Email Id : ");
                            address.setEmail(sc.nextLine());
                            break;
                    }

                    System.out.println(address);
                    System.out.println("Contact Edited Successfully.");
                    flag = true;
                    break;
                }
            }
            if (!flag && !type.equals(AddressBook.MAP)) {
                System.out.println("Please Enter Valid Phone Number!");
            }
        }
    }

    // Here we are create a function to delete details from the arraylist by using user input :-
    public void deleteDetails(String deleteByPhoneNumber, String type) {
        boolean flag = false;
        if (list.isEmpty() && map.isEmpty()) {
            System.out.println("There Is No Contact Available");
        } else {
            for (Address address : list) {
                if (address.getPhoneNumber().equals(deleteByPhoneNumber)) {
                    list.remove(address);
                    System.out.println("Contact Deleted Successfully.");
                    flag = true;
                    break;
                }
            }
            if (!flag && !type.equals(AddressBook.MAP)) {
                System.out.println("Please Enter Valid Phone Number!");
            }
        }
    }

    // Here we are create a function to show details from the arraylist by using user input :-
    public void showDetails() {
        System.out.println("Number Of Contacts Stored : " + list.size());
        if (list.isEmpty()) {
            System.out.println("There Is No Contact Available");
        } else {
            list.forEach(System.out::println);
        }
    }

    // Here we are create a function to add multiple books using map by using user input :-
    public void newAddressBook() {
        System.out.println("Enter Name Of New AddressBook : ");
        String addressBookName = sc.nextLine().toUpperCase();
        if (map.containsKey(addressBookName)) {
            System.out.println("[" + addressBookName + "]->This AddressBook Is Already Exists!");
        } else {
            list = new ArrayList<>();
            map.put(addressBookName, list);
            System.out.println("AddressBook [" + addressBookName + "] Added Successfully.");
            operationInBook();
        }
    }

    // Here we are create a function to edit address book name by using user input :-
    public void editAddressBookName() {
        System.out.println("AddressBooks : " + map.keySet());
        System.out.println("Enter Old Name Of AddressBook You Want To Change Name : ");
        String oldBookName = sc.nextLine().toUpperCase();
        if (map.containsKey(oldBookName)) {

            System.out.println("Enter New Name Of AddressBook : ");
            String newBookName = sc.nextLine().toUpperCase();

            map.put(newBookName, map.remove(oldBookName));
            System.out.println("AddressBook Name [" + newBookName + "] Edited Successfully.");
        } else {
            System.out.println("Please Enter Valid Name Of AddressBook!");
        }
    }

    // Here we are create a function to display books and their addresses :-
    public void displayAddressBook() {
        System.out.println("Number Of AddressBook : " + map.size() + "\n");
        if (map.isEmpty()) {
            System.out.println("There Is No AddressBook Available");
        } else {
            map.forEach((key, value) -> System.out.println("[" + key + "]" + "->" + value + "\n"));
        }
    }

    // Here we are create a function to open old book and perform the operation in the arraylist by using user input :-
    public void openAddressBook() {
        System.out.println("AddressBooks : " + map.keySet());
        if (map.isEmpty()) {
            System.out.println("There Is No AddressBook Available");
        } else {
            System.out.println("Enter The Name Of Old AddressBook You Want To Open : ");
            String addBookDetails = sc.nextLine().toUpperCase();
            if (map.containsKey(addBookDetails)) {
                list = map.get(addBookDetails);
                operationInBook();
            } else {
                System.out.println("AddressBook Doesn't Exists!");
            }
        }
    }

    // Here we are create a function to edit details from the multiple book by using user input :-
    public void editFromMultipleBook() {
        System.out.println("Enter Phone Number Of A Person To Edit : ");
        String editByPhoneNumber = sc.nextLine();
        for (Map.Entry<String, ArrayList<Address>> entry : map.entrySet()) {
            list = map.get(entry.getKey());
            editDetails(editByPhoneNumber, AddressBook.MAP);
            writeAddressBookTxt();
            writeAddressBookCsv();
            writeAddressBookJson();
        }
    }

    // Here we are create a function to delete details from the multiple book by using user input :-
    public void deleteFromMultipleBook() {
        System.out.println("Enter Phone Number Of A Person To Delete : ");
        String deleteByPhoneNumber = sc.nextLine();
        for (Map.Entry<String, ArrayList<Address>> entry : map.entrySet()) {
            list = map.get(entry.getKey());
            deleteDetails(deleteByPhoneNumber, AddressBook.MAP);
            writeAddressBookTxt();
            writeAddressBookCsv();
            writeAddressBookJson();
        }
    }

    // Here we are create a function to delete book from the map by using user input :-
    public void deleteAddressBook() {
        System.out.println("AddressBooks : " + map.keySet());
        if (map.isEmpty()) {
            System.out.println("There Is No AddressBook Available");
        } else {
            System.out.println("Enter The Name Of AddressBook You Want To Delete : ");
            String deleteBook = sc.nextLine().toUpperCase();
            if (map.containsKey(deleteBook)) {
                map.remove(deleteBook);
                writeAddressBookTxt();
                writeAddressBookCsv();
                writeAddressBookJson();
                System.out.println("AddressBook Deleted Successfully.");
            } else {
                System.out.println("AddressBook Doesn't Exist!");
            }
        }
    }

    // Here we are create a function to check the duplicate phone number in the arraylist :-
    public boolean duplicateCheck(String phoneNumber) {
        Address check = list.stream()
                .filter(i -> i.getPhoneNumber().equals(phoneNumber))
                .findFirst().orElse(null);
        return check != null;
    }

    // Here we are create a function to search person by city from the map by using user input :-
    public void searchPersonByCityMap() {
        System.out.println("Enter City Name To Search Person : ");
        String searchByCity = sc.nextLine();
        map.keySet().forEach(key -> map.get(key).stream()
                .filter(i -> i.getCity().equals(searchByCity))
                .forEach(System.out::println));
    }

    // Here we are create a function to search person by state from the map by using user input :-
    public void searchPersonByStateMap() {
        System.out.println("Enter State Name To Search Person : ");
        String searchByState = sc.nextLine();
        map.keySet().forEach(key -> map.get(key).stream()
                .filter(i -> i.getState().equals(searchByState))
                .forEach(System.out::println));
    }

    // Here we are create a function to search person by city from the arraylist by using user input :-
    public void searchPersonByCityList() {
        System.out.println("Enter City Name To Search Person : ");
        String searchByCity = sc.nextLine();
        list.stream().filter(i -> i.getCity().equals(searchByCity))
                .forEach(System.out::println);
    }

    // Here we are create a function to search person by state from the arraylist by using user input :-
    public void searchPersonByStateList() {
        System.out.println("Enter State Name To Search Person : ");
        String searchByState = sc.nextLine();
        list.stream().filter(i -> i.getState().equals(searchByState))
                .forEach(System.out::println);
    }

    // Here we are create a function to view person by city from the map :-
    public void viewPersonByCityMap() {
        viewMap = new HashMap<>();
        map.keySet().forEach(i -> map.get(i).stream()
                .collect(groupingBy(Address::getCity))
                .forEach((key, value) -> viewMap.merge(key, value, (city, details) -> {
                    city.addAll(details);
                    return city;
                })));
        viewMap.forEach(((key, value) -> System.out.println("[" + key.toUpperCase() + "]" + "->" + value + "\n")));
    }

    // Here we are create a function to view person by state from the map :-
    public void viewPersonByStateMap() {
        viewMap = new HashMap<>();
        map.keySet().forEach(i -> map.get(i).stream()
                .collect(groupingBy(Address::getState))
                .forEach((key, value) -> viewMap.merge(key, value, (state, details) -> {
                    state.addAll(details);
                    return state;
                })));
        viewMap.forEach(((key, value) -> System.out.println("[" + key.toUpperCase() + "]" + "->" + value + "\n")));
    }

    // Here we are create a function to view person by city from the arraylist :-
    public void viewPersonByCityList() {
        viewMap = new HashMap<>();
        list.stream().collect(groupingBy(Address::getCity))
                .forEach((key, value) -> viewMap.merge(key, value, (city, details) -> {
                    city.addAll(details);
                    return city;
                }));
        viewMap.forEach(((key, value) -> System.out.println("[" + key.toUpperCase() + "]" + "->" + value + "\n")));
    }

    // Here we are create a function to view person by state from the arraylist :-
    public void viewPersonByStateList() {
        viewMap = new HashMap<>();
        list.stream().collect(groupingBy(Address::getState))
                .forEach((key, value) -> viewMap.merge(key, value, (state, details) -> {
                    state.addAll(details);
                    return state;
                }));
        viewMap.forEach(((key, value) -> System.out.println("[" + key.toUpperCase() + "]" + "->" + value + "\n")));
    }

    // Here we are create a function to count person by city from the map :-
    public void countPersonByCityMap() {
        countMap = new HashMap<>();
        map.keySet().forEach(i -> map.get(i).stream()
                .collect(groupingBy(Address::getCity, counting()))
                .forEach((key, value) -> {
                    countMap.merge(key, value, Long::sum);
                }));
        countMap.forEach(((key, value) -> System.out.println("[" + key.toUpperCase() + "]" + "->" + value + "\n")));
    }

    // Here we are create a function to count person by state from the map :-
    public void countPersonByStateMap() {
        countMap = new HashMap<>();
        map.keySet().forEach(i -> map.get(i).stream()
                .collect(groupingBy(Address::getState, counting()))
                .forEach((key, value) -> {
                    countMap.merge(key, value, Long::sum);
                }));
        countMap.forEach(((key, value) -> System.out.println("[" + key.toUpperCase() + "]" + "->" + value + "\n")));
    }

    // Here we are create a function to count person by city from the arraylist :-
    public void countPersonByCityList() {
        countMap = new HashMap<>();
        list.stream().collect(groupingBy(Address::getCity, counting()))
                .forEach((key, value) -> {
                    countMap.merge(key, value, Long::sum);
                });
        countMap.forEach(((key, value) -> System.out.println("[" + key.toUpperCase() + "]" + "->" + value + "\n")));
    }

    // Here we are create a function to count person by state from the arraylist :-
    public void countPersonByStateList() {
        countMap = new HashMap<>();
        list.stream().collect(groupingBy(Address::getState, counting()))
                .forEach((key, value) -> {
                    countMap.merge(key, value, Long::sum);
                });
        countMap.forEach(((key, value) -> System.out.println("[" + key.toUpperCase() + "]" + "->" + value + "\n")));
    }

    // Here we are create a function to sort person by name from the map :-
    public void sortByNameMap() {
        viewMap = new HashMap<>();
        map.keySet().forEach(i -> map.get(i).stream().collect(groupingBy(Address::getFirstName))
                .forEach((key, value) -> viewMap.merge(key.toUpperCase(), value, (firstName, Details) -> {
                    firstName.addAll(Details);
                    return firstName;
                })));
        viewMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
    }

    // Here we are create a function to sort person by city from the map :-
    public void sortByCityMap() {
        viewMap = new HashMap<>();
        map.keySet().forEach(i -> map.get(i).stream().collect(groupingBy(Address::getCity))
                .forEach((key, value) -> viewMap.merge(key.toUpperCase(), value, (city, Details) -> {
                    city.addAll(Details);
                    return city;
                })));
        viewMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
    }

    // Here we are create a function to sort person by state from the map :-
    public void sortByStateMap() {
        viewMap = new HashMap<>();
        map.keySet().forEach(i -> map.get(i).stream().collect(groupingBy(Address::getState))
                .forEach((key, value) -> viewMap.merge(key.toUpperCase(), value, (state, Details) -> {
                    state.addAll(Details);
                    return state;
                })));
        viewMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
    }

    // Here we are create a function to sort person by zip from the map :-
    public void sortByZipMap() {
        viewMap = new HashMap<>();
        map.keySet().forEach(i -> map.get(i).stream().collect(groupingBy(Address::getZip))
                .forEach((key, value) -> viewMap.merge(key.toUpperCase(), value, (zip, Details) -> {
                    zip.addAll(Details);
                    return zip;
                })));
        viewMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
    }

    // Here we are create a function to sort person by name from the arraylist :-
    public void sortByNameList() {
        list.stream().sorted(Comparator.comparing(Address::getFirstName))
                .toList().forEach(System.out::println);
    }

    // Here we are create a function to sort person by city from the arraylist :-
    public void sortByCityList() {
        list.stream().sorted(Comparator.comparing(Address::getCity))
                .toList().forEach(System.out::println);
    }

    // Here we are create a function to sort person by state from the arraylist :-
    public void sortByStateList() {
        list.stream().sorted(Comparator.comparing(Address::getState))
                .toList().forEach(System.out::println);
    }

    // Here we are create a function to sort person by zip from the arraylist :-
    public void sortByZipList() {
        list.stream().sorted(Comparator.comparing(Address::getZip))
                .toList().forEach(System.out::println);
    }

    // Here we are create a function to write a data in the text file from the map :-
    public void writeAddressBookTxt() {
        StringBuilder buffer = new StringBuilder();
        map.keySet().forEach(key -> map.get(key)
                .forEach(c -> buffer.append(c.toString().concat("\n"))));
        try {
            Path path = Paths.get("addressBook.txt");
            Files.write(path, buffer.toString().getBytes());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Here we are create a function to read the data from the text file :-
    public void readAddressBookTxt() {
        boolean flag = false;
        try {
            File file = new File("addressBook.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                System.out.println(data);
                if (!data.isEmpty()) {
                    flag = true;
                }
            }
            if (flag) {
                System.out.println("Data Read Successfully.");
            } else {
                System.out.println("File Is Empty!");
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Here we are create a function to write a data in the csv file from the map :-
    public void writeAddressBookCsv() {
        Path path = Paths.get("addressBook.csv");
        try {
            FileWriter fileFilter = new FileWriter(path.toFile());
            CSVWriter writer = new CSVWriter(fileFilter);
            fileData = new ArrayList<>();
            String[] header = {"FirstName", "LastName", "Address", "City", "State",
                    "ZipCode", "PhoneNumber", "EmailId"};
            fileData.add(header);

            map.values().forEach(value -> value.stream().map(contact -> new String[]{contact.getFirstName(),
                    contact.getLastName(), contact.getAddress(), contact.getCity(),
                    contact.getState(), contact.getZip(), contact.getPhoneNumber(),
                    contact.getEmail()}).forEach(info -> fileData.add(info)));

            writer.writeAll(fileData);
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    // Here we are create a function to read the data from the csv file :-
    public void readAddressBookCsv() {
        boolean flag = false;
        try {
            Scanner sc = new Scanner(new File("addressBook.csv"));
            sc.useDelimiter(",");
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                System.out.println(data);
                if (!data.isEmpty()) {
                    flag = true;
                }
            }
            if (flag) {
                System.out.println("Data Read Successfully.");
            } else {
                System.out.println("File Is Empty!");
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Here we are create a function to write a data in the json file from the map :-
    public void writeAddressBookJson() {
        try {
            file = new ArrayList<>();
            Gson gson = new Gson();
            FileWriter writer = new FileWriter("addressBook.json");
            map.values().forEach(value -> file.addAll(value));
            String json = gson.toJson(file);
            writer.write(json);
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // Here we are create a function to read the data from the json file :-
    public void readAddressBookJson() {
        try {
            String reader = new String(Files.readAllBytes(Paths.get("addressBook.json")));
            if (reader.isEmpty()) {
                System.out.println("File Is Empty!");
            } else {
                System.out.println(reader);
                System.out.println("Data Read Successfully.");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void sortPersonList() {
        boolean exit = true;
        while (exit) {
            System.out.print("1.Sort Person By City ");
            System.out.print(" 2.Sort Person By State ");
            System.out.print(" 3.Sort Person By Name ");
            System.out.print(" 4.Sort Person By Zip ");
            System.out.print(" 0.Exit \n->");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case BY_CITY:
                    sortByCityList();
                    break;
                case BY_STATE:
                    sortByStateList();
                    break;
                case BY_NAME:
                    sortByNameList();
                    break;
                case BY_ZIP:
                    sortByZipList();
                    break;
                case EXIT:
                    exit = false;
                    break;
                default:
                    System.out.println("Please Choose Valid Option!");
                    break;
            }
        }
    }

    public void sortPersonMap() {
        boolean exit = true;
        while (exit) {
            System.out.print("1.Sort Person By City ");
            System.out.print(" 2.Sort Person By State ");
            System.out.print(" 3.Sort Person By Name ");
            System.out.print(" 4.Sort Person By Zip ");
            System.out.print(" 0.Exit \n->");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case BY_CITY:
                    sortByCityMap();
                    break;
                case BY_STATE:
                    sortByStateMap();
                    break;
                case BY_NAME:
                    sortByNameMap();
                    break;
                case BY_ZIP:
                    sortByZipMap();
                    break;
                case EXIT:
                    exit = false;
                    break;
                default:
                    System.out.println("Please Choose Valid Option!");
                    break;
            }
        }
    }

    public void countPersonList() {
        boolean exit = true;
        while (exit) {
            System.out.print("1.Count Person By City ");
            System.out.print(" 2.Count Person By State ");
            System.out.print(" 0.Exit \n->");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case BY_CITY:
                    countPersonByCityList();
                    break;
                case BY_STATE:
                    countPersonByStateList();
                    break;
                case EXIT:
                    exit = false;
                    break;
                default:
                    System.out.println("Please Choose Valid Option!");
                    break;
            }
        }
    }

    public void countPersonMap() {
        boolean exit = true;
        while (exit) {
            System.out.print("1.Count Person By City ");
            System.out.print(" 2.Count Person By State ");
            System.out.print(" 0.Exit \n->");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case BY_CITY:
                    countPersonByCityMap();
                    break;
                case BY_STATE:
                    countPersonByStateMap();
                    break;
                case EXIT:
                    exit = false;
                    break;
                default:
                    System.out.println("Please Choose Valid Option!");
                    break;
            }
        }
    }

    public void viewPersonFromList() {
        boolean exit = true;
        while (exit) {
            System.out.print("1.View Person By City ");
            System.out.print(" 2.View Person By State ");
            System.out.print(" 0.Exit \n->");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case BY_CITY:
                    viewPersonByCityList();
                    break;
                case BY_STATE:
                    viewPersonByStateList();
                    break;
                case EXIT:
                    exit = false;
                    break;
                default:
                    System.out.println("Please Choose Valid Option!");
                    break;
            }
        }
    }

    public void viewPersonFromMap() {
        boolean exit = true;
        while (exit) {
            System.out.print("1.View Person By City ");
            System.out.print(" 2.View Person By State ");
            System.out.print(" 0.Exit \n->");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case BY_CITY:
                    viewPersonByCityMap();
                    break;
                case BY_STATE:
                    viewPersonByStateMap();
                    break;
                case EXIT:
                    exit = false;
                    break;
                default:
                    System.out.println("Please Choose Valid Option!");
                    break;
            }
        }
    }

    public void searchPersonFromList() {
        boolean exit = true;
        while (exit) {
            System.out.print("1.Search Person By City ");
            System.out.print(" 2.Search Person By State ");
            System.out.print(" 0.Exit \n->");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case BY_CITY:
                    searchPersonByCityList();
                    break;
                case BY_STATE:
                    searchPersonByStateList();
                    break;
                case EXIT:
                    exit = false;
                    break;
                default:
                    System.out.println("Please Choose Valid Option!");
                    break;
            }
        }
    }

    public void searchPersonFromMap() {
        boolean exit = true;
        while (exit) {
            System.out.print("1.Search Person By City ");
            System.out.print(" 2.Search Person By State ");
            System.out.print(" 0.Exit \n->");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case BY_CITY:
                    searchPersonByCityMap();
                    break;
                case BY_STATE:
                    searchPersonByStateMap();
                    break;
                case EXIT:
                    exit = false;
                    break;
                default:
                    System.out.println("Please Choose Valid Option!");
                    break;
            }
        }
    }

    public void operationInBook() {
        boolean exit = true;
        while (exit) {
            System.out.print("1.Add Contact ");
            System.out.print(" 2.Edit Contact ");
            System.out.print(" 3.Delete Contact ");
            System.out.print(" 4.View Contact ");
            System.out.print(" 5.Search Contact(City/State) ");
            System.out.print(" 6.View Contact(City/State) ");
            System.out.print("\n7.Count Contact(City/State) ");
            System.out.print(" 8.Sort Person(Name/City/State/Zip) ");
            System.out.print(" 0.Exit \n->");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case ADD:
                    addDetails();
                    break;
                case EDIT:
                    System.out.println("Enter Phone Number Of A Person To Edit : ");
                    editDetails(sc.nextLine(), "");
                    break;
                case DELETE:
                    System.out.println("Enter Phone Number Of A Person To Delete : ");
                    deleteDetails(sc.nextLine(), "");
                    break;
                case VIEW:
                    showDetails();
                    break;
                case SEARCH_PERSON:
                    searchPersonFromList();
                    break;
                case VIEW_PERSON:
                    viewPersonFromList();
                    break;
                case COUNT_PERSON:
                    countPersonList();
                    break;
                case SORT_PERSON:
                    sortPersonList();
                    break;
                case EXIT:
                    exit = false;
                    break;
                default:
                    System.out.println("Please Choose Valid Option!");
                    break;
            }
            writeAddressBookTxt();
            writeAddressBookCsv();
            writeAddressBookJson();
        }
    }
}