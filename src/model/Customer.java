package model;

/**
 *
 * @author joshuadorsett
 */
public class Customer {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPhoneNumber;

    public Customer(int id, String name, String address, String phone) {
        this.customerId = id;
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

    public void setCustomerPhoneNumber(String phoneNumber){
        this.customerPhoneNumber = phoneNumber;
    }

    public String getCustomerPhoneNumber(){
        return this.customerPhoneNumber;
    }


}
