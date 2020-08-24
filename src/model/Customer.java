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

    public void setCustomerId(int id){
        this.customerId = id;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerName(String name){
        this.customerName = name;
    }

    public String getCustomerName(){
        return this.customerName;
    }

    public void setCustomerAddress(String addressId){
        this.customerAddress = addressId;
    }

    public String getCustomerAddress(){
        return this.customerAddress;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setCustomerPhoneNumber(String phoneNumber){
        this.customerPhoneNumber = phoneNumber;
    }

    public String getCustomerPhoneNumber(){
        return this.customerPhoneNumber;
    }


}
