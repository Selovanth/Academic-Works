package lab.pkg9;

public class Customer extends Person{
    private int customerID;
    boolean mailList = false;
    
    public Customer(String name, String address, String phoneNumber, char mailList){
        super(name, address, phoneNumber);
        if(mailList == 'Y')
            this.mailList =  true;
    }
    
    public int getCustomerID(){
        return customerID;
    }
    public boolean getMailStatus(){
        return mailList;
    }
    public String getname(){
        return getName();
    }
    public String getaddress(){
        return getAddress();
    }
    public String getphone(){
        return getPhone();
    }
}