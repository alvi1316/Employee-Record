package javaapplication2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class InformationController implements ActionListener, FocusListener {

    Information information;
    Database database;

    public InformationController(Information information, Database database) {
        this.information = information;
        this.database = database;
    }

    void init() {
        List<Info> infoList = database.getInfo();
        for (Info info : infoList) {
            Object[] obj = {info.id, info.name, info.salary, info.address};
            information.model.addRow(obj);
        }
    }
    
    void deleteAllRow(){
        int rowCount = information.model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
                information.model.removeRow(i);
        }
    }

    void addButtonAction() {
        if (information.name.getText().equals("Name") || information.salary.getText().equals("Salary") || information.address.getText().equals("Address")) {
            JOptionPane.showMessageDialog(information, "Invalid entry!");
        } else {
            try{
                String name = information.name.getText();
                int salary = Integer.parseInt(information.salary.getText());
                String address = information.address.getText();
                database.addInfo(name, salary, address);
                deleteAllRow();
                init();
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(information, "Please enter valid salary!");
            }            
        }
    }
    
    void deleteButtonAction(){
        try{
            int id = Integer.parseInt(information.id.getText());
            if(database.deleteRow(id)){
                deleteAllRow();
                init();
            }else{
                JOptionPane.showMessageDialog(information, "ID doesn't exits!");
            }            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(information, "Please enter valid id!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == information.addButton) {
            addButtonAction();
        } else if (e.getSource() == information.deleteButton) {
            deleteButtonAction();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == information.name) {
            if (information.name.getText().equals("Name")) {
                information.name.setText("");
            }
        } else if (e.getSource() == information.salary) {
            if (information.salary.getText().equals("Salary")) {
                information.salary.setText("");
            }
        } else if (e.getSource() == information.address) {
            if (information.address.getText().equals("Address")) {
                information.address.setText("");
            }
        } else if (e.getSource() == information.id) {
            if (information.id.getText().equals("ID")) {
                information.id.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == information.name) {
            if (information.name.getText().equals("")) {
                information.name.setText("Name");
            }
        } else if (e.getSource() == information.salary) {
            if (information.salary.getText().equals("")) {
                information.salary.setText("Salary");
            }
        } else if (e.getSource() == information.address) {
            if (information.address.getText().equals("")) {
                information.address.setText("Address");
            }
        } else if (e.getSource() == information.id) {
            if (information.id.getText().equals("")) {
                information.id.setText("ID");
            }
        }
    }

}
