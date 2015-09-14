package hardaway.clark.finalproj;

import hardaway.clark.finalproj.database.SQLiteDb;
import hardaway.clark.finalproj.presentation.MainView;

import javax.swing.*;

/**
 * Created by User: clarkhardaway Date: 7/20/13
 */
public class Main {
    static SQLiteDb sqLiteDb;
    public static MainView mainView;
    public static void main(String[] args) {
        sqLiteDb = new SQLiteDb();
        sqLiteDb.createDatabaseTable();

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                mainView = new MainView();
            }
        });
    }
}