public class Utente {
    String address;
    String name;
    String Surname;
    public Utente(String address, String name, String surname) {
        setAddress(address);
        setName(name);
        setSurname(surname);
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return Surname;
    }
    public void setSurname(String Surname) {
        this.Surname = Surname;
    }
    
}
