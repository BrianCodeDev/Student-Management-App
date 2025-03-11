package student_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class student_management_system {

    static class Student {
        private String studentName;
        private int studentId;

        public Student(String studentName, int studentId) {
            this.studentName = studentName;
            this.studentId = studentId;
        }

        public String getStudentName() {
            return studentName;
        }

        public int getStudentId() {
            return studentId;
        }

        @Override
        public String toString() {
            return "Student ID: " + studentId + ", Name: " + studentName;
        }
    }

    static class StudentManagementSystem {
        private List<Student> studentList;

        public StudentManagementSystem() {
            studentList = new ArrayList<>();
        }

        public void addStudentToSystem(String studentName, int studentId) {
            studentList.add(new Student(studentName, studentId));
        }

        public String getAllStudents() {
            if (studentList.isEmpty()) {
                return "No students found in the system.";
            } else {
                StringBuilder studentDetails = new StringBuilder();
                for (Student student : studentList) {
                    studentDetails.append(student).append("\n");
                }
                return studentDetails.toString();
            }
        }

        public boolean removeStudentById(int studentId) {
            for (Student student : studentList) {
                if (student.getStudentId() == studentId) {
                    studentList.remove(student);
                    return true;
                }
            }
            return false;
        }

        public boolean updateStudent(int studentId, String newName) {
            for (Student student : studentList) {
                if (student.getStudentId() == studentId) {
                    student.studentName = newName;
                    return true;
                }
            }
            return false;
        }

        public List<Student> searchStudent(String query) {
            List<Student> resultList = new ArrayList<>();
            for (Student student : studentList) {
                if (student.getStudentName().toLowerCase().contains(query.toLowerCase()) ||
                    String.valueOf(student.getStudentId()).contains(query)) {
                    resultList.add(student);
                }
            }
            return resultList;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Management System");

        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StudentManagementSystem system = new StudentManagementSystem();

        JTextField studentNameField = new JTextField(20);
        studentNameField.setBackground(new Color(240, 240, 240));
        studentNameField.setForeground(new Color(50, 50, 50));
        studentNameField.setFont(new Font("Arial", Font.PLAIN, 18));

        JTextField studentIdField = new JTextField(20);
        studentIdField.setBackground(new Color(240, 240, 240));
        studentIdField.setForeground(new Color(50, 50, 50));
        studentIdField.setFont(new Font("Arial", Font.PLAIN, 18));

        JTextField searchField = new JTextField(20);
        searchField.setBackground(new Color(240, 240, 240));
        searchField.setForeground(new Color(50, 50, 50));
        searchField.setFont(new Font("Arial", Font.PLAIN, 18));

        JTextArea studentDisplayArea = new JTextArea(10, 30);
        studentDisplayArea.setEditable(false);
        studentDisplayArea.setBackground(new Color(245, 245, 245));
        studentDisplayArea.setForeground(new Color(50, 50, 50));
        studentDisplayArea.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(studentDisplayArea);

        JButton addButton = new JButton("Add Student");
        addButton.setBackground(new Color(0, 123, 255));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));

        JButton showButton = new JButton("Show All Students");
        showButton.setBackground(new Color(40, 167, 69));
        showButton.setForeground(Color.WHITE);
        showButton.setFont(new Font("Arial", Font.BOLD, 20));
        showButton.setFocusPainted(false);
        showButton.setBorder(BorderFactory.createLineBorder(new Color(40, 167, 69), 2));

        JButton searchButton = new JButton("Search Students");
        searchButton.setBackground(new Color(255, 193, 7));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createLineBorder(new Color(255, 193, 7), 2));

        JButton removeButton = new JButton("Remove Student");
        removeButton.setBackground(new Color(220, 53, 69));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(new Font("Arial", Font.BOLD, 20));
        removeButton.setFocusPainted(false);
        removeButton.setBorder(BorderFactory.createLineBorder(new Color(220, 53, 69), 2));

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(60, 63, 65));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Student Name:"), gbc);

        gbc.gridx = 1;
        panel.add(studentNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        panel.add(studentIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(addButton, gbc);

        gbc.gridx = 1;
        panel.add(showButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(searchButton, gbc);

        gbc.gridx = 1;
        panel.add(searchField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(removeButton, gbc);

        gbc.gridx = 1;
        panel.add(scrollPane, gbc);

        frame.add(panel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = studentNameField.getText();
                    int id = Integer.parseInt(studentIdField.getText());
                    system.addStudentToSystem(name, id);
                    studentNameField.setText("");
                    studentIdField.setText("");
                    JOptionPane.showMessageDialog(frame, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentDisplayArea.setText(system.getAllStudents());
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                List<Student> results = system.searchStudent(query);
                if (results.isEmpty()) {
                    studentDisplayArea.setText("No results found.");
                } else {
                    StringBuilder searchResults = new StringBuilder();
                    for (Student student : results) {
                        searchResults.append(student).append("\n");
                    }
                    studentDisplayArea.setText(searchResults.toString());
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(studentIdField.getText());
                    if (system.removeStudentById(id)) {
                        JOptionPane.showMessageDialog(frame, "Student removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
