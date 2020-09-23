package addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressOptions
{
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedstatement = null;
    
    //AddressOptions constructor for login
    public AddressOptions(){
        try{
            connection = DriverManager.getConnection(Database.DATABASE_URL, Database.USERNAME, Database.PASSWORD);
            System.out.println("Connection is successful");
        }
        catch(SQLException ex){
            Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //login method
    public boolean login (String username, String password){
        try{
            String query = "select * from ADMIN where USERNAME=? AND " + "PASSWORD=?";
            preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1, username);
            preparedstatement.setString(2, password);

            //select query returns a result set
            ResultSet rs = preparedstatement.executeQuery();
            return rs.next();
            //method next returns true if a record exists
        }
        catch (SQLException ex) {
            Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //display all addresses
    public ArrayList <Address> viewAddress() {
        try {
            //creates an array list to hold addresses
            //creates a string query to be executed as SQL command
            ArrayList <Address> address = new ArrayList <> ();
            String query = "select * from ADDRESSES";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            //while query is executing
            //store variables in address array
            while (rs.next()) {
                int id = rs.getInt("ADDRESSID");
                String firstname = rs.getString("FIRSTNAME");
                String lastname = rs.getString("LASTNAME");
                String email = rs.getString("EMAIL");
                String phoneNumber = rs.getString("PHONENUMBER");
                address.add(new Address(id, firstname, lastname, email, phoneNumber));
            }
            return address;
        }
        catch (SQLException ex) {
            Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //add an address to addressbook
    public void addAddress (String firstname, String lastname, String email, String phoneNumber) {
        try {
            //query command to insert new address into database
            String query = "insert into ADDRESSES (FIRSTNAME, LASTNAME, " + "EMAIL, PHONENUMBER) values (?, ?, ?, ?)";
            preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1, firstname);
            preparedstatement.setString(2, lastname);
            preparedstatement.setString(3, email);
            preparedstatement.setString(4, phoneNumber);
            preparedstatement.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //delete an address
    public void deleteAddress (int id) {
        try {
            //execute delete query using address ID as identifier for which address to delete
            String query = "delete from ADDRESSES where ADDRESSID=?";
            preparedstatement = connection.prepareStatement(query);
            preparedstatement.setInt(1, id);
            preparedstatement.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //deletes all addresses
    public void deleteAllAddresses(){
        try{
            //query to delete all addresses wherre the address ID is greater than or equal to 1
            //deletion is executed
            String query = "delete from ADDRESSES where ADDRESSID>=1";
            preparedstatement = connection.prepareStatement(query);
            preparedstatement.executeUpdate();
        }
        catch (SQLException ex){
            Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //update an address
    public void updateAddress (int id, String firstName, String lastName, String email, String phoneNumber) {
        try {
            //query command to update an address on the basis of which address ID is clicked on
            String query = "update ADDRESSES set FIRSTNAME=?, LASTNAME=?, " + "EMAIL=?, PHONENUMBER=? where ADDRESSID=?";
            preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1,firstName);
            preparedstatement.setString(2, lastName);
            preparedstatement.setString(3, email);
            preparedstatement.setString(4, phoneNumber);
            preparedstatement.setInt(5, id);
            preparedstatement.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(AddressOptions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}