package model;

/**
 *
 * @author joshuadorsett
 */
public class Customer {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private int customerPhoneNumber;

    public Customer(int id, String name, String address, int phoneNumber) {
        this.customerId = id;
        this.customerName = name;
        this.customerAddress = address;
        this.customerPhoneNumber = phoneNumber;
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

    public void setCustomerAddress(String address){
        this.customerAddress = address;
    }

    public String getCustomerAddress(){
        return this.customerAddress;
    }

    public void setCustomerPhoneNumber(int phoneNumber){
        this.customerPhoneNumber = phoneNumber;
    }

    public int getCustomerPhoneNumber(){
        return this.customerPhoneNumber;
    }


}
