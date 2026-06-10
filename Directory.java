class Directory {

   private Contact[] contacts;
private int count;

// Constructor
Directory(int size) {

    contacts = new Contact[size];

    count = 0;

    try {

        java.util.ArrayList<Contact> dbContacts =
                ContactDAO.getAllContacts();

        for(Contact c : dbContacts) {

            contacts[count] = c;

            count++;
        }

        System.out.println(
                "Loaded "
                + count
                + " contacts from database."
        );

    } catch(Exception e) {

        e.printStackTrace();
    }
}

    // Add Contact
    void addContact(String name, String phone) {

        if (count >= contacts.length) {
            System.out.println("Directory is full!");
            return;
        }

        // Duplicate Check
        for (int i = 0; i < count; i++) {
            if (contacts[i].getName().equalsIgnoreCase(name)) {
                System.out.println("Contact already exists!");
                return;
            }
        }

        // Phone Validation
        if (phone.length() != 10) {
            System.out.println("Invalid phone number!");
            return;
        }

        contacts[count] = new Contact(name, phone);
        count++;

        System.out.println("Contact added successfully!");
    }

    // Search by Name
    void searchByName(String name) {

        for (int i = 0; i < count; i++) {

            if (contacts[i].getName().equalsIgnoreCase(name)) {
                contacts[i].displayContact();
                return;
            }
        }

        System.out.println("Contact not found!");
    }

    // Search by Phone
    void searchByPhone(String phone) {

        for (int i = 0; i < count; i++) {

            if (contacts[i].getPhone().equals(phone)) {
                contacts[i].displayContact();
                return;
            }
        }

        System.out.println("Contact not found!");
    }

    // Delete Contact
    void deleteContact(String name) {

        for (int i = 0; i < count; i++) {

            if (contacts[i].getName().equalsIgnoreCase(name)) {

                for (int j = i; j < count - 1; j++) {
                    contacts[j] = contacts[j + 1];
                }

                contacts[count - 1] = null;
                count--;

                System.out.println("Contact deleted successfully!");
                return;
            }
        }

        System.out.println("Contact not found!");
    }

    // Display All Contacts
    void displayAllContacts() {

        if (count == 0) {
            System.out.println("No contacts available!");
            return;
        }

        for (int i = 0; i < count; i++) {
            contacts[i].displayContact();
        }
    }

    // Mark Favorite
void markFavorite(String name) {

    for (int i = 0; i < count; i++) {

        if (contacts[i].getName().equalsIgnoreCase(name)) {

            contacts[i].setFavorite(true);

            System.out.println("Contact marked as favorite!");
            return;
        }
    }

    System.out.println("Contact not found!");
}

// Show Favorite Contacts
void displayFavoriteContacts() {

    boolean found = false;

    for (int i = 0; i < count; i++) {

        if (contacts[i].isFavorite()) {

            contacts[i].displayContact();
            found = true;
        }
    }

    if (!found) {
        System.out.println("No favorite contacts found!");
    }
}

    // Mark Emergency Contact
    // Mark Emergency Contact
void markEmergency(String name) {

    for (int i = 0; i < count; i++) {

        if (contacts[i].getName().equalsIgnoreCase(name)) {

            contacts[i].setEmergency(true);

            System.out.println("Contact marked as emergency!");
            return;
        }
    }

    System.out.println("Contact not found!");
}

    // Show Emergency Contacts
    void displayEmergencyContacts() {

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (contacts[i].isEmergency()) {
                contacts[i].displayContact();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No emergency contacts found!");
        }
    }

    // Contact Statistics
    void displayStatistics() {

        int favoriteCount = 0;
        int emergencyCount = 0;

        for (int i = 0; i < count; i++) {

            if (contacts[i].isFavorite()) {
                favoriteCount++;
            }

            if (contacts[i].isEmergency()) {
                emergencyCount++;
            }
        }

        System.out.println("\n--- CONTACT STATISTICS ---");
        System.out.println("Total Contacts : " + count);
        System.out.println("Favorite Contacts : " + favoriteCount);
        System.out.println("Emergency Contacts : " + emergencyCount);
    }
        // Get Total Contacts
    int getTotalContacts() {
        return count;
    }

    // Get Favorite Count
    int getFavoriteCount() {

        int favoriteCount = 0;

        for (int i = 0; i < count; i++) {

            if (contacts[i].isFavorite()) {
                favoriteCount++;
            }
        }

        return favoriteCount;
    }

    // Get Emergency Count
    int getEmergencyCount() {

        int emergencyCount = 0;

        for (int i = 0; i < count; i++) {

            if (contacts[i].isEmergency()) {
                emergencyCount++;
            }
        }

        return emergencyCount;
    }

    // Get Contacts Array
    Contact[] getContacts() {
        return contacts;
    }

    boolean contactExists(String name){

    for(int i = 0; i < count; i++){

        if(contacts[i].getName()
                .equalsIgnoreCase(name)){

            return true;
        }
    }

    return false;
}

void toggleFavorite(String name) {

    for(int i=0;i<count;i++) {

        if(contacts[i].getName()
                .equalsIgnoreCase(name)) {

            contacts[i].toggleFavorite();
            return;
        }
    }
}

void toggleEmergency(String name) {

    for(int i=0;i<count;i++) {

        if(contacts[i].getName()
                .equalsIgnoreCase(name)) {

            contacts[i].toggleEmergency();
            return;
        }
    }
}

Contact getContact(String name) {

    for(int i = 0; i < count; i++) {

        if(contacts[i].getName()
                .equalsIgnoreCase(name)) {

            return contacts[i];
        }
    }

    return null;
}
}
