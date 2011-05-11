package project;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import java.awt.Rectangle;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;

public class VisualGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTabbedPane tabs = null;
	private JSplitPane encode = null;
	private JPanel leftSideEncode = null;
	private JPanel rightSideEncode = null;
	private JSplitPane decode = null;
	private JPanel leftSideDecode = null;
	private JPanel rightSideDecode = null;
	private JRadioButton encodeTextRadio = null;
	private JRadioButton encodePictureRadio = null;
	/**
	 * This method initializes tabs	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabs() {
		if (tabs == null) {
			tabs = new JTabbedPane();
			tabs.setBounds(new Rectangle(1, 1, 641, 427));
			tabs.addTab("Encode", null, getEncode(), null);
			tabs.addTab("Decode", null, getDecode(), null);
		}
		return tabs;
	}

	/**
	 * This method initializes encode	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getEncode() {
		if (encode == null) {
			encode = new JSplitPane();
			encode.setDividerLocation(300);
			encode.setRightComponent(getRightSideEncode());
			encode.setLeftComponent(getLeftSideEncode());
		}
		return encode;
	}

	/**
	 * This method initializes leftSideEncode	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLeftSideEncode() {
		if (leftSideEncode == null) {
			leftSideEncode = new JPanel();
			leftSideEncode.setLayout(null);
			leftSideEncode.add(getEncodeTextRadio(), null);
			leftSideEncode.add(getEncodePictureRadio(), null);
		}
		return leftSideEncode;
	}

	/**
	 * This method initializes rightSideEncode	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRightSideEncode() {
		if (rightSideEncode == null) {
			rightSideEncode = new JPanel();
			rightSideEncode.setLayout(null);
		}
		return rightSideEncode;
	}

	/**
	 * This method initializes decode	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getDecode() {
		if (decode == null) {
			decode = new JSplitPane();
			decode.setDividerLocation(300);
			decode.setRightComponent(getRightSideDecode());
			decode.setLeftComponent(getLeftSideDecode());
		}
		return decode;
	}

	/**
	 * This method initializes leftSideDecode	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLeftSideDecode() {
		if (leftSideDecode == null) {
			leftSideDecode = new JPanel();
			leftSideDecode.setLayout(null);
		}
		return leftSideDecode;
	}

	/**
	 * This method initializes rightSideDecode	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRightSideDecode() {
		if (rightSideDecode == null) {
			rightSideDecode = new JPanel();
			rightSideDecode.setLayout(null);
		}
		return rightSideDecode;
	}

	/**
	 * This method initializes encodeTextRadio	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getEncodeTextRadio() {
		if (encodeTextRadio == null) {
			encodeTextRadio = new JRadioButton();
			encodeTextRadio.setBounds(new Rectangle(52, 16, 61, 33));
			encodeTextRadio.setText("Text");
			encodeTextRadio.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					encodePictureRadio.setSelected(false);
				}
			});
		}
		return encodeTextRadio;
	}

	/**
	 * This method initializes encodePictureRadio	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getEncodePictureRadio() {
		if (encodePictureRadio == null) {
			encodePictureRadio = new JRadioButton();
			encodePictureRadio.setBounds(new Rectangle(170, 16, 70, 34));
			encodePictureRadio.setText("Picture");
			encodePictureRadio.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					encodeTextRadio.setSelected(false);
				}
			});
		}
		return encodePictureRadio;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VisualGUI thisClass = new VisualGUI();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public VisualGUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(660, 466);
		this.setContentPane(getJContentPane());
		this.setTitle("Steganography");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getTabs(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="13,-24"
