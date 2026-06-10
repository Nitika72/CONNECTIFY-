public class DatabaseInsertTest {

    public static void main(String[] args) {

        Contact c =
                new Contact(
                        "Nitika",
                        "9876543210");

        c.setNickname("Nitu");

        c.setEmail(
                "nitika@gmail.com");

        ContactDAO.addContact(c);
    }
}
