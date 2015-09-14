package hardaway.clark.finalproj.presentation;

import hardaway.clark.finalproj.business.Person;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User: clarkhardaway Date: 7/20/13
 */
public class AddPersonView extends JDialog{
    JFrame mainFrame = new JFrame();
    JPanel panelA = new JPanel();

    JLabel lblTitle = new JLabel("New Person");

    JLabel lblName = new JLabel("Name:");
    JTextField txtName = new JTextField(20);
    JLabel lblEmail = new JLabel("Email:");
    JTextField txtEmail = new JTextField(20);
    JLabel lblPhone = new JLabel("Phone:");
    JTextField txtPhone = new JTextField(20);

    JButton okButton = new JButton("Ok");
    JButton cancelButton = new JButton("Cancel");

    static Person person;

    MainView mainView;
    public AddPersonView() {
        this.mainView = mainView;
        panelA.setLayout(new MigLayout());

        lblTitle.setFont(new Font("Serif", Font.PLAIN, 24));
        panelA.add(lblTitle, "wrap");panelA.add(lblName);
        panelA.add(txtName, "wrap");
        panelA.add(lblEmail);
        panelA.add(txtEmail, "wrap");
        panelA.add(lblPhone);
        panelA.add(txtPhone, "wrap");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person newPerson = new Person(txtName.getText(),
                        txtEmail.getText(), txtPhone.getText());
                setPerson(newPerson);
                newPerson.addToDatabase();
                mainFrame.dispose();
            }
        });
        panelA.add(okButton, "alignx left, split 2");

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
            }
        });
        panelA.add(cancelButton);

        mainFrame.setLayout(new MigLayout());
        mainFrame.add(panelA, "wrap");
        mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocation(650,100);
        mainFrame.setVisible(true);
    }

    public static Person getPerson() {
        return person;
    }

    public static void setPerson(Person person) {
        AddPersonView.person = person;
    }
}