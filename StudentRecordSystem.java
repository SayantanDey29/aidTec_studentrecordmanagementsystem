import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String address;
    private String phoneNumber;

    public Student(String name, int rollNumber, String address, String phoneNumber) 
    {
        this.name = name;
        this.rollNumber = rollNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }

    public void setRollNumber(int rollNumber)
    {
        this.rollNumber=rollNumber;
    }
    public int getRollNumber()
    {
        return rollNumber;
    }

    public void setAddress(String address)
    {
        this.address=address;
    }
    public String getAddress()
    {
        return address;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber=phoneNumber ;
    }
    public String getPhoneNumber ()
    {
        return phoneNumber;
    }


    @Override
    public String toString() 
    {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Address: " + address + ", Phone Number: " + phoneNumber;
    }
}

class StudentRecord implements Serializable {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student student) 
    {
        students.add(student);
    }

    public void removeStudentByRollNumber(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student getStudentByRollNumber(int rollNumber) 
    {
        for (Student student : students) 
        {
            if (student.getRollNumber() == rollNumber) 
            {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Student> getStudents() 
    {
        return students;
    }
}

class StudentRecordSystem extends JFrame {
    private StudentRecord studentRecord;
    private DefaultTableModel tableModel;
    private JTextArea displayArea;
    private JTextField nameField, rollNumberField, addressField, phoneNumberField,searchField,removeRollField;
    private ImageIcon image=new ImageIcon("student.jpg");


    public StudentRecordSystem() {
        studentRecord = new StudentRecord();

        // Create the table model with column names
        String[] columnNames = {"Name", "Roll Number", "Address", "Phone Number"};
        tableModel = new DefaultTableModel(columnNames, 0);

        displayArea = new JTextArea(10, 60);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JLabel nameLabel = createStyledLabel("Name:",16,Font.BOLD);
        JLabel rollNumberLabel = createStyledLabel("Roll Number:",16,Font.BOLD);
        JLabel addressLabel =createStyledLabel("Address:",16,Font.BOLD);
        JLabel phoneNumberLabel =createStyledLabel("Phone Number:",16,Font.BOLD);
        JLabel removeRollLabel = createStyledLabel("Remove student by roll number:",16,Font.BOLD);

        nameField =createStyledTextField(15);
        rollNumberField =createStyledTextField(10);
        addressField = createStyledTextField(20);
        phoneNumberField =createStyledTextField(15);
        searchField = createStyledTextField(10);
        removeRollField=createStyledTextField(10);

        JButton addButton =createStyledButton("Add Student");
        JButton removeButton = createStyledButton("Remove Student");
        JButton displayButton = createStyledButton("Display Students Info");
        JButton searchButton = createStyledButton("Search");


        addButton.setFocusable(false);
        removeButton.setFocusable(false);
        displayButton.setFocusable(false);
        searchButton.setFocusable(false);
    
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStudentInfo();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });


        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(15, 15, 15, 15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(rollNumberLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(rollNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(phoneNumberLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(phoneNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // span two columns
        inputPanel.add(addButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // span two columns
        inputPanel.add(displayButton, gbc);


        // Add removeField to inputPanel
        JPanel removePanel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1; // reset grid width
        removePanel.add(removeRollLabel, gbc);
        gbc.gridx = 1;
        removePanel.add(removeRollField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2; // span two columns
        removePanel.add(removeButton, gbc);

         // Add this line to add the displayButton


        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(createStyledLabel("Search by roll number:",16,Font.BOLD));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        inputPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10)); // Add some padding
        removePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding
        inputPanel.setBackground(new Color(204, 255, 204));  // Set the background color for the left panel
        removePanel.setBackground(new Color(204, 255, 204));  // Set the background color for the left panel

        JPanel leftJPanel=new JPanel(new BorderLayout());
        leftJPanel.setBackground(new Color(204, 255, 204));  // Set the background color for the left panel
        leftJPanel.add(inputPanel, BorderLayout.NORTH);
        leftJPanel.add(removePanel, BorderLayout.CENTER);

        leftJPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding
        
        JPanel rightJPanel=new JPanel(new BorderLayout());
        rightJPanel.setBackground(new Color(204, 255, 204));  // Set the background color for the right panel
        rightJPanel.add(searchPanel, BorderLayout.NORTH);
        rightJPanel.add(scrollPane, BorderLayout.CENTER);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(204, 255, 204));  // Set the background color for the main panel
        mainPanel.add(leftJPanel,BorderLayout.WEST);
        mainPanel.add(rightJPanel,BorderLayout.EAST);

        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Add some padding

        add(mainPanel);

        loadStudentRecordFromFile();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Record Management System");
        setSize(1200, 800);
        setIconImage(image.getImage());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
       
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setFont(new Font("Cambria",Font.PLAIN,16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(255, 174, 66));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        button.setPreferredSize(new Dimension(180, 30));
        return button;
    }

    private JTextField createStyledTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setBackground(new Color(255, 255, 255));
        textField.setForeground(new Color(0, 0, 0));
        textField.setFont(new Font("Cambria", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        return textField;
    }

    private JLabel createStyledLabel(String text, int fontSize, int fontStyle) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Cambria", fontStyle, fontSize));
        return label;
    }


    private void addStudent() {
        String name = nameField.getText();
        String rollNumberText = rollNumberField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();
    
        if (name.isEmpty() || rollNumberText.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            showWarningMessage("Please provide all the required inputs.");
            return;
        }
        else if (!name.matches("^[a-zA-Z\\s]+$")) {
            showErrorMessage("Invalid name. Please enter a valid name.");
            return;
        }

        else if(!rollNumberText.matches("\\d+"))
        {
            showWarningMessage("Please provide a valid roll number");
            return;
        }
        else if(Integer.parseInt(rollNumberText)<=0)
        {
            showWarningMessage("Please provide a positive valid roll number");
            return;
        }

        else if(phoneNumber.length()!=10 || !phoneNumber.matches("\\d+"))
        {
            showWarningMessage("Please provide a valid 10 digit phone number");
            return;
        }
    
        try {
            int rollNumber = Integer.parseInt(rollNumberText);
            Student student = new Student(name, rollNumber, address, phoneNumber);
            studentRecord.addStudent(student);
             // Add the student to the table
            tableModel.addRow(new Object[]{name, rollNumber, address, phoneNumber});
            clearFields();
            saveStudentRecordToFile();
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid roll number. Please enter a valid integer for roll number.");
        }
    }


    private void removeStudent() {
        String rollNumberText = removeRollField.getText();
    
        if (rollNumberText.isEmpty()) {
            showWarningMessage("Please provide a roll number for removal.");
            return;

        }
        else if(!rollNumberText.matches("\\d+"))
        {
            showWarningMessage("Please provide a valid roll number");
            return;
        }
        else if(Integer.parseInt(rollNumberText)<=0)
        {
            showWarningMessage("Please provide a positive valid roll number");
            return;
        }
    
        try 
        {
             
            int rollNumber = Integer.parseInt(rollNumberText);

            // Check if the student with the given roll number exists in the table
            boolean studentFound = false;
            int rowCount = tableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                if ((int) tableModel.getValueAt(i, 1) == rollNumber) 
                {
                    studentFound = true;
                    tableModel.removeRow(i);
                    break;
                }
            }


            if (!studentFound) {
                showWarningMessage("Student not found with roll number: " + rollNumber);
                return;
            }
            studentRecord.removeStudentByRollNumber(rollNumber);
    
            showSuccessMessage("Student removed with roll number: " + rollNumber);
            clearFields();
            saveStudentRecordToFile();
        } 
        catch (NumberFormatException e) 
        {
            showErrorMessage("Invalid roll number. Please enter a valid integer for roll number.");
            return;
        }
    }

    private void displayStudentInfo() 
    {
        ArrayList<Student> allStudents = studentRecord.getStudents();

        tableModel.setRowCount(0);

        if (!allStudents.isEmpty()) {
            for (Student student : allStudents) {
                tableModel.addRow(new Object[]{student.getName(), student.getRollNumber(), student.getAddress(), student.getPhoneNumber()});
            }
        } else {
            
            showWarningMessage("No students found in the records.");
        }
        clearFields();
    }


    private void searchStudent() 
    {
      
        String rollNumberText = searchField.getText();

        if (rollNumberText.isEmpty()) 
        {
            showWarningMessage("Please provide a roll number for searching.");
            return;
        }
        else if(!rollNumberText.matches("\\d+"))
        {
            showWarningMessage("Please provide a valid roll number");
            return;
        }
        else if(Integer.parseInt(rollNumberText)<=0)
        {
            showWarningMessage("Please provide a positive valid roll number");
            return;
        }
        try 
        {
            int rollNumber = Integer.parseInt(rollNumberText);
            Student student = studentRecord.getStudentByRollNumber(rollNumber);

            // Clear the table before updating it with new data
            tableModel.setRowCount(0);

            if (student != null) 
            {
                tableModel.addRow(new Object[]{student.getName(), student.getRollNumber(), student.getAddress(), student.getPhoneNumber()});
                displayArea.setText(student.toString());
            } 
            else 
            {
                
                showWarningMessage("Student not found with roll number: " + rollNumber);
            }
            clearFields();
        } 
        catch (NumberFormatException e) 
        {
            showErrorMessage("Invalid Roll Number. Please enter a valid integer for roll number.");
        }

    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        addressField.setText("");
        phoneNumberField.setText("");
    }


    private void saveStudentRecordToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("studentRecord.ser"))) {
            oos.writeObject(studentRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStudentRecordFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studentRecord.ser"))) {
            studentRecord = (StudentRecord) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new StudentRecordSystem();
            }
        });
    }
}
