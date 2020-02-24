import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;

import javax.swing.*;

/**
 * This class demonstrates how to do a 'PrintScreen' feature
 * for a Java Swing application.
 * 
 * @author ralph
 */
public class JFramePrintable extends MyJFrame implements ActionListener {
	private PrinterJob pjob;
	private JPanel jp;

	public JFramePrintable() {
		super();
		this.setSize(400, 400);

		JLabel jl1 = new JLabel("UserID");
		JTextField jt1 = new JTextField(5);		
		JButton jb1 = new JButton("Print");
		jb1.addActionListener(this);

		jp = new JPanel();
		jp.setLayout(new FlowLayout());
		jp.setSize(400, 300);
		jp.add(jl1);
		jp.add(jt1);
		jp.add(jb1);

		this.getContentPane().setLayout(null);
		this.getContentPane().add(jp);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		JFramePrintable f = new JFramePrintable();
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * This method is called when the user clicks on
	 * the Print button, it will print the contents
	 * of MyPanel in it current GUI state.
	 */
	public void actionPerformed(ActionEvent arg0) {
		// initialze the PrinterJob:
		this.pjob = PrinterJob.getPrinterJob();
		pjob.setPrintable(this, pjob.defaultPage());

		// select the Printer dialog:
		if (pjob.printDialog()) {

			// do the actual printing:
			try {
				pjob.print();
			} catch (Exception e) {
				System.out.println(e);
				System.exit(1);
			}
		}		
	}

}

/**
 * We need to override JFrame, since we need to handle 
 * the paint() method to allow printing.  Using the 
 * printAll() method we call all the child components 
 * inside this panel.
 * 
 * @author ralph
 *
 */
class MyJFrame extends JFrame implements Printable {
	public MyJFrame() {
		super();
	}

	public int print(Graphics g, PageFormat pf, int pi) throws PrinterException {
		if (pi >= 1) {
			return Printable.NO_SUCH_PAGE;
		}

		Graphics2D g2 = (Graphics2D) g;
		g2.translate(pf.getImageableX(), pf.getImageableY());
		Font f = new Font("Monospaced", Font.PLAIN, 12);
		g2.setFont(f);
		this.printAll(g);
		// paint(g2);
		return Printable.PAGE_EXISTS;
	}
}