package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
import java.sql.*;


@SuppressWarnings("serial")
public class Viewre extends JFrame implements ActionListener{

	DefaultTableModel model = new DefaultTableModel();
	JTable jtbl = new JTable(model);
	Container co = getContentPane();
	JButton btn;

	public Viewre() {
		setLayout(new FlowLayout());

		model.addColumn("Student ID");
		model.addColumn("Student Name");
		model.addColumn("Year & Class");
		model.addColumn("Book ID");
		model.addColumn("Book Name");
		model.addColumn("Issuee Date");
		model.addColumn("Return Date");
		
		btn = new JButton("Back");
		add(btn);
		btn.setBounds(450,520,120,30);
		btn.addActionListener(this);
		
		setLayout(null);
		setSize(1000, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		try {
			Coon conn = new Coon();

			String sql = "select * from returnb";
			PreparedStatement ps = conn.c.prepareStatement(sql);

			ResultSet Rs = ps.executeQuery();
			while (Rs.next()) {
				model.addRow(new Object[] { Rs.getString(1), Rs.getString(2), Rs.getString(3) + Rs.getString(4),
						Rs.getString(5), Rs.getString(6), Rs.getString(7), Rs.getString(8) });
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		JScrollPane pg = new JScrollPane(jtbl);
		pg.setBounds(84, 5, 811, 402);
		pg.setOpaque(false);
		pg.getViewport().setOpaque(false);
		co.add(pg);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sa = e.getActionCommand();
		if (sa.equals("Back")) {
			dispose();
		}
	}
}
