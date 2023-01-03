/**
 * @author Bharat Pathak
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook {

    public static final int ADD = 1;
    public static final int EDIT = 2;
    public static final int DELETE = 3;
    public static final int VIEW = 4;
    public static final int EXIT = 0;
    public static final String MAP = "map";

    Map<String, ArrayList<Address>> map = new HashMap<>();
    ArrayList<Address> list;
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

        System.out.println("Enter Email Id : ");
        address.setEmail(sc.nextLine());

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
                            "5.Edit State Name  6.Edit Zip Code " +
                            " 7.Edit Phone Number  8.Edit Email Id\n->");

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
                            address.setPhoneNumber(sc.nextLine());
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
        }
    }

    // Here we are create a function to delete details from the multiple book by using user input :-
    public void deleteFromMultipleBook() {
        System.out.println("Enter Phone Number Of A Person To Delete : ");
        String deleteByPhoneNumber = sc.nextLine();
        for (Map.Entry<String, ArrayList<Address>> entry : map.entrySet()) {
            list = map.get(entry.getKey());
            deleteDetails(deleteByPhoneNumber, AddressBook.MAP);
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
                System.out.println("AddressBook Deleted Successfully.");
            } else {
                System.out.println("AddressBook Doesn't Exist!");
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
                case EXIT:
                    exit = false;
                    break;
                default:
                    System.out.println("Please Choose Valid Option!");
                    break;
            }
        }
    }
}