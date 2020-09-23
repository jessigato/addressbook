package addressbook;

public class Address
{   
    //private variables
    private int addressID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    
    //set & get methods for address ID
    public int getId(){
        return addressID;
    }
    public void setId(int id){
        this.addressID = id;
    }
    
    //set & get methods for first name
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    //set & get methods for last name
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    //set & get methods for email
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    
    //set & get methods for phone number
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    
    //address constructor
    public Address(int addressID, String firstName, String lastName, String email, String phoneNumber){
        this.addressID = addressID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}