package hardaway.clark.finalproj.presentation;

import hardaway.clark.finalproj.business.DatabaseConnection;
import hardaway.clark.finalproj.business.Person;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by User: clarkhardaway Date: 7/20/13
 */
public class MainView extends JFrame{
    static JFrame mainFrame = new JFrame();
    JPanel panelA = new JPanel();
    JPanel panelB = new JPanel();
    static JPanel panelC = new JPanel();

    JLabel lblTitle = new JLabel("Address Book");
    JTextField txtFilter = new JTextField(20);
    JButton btnAddPerson = new JButton("Add +");
    JButton btnDelPerson = new JButton("Del -");
    static JButton btnRefreshContent = new JButton("Refresh");

    JLabel lblName = new JLabel("Name");
    JLabel lblEmail = new JLabel("Email");
    JLabel lblPhone = new JLabel("Phone");
    JLabel lblEmpty = new JLabel();

    static DatabaseConnection databaseConnection = new DatabaseConnection();

    static int addressBookSize = 0;
    static int filteredAddressBookSize = 0;
    static ArrayList<JLabel> jLabelArrayList= new ArrayList<JLabel>();
    static ArrayList<JLabel> jLabelFilteredArrayList = new ArrayList<>();

    public MainView(){
        panelA.setLayout(new MigLayout("insets 2 1 2 1"));
        lblTitle.setFont(new Font("Serif", Font.PLAIN, 24));
        panelA.add(lblTitle);
        panelA.add(btnAddPerson, "alignx right, span 2 2, growy");
        btnAddPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPersonView();
            }
        });
        panelA.add(btnDelPerson, "alignx right, wrap, span 2 2, growy");
        btnDelPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DelPersonView();
            }
        });
        txtFilter.setText("Filter");
        txtFilter.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                databaseConnection.setFilterPersonArrayList(new ArrayList<Person>());
                databaseConnection.setFilterInput(e.getKeyChar());

                if (txtFilter.getText().equals("") && txtFilter.getText().isEmpty()) {
                    databaseConnection.setFilterString("" + e.getKeyChar());
                    databaseConnection.setFilterPersonArrayList(new ArrayList<Person>());
                    databaseConnection.setPersonArrayList(new ArrayList<Person>());
                    for (JLabel jLabel : jLabelFilteredArrayList){
                        remove(jLabel);
                        panelC.remove(jLabel);
                    }

                    for (JLabel jLabel : jLabelArrayList){
                        remove(jLabel);
                        panelC.remove(jLabel);
                    }
                    panelC.revalidate();
                    panelC.repaint();
                    getRecords();
                    loadContent();
                } else {
                    databaseConnection.setPersonArrayList(new ArrayList<Person>());
                    for (JLabel jLabel : jLabelFilteredArrayList){
                        remove(jLabel);
                        panelC.remove(jLabel);
                    }

                    for (JLabel jLabel : jLabelArrayList){
                        remove(jLabel);
                        panelC.remove(jLabel);
                    }
                    panelC.revalidate();
                    panelC.repaint();

                    databaseConnection.filterByText(databaseConnection.getFilterString());
                    loadFilteredContent();
                }
            }
        });
        panelA.add(txtFilter, "wrap, pushx, growx");

        panelB.setLayout(new MigLayout("insets 2 1 2 1"));
        lblName.setBorder(BorderFactory.createEtchedBorder());
        panelB.add(lblName, "alignx left, width :150:");
        lblEmail.setBorder(BorderFactory.createEtchedBorder());
        panelB.add(lblEmail, "alignx left, width :200:");
        lblPhone.setBorder(BorderFactory.createEtchedBorder());
        panelB.add(lblPhone, "alignx left, width :150:, wrap");

        panelC.setLayout(new MigLayout("fill, insets 2 1 2 1"));
        panelC.setBorder(BorderFactory.createEtchedBorder());

        getRecords();
        loadContent();
        btnRefreshContent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                databaseConnection.setPersonArrayList(new ArrayList<Person>());

                for (JLabel jLabel : jLabelFilteredArrayList){
                    remove(jLabel);
                    panelC.remove(jLabel);
                }

                for (JLabel jLabel : jLabelArrayList){
                    remove(jLabel);
                    panelC.remove(jLabel);
                }
                panelC.revalidate();
                panelC.repaint();
                getRecords();
                loadContent();
                mainFrame.revalidate();
                mainFrame.pack();
                mainFrame.repaint();
            }
        });

        mainFrame.setLayout(new MigLayout());
        mainFrame.add(panelA, "wrap");
        mainFrame.add(panelB, "wrap");
        mainFrame.add(panelC, "wrap");
        mainFrame.add(btnRefreshContent);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocation(100, 100);
        mainFrame.setVisible(true);
    }

    public static void getRecords(){
        databaseConnection.selectAllRecords();
    }

    public static void loadContent(){
        addressBookSize = databaseConnection.getPersonArrayList().size();
        for (int i = 0; i < databaseConnection.getPersonArrayList().size(); i++){
            JLabel jLabelName = new JLabel(databaseConnection.getPersonArrayList().get(i).getName());
            panelC.add(jLabelName,
                    "alignx left, width :150:");
            JLabel jLabelEmail = new JLabel(databaseConnection.getPersonArrayList().get(i).getEmail());
            panelC.add(jLabelEmail,
                    "alignx left, width :200:");
            JLabel jLabelPhone = new JLabel(databaseConnection.getPersonArrayList().get(i).getPhone());
            panelC.add(jLabelPhone,
                    "alignx left, width :150:, wrap 5px");
            jLabelArrayList.add(jLabelName);
            jLabelArrayList.add(jLabelEmail);
            jLabelArrayList.add(jLabelPhone);
        }
    }

    public static void loadFilteredContent(){
        addressBookSize = databaseConnection.getFilterPersonArrayList().size();
        for (int i = 0; i < databaseConnection.getFilterPersonArrayList().size(); i++){
            JLabel jLabelName = new JLabel(databaseConnection.getFilterPersonArrayList().get(i).getName());
            panelC.add(jLabelName,
                    "alignx left, width :150:");
            JLabel jLabelEmail = new JLabel(databaseConnection.getFilterPersonArrayList().get(i).getEmail());
            panelC.add(jLabelEmail,
                    "alignx left, width :200:");
            JLabel jLabelPhone = new JLabel(databaseConnection.getFilterPersonArrayList().get(i).getPhone());
            panelC.add(jLabelPhone,
                    "alignx left, width :150:, wrap 5px");
            jLabelFilteredArrayList.add(jLabelName);
            jLabelFilteredArrayList.add(jLabelEmail);
            jLabelFilteredArrayList.add(jLabelPhone);
        }
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JPanel getPanelA() {
        return panelA;
    }

    public void setPanelA(JPanel panelA) {
        this.panelA = panelA;
    }

    public JPanel getPanelB() {
        return panelB;
    }

    public void setPanelB(JPanel panelB) {
        this.panelB = panelB;
    }

    public JPanel getPanelC() {
        return panelC;
    }

    public void setPanelC(JPanel panelC) {
        this.panelC = panelC;
    }
}