package  com.chatapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;

import javax.swing.text.Segment;

public class SimpleChatClient {
	
public class IncomingReader implements Runnable {

		public void run() {
			String message;
			try {
			while((message = reader.readLine())!= null){
				System.out.println(" Read : " + message );
				incoming.append(message+"\n");
				if (((qScroller.getVerticalScrollBar().getAlignmentY())*10)>10){
					incoming.setSize(incoming.getColumns(), incoming.getHeight()+10);
				}
			}	
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	public class SendButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			try {
				writer.println(System.getProperty("user.name")+" :  "+outgoing.getText());
				writer.flush();
				outgoing.setText("");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	public class SereverListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			serverIP = server.getText();
			setUpNetWorking();

		}

	}
	
	JTextArea incoming;
	JTextField outgoing;
	BufferedReader reader;
	PrintWriter writer;
	JTextField server;
	String serverIP;
	Socket sock;
	JScrollPane qScroller;
	
	public static void main(String[] args) {
		SimpleChatClient client = new SimpleChatClient();
		client.go();
		}
	
	public void go() {
		JFrame frame = new JFrame("Chat Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Panel for server setup.
		JPanel serverpanel = new JPanel();
		server = new JTextField(10);
		JButton serverButton = new JButton("SET SERVER");
		serverButton.addActionListener(new SereverListener());
		serverpanel.add(server);
		serverpanel.add(serverButton);
		
		// Panel for adding all the components.
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new BoxLayout(mainpanel,BoxLayout.Y_AXIS));
		
		// Text area for incoming texts.
		incoming = new JTextArea(10,50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		DefaultCaret caret = (DefaultCaret)incoming.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);


		
		//Scroller for above text area.
		qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		qScroller.setVisible(true);
		
		

		
		// Text field  for outgoing texts.
		outgoing = new JTextField(20);
		JButton sendButton = new JButton("SEND");
		sendButton.addActionListener(new SendButtonListener());
		
		//Adding all the components to Panel.
		mainpanel.add(serverpanel);
		mainpanel.add(qScroller);
		mainpanel.add(outgoing);
		mainpanel.add(sendButton);
		
		//Adding main panel to frame.
		frame.getContentPane().add(mainpanel);
		frame.setSize(600,300);
		frame.setVisible(true);
	}
	
	public void setUpNetWorking() {
		try {
			sock = new Socket(serverIP,5000);
			sock.setKeepAlive(true);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(sock.getOutputStream());
			System.out.println("Networking OK !");
			Thread readerThread = new Thread(new IncomingReader());
			readerThread.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}


