package hardaway.clark.finalproj.business;

import hardaway.clark.finalproj.database.SQLiteDb;

/**
 * Created by User: clarkhardaway Date: 7/20/13
 */
public class Person {
    private int id;
    private String name;
    private String email;
    private String phone;
    SQLiteDb sqLiteDb = new SQLiteDb();

    public Person(String name, String email, String phone){
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void addToDatabase(){
        sqLiteDb.addPerson(this);
    }

    public void removeFromDatabase(){
        sqLiteDb.removePerson(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}