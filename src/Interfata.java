import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Color;

import javax.swing.UIManager;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class Interfata {

	private JFrame frmProject;
	private JTextField original;
	private JTextField criptat;
	private JTextField decriptat;
	protected Component frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfata window = new Interfata();
					window.frmProject.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfata() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProject = new JFrame();
		frmProject.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Deian\\Desktop\\hacker.jpg"));
		frmProject.getContentPane().setBackground(Color.ORANGE);
		frmProject.setBackground(Color.LIGHT_GRAY);
		frmProject.setTitle("AMSR Project");
		
		frmProject.setBounds(100, 100, 450, 300);
		frmProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnAesAlgorythm = new JButton("AES ");
		btnAesAlgorythm.setBounds(25, 76, 100, 23);
		btnAesAlgorythm.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAesAlgorythm.setBorder(UIManager.getBorder("Button.border"));
		btnAesAlgorythm.setBackground(SystemColor.activeCaption);
		btnAesAlgorythm.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAesAlgorythm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        KeyGenerator keyGenerator;
		        String text = original.getText();
				try {
					keyGenerator = KeyGenerator.getInstance("AES");
				keyGenerator.init(256);
		        SecretKey key = keyGenerator.generateKey();
		        
		        // Generating IV.
		        byte[] IV = new byte[16];
		        SecureRandom random = new SecureRandom();
		        random.nextBytes(IV);
		        
		       //test encryption speed
				byte[] cipherText = AES.encrypt(text.getBytes(),key, IV); 
				String EncryptedString = Base64.getEncoder().encodeToString(cipherText);
				criptat.setText(EncryptedString);
				
				//test decryption speed
				String DecryptedText = AES.decrypt(cipherText,key, IV);
				decriptat.setText(DecryptedText);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					
				}
				JOptionPane.showMessageDialog(frame, "Encrypted message!");
			}
		});
		frmProject.getContentPane().setLayout(null);
		frmProject.getContentPane().add(btnAesAlgorythm);
		
		JButton btnRsaAlgorythm = new JButton("RSA ");
		btnRsaAlgorythm.setBounds(25, 110, 100, 23);
		btnRsaAlgorythm.setBackground(SystemColor.activeCaption);
		btnRsaAlgorythm.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRsaAlgorythm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RSA rsa = new RSA();
				// encrypt
				byte[] encrypted = rsa.encrypt(original.getText().getBytes());
				String EncryptedString = Base64.getEncoder().encodeToString(encrypted);
				criptat.setText(EncryptedString);
				// decrypt
				byte[] decrypted = rsa.decrypt(encrypted);
				String DecryptedString = Base64.getEncoder().encodeToString(decrypted);
				decriptat.setText(DecryptedString);
				
				JOptionPane.showMessageDialog(frame, "Encrypted message!");
			}
		});
		frmProject.getContentPane().add(btnRsaAlgorythm);
		
		JButton btnVigenereAlgorythm = new JButton("Vigenere ");
		btnVigenereAlgorythm.setBounds(25, 144, 100, 23);
		btnVigenereAlgorythm.setBackground(SystemColor.activeCaption);
		btnVigenereAlgorythm.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnVigenereAlgorythm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = "CIPHER";

		        String message = original.getText();
		        
		        criptat.setText(Vigenere.vcriptare(message, key));

		        decriptat.setText(Vigenere.vdecriptare(Vigenere.vcriptare(message, key), key));
		        
		        JOptionPane.showMessageDialog(frame, "Encrypted message!");
			}
		});
		frmProject.getContentPane().add(btnVigenereAlgorythm);
		
		original = new JTextField();
		original.setBounds(135, 8, 289, 20);
		original.setBackground(Color.YELLOW);
		original.setSelectedTextColor(new Color(204, 255, 204));
		frmProject.getContentPane().add(original);
		original.setColumns(10);
		
		criptat = new JTextField();
		criptat.setBounds(135, 184, 289, 20);
		criptat.setBackground(Color.YELLOW);
		criptat.setSelectedTextColor(new Color(204, 255, 204));
		frmProject.getContentPane().add(criptat);
		criptat.setColumns(10);
		
		decriptat = new JTextField();
		decriptat.setBounds(135, 215, 289, 20);
		decriptat.setBackground(Color.YELLOW);
		decriptat.setSelectedTextColor(new Color(204, 255, 204));
		frmProject.getContentPane().add(decriptat);
		decriptat.setColumns(10);
		
		JLabel lblIntroducetiTextul = new JLabel("Enter the text:");
		lblIntroducetiTextul.setBounds(10, 11, 115, 17);
		lblIntroducetiTextul.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducetiTextul.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		frmProject.getContentPane().add(lblIntroducetiTextul);
		
		JLabel lblTextulCriptat = new JLabel("Encrypted text:");
		lblTextulCriptat.setBounds(10, 187, 115, 17);
		lblTextulCriptat.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextulCriptat.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		frmProject.getContentPane().add(lblTextulCriptat);
		
		JLabel lblNewLabel = new JLabel("Decrypted text:");
		lblNewLabel.setBounds(10, 218, 119, 14);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		frmProject.getContentPane().add(lblNewLabel);
		
		JLabel lblAlegetiUnulDin = new JLabel("Choose one of the 4 algorithms:");
		lblAlegetiUnulDin.setBounds(10, 39, 382, 14);
		lblAlegetiUnulDin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		frmProject.getContentPane().add(lblAlegetiUnulDin);
		
		JLabel label = new JLabel("New label");
		label.setBounds(241, 77, 183, 86);
		Image img=new ImageIcon(this.getClass().getResource("/hack.jpg")).getImage();
		label.setIcon(new ImageIcon(img));
		frmProject.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("Hill");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String key = "GYBNQKURP";

		        //String message = original.getText();
		        
		        String line = original.getText();
		        
		        Hill obj = new Hill();
		        
		        criptat.setText(Hill.cofact(line, key));

		        decriptat.setText(line);
				
		       
				//String key = textField_CheieInversa_Hill2.getText();
				
				
		        double sq = Math.sqrt(key.length());
		        if (sq != (long) sq)
		            System.out.println("Invalid key length! It does not form a quadratic matrix.");
		        else{
		            int s = (int) sq;
		            if (obj.check(key, s)){
		               criptat.setText(obj.divide(line, s));
		               //decriptat.setText(obj.cofact(obj.keymatrix, s));
		            }
		        }
		        
		        JOptionPane.showMessageDialog(frame, "Encrypted message!");
				
			}
		});
		btnNewButton.setBounds(135, 75, 89, 23);
		frmProject.getContentPane().add(btnNewButton);
		
		
		
		
	
		
	}
}
