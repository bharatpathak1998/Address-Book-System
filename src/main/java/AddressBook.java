/**
 * @author Bharat Pathak
 */

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {

    ArrayList<Address> list = new ArrayList<>();
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
    public void editDetails() {
        boolean flag = false;
        if (list.isEmpty()) {
            System.out.println("There Is No Contact Available");
        } else {
            System.out.println("Enter Phone Number Of A Person To Edit : ");
            String editByPhoneNumber = sc.nextLine();
            for (Address address : list) {
                if ((address.getPhoneNumber().equals(editByPhoneNumber))) {

                    System.out.print("1.Edit First Name  2.Edit Last Name  " +
                            "3.Edit Address  4.Edit City Name  " +
                            "5.Edit State Name  6.Edit Zip Code " +
                            " 7.Edit Phone Number  8.Edit Email Id\n->");

                    int option = sc.nextInt();
                    sc.nextLine();

                    switch (option) {
                        case 1 -> {
                            System.out.println("Enter New First Name : ");
                            address.setFirstName(sc.nextLine());
                        }
                        case 2 -> {
                            System.out.println("Enter New Last Name : ");
                            address.setLastName(sc.nextLine());
                        }
                        case 3 -> {
                            System.out.println("Enter New Address : ");
                            address.setAddress(sc.nextLine());
                        }
                        case 4 -> {
                            System.out.println("Enter New City Name : ");
                            address.setCity(sc.nextLine());
                        }
                        case 5 -> {
                            System.out.println("Enter New State Name : ");
                            address.setState(sc.nextLine());
                        }
                        case 6 -> {
                            System.out.println("Enter New Zip Code : ");
                            address.setZip(sc.nextLine());
                        }
                        case 7 -> {
                            System.out.println("Enter New Phone Number : ");
                            address.setPhoneNumber(sc.nextLine());
                        }
                        case 8 -> {
                            System.out.println("Enter New Email Id : ");
                            address.setEmail(sc.nextLine());
                        }
                        default -> System.out.println("Please Choose Valid Option!");
                    }

                    System.out.println(address);
                    System.out.println("Contact Edited Successfully.");
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("Please Enter Valid Phone Number!");
            }
        }
    }

    // Here we are create a function to delete details from the arraylist by using user input :-
    public void deleteDetails() {
        boolean flag = false;
        if (list.isEmpty()) {
            System.out.println("There Is No Contact Available");
        } else {
            System.out.println("Enter Phone Number Of A Person To Delete : ");
            String deleteByPhoneNumber = sc.nextLine();
            for (Address address : list) {
                if (address.getPhoneNumber().equals(deleteByPhoneNumber)) {
                    list.remove(address);
                    System.out.println("Contact Deleted Successfully.");
                    flag = true;
                    break;
                }
            }
            if (!flag) {
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
}