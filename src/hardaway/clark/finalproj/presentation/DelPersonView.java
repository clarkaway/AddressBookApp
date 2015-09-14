package hardaway.clark.finalproj.presentation;

import hardaway.clark.finalproj.business.DatabaseConnection;
import hardaway.clark.finalproj.business.Person;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User: clarkhardaway Date: 7/21/13
 */
public class DelPersonView {
    JFrame mainFrame = new JFrame();
    JPanel panelA = new JPanel();

    JLabel lblTitle = new JLabel("Remove Person");

    JLabel lblName = new JLabel("Name:");
    JTextField txtName = new JTextField(20);

    JButton okButton = new JButton("Ok");
    JButton cancelButton = new JButton("Cancel");

    DatabaseConnection databaseConnection = new DatabaseConnection();
    static Person person;

    MainView mainView;
    public DelPersonView(){
        this.mainView = mainView;
        panelA.setLayout(new MigLayout());

        lblTitle.setFont(new Font("Serif", Font.PLAIN, 24));
        panelA.add(lblTitle, "wrap");panelA.add(lblName);
        panelA.add(txtName, "wrap");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Person newPerson = databaseConnection.selectSingleRecord(txtName.getText());
                setPerson(newPerson);
                try{

                    newPerson.removeFromDatabase();
                } catch (NullPointerException npe){
                    JFrame jFrame = new JFrame();
                    jFrame.setLocation(650,100);
                    JOptionPane.showMessageDialog(jFrame,"Invalid Entry","Error",JOptionPane.ERROR_MESSAGE);
                }
                mainFrame.dispose();
            }
        });
        panelA.add(okButton, "split 2, alignx left");

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
            }
        });
        panelA.add(cancelButton);


        mainFrame.setLayout(new MigLayout());
        mainFrame.add(panelA, "wrap");
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocation(650,100);
        mainFrame.setVisible(true);
    }

    public static Person getPerson() {
        return person;
    }

    public static void setPerson(Person person) {
        DelPersonView.person = person;
    }
}
