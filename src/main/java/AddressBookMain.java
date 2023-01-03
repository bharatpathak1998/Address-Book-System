import java.util.Scanner;

public class AddressBookMain {

    public static final int NEW_BOOK = 1;
    public static final int DISPLAY_BOOK = 2;
    public static final int OPEN_BOOK = 3;
    public static final int EDIT_BOOK_DETAILS = 4;
    public static final int DELETE_BOOK_DETAILS = 5;
    public static final int DELETE_BOOK = 6;
    public static final int SEARCH_PERSON = 7;
    public static final int VIEW_PERSON = 8;
    public static final int COUNT_PERSON = 9;
    public static final int SORT_PERSON = 10;
    public static final int READ_BOOK = 11;
    public static final int EXIT = 0;

    public static void main(String[] args) {
        System.out.println("Welcome To Address Book System");
        Scanner sc = new Scanner(System.in);
        AddressBook addressBook = new AddressBook();
        boolean exit = true;
        while (exit) {

            System.out.print("1.Add New AddressBook ");
            System.out.print(" 2.Display AddressBook ");
            System.out.print(" 3.Open Old AddressBook ");
            System.out.print(" 4.Edit From Multiple Book ");
            System.out.print(" 5.Delete From Multiple Book \n");
            System.out.print("6.Delete AddressBook ");
            System.out.print(" 7.Search Person ");
            System.out.print(" 8.View Person ");
            System.out.print(" 9.Count Person ");
            System.out.print(" 10.Sort Person ");
            System.out.print(" 11.Read Data From File ");
            System.out.print(" 0.Exit \n->");

            int options = sc.nextInt();
            switch (options) {
                case NEW_BOOK:
                    addressBook.newAddressBook();
                    break;
                case DISPLAY_BOOK:
                    addressBook.displayAddressBook();
                    break;
                case OPEN_BOOK:
                    addressBook.openAddressBook();
                    break;
                case EDIT_BOOK_DETAILS:
                    addressBook.editFromMultipleBook();
                    break;
                case DELETE_BOOK_DETAILS:
                    addressBook.deleteFromMultipleBook();
                    break;
                case DELETE_BOOK:
                    addressBook.deleteAddressBook();
                    break;
                case SEARCH_PERSON:
                    addressBook.searchPersonFromMap();
                    break;
                case VIEW_PERSON:
                    addressBook.viewPersonFromMap();
                    break;
                case COUNT_PERSON:
                    addressBook.countPersonMap();
                    break;
                case SORT_PERSON:
                    addressBook.sortPersonMap();
                    break;
                case READ_BOOK:
                    addressBook.readAddressBookTxt();
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