package cc.landfill.crowd.entity;

/**
 * @title: Address
 * @Author Landfill
 * @Date: 2020/7/24 16:08
 * @Version 1.0
 */
public class Address {
    private String Province;
    private String city;
    private String street;

    public Address() {
    }

    public Address(String province, String city, String street) {
        Province = province;
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "Province='" + Province + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}