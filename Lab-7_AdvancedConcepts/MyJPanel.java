import java.awt.FlowLayout;import javax.swing.*;public class MyJPanel extends JPanel {public MyJPanel() {	setLayout(new FlowLayout());	setSize(400, 300);add( new JLabel("User") );add( new JTextField(5) );add( new JButton("Primnt") );}}