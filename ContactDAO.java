import java.sql.*;
import java.util.ArrayList;

public class ContactDAO {

    public static void addContact(Contact contact) {

        String sql =

            "INSERT INTO contacts "
            + "(name, phone, nickname, "
            + "alternate_number, email, "
            + "favorite, emergency) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(
                    1,
                    contact.getName());

            ps.setString(
                    2,
                    contact.getPhone());

            ps.setString(
                    3,
                    contact.getNickname());

            ps.setString(
                    4,
                    contact.getAlternateNumber());

            ps.setString(
                    5,
                    contact.getEmail());

            ps.setBoolean(
                    6,
                    contact.isFavorite());

            ps.setBoolean(
                    7,
                    contact.isEmergency());

            ps.executeUpdate();

            System.out.println(
                    "Contact saved to database!");

            conn.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public static ArrayList<Contact> getAllContacts() {

        ArrayList<Contact> contacts =
                new ArrayList<>();

        String sql =
                "SELECT * FROM contacts";

        try {

            Connection conn =
                    DatabaseConnection.getConnection();

            Statement stmt =
                    conn.createStatement();

            ResultSet rs =
                    stmt.executeQuery(sql);

            while(rs.next()) {

                Contact c =
                        new Contact(
                                rs.getString("name"),
                                rs.getString("phone"));

                c.setNickname(
                        rs.getString("nickname"));

                c.setAlternateNumber(
                        rs.getString("alternate_number"));

                c.setEmail(
                        rs.getString("email"));

                c.setFavorite(
                        rs.getBoolean("favorite"));

                c.setEmergency(
                        rs.getBoolean("emergency"));

                contacts.add(c);
            }

            conn.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        return contacts;
    }

    public static void updateContact(Contact contact) {

    String sql =
            "UPDATE contacts SET "
            + "nickname=?, "
            + "alternate_number=?, "
            + "email=?, "
            + "favorite=?, "
            + "emergency=? "
            + "WHERE name=?";

    try {

        Connection conn =
                DatabaseConnection.getConnection();

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ps.setString(1, contact.getNickname());
        ps.setString(2, contact.getAlternateNumber());
        ps.setString(3, contact.getEmail());
        ps.setBoolean(4, contact.isFavorite());
        ps.setBoolean(5, contact.isEmergency());
        ps.setString(6, contact.getName());

        ps.executeUpdate();

        conn.close();

    } catch(Exception e) {

        e.printStackTrace();
    }
}

public static void deleteContact(String name) {

    String sql =
            "DELETE FROM contacts WHERE name=?";

    try {

        Connection conn =
                DatabaseConnection.getConnection();

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ps.setString(1, name);

        ps.executeUpdate();

        conn.close();

    } catch(Exception e) {

        e.printStackTrace();
    }
}
}