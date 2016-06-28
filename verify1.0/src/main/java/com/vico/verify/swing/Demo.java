package com.vico.verify.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.vico.verify.implement.VerifyLicenseImp;

public class Demo extends JFrame implements ActionListener {

	/**
	 * 用户输入序列号界面
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label1;
	private JButton b1;
	private JTextField text;
	private JMenuBar MenuB;
	private JMenu menu;

	public Demo() {
		setTitle("验证服务Demo");
		setSize(500, 200);
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		MenuB = new JMenuBar();
		menu = new JMenu("激活");
		setJMenuBar(MenuB);
		MenuB.add(menu);
		label1 = new JLabel("序列号：");
		add(label1);
		text = new JTextField(30);
		add(text);
		b1 = new JButton("激活");
		b1.setToolTipText("点击按钮进行激活。");
		add(b1);
		b1.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			if (text.getText().trim().equals("")) {
				showMessageDialog("序列号不能为空!");
			} else {
				String result = new VerifyLicenseImp(text.getText()).verifyNoSourceNumber();
				showMessageDialog(result);
			}
			// this.dispose();
		}
	}

	public static void showMessageDialog(String result) {
		JOptionPane.showMessageDialog(null, result);
	}
}
