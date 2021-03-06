package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Enumeration;
import gnu.io.SerialPort;
import gnu.io.SerialPortEventListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.eclipse.persistence.jpa.jpql.parser.ConcatExpression;

import controller.ControladorRegistro;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class View extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private OutputStream Output = null;
	private InputStream Input = null;
	SerialPort serialPort;
	private final String PORT_NAME = "COM4";
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	JButton conectar, desconectar;

	public static void main(String[] args) {
		View v = new View();
		v.setVisible(true);
		v.setSize(400, 300);
	}

	private class ImplementoRunnable implements Runnable {
		char v1, v2, v3, v4;

		ControladorRegistro rc = new ControladorRegistro();

		public void run() {
			while (true) {
				try {
					Output.write('T');
					Thread.sleep(500);
					v1 = (char) Input.read();
					v2 = (char) Input.read();
					v3 = (char) Input.read();
					v4 = (char) Input.read();
					int n1=Character.getNumericValue(v1);
					int n2=Character.getNumericValue(v2);
					int n3=Character.getNumericValue(v3);
					int n4=Character.getNumericValue(v4);
					
					
					if ( n1 >= 0 &&  n1 <= 9 &&  n2 >= 0 &&  n2 <= 9 &&  n3 >= 0
							&&  n3 <= 9 &&  n4 >= 0 &&  n4 <= 9) {
						
						ControladorRegistro reg = new ControladorRegistro();
						reg.CrearReg(n3*10+n4,n1*10+n2);
						
						System.out.print("Temp "+n1+n2);
						System.out.println("Hume "+n3+n4);
					}
					repaint();
				} catch (Exception e1) {
				}
			}
		}
	}

	public View() {
		setTitle("Clima");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 365);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Thread timer = new Thread(new ImplementoRunnable());
		timer.start();
		timer.interrupt();
		conectar = new JButton("Conectar");
		conectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommPortIdentifier portId = null;
				Enumeration PortEnum = CommPortIdentifier.getPortIdentifiers();
				while (PortEnum.hasMoreElements()) {
					CommPortIdentifier currPortId = (CommPortIdentifier) PortEnum.nextElement();
					if (PORT_NAME.equals(currPortId.getName())) {
						portId = currPortId;
						try {
							serialPort = (SerialPort) portId.open("puerto serial", TIME_OUT);
							serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
									SerialPort.PARITY_NONE);
							serialPort.setDTR(true);
							Output = serialPort.getOutputStream();
							Input = serialPort.getInputStream();
							desconectar.setEnabled(true);
							conectar.setEnabled(false);
							timer.resume();
						} catch (PortInUseException e1) {
							e1.printStackTrace();
						} catch (UnsupportedCommOperationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					}
				}
			}
		});
		conectar.setBounds(38, 63, 89, 23);
		contentPane.add(conectar);

		desconectar = new JButton("Desconectar");
		desconectar.setEnabled(false);
		desconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timer.interrupt();
				serialPort.close();
				desconectar.setEnabled(false);
				conectar.setEnabled(true);
			}
		});

		desconectar.setBounds(205, 63, 128, 23);
		contentPane.add(desconectar);
	}
}