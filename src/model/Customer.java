package model;

/**
 * creates an customer object used to contain the data selected from the DB
 * @author joshuadorsett
 */

public class Customer {


    private final int customerId;

    private final int addressId;

    private final String customerName;

    private final String address;

    private final String phone;


    public Customer(int id, int addressId, String customerName, String address, String phone) {
        this.customerId = id;
        this.addressId = addressId;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
    }


    public int getCustomerId() {
        return this.customerId;
    }


    public String getCustomerName(){
        return this.customerName;
    }


    public String getAddress(){
        return this.address;
    }


    public int getAddressId() {
        return addressId;
    }


    public String getPhone(){
        return this.phone;
    }


}
