package scrabble;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

//Main class that creates the GUI and connects object classes
public class Main
{

	private JFrame frame;
	JButton[][] cellButtons; //15x15 button array representing cells in board
	JButton[] rackButtons; //7 buttons for each tile in player's rack
	
	JPanel boardPanel; //panel to hold cellButtons array or board of cells
	JPanel playerPanel; //panel to hold rack panel of tiles to play + player options in game such as skip turn, play tiles inserted into board, etc
	JPanel rackPanel; //panel to hold only tiles ready to play
	
	Board board;
	Bag bag;
	Player player;
	Engine engine;
	
	private JPanel logoPanel;
	private JLabel logoLabel;
	private JLabel scoreLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
					main.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		createWindow();
		createBoardPanel();
		createPlayerPanel();
		createRackPanel();
		
		board = new Board();
		player = new Player();
		engine = new Engine(player,board);
		bag = new Bag();
		
		//create and insert cell buttons into board panel, then connect cell buttons to actual cells in matrix
		createCellButtons(board.cellMatrix);
		
		givePlayerStartingTiles();
		
		createButtonsForPlayerRack();
		
		updatePlayerRackGUI();
		
	}
	
	public void newGame()
	{
		board = new Board();
		player = new Player();
		engine = new Engine(player, board);
		bag = new Bag();
	}
	
	//CREATING WINDOW AND PANELS - START
	
	private void createWindow()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 866, 802);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//create window for all panels
	}
	
	//create Scrabble board panel for cell button matrix that will be connected to actual cell matrix
	private void createBoardPanel()
	{
		boardPanel = new JPanel();
		boardPanel.setBounds(10, 63, 831, 541);
		frame.getContentPane().add(boardPanel);
		boardPanel.setLayout(new GridLayout(15, 15, 0, 0));
	}
	
	//create player panel that shows hold rack panel, has play/pass/shuffle tiles/undo buttons, etc
	private void createPlayerPanel()
	{
		playerPanel = new JPanel();
		playerPanel.setBounds(10, 615, 831, 143);
		frame.getContentPane().add(playerPanel);
		playerPanel.setLayout(null);
		{
			logoPanel = new JPanel();
			logoPanel.setBounds(10, 11, 831, 47);
			frame.getContentPane().add(logoPanel);
			logoPanel.setLayout(new BorderLayout(0, 0));
			{
				logoLabel = new JLabel("S  C  R  A  B  B  L  E");
				logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
				logoLabel.setFont(new Font("Calibri Light", Font.BOLD, 22));
				logoPanel.add(logoLabel);
			}
		}
	}
	
	//create rack panel for player's tiles
	private void createRackPanel()
	{
		rackPanel = new JPanel();
		rackPanel.setBounds(165, 60, 500, 61);
		playerPanel.add(rackPanel);
		rackPanel.setLayout(new GridLayout(0, 7, 0, 0));
		
		JPanel actionPanel = new JPanel();
		actionPanel.setBounds(165, 12, 500, 37);
		playerPanel.add(actionPanel);
		actionPanel.setLayout(new GridLayout(1, 4, 0, 0));
		
		//button to shuffle tile pieces in player's rack
		JButton shuffleButton = new JButton("Shuffle");
		actionPanel.add(shuffleButton);
		shuffleButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				player.shuffleRack();
				player.organizeRack();
				updatePlayerRackGUI();
			}
		});
		
		//button to undo tile placement on board, cannot undo submitted moves
		JButton undoButton = new JButton("Undo");
		actionPanel.add(undoButton);
		undoButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//check if stack containing previous move (tile, tile index in player rack, rack button, cell, and cell button all exists to undo previous move as whole. Also denies the ability to undo any move if a tile from rack is currently selected
				if(engine.recentlyPlayedTileStack.size() > 0  && engine.recentlyPlayedCellStack.size() > 0 && engine.recentlyPlayedCellButtonStack.size() > 0 && engine.rackTileSelected == null)
				{
					Tile recentTilePlayed = engine.recentlyPlayedTileStack.pop();
					
					//Return recently played tile rack to player's rack
					player.addTileToRack(recentTilePlayed);
					
					//Set recently cell played to null
					Cell recentCell = engine.recentlyPlayedCellStack.pop();
					recentCell.setTile(null);
					
					//undo recent cell button played GUI
					JButton recentCellButton = engine.recentlyPlayedCellButtonStack.pop();
					recentCellButton.setText(recentCell.getBonus());
					recentCellButton.setBackground(Color.LIGHT_GRAY);
					
					player.organizeRack();
					updatePlayerRackGUI();
				}
			}
		});
		
		
		JButton submitButton = new JButton("Submit");
		actionPanel.add(submitButton);
		submitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(engine.checkBoard() == true)
				{
					System.out.println("Move passed");
					scoreLabel.setText(player.getScore()); //Updates player's score on GUI
					
					while(true)
					{
						if(givePlayerANewTile() == false)
						{
							break;
						}
					}
					
					updatePlayerRackGUI();
				}
				else
				{
					System.out.println("Move denied");
				}
			}
		});
		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Help help = new Help();
				help.setVisible(true);
			}
		});
		helpButton.setBounds(709, 39, 65, 37);
		playerPanel.add(helpButton);
		
		scoreLabel = new JLabel("0");
		scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		scoreLabel.setBounds(10, 39, 65, 53);
		playerPanel.add(scoreLabel);
		
		JLabel lblNewLabel = new JLabel("pts.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(85, 48, 43, 42);
		playerPanel.add(lblNewLabel);
		
	}
	
	
	//CREATING WINDOW AND PANELS - END
		
	//CREATING BUTTONS - START
	
	//creating, connecting, and labeling cell buttons
	private void createCellButtons(Cell[][] cellMatrix)
	{
		cellButtons = new JButton[15][15];
		
		for(int i = 0; i < 15; i++)
		{
			for(int j = 0; j < 15; j++)
			{
				//creating then adding cell buttons to board panel
				JButton cellButton = new JButton();
				cellButton.setBackground(Color.LIGHT_GRAY);
				Cell currentCell = cellMatrix[i][j];
				
				//connecting cells in cell matrix to their buttons
				cellButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						if(engine.rackTileSelected != null && currentCell.getTile() == null)
						{
							if(engine.rackTileSelected.getLetter() == "-")
							{
								chooseTileDialog ct = new chooseTileDialog();
								ct.setModal(true);
								ct.setVisible(true);
								currentCell.setTile(Bag.swappedBlankTile); //set cell matched with board button pressed to store chosen swapped blank tile
								cellButton.setText(Bag.swappedBlankTile.getLetter()); //set cell button text by chosen swapped blank tile

							}
							else
							{
								currentCell.setTile(engine.rackTileSelected); //set cell matched with board button pressed to store current tile from rack chosen
								cellButton.setText(engine.rackTileSelected.getLetter()); //set cell button text by tile from rack played
							}
							
							cellButton.setBackground(Color.ORANGE); 
							engine.rackTileSelected = null;
							engine.recentlyPlayedCellStack.push(currentCell); //push cell played into stack for undo-ing purposes
							engine.recentlyPlayedCellButtonStack.push(cellButton);
						}
					}
				});
				
				cellButtons[i][j] = cellButton;
				boardPanel.add(cellButton);
					
				//labeling cell buttons with bonus label (Triple/double word/letter on specific cell buttons)
				if(cellMatrix[i][j].getBonus() != null)
				{
					cellButtons[i][j].setText(cellMatrix[i][j].getBonus());
				}
			}
		}
		
	}
	
	//create button for each of 7 tiles for player's rack
	private void createButtonsForPlayerRack()
	{
		rackButtons = new JButton[7];
			
		//rack button 1
		JButton rackButton1 = new JButton();
		rackButton1.setBackground(Color.ORANGE);
		rackButton1.setText(player.getRack()[0].getLetter()); //set tile's letter and points to show on button
		
		rackButton1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//check if player has no tiles from rack selected before selecting another tile from rack. Also update rack tile button GUI
				if(engine.rackTileSelected == null && rackButton1.getText() != "")
				{
					engine.rackTileSelected = player.getAndRemoveFromRackAt(0); //to transfer tile from player's rack onto board
					engine.recentlyPlayedTileStack.push(engine.rackTileSelected); //for undo purposes

					rackButton1.setBackground(Color.LIGHT_GRAY); //update rack tile button GUI
					rackButton1.setText(""); //update rack tile button GUI
				
				}
				
			}
		});
		
		rackButtons[0] = rackButton1;
		rackPanel.add(rackButton1);
		
		//rack button 2
		JButton rackButton2 = new JButton();
		rackButton2.setBackground(Color.ORANGE);
		rackButton2.setText(player.getRack()[1].getLetter());
		
		rackButton2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(engine.rackTileSelected == null && rackButton2.getText() != "")
				{
					engine.rackTileSelected = player.getAndRemoveFromRackAt(1);
					engine.recentlyPlayedTileStack.push(engine.rackTileSelected);
				
					rackButton2.setBackground(Color.LIGHT_GRAY);
					rackButton2.setText("");
				
				}
				
			}
		});
		
		rackButtons[1] = rackButton2;
		rackPanel.add(rackButton2);
		
		
		//rack button 3
		JButton rackButton3 = new JButton();
		rackButton3.setBackground(Color.ORANGE);
		rackButton3.setText(player.getRack()[2].getLetter());
		
		rackButton3.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(engine.rackTileSelected == null && rackButton3.getText() != "")
				{
					engine.rackTileSelected = player.getAndRemoveFromRackAt(2);
					engine.recentlyPlayedTileStack.push(engine.rackTileSelected);
		
					rackButton3.setBackground(Color.LIGHT_GRAY);
					rackButton3.setText("");
					
				}
				
			}
		});
		
		rackButtons[2] = rackButton3;
		rackPanel.add(rackButton3);
		
		//rack button 4
		JButton rackButton4 = new JButton();
		rackButton4.setBackground(Color.ORANGE);
		rackButton4.setText(player.getRack()[3].getLetter());
		
		rackButton4.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(engine.rackTileSelected == null && rackButton4.getText() != "")
				{
					engine.rackTileSelected = player.getAndRemoveFromRackAt(3);
					engine.recentlyPlayedTileStack.push(engine.rackTileSelected);
				
					rackButton4.setBackground(Color.LIGHT_GRAY);
					rackButton4.setText("");
				
				}
				
			}
		});
		
		rackButtons[3] = rackButton4;
		rackPanel.add(rackButton4);
		
		//rack button 5
		JButton rackButton5 = new JButton();
		rackButton5.setBackground(Color.ORANGE);
		rackButton5.setText(player.getRack()[4].getLetter());
		
		rackButton5.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(engine.rackTileSelected == null && rackButton5.getText() != "")
				{
					engine.rackTileSelected = player.getAndRemoveFromRackAt(4);
					engine.recentlyPlayedTileStack.push(engine.rackTileSelected);
				
					rackButton5.setBackground(Color.LIGHT_GRAY);
					rackButton5.setText("");
					
				}
				
			}
		});
		
		rackButtons[4] = rackButton5;
		rackPanel.add(rackButton5);
		
		//rack button 6
		JButton rackButton6 = new JButton();
		rackButton6.setBackground(Color.ORANGE);
		rackButton6.setText(player.getRack()[5].getLetter());
		
		rackButton6.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(engine.rackTileSelected == null && rackButton6.getText() != "")
				{
					engine.rackTileSelected = player.getAndRemoveFromRackAt(5);
					engine.recentlyPlayedTileStack.push(engine.rackTileSelected);
			
					rackButton6.setBackground(Color.LIGHT_GRAY);
					rackButton6.setText("");
					
				}
				
			}
		});
		
		rackButtons[5] = rackButton6;
		rackPanel.add(rackButton6);
		
		//rack button 7
		JButton rackButton7 = new JButton();
		rackButton7.setBackground(Color.ORANGE);
		rackButton7.setText(player.getRack()[6].getLetter());
		
		rackButton7.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(engine.rackTileSelected == null && rackButton7.getText() != "")
				{
					engine.rackTileSelected = player.getAndRemoveFromRackAt(6);
					engine.recentlyPlayedTileStack.push(engine.rackTileSelected);
				
					rackButton7.setBackground(Color.LIGHT_GRAY);
					rackButton7.setText("");
				
				}
				
			}
		});
		
		rackButtons[6] = rackButton7;
		rackPanel.add(rackButton7);
	}
	
	
	//CREATING BUTTONS - END
	
	//UPDATING BUTTONS/DATA STRUCTURES - START
	
	//Add a tile from bag to player's rack if possible
	private boolean givePlayerANewTile()
	{
		//cannot add tiles to player's rack if bag is empty or rack is full (rack has more than 7 elements)
		if(bag.bagIsEmpty() || (player.getRackSize() == 7))
		{
			return false;
		}
		
		Tile newTile = bag.getNextTile();
		player.addTileToRack(newTile);
		return true;
	}
	
	//Add 7 tiles from bag to player's rack at start of game
	private void givePlayerStartingTiles()
	{
		for(int i = 0; i < 7; i++)
		{
			givePlayerANewTile();
		}
	}
	
	private void updatePlayerRackGUI() //places tiles pieces found directly in player's rack onto rack button GUI
	{
		for(int i = 0; i < 7; i++)
		{
			if(player.getRack()[i] != null)
			{
				rackButtons[i].setText(player.getRack()[i].getLetter());
				rackButtons[i].setBackground(Color.ORANGE);
			}
			else if(player.getRack()[i] == null)
			{
				rackButtons[i].setText("");
				rackButtons[i].setBackground(Color.LIGHT_GRAY);
			}
			
		}
	}
}
