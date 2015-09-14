package hardaway.clark.finalproj.database;

import hardaway.clark.finalproj.business.Person;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by User: clarkhardaway Date: 7/9/13
 */
public class SQLiteDb{
    private Connection connection;
    ArrayList<Person> personArrayList = new ArrayList<Person>();
    ArrayList<Person> filterPersonArrayList = new ArrayList<>();

    Person person;
    public SQLiteDb() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:AddressBook.sqlite");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDatabaseTable(){
        Statement s;
        File file = new File("AddressBook.sqlite");
        if (!file.exists()){
            try{
                s = connection.createStatement();
                String sql = "CREATE TABLE ADDRESSBOOK " +
                        "(ID            INTEGER PRIMARY KEY AUTOINCREMENT	NOT NULL," +
                        "NAME           TEXT	NOT NULL," +
                        "EMAIL			TEXT	NOT NULL," +
                        "PHONE		    TEXT)";
                s.executeUpdate(sql);
                s.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            System.out.println("Table created successfully");
        }
    }

    public void selectAllRecords(){
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM ADDRESSBOOK;");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                personArrayList.add(new Person(name, email, phone));

//                System.out.println("ID = " + id);
//                System.out.println("NAME = " + name);
//                System.out.println("EMAIL = " + email);
//                System.out.println("PHONE = " + phone);
//                System.out.println();
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPerson(Person person) {
        Statement state;
        try {
            state = connection.createStatement();

            String sql = 	"INSERT INTO ADDRESSBOOK (ID, NAME, EMAIL, PHONE)" +
                            " VALUES (NULL,'" + person.getName() + "'," + "'" +
                            person.getEmail() + "','" + person.getPhone() + "')";
            state.executeUpdate(sql);
            state.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removePerson(Person person){
        Statement state;
        try {
            state = connection.createStatement();
            String sql = "DELETE FROM ADDRESSBOOK where ID=" + person.getId() + ";";
            state.executeUpdate(sql);
            state.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Person selectSingleRecord(String filterName) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM ADDRESSBOOK WHERE NAME = '" + filterName + "'");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                person = new Person(name, email, phone);
                person.setId(id);

//                System.out.println("SINGLE SELECTION");
//                System.out.println("ID = " + id);
//                System.out.println("NAME = " + name);
//                System.out.println("EMAIL = " + email);
//                System.out.println("PHONE = " + phone);
//                System.out.println();
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public Person filterByText(String filter) {
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM ADDRESSBOOK WHERE " +
                    "NAME LIKE '%" + filter + "%' or " +
                    "EMAIL LIKE '%" + filter + "%' or " +
                    "PHONE LIKE '%" + filter + "%'";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                filterPersonArrayList.add(new Person(name, email, phone));

//                System.out.println(sql);
//                System.out.println("SINGLE SELECTION");
//                System.out.println("ID = " + id);
//                System.out.println("NAME = " + name);
//                System.out.println("EMAIL = " + email);
//                System.out.println("PHONE = " + phone);
//                System.out.println();
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public ArrayList<Person> getPersonArrayList() {
        return personArrayList;
    }

    public void setPersonArrayList(ArrayList<Person> personArrayList) {
        this.personArrayList = personArrayList;
    }

    public ArrayList<Person> getFilterPersonArrayList() {
        return filterPersonArrayList;
    }

    public void setFilterPersonArrayList(ArrayList<Person> filterPersonArrayList) {
        this.filterPersonArrayList = filterPersonArrayList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}