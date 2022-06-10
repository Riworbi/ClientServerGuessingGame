package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.math.NumberUtils;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;

public class GUI {

	PrintWriter    out;
	BufferedReader  in;
	Socket socket;
	private JFrame frame = new JFrame();
	
	private JLabel TwojaCyfraText = new JLabel("");
	private JLabel lblNewLabel_KomputeraCyfra = new JLabel("-");
	private JLabel lblNewLabel_2 = new JLabel("0");
	private JLabel CyfraSerweraText_LABEL = new JLabel("Cyfra Serwera:");
	private JLabel lblNewLabel = new JLabel("Wybierz stawke :");
	private JLabel CyfraText = new JLabel("Twoja cyfra:");
	private JLabel SaldoText = new JLabel("Saldo: ");

	private JButton btnNewButton = new JButton("5");
	private JButton btnNewButton_1 = new JButton("10");
	private JButton btnNewButton_1_1 = new JButton("15");
	private JButton btnObstaw = new JButton("Obstaw");
	private	JButton btnLosuj = new JButton("Wylosuj kolejna cyfre");
	private JButton btnZostaw = new JButton("Zostaw");
	
	private int Saldo=100;
	private int Obstawianie=0;
	private String PlayerNumber;
	private String ServerNumber;
	private String ObstawianieString = Integer.toString(Obstawianie);
	private String MoneyString = Integer.toString(Saldo);
	
	public void EndGame() throws IOException {
		if(Saldo==0) {	
			
		out.println("EndGame");
		JOptionPane.showMessageDialog(frame,
			    "Koniec Gry!! - Koniec Œrodków!!",
			    "Koniec Gry",
			    JOptionPane.ERROR_MESSAGE);
		frame.setVisible(false);
		frame.dispose();
		}
	}
	
	private void CheckBoundForBid(int bid) {
		if(!(Obstawianie+bid>Saldo)) {					
			Obstawianie=Obstawianie+bid;
			ObstawianieString = Integer.toString(Obstawianie);
			lblNewLabel_2.setText(ObstawianieString);
			}
	}
	
	private void DisplayPopUpMessage(String msg, String nameOfWindow) {
		JOptionPane.showMessageDialog(frame,
				msg,
			    nameOfWindow,
			    JOptionPane.PLAIN_MESSAGE);
	}
	
	public GUI(final Socket socket) throws IOException {
		this.socket = socket;
		this.in =  new BufferedReader( new InputStreamReader(socket.getInputStream()));	
		this.out = new PrintWriter(socket.getOutputStream(),true);
		
		frame.setBounds(100, 100, 660, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
				
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 170, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 102, SpringLayout.WEST, frame.getContentPane());
		lblNewLabel.setText("Wybierz stawk\u0119: ");
		frame.getContentPane().add(lblNewLabel);

		springLayout.putConstraint(SpringLayout.NORTH, CyfraText, 124, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, CyfraText, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, CyfraText, 123, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(CyfraText);
		
		springLayout.putConstraint(SpringLayout.NORTH, SaldoText, 147, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, SaldoText, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, SaldoText, 191, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(SaldoText);
		SaldoText.setText(SaldoText.getText() + MoneyString);
		
		springLayout.putConstraint(SpringLayout.NORTH, CyfraSerweraText_LABEL, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, CyfraSerweraText_LABEL, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, CyfraSerweraText_LABEL, 123, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(CyfraSerweraText_LABEL);
		
		
		springLayout.putConstraint(SpringLayout.NORTH, TwojaCyfraText, 124, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, TwojaCyfraText, 97, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, TwojaCyfraText, 137, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, TwojaCyfraText, 142, SpringLayout.WEST, frame.getContentPane());
		TwojaCyfraText.setVerticalAlignment(SwingConstants.TOP);
		frame.getContentPane().add(TwojaCyfraText);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_KomputeraCyfra, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_KomputeraCyfra, 133, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_KomputeraCyfra, 178, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel_KomputeraCyfra);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 170, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 105, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, 191, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel_2);
		frame.setVisible(true);
				
		//Stawka 5
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 193, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 55, SpringLayout.WEST, frame.getContentPane());
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(btnNewButton.isEnabled()) {		
					CheckBoundForBid(5);
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		//Stawka 10
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1, 193, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1, 65, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1, 123, SpringLayout.WEST, frame.getContentPane());
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(btnNewButton_1.isEnabled()) {				
					CheckBoundForBid(10);
			}
		}
		});
		frame.getContentPane().add(btnNewButton_1);
		
		//Stawka 15
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_1_1, 193, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_1_1, 133, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_1_1, 191, SpringLayout.WEST, frame.getContentPane());
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(btnNewButton_1_1.isEnabled()) {
					CheckBoundForBid(15);
				}
			}
		});
		frame.getContentPane().add(btnNewButton_1_1);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnLosuj, 224, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnLosuj, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnLosuj, 253, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnLosuj, 154, SpringLayout.WEST, frame.getContentPane());
		btnLosuj.setEnabled(false);
		btnLosuj.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(btnLosuj.isEnabled()){
					out.println("Losuj");
					try {
						PlayerNumber = in.readLine();						
						TwojaCyfraText.setText(PlayerNumber);
						lblNewLabel_KomputeraCyfra.setText(ServerNumber); 
						if(NumberUtils.toInt(PlayerNumber)>NumberUtils.toInt(ServerNumber)) {
							Saldo+=Integer.parseInt(lblNewLabel_2.getText());
							SaldoText.setText("Saldo: "+ Saldo);
							btnObstaw.setEnabled(true);
							btnLosuj.setEnabled(false);
							btnZostaw.setEnabled(false);
							btnNewButton.setEnabled(true);
							btnNewButton_1.setEnabled(true);
							btnNewButton_1_1.setEnabled(true);
							Obstawianie=0;
							lblNewLabel_2.setText("0");

							JOptionPane.showMessageDialog(frame,
								    "Brawo, Wygrales!",
								    "Wygrana",
								    JOptionPane.PLAIN_MESSAGE);
						}
						else if(NumberUtils.toInt(PlayerNumber)<NumberUtils.toInt(ServerNumber)) {
							Saldo-=Obstawianie;
							SaldoText.setText("Saldo: "+ Saldo);
							btnObstaw.setEnabled(true);
							btnLosuj.setEnabled(false);
							btnZostaw.setEnabled(false);
							btnNewButton.setEnabled(true);
							btnNewButton_1.setEnabled(true);
							btnNewButton_1_1.setEnabled(true);
							Obstawianie=0;
							lblNewLabel_2.setText("0");

							JOptionPane.showMessageDialog(frame,
								    "Przegrana",
								    "PRZEGRANA",
								    JOptionPane.PLAIN_MESSAGE);
						}
						else if(NumberUtils.toInt(PlayerNumber)==NumberUtils.toInt(ServerNumber)) {
							SaldoText.setText("Saldo: "+ Saldo);
							btnObstaw.setEnabled(true);
							btnLosuj.setEnabled(false);
							btnZostaw.setEnabled(false);
							btnNewButton.setEnabled(true);
							btnNewButton_1.setEnabled(true);
							btnNewButton_1_1.setEnabled(true);
							Obstawianie=0;
							lblNewLabel_2.setText("0");
							JOptionPane.showMessageDialog(frame,
								    "Remis!",
								    "REMIS",
								    JOptionPane.PLAIN_MESSAGE);

						}
						EndGame();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		frame.getContentPane().add(btnLosuj);
		
		
		
		springLayout.putConstraint(SpringLayout.NORTH, btnZostaw, 224, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnZostaw, 164, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnZostaw, 253, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnZostaw, 277, SpringLayout.WEST, frame.getContentPane());
		btnZostaw.setEnabled(false);
		btnZostaw.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(btnZostaw.isEnabled()) {
				lblNewLabel_KomputeraCyfra.setText(ServerNumber); 
				if(NumberUtils.toInt(PlayerNumber)>NumberUtils.toInt(ServerNumber)) {
					Saldo+=Integer.parseInt(lblNewLabel_2.getText());
					SaldoText.setText("Saldo: "+ Saldo);
					
					btnObstaw.setEnabled(true);
					btnLosuj.setEnabled(false);
					btnZostaw.setEnabled(false);
					btnNewButton.setEnabled(true);
					btnNewButton_1.setEnabled(true);
					btnNewButton_1_1.setEnabled(true);
					Obstawianie=0;
					lblNewLabel_2.setText("0");

				
					DisplayPopUpMessage( "Brawo, Wygrales!", "WYGRANA");

				}
				else if(NumberUtils.toInt(PlayerNumber)<NumberUtils.toInt(ServerNumber)) {
					Saldo-=Obstawianie;
					SaldoText.setText("Saldo: "+ Saldo);
					btnObstaw.setEnabled(true);
					btnLosuj.setEnabled(false);
					btnZostaw.setEnabled(false);
					btnNewButton.setEnabled(true);
					btnNewButton_1.setEnabled(true);
					btnNewButton_1_1.setEnabled(true);
					Obstawianie=0;
					lblNewLabel_2.setText("0");

			
					DisplayPopUpMessage("Przegrana","PRZEGRANA");
				}
				else if(NumberUtils.toInt(PlayerNumber)==NumberUtils.toInt(ServerNumber)) {
					SaldoText.setText("Saldo: "+ Saldo);
					btnObstaw.setEnabled(true);
					btnLosuj.setEnabled(false);
					btnZostaw.setEnabled(false);
					btnNewButton.setEnabled(true);
					btnNewButton_1.setEnabled(true);
					btnNewButton_1_1.setEnabled(true);
					Obstawianie=0;
					lblNewLabel_2.setText("0");
					
					DisplayPopUpMessage("Remis!", "REMIS");

				}
				try {
					EndGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			}
		});
		frame.getContentPane().add(btnZostaw);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnObstaw, 224, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnObstaw, 287, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnObstaw, 253, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnObstaw, 400, SpringLayout.WEST, frame.getContentPane());
		btnObstaw.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					out.println("Obstaw");
					 PlayerNumber = in.readLine();
					 ServerNumber = in.readLine();
					 lblNewLabel_KomputeraCyfra.setText(" ");
					if(PlayerNumber!=null) {	
						TwojaCyfraText.setText(PlayerNumber);
						btnNewButton.setEnabled(false);
						btnNewButton_1.setEnabled(false);
						btnNewButton_1_1.setEnabled(false);
						btnObstaw.setEnabled(false);
						btnLosuj.setEnabled(true);
						btnZostaw.setEnabled(true);
					}
					else {
						btnLosuj.setEnabled(false);
						btnZostaw.setEnabled(false);
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					EndGame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnObstaw);
	}
}
