package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class ThicknessPicker extends JDialog {
	private static final long serialVersionUID = 1L;
	private JSlider js = new JSlider(JSlider.HORIZONTAL, 0, 50, 0);
	private JLabel label = new JLabel("Thickness");
	private JButton ok = new JButton("Ok");
	private JButton cancel = new JButton("Cancel");

	public void showThicknessPicker(ActionListener listener) {
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.actionPerformed(e);
				setVisible(false);
			}
		});

		setVisible(true);
	}

	private void init() {
		js.setMajorTickSpacing(10);
		js.setMinorTickSpacing(2);
		js.setPaintTicks(true);
		js.setPaintLabels(true);
		label.setHorizontalAlignment(JLabel.CENTER);

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(label, BorderLayout.PAGE_START);
		contentPane.add(js, BorderLayout.CENTER);
		Container buttons = new Container();
		buttons.setLayout(new FlowLayout());
		buttons.add(ok);
		buttons.add(cancel);
		contentPane.add(buttons, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(getOwner());
		setResizable(false);
	}

	public ThicknessPicker(Frame owner, String title) {
		super(owner, title);
		init();
	}

	public int getValue() {
		return js.getValue();
	}
}
