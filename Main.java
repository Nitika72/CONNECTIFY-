import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Directory dir = new Directory(20);

        int choice;

        do {

            System.out.println("\n===== CONNECTIFY =====");
            System.out.println("1. Add Contact");
            System.out.println("2. Search by Name");
            System.out.println("3. Search by Phone");
            System.out.println("4. Delete Contact");
            System.out.println("5. Display All Contacts");
            System.out.println("6. Mark Favorite");
            System.out.println("7. Show Favorite Contacts");
            System.out.println("8. Mark Emergency Contact");
            System.out.println("9. Show Emergency Contacts");
            System.out.println("10. Contact Statistics");
            System.out.println("11. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String phone = sc.nextLine();

                    dir.addContact(name, phone);
                    break;

                case 2:
                    System.out.print("Enter Name to Search: ");
                    dir.searchByName(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Enter Phone Number to Search: ");
                    dir.searchByPhone(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Enter Name to Delete: ");
                    dir.deleteContact(sc.nextLine());
                    break;

                case 5:
                    dir.displayAllContacts();
                    break;

                case 6:
                    System.out.print("Enter Name to Mark Favorite: ");
                    dir.markFavorite(sc.nextLine());
                    break;

                case 7:
                    dir.displayFavoriteContacts();
                    break;

                case 8:
                    System.out.print("Enter Name to Mark Emergency: ");
                    dir.markEmergency(sc.nextLine());
                    break;

                case 9:
                    dir.displayEmergencyContacts();
                    break;

                case 10:
                    dir.displayStatistics();
                    break;

                case 11:
                    System.out.println("Thank you for using Connectify!");
                    break;

                default:
                    System.out.println("Invalid Choice! Please try again.");
            }

        } while (choice != 11);

        sc.close();
    }
}
