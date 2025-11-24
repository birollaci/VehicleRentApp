package mytests;

public class Address {

    private String city;
    private String street;
    private int zipcode;

    public Address() {}

    public Address(String city, String street, int zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    // Getters & Setters
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public int getZipcode() { return zipcode; }
    public void setZipcode(int zipcode) { this.zipcode = zipcode; }
}
