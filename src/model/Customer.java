package model;

/**
 *
 * @author joshuadorsett
 */
public class Customer {
    private int customerId;
    private String customerName;
    private int customerAddressId;
    private int customerPhoneNumber;

    public Customer(int id, String name, int addressId, int phoneNumber) {
        this.customerId = id;
        this.customerName = name;
        this.customerAddressId = addressId;
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

    public void setCustomerAddressId(int addressId){
        this.customerAddressId = addressId;
    }

    public int getCustomerAddressId(){
        return this.customerAddressId;
    }

    public void setCustomerPhoneNumber(int phoneNumber){
        this.customerPhoneNumber = phoneNumber;
    }

    public int getCustomerPhoneNumber(){
        return this.customerPhoneNumber;
    }


}
