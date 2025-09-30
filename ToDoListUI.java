import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoListUI extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskField;
    private ArrayList<String> tasks;

    public ToDoListUI() {
        tasks = new ArrayList<>();
        setTitle("To-Do List App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Task input field
        taskField = new JTextField();
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton editButton = new JButton("Edit");

        // Panel for input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Task list
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add Task
        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                listModel.addElement(task);
                tasks.add(task);
                taskField.setText("");
            }
        });

        // Delete Task
        deleteButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                listModel.remove(index);
                tasks.remove(index);
            }
        });

        // Edit Task
        editButton.addActionListener(e -> {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                String newTask = JOptionPane.showInputDialog(this, "Edit Task:", tasks.get(index));
                if (newTask != null && !newTask.trim().isEmpty()) {
                    listModel.set(index, newTask);
                    tasks.set(index, newTask);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListUI::new);
    }
}
