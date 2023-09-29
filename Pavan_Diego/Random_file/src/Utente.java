public class Utente {
    String address;
    String name;
    String sirname;
    public Utente(String address, String name, String sirname) {
        this.address = address;
        this.name = name;
        this.sirname = sirname;
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
    public String getSirname() {
        return sirname;
    }
    public void setSirname(String sirname) {
        this.sirname = sirname;
    }
    
}
