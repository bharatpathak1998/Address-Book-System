/**
 * @author Bharat Pathak
 */
import java.util.Scanner;

public class AddressBook {

    Scanner sc = new Scanner(System.in);

    // In this addDetails function we are set some data by user input :-
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

        System.out.println(address);
    }
}