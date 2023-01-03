public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("***** Welcome To Address Book System *****");
        AddressBook addressBook = new AddressBook();
        addressBook.addDetails();
        addressBook.editDetails();
    }
}