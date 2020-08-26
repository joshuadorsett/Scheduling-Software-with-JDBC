package model;

/**
 *
 * @author joshuadorsett
 */
public class Customer {
    private int customerId;
    private int addressId;
    private String customerName;
    private String customerAddress;
    private String customerPhoneNumber;

    public Customer(int id, int addressId, String name, String address, String phone) {
        this.customerId = id;
        this.addressId = addressId;
        this.customerName = name;
        this.customerAddress = address;
        this.customerPhoneNumber = phone;
    }
    public int getCustomerId() {
        return this.customerId;
    }

    public String getCustomerName(){
        return this.customerName;
    }

    public String getCustomerAddress(){
        return this.customerAddress;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getCustomerPhoneNumber(){
        return this.customerPhoneNumber;
    }


}
