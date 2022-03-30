package lab.pkg9;

public class Person {
    private String name, address, phoneNumber;
    
    public Person(String name, String address, String phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPhone(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    public String getPhone(){
        return phoneNumber;
    }
    public String getAddress(){
        return address;
    }
    public String getName(){
        return name;
    }
}