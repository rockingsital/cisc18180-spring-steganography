package project;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import java.awt.Rectangle;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VisualGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTabbedPane tabs = null;
	private JSplitPane encode = null;
	private JPanel leftSideEncode = null;
	private JPanel rightSideEncode = null;
	private JRadioButton encodeTextRadio = null;
	private JRadioButton encodePictureRadio = null;
	private JTextField encodeStartingPicture = null;
	private JLabel encodeStartingPictureLabel = null;
	private JButton encodeStartingBrowse = null;
	private JTextField encodeSecretText = null;
	private JLabel encodeSecretTextLabel = null;
	private JTextField encodeSecretPicture = null;
	private JLabel encodeSecretPictureLabel = null;
	private JButton encodeSecretBrowse = null;
	private JTextField encodeOutput = null;
	private JLabel encodeOutputLabel = null;
	private JButton encodeOutputBrowse = null;
	private File[] encodeArguments = new File[3];
	private File[] decodeArguments = new File[2];
	private JButton encodeButton = null;
	private JScrollPane encodeSecretPicturePane = null;
	private JScrollPane encodeOriginalImagePane = null;
	private JLabel encodeOriginalImageLabel = null;
	private JLabel encodeSecretPicturelabel = null;
	private JPanel decode = null;
	private JTextField decodeStartingPicture = null;
	private JLabel decodeStartingPictureLabel = null;
	private JTextField decodeOutputLocation = null;
	private JLabel decodeOutputLocationLabel = null;
	private JButton decodeStartingPictureBrowse = null;
	private JButton decodeOutputLocationBrowse = null;
	private JTextField decodePassword = null;
	private JLabel decodePasswordLabel = null;
	private JButton decodeButton = null;
	private JScrollPane decodeStartingPicturePane = null;
	private JLabel decodeOriginalPictureLabel = null;
	/**
	 * This method initializes tabs	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabs() {
		if (tabs == null) {
			tabs = new JTabbedPane();
			tabs.setBounds(new Rectangle(1, 1, 852, 586));
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
			encode.setDividerLocation(350);
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
			encodeSecretPicturelabel = new JLabel();
			encodeSecretPicturelabel.setBounds(new Rectangle(137, 236, 79, 17));
			encodeSecretPicturelabel.setText("Secret Image");
			encodeOutputLabel = new JLabel();
			encodeOutputLabel.setBounds(new Rectangle(8, 195, 93, 27));
			encodeOutputLabel.setText("Output Location");
			encodeSecretPictureLabel = new JLabel();
			encodeSecretPictureLabel.setBounds(new Rectangle(14, 152, 85, 24));
			encodeSecretPictureLabel.setText("Secret Picture");
			encodeSecretPictureLabel.setVisible(false);
			encodeSecretTextLabel = new JLabel();
			encodeSecretTextLabel.setBounds(new Rectangle(9, 105, 95, 29));
			encodeSecretTextLabel.setText("Secret Message");
			encodeSecretTextLabel.setVisible(false);
			encodeStartingPictureLabel = new JLabel();
			encodeStartingPictureLabel.setBounds(new Rectangle(8, 57, 93, 27));
			encodeStartingPictureLabel.setText("Starting Picture");
			leftSideEncode = new JPanel();
			leftSideEncode.setLayout(null);
			leftSideEncode.add(getEncodeTextRadio(), null);
			leftSideEncode.add(getEncodePictureRadio(), null);
			leftSideEncode.add(getEncodeStartingPicture(), null);
			leftSideEncode.add(encodeStartingPictureLabel, null);
			leftSideEncode.add(getEncodeStartingBrowse(), null);
			leftSideEncode.add(getEncodeSecretText(), null);
			leftSideEncode.add(encodeSecretTextLabel, null);
			leftSideEncode.add(getEncodeSecretPicture(), null);
			leftSideEncode.add(encodeSecretPictureLabel, null);
			leftSideEncode.add(getEncodeSecretBrowse(), null);
			leftSideEncode.add(getEncodeOutput(), null);
			leftSideEncode.add(encodeOutputLabel, null);
			leftSideEncode.add(getEncodeOutputBrowse(), null);
			leftSideEncode.add(getEncodeButton(), null);
			leftSideEncode.add(getEncodeSecretPicturePane(), null);
			leftSideEncode.add(encodeSecretPicturelabel, null);
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
			encodeOriginalImageLabel = new JLabel();
			encodeOriginalImageLabel.setBounds(new Rectangle(204, 4, 90, 21));
			encodeOriginalImageLabel.setText("Starting Image");
			rightSideEncode = new JPanel();
			rightSideEncode.setLayout(null);
			rightSideEncode.add(getEncodeOriginalImagePane(), null);
			rightSideEncode.add(encodeOriginalImageLabel, null);
		}
		return rightSideEncode;
	}

	/**
	 * This method initializes encodeTextRadio	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getEncodeTextRadio() {
		if (encodeTextRadio == null) {
			encodeTextRadio = new JRadioButton();
			encodeTextRadio.setBounds(new Rectangle(25, 15, 61, 33));
			encodeTextRadio.setText("Text");
			encodeTextRadio.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					encodePictureRadio.setSelected(false);
					encodeSecretTextLabel.setVisible(true);
					encodeSecretText.setVisible(true);
					encodeSecretPicture.setVisible(false);
					encodeSecretPictureLabel.setVisible(false);
					encodeSecretBrowse.setVisible(false);
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
			encodePictureRadio.setBounds(new Rectangle(114, 16, 70, 34));
			encodePictureRadio.setText("Picture");
			encodePictureRadio.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					encodeTextRadio.setSelected(false);
					encodeSecretTextLabel.setVisible(false);
					encodeSecretText.setVisible(false);
					encodeSecretPicture.setVisible(true);
					encodeSecretPictureLabel.setVisible(true);
					encodeSecretBrowse.setVisible(true);
				}
			});
		}
		return encodePictureRadio;
	}

	/**
	 * This method initializes encodeStartingPicture	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEncodeStartingPicture() {
		if (encodeStartingPicture == null) {
			encodeStartingPicture = new JTextField();
			encodeStartingPicture.setBounds(new Rectangle(108, 59, 127, 26));
		}
		return encodeStartingPicture;
	}

	/**
	 * This method initializes encodeStartingBrowse	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEncodeStartingBrowse() {
		if (encodeStartingBrowse == null) {
			encodeStartingBrowse = new JButton();
			encodeStartingBrowse.setBounds(new Rectangle(240, 55, 99, 32));
			encodeStartingBrowse.setText("Browse");
			encodeStartingBrowse.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser searcher = new JFileChooser();
					searcher.setFileSelectionMode(JFileChooser.FILES_ONLY);
					searcher.setDialogTitle("Strarting Picture");
					searcher.showDialog(null,"Open File");
					encodeStartingPicture.setText(searcher.getSelectedFile().getAbsolutePath());
					encodeArguments[0] = searcher.getSelectedFile();
					ImagePanel img = new ImagePanel(encodeStartingPicture);
					img.setPreferredSize(new Dimension(img.image.getWidth(), img.image.getHeight()));
					encodeOriginalImagePane.setViewportView(img);
					encodeOriginalImagePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					encodeOriginalImagePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					
				}
			});
		}
		return encodeStartingBrowse;
	}

	/**
	 * This method initializes encodeSecretText	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEncodeSecretText() {
		if (encodeSecretText == null) {
			encodeSecretText = new JTextField();
			encodeSecretText.setBounds(new Rectangle(109, 105, 125, 27));
			encodeSecretText.setVisible(false);
		}
		return encodeSecretText;
	}

	/**
	 * This method initializes encodeSecretPicture	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEncodeSecretPicture() {
		if (encodeSecretPicture == null) {
			encodeSecretPicture = new JTextField();
			encodeSecretPicture.setBounds(new Rectangle(108, 151, 126, 26));
			encodeSecretPicture.setVisible(false);
		}
		return encodeSecretPicture;
	}

	/**
	 * This method initializes encodeSecretBrowse	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEncodeSecretBrowse() {
		if (encodeSecretBrowse == null) {
			encodeSecretBrowse = new JButton();
			encodeSecretBrowse.setBounds(new Rectangle(238, 149, 103, 31));
			encodeSecretBrowse.setText("Browse");
			encodeSecretBrowse.setVisible(false);
			encodeSecretBrowse.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser searcher = new JFileChooser();
					searcher.setFileSelectionMode(JFileChooser.FILES_ONLY);
					searcher.setDialogTitle("Secret Picture");
					searcher.showDialog(null,"Open File");
					encodeSecretPicture.setText(searcher.getSelectedFile().getAbsolutePath());
					encodeArguments[2] = searcher.getSelectedFile();
					ImagePanel img = new ImagePanel(encodeSecretPicture);
					img.setPreferredSize(new Dimension(img.image.getWidth(), img.image.getHeight()));
					encodeSecretPicturePane.setViewportView(img);
					encodeSecretPicturePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					encodeSecretPicturePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					
				}
			});
		}
		return encodeSecretBrowse;
	}

	/**
	 * This method initializes encodeOutput	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEncodeOutput() {
		if (encodeOutput == null) {
			encodeOutput = new JTextField();
			encodeOutput.setBounds(new Rectangle(108, 196, 127, 26));
		}
		return encodeOutput;
	}

	/**
	 * This method initializes encodeOutputBrowse	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEncodeOutputBrowse() {
		if (encodeOutputBrowse == null) {
			encodeOutputBrowse = new JButton();
			encodeOutputBrowse.setBounds(new Rectangle(237, 193, 104, 31));
			encodeOutputBrowse.setText("Browse");
			encodeOutputBrowse.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser searcher = new JFileChooser();
					searcher.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					searcher.setDialogTitle("Output Location");
					searcher.showDialog(null,"Open Directory");
					encodeOutput.setText(searcher.getSelectedFile().getAbsolutePath());
					encodeArguments[1] = searcher.getSelectedFile();
				}
			});
		}
		return encodeOutputBrowse;
	}

	/**
	 * This method initializes encodeButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEncodeButton() {
		if (encodeButton == null) {
			encodeButton = new JButton();
			encodeButton.setBounds(new Rectangle(200, 15, 136, 36));
			encodeButton.setText("Encode!");
			encodeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (encodeTextRadio.isSelected()){
						EncodeAndDecode.encodeText(encodeArguments[0], 
								encodeArguments[1], 
								encodeSecretText.getText());
					}
					else if (encodePictureRadio.isSelected()){
						EncodeAndDecode.encodePicture(encodeArguments[2], 
								encodeArguments[0], encodeArguments[1].getAbsolutePath());
					}
					else{
						System.out.println("Please select to encode with either a picture or with text.");
					}
				}
			});
		}
		return encodeButton;
	}

	/**
	 * This method initializes encodeSecretPicturePane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getEncodeSecretPicturePane() {
		if (encodeSecretPicturePane == null) {
			encodeSecretPicturePane = new JScrollPane();
			encodeSecretPicturePane.setBounds(new Rectangle(1, 257, 346, 298));
		}
		return encodeSecretPicturePane;
	}

	/**
	 * This method initializes encodeOriginalImagePane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getEncodeOriginalImagePane() {
		if (encodeOriginalImagePane == null) {
			encodeOriginalImagePane = new JScrollPane();
			encodeOriginalImagePane.setBounds(new Rectangle(1, 29, 484, 527));
		}
		return encodeOriginalImagePane;
	}

	/**
	 * This method initializes decode	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDecode() {
		if (decode == null) {
			decodeOriginalPictureLabel = new JLabel();
			decodeOriginalPictureLabel.setBounds(new Rectangle(376, 127, 95, 20));
			decodeOriginalPictureLabel.setText("Original Picture");
			decodePasswordLabel = new JLabel();
			decodePasswordLabel.setBounds(new Rectangle(416, 60, 80, 22));
			decodePasswordLabel.setText("Password");
			decodeOutputLocationLabel = new JLabel();
			decodeOutputLocationLabel.setBounds(new Rectangle(12, 79, 99, 25));
			decodeOutputLocationLabel.setText("Output Location");
			decodeStartingPictureLabel = new JLabel();
			decodeStartingPictureLabel.setBounds(new Rectangle(12, 33, 100, 26));
			decodeStartingPictureLabel.setText("Starting Picture");
			decode = new JPanel();
			decode.setLayout(null);
			decode.add(getDecodeStartingPicture(), null);
			decode.add(decodeStartingPictureLabel, null);
			decode.add(getDecodeOutputLocation(), null);
			decode.add(decodeOutputLocationLabel, null);
			decode.add(getDecodeStartingPictureBrowse(), null);
			decode.add(getDecodeOutputLocationBrowse(), null);
			decode.add(getDecodePassword(), null);
			decode.add(decodePasswordLabel, null);
			decode.add(getDecodeButton(), null);
			decode.add(getDecodeStartingPicturePane(), null);
			decode.add(decodeOriginalPictureLabel, null);
		}
		return decode;
	}

	/**
	 * This method initializes decodeStartingPicture	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDecodeStartingPicture() {
		if (decodeStartingPicture == null) {
			decodeStartingPicture = new JTextField();
			decodeStartingPicture.setBounds(new Rectangle(120, 32, 153, 29));
		}
		return decodeStartingPicture;
	}

	/**
	 * This method initializes decodeOutputLocation	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDecodeOutputLocation() {
		if (decodeOutputLocation == null) {
			decodeOutputLocation = new JTextField();
			decodeOutputLocation.setBounds(new Rectangle(120, 76, 151, 29));
		}
		return decodeOutputLocation;
	}

	/**
	 * This method initializes decodeStartingPictureBrowse	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDecodeStartingPictureBrowse() {
		if (decodeStartingPictureBrowse == null) {
			decodeStartingPictureBrowse = new JButton();
			decodeStartingPictureBrowse.setBounds(new Rectangle(285, 27, 98, 34));
			decodeStartingPictureBrowse.setText("Browse");
			decodeStartingPictureBrowse
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							JFileChooser searcher = new JFileChooser();
							searcher.setFileSelectionMode(JFileChooser.FILES_ONLY);
							searcher.setDialogTitle("Strarting Picture");
							searcher.showDialog(null,"Open File");
							decodeStartingPicture.setText(searcher.getSelectedFile().getAbsolutePath());
							decodeArguments[0] = searcher.getSelectedFile();
							ImagePanel img = new ImagePanel(decodeStartingPicture);
							img.setPreferredSize(new Dimension(img.image.getWidth(), img.image.getHeight()));
							decodeStartingPicturePane.setViewportView(img);
							decodeStartingPicturePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
							decodeStartingPicturePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
							
						}
					});
		}
		return decodeStartingPictureBrowse;
	}

	/**
	 * This method initializes decodeOutputLocationBrowse	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDecodeOutputLocationBrowse() {
		if (decodeOutputLocationBrowse == null) {
			decodeOutputLocationBrowse = new JButton();
			decodeOutputLocationBrowse.setBounds(new Rectangle(285, 73, 98, 33));
			decodeOutputLocationBrowse.setText("Browse");
			decodeOutputLocationBrowse
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							JFileChooser searcher = new JFileChooser();
							searcher.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							searcher.setDialogTitle("Output Location");
							searcher.showDialog(null,"Open Directory");
							decodeOutputLocation.setText(searcher.getSelectedFile().getAbsolutePath());
							decodeArguments[1] = searcher.getSelectedFile();
						}
					});
		}
		return decodeOutputLocationBrowse;
	}

	/**
	 * This method initializes decodePassword	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDecodePassword() {
		if (decodePassword == null) {
			decodePassword = new JTextField();
			decodePassword.setBounds(new Rectangle(506, 57, 173, 29));
		}
		return decodePassword;
	}

	/**
	 * This method initializes decodeButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDecodeButton() {
		if (decodeButton == null) {
			decodeButton = new JButton();
			decodeButton.setBounds(new Rectangle(696, 50, 122, 41));
			decodeButton.setText("Decode!");
			decodeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					EncodeAndDecode.decode(decodeArguments[0], decodeArguments[1], decodePassword.getText());
				}
			});
		}
		return decodeButton;
	}

	/**
	 * This method initializes decodeStartingPicturePane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getDecodeStartingPicturePane() {
		if (decodeStartingPicturePane == null) {
			decodeStartingPicturePane = new JScrollPane();
			decodeStartingPicturePane.setBounds(new Rectangle(0, 150, 847, 407));
		}
		return decodeStartingPicturePane;
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
		this.setSize(871, 624);
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

}  //  @jve:decl-index=0:visual-constraint="-3,-34"
