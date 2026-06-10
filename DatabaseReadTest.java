import java.util.ArrayList;

public class DatabaseReadTest {

    public static void main(String[] args) {

        ArrayList<Contact> contacts =
                ContactDAO.getAllContacts();

        for(Contact c : contacts) {

            System.out.println(
                    c.getName()
                    + " - "
                    + c.getPhone());
        }
    }
}
