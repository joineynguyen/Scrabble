package scrabble;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//When a player places a blank tile on the scrabble board, this GUI pops up letting users choose what letter to replace with.
public class chooseTileDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	char chosenLetter;
	ArrayList<JButton> letterButtons = new ArrayList<>(); //Help iterate through all letters' button
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			chooseTileDialog dialog = new chooseTileDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public chooseTileDialog() 
	{
		chosenLetter = '0';
		
		setBounds(100, 100, 325, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 299, 300);
		contentPanel.add(panel);
		panel.setLayout(null);
		{
			JButton aButton = new JButton("A");
			aButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					aButton.setBackground(Color.ORANGE);
					chosenLetter = 'A';
				}
			});
			aButton.setBounds(10, 11, 47, 38);
			panel.add(aButton);
			letterButtons.add(aButton);
		}
		{
			JButton bButton = new JButton("B");
			bButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					bButton.setBackground(Color.ORANGE);
					chosenLetter = 'B';
				}
			});
			bButton.setBounds(67, 11, 47, 38);
			panel.add(bButton);
			letterButtons.add(bButton);
		}
		{
			JButton cButton = new JButton("C");
			cButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					cButton.setBackground(Color.ORANGE);
					chosenLetter = 'C';
				}
			});
			cButton.setBounds(124, 11, 47, 38);
			panel.add(cButton);
			letterButtons.add(cButton);
		}
		{
			JButton dButton = new JButton("D");
			dButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					dButton.setBackground(Color.ORANGE);
					chosenLetter = 'D';
				}
			});
			dButton.setBounds(181, 11, 47, 38);
			panel.add(dButton);
			letterButtons.add(dButton);
		}
		{
			JButton eButton = new JButton("E");
			eButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					eButton.setBackground(Color.ORANGE);
					chosenLetter = 'E';
				}
			});
			eButton.setBounds(238, 11, 47, 38);
			panel.add(eButton);
			letterButtons.add(eButton);
		}
		{
			JButton fButton = new JButton("F");
			fButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					fButton.setBackground(Color.ORANGE);
					chosenLetter = 'F';
				}
			});
			fButton.setBounds(10, 60, 47, 38);
			panel.add(fButton);
			letterButtons.add(fButton);
		}
		{
			JButton gButton = new JButton("G");
			gButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					gButton.setBackground(Color.ORANGE);
					chosenLetter = 'G';
				}
			});
			gButton.setBounds(67, 60, 47, 38);
			panel.add(gButton);
			letterButtons.add(gButton);
		}
		{
			JButton hButton = new JButton("H");
			hButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					hButton.setBackground(Color.ORANGE);
					chosenLetter = 'H';
				}
			});
			hButton.setBounds(124, 60, 47, 38);
			panel.add(hButton);
			letterButtons.add(hButton);
		}
		{
			JButton iButton = new JButton("I");
			iButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					iButton.setBackground(Color.ORANGE);
					chosenLetter = 'I';
				}
			});
			iButton.setBounds(181, 60, 47, 38);
			panel.add(iButton);
			letterButtons.add(iButton);
		}
		{
			JButton jButton = new JButton("J");
			jButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					jButton.setBackground(Color.ORANGE);
					chosenLetter = 'J';
				}
			});
			jButton.setBounds(238, 60, 47, 38);
			panel.add(jButton);
			letterButtons.add(jButton);
		}
		{
			JButton kButton = new JButton("K");
			kButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					kButton.setBackground(Color.ORANGE);
					chosenLetter = 'K';
				}
			});
			kButton.setBounds(10, 109, 47, 38);
			panel.add(kButton);
			letterButtons.add(kButton);
		}
		{
			JButton lButton = new JButton("L");
			lButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					lButton.setBackground(Color.ORANGE);
					chosenLetter = 'L';
				}
			});
			lButton.setBounds(67, 109, 47, 38);
			panel.add(lButton);
			letterButtons.add(lButton);
		}
		{
			JButton mButton = new JButton("M");
			mButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					mButton.setBackground(Color.ORANGE);
					chosenLetter = 'M';
				}
			});
			mButton.setBounds(124, 109, 47, 38);
			panel.add(mButton);
			letterButtons.add(mButton);
		}
		{
			JButton nButton = new JButton("N");
			nButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					nButton.setBackground(Color.ORANGE);
					chosenLetter = 'N';
				}
			});
			nButton.setBounds(181, 109, 47, 38);
			panel.add(nButton);
			letterButtons.add(nButton);
		}
		{
			JButton oButton = new JButton("O");
			oButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					oButton.setBackground(Color.ORANGE);
					chosenLetter = 'O';
				}
			});
			oButton.setBounds(238, 109, 47, 38);
			panel.add(oButton);
			letterButtons.add(oButton);
		}
		{
			JButton pButton = new JButton("P");
			pButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					pButton.setBackground(Color.ORANGE);
					chosenLetter = 'P';
				}
			});
			pButton.setBounds(10, 158, 47, 38);
			panel.add(pButton);
			letterButtons.add(pButton);
		}
		{
			JButton qButton = new JButton("Q");
			qButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					qButton.setBackground(Color.ORANGE);
					chosenLetter = 'Q';
				}
			});
			qButton.setBounds(67, 158, 47, 38);
			panel.add(qButton);
			letterButtons.add(qButton);
		}
		{
			JButton rButton = new JButton("R");
			rButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					rButton.setBackground(Color.ORANGE);
					chosenLetter = 'R';
				}
			});
			rButton.setBounds(124, 158, 47, 38);
			panel.add(rButton);
			letterButtons.add(rButton);
		}
		{
			JButton sButton = new JButton("S");
			sButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					sButton.setBackground(Color.ORANGE);
					chosenLetter = 'S';
				}
			});
			sButton.setBounds(181, 158, 47, 38);
			panel.add(sButton);
			letterButtons.add(sButton);
		}
		{
			JButton tButton = new JButton("T");
			tButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					tButton.setBackground(Color.ORANGE);
					chosenLetter = 'T';
				}
			});
			tButton.setBounds(238, 158, 47, 38);
			panel.add(tButton);
			letterButtons.add(tButton);
		}
		{
			JButton uButton = new JButton("U");
			uButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					uButton.setBackground(Color.ORANGE);
					chosenLetter = 'U';
				}
			});
			uButton.setBounds(10, 207, 47, 38);
			panel.add(uButton);
			letterButtons.add(uButton);
		}
		{
			JButton vButton = new JButton("V");
			vButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					vButton.setBackground(Color.ORANGE);
					chosenLetter = 'V';
				}
			});
			vButton.setBounds(67, 207, 47, 38);
			panel.add(vButton);
			letterButtons.add(vButton);
		}
		{
			JButton wButton = new JButton("W");
			wButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					wButton.setBackground(Color.ORANGE);
					chosenLetter = 'W';
				}
			});
			wButton.setBounds(124, 207, 47, 38);
			panel.add(wButton);
			letterButtons.add(wButton);
		}
		{
			JButton xButton = new JButton("X");
			xButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					xButton.setBackground(Color.ORANGE);
					chosenLetter = 'X';
				}
			});
			xButton.setBounds(181, 207, 47, 38);
			panel.add(xButton);
			letterButtons.add(xButton);
		}
		{
			JButton yButton = new JButton("Y");
			yButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					yButton.setBackground(Color.ORANGE);
					chosenLetter = 'Y';
				}
			});
			yButton.setBounds(238, 207, 47, 38);
			panel.add(yButton);
			letterButtons.add(yButton);
		}
		{
			JButton zButton = new JButton("Z");
			zButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					clearAllButtonBackgroundColor();
					zButton.setBackground(Color.ORANGE);
					chosenLetter = 'Z';
				}
			});
			zButton.setBounds(10, 256, 47, 38);
			panel.add(zButton);
			letterButtons.add(zButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton swapButton = new JButton("Swap");
				swapButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						if(chosenLetter != '0')
						{
							Bag.swapBlankTile(chosenLetter);
							dispose();
						}
					}
				});
				swapButton.setActionCommand("OK");
				buttonPane.add(swapButton);
				getRootPane().setDefaultButton(swapButton);
			}
		}
	
	}
	
	public void clearAllButtonBackgroundColor()
	{
		for(int i = 0; i < letterButtons.size(); i++)
		{
			JButton currentButton = letterButtons.get(i);
			currentButton.setBackground(null);
		}
	}
}
