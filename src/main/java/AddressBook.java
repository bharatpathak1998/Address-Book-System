/**
 * @author Bharat Pathak
 */

import java.util.Scanner;

public class AddressBook {

    Scanner sc = new Scanner(System.in);
    Address address = new Address();


    // In this addDetails function we are set some data by user input :-
    public void addDetails() {

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

        System.out.println(address);
    }

    // In this editDetails function we are edit data by user input :-
    public void editDetails() {
        System.out.println("Enter Phone Number Of A Person To Edit :  ");
        String editByPhoneNumber = sc.nextLine();
        if (address.getPhoneNumber().equals(editByPhoneNumber)) {
            addDetails();
        } else {
            System.out.println("Please Enter Valid Phone Number!");
            editDetails();
        }
    }
}