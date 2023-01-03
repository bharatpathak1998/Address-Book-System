import java.util.Scanner;

public class AddressBookMain {

    public static final int ADD = 1;
    public static final int EDIT = 2;
    public static final int DELETE = 3;
    public static final int VIEW = 4;
    public static final int EXIT = 0;

    public static void main(String[] args) {
        System.out.println("Welcome To Address Book System");
        Scanner sc = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();
        boolean exit = true;
        while (exit) {

            System.out.print("1.Add Contact ");
            System.out.print(" 2.Edit Contact ");
            System.out.print(" 3.Delete Contact ");
            System.out.print(" 4.View Contact ");
            System.out.print(" 0.Exit \n->");

            int options = sc.nextInt();
            switch (options) {
                case ADD:
                    addressBook.addDetails();
                    break;
                case EDIT:
                    addressBook.editDetails();
                    break;
                case DELETE:
                    addressBook.deleteDetails();
                    break;
                case VIEW:
                    addressBook.showDetails();
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