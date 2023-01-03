/**
 * @author Bharat Pathak
 */
public class AddressBook {

    // In this addDetails function we are set some data without user input(By hardcore) :-
    public void addDetails() {
        Address address = new Address();

        address.setFirstName("Bharat");
        address.setLastName("Pathak");
        address.setAddress("Shivaji Nagar");
        address.setCity("Tumsar");
        address.setState("Maharashtra");
        address.setZip("441912");
        address.setPhoneNumber("7559201321");
        address.setEmail("bharatpathak099@gmail.com");

        System.out.println(address);
    }
}