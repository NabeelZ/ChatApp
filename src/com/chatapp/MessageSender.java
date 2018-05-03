package com.chatapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import java.awt.event.MouseAdapter;

public class MessageSender implements ActionListener {
	
	ClientApp sl = new ClientApp();

		public void actionPerformed(ActionEvent arg0) {
			try {
				sl.writer.println(sl.usernamefield.getText() + " :  " + sl.outgoing.getText());
				sl.writer.flush();
				sl.outgoing.setText("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
