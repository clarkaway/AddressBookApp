package hardaway.clark.finalproj.business;

import hardaway.clark.finalproj.database.SQLiteDb;

import java.util.ArrayList;

/**
 * Created by User: clarkhardaway Date: 7/20/13
 */
public class DatabaseConnection {
    SQLiteDb sqLiteDb = new SQLiteDb();
    String filterString;
    Character filterInput;
    Boolean initFilterStringRan = false;

    public void selectAllRecords(){
        sqLiteDb.selectAllRecords();
    }

    public Person selectSingleRecord(String filterName){
        return sqLiteDb.selectSingleRecord(filterName);
    }

    public Person filterByText(String filter){
        return sqLiteDb.filterByText(filter);
    }

    public String getFilterString() {
        return filterString;
    }

    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }

    public Character getFilterInput() {
        return filterInput;
    }

    public void setFilterInput(Character filterInput) {
        this.filterInput = filterInput;
        updateFilterString();
        if (!initFilterStringRan){
            initFilterString();
        }

    }

    private void updateFilterString() {
        this.filterString += filterInput;
    }

    public void initFilterString(){
        filterString = filterString.replace("null", "");
    }

    public ArrayList<Person> getPersonArrayList() {
        return sqLiteDb.getPersonArrayList();
    }

    public void setPersonArrayList(ArrayList<Person> personArrayList) {
        sqLiteDb.setPersonArrayList(personArrayList);
    }

    public ArrayList<Person> getFilterPersonArrayList() {
        return sqLiteDb.getFilterPersonArrayList();
    }

    public void setFilterPersonArrayList(ArrayList<Person> filterPersonArrayList) {
        sqLiteDb.setFilterPersonArrayList(filterPersonArrayList);
    }
}