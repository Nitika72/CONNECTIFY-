class Contact {

    private String name;
    private String phone;
    private boolean isFavorite;
    private boolean isEmergency;
    private String nickname;
    private String alternateNumber;
    private String email;
 

    // Constructor
    Contact(String name, String phone) {

    this.name = name;
    this.phone = phone;

    this.nickname = "";
    this.alternateNumber = "";
    this.email = "";

    this.isFavorite = false;
    this.isEmergency = false;
}

    // Getters
    String getName() {
        return name;
    }

    String getPhone() {
        return phone;
    }

    String getNickname() {
    return nickname;
}

    String getAlternateNumber() {
    return alternateNumber;
}

    String getEmail() {
    return email;
}

    boolean isFavorite() {
        return isFavorite;
    }

    boolean isEmergency() {
        return isEmergency;
    }

    // Setters
    void setFavorite(boolean fav) {
        isFavorite = fav;
    }

    void setEmergency(boolean emergency) {
        isEmergency = emergency;
    }

    void setNickname(String nickname) {
    this.nickname = nickname;
}

void setAlternateNumber(String alternateNumber) {
    this.alternateNumber = alternateNumber;
}

void setEmail(String email) {
    this.email = email;
}

    // Display Contact
    void displayContact() {

    System.out.println(
            "Name: " + name +
            ", Phone: " + phone +
            ", Nickname: " + nickname +
            ", Alternate: " + alternateNumber +
            ", Email: " + email
    );

    if(isFavorite) {
        System.out.print(" [Favorite]");
    }

    if(isEmergency) {
        System.out.print(" [Emergency]");
    }

    System.out.println();
}
    void toggleFavorite() {
    isFavorite = !isFavorite;
}

void toggleEmergency() {
    isEmergency = !isEmergency;
}
}