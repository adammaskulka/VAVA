package gui.zapas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class ZapasStatistika extends JPanel{
	private JTextField txtMatchNumb;
	private JTextField txtMostStadiums;
	private JTextField txtMostTeams;
	private JTextField txtMostRef;
	private JTextField txtMostLinesman;
	private JTextField txtMostTimekeeper;
	
	private JTextField txtStadiumNumb;
	private JTextField txtTeamNumb;
	private JTextField txtRefereeNumb;
	private JTextField txtLinesmanNumb;
	private JTextField txtTimekeeperNumb;
	
	public ZapasStatistika() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{67, 75, 59, 56, 96, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		JLabel lblNumberOfMatches = new JLabel("Number of matches");
		GridBagConstraints gbc_lblNumberOfMatches = new GridBagConstraints();
		gbc_lblNumberOfMatches.anchor = GridBagConstraints.EAST;
		gbc_lblNumberOfMatches.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfMatches.gridx = 1;
		gbc_lblNumberOfMatches.gridy = 2;
		add(lblNumberOfMatches, gbc_lblNumberOfMatches);
		
		txtMatchNumb = new JTextField();
		txtMatchNumb.setEditable(false);
		GridBagConstraints gbc_txtMatchNumb = new GridBagConstraints();
		gbc_txtMatchNumb.insets = new Insets(0, 0, 5, 5);
		gbc_txtMatchNumb.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMatchNumb.gridx = 2;
		gbc_txtMatchNumb.gridy = 2;
		add(txtMatchNumb, gbc_txtMatchNumb);
		txtMatchNumb.setColumns(10);
		
		JLabel lblMostMatchesAt = new JLabel("Most matches at stadium");
		GridBagConstraints gbc_lblMostMatchesAt = new GridBagConstraints();
		gbc_lblMostMatchesAt.anchor = GridBagConstraints.EAST;
		gbc_lblMostMatchesAt.insets = new Insets(0, 0, 5, 5);
		gbc_lblMostMatchesAt.gridx = 1;
		gbc_lblMostMatchesAt.gridy = 3;
		add(lblMostMatchesAt, gbc_lblMostMatchesAt);
		
		txtMostStadiums = new JTextField();
		txtMostStadiums.setEditable(false);
		GridBagConstraints gbc_txtMostStadiums = new GridBagConstraints();
		gbc_txtMostStadiums.insets = new Insets(0, 0, 5, 5);
		gbc_txtMostStadiums.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMostStadiums.gridx = 2;
		gbc_txtMostStadiums.gridy = 3;
		add(txtMostStadiums, gbc_txtMostStadiums);
		txtMostStadiums.setColumns(10);
		
		txtStadiumNumb = new JTextField();
		txtStadiumNumb.setEditable(false);
		GridBagConstraints gbc_txtStadiumNumb = new GridBagConstraints();
		gbc_txtStadiumNumb.insets = new Insets(0, 0, 5, 5);
		gbc_txtStadiumNumb.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStadiumNumb.gridx = 3;
		gbc_txtStadiumNumb.gridy = 3;
		add(txtStadiumNumb, gbc_txtStadiumNumb);
		txtStadiumNumb.setColumns(10);
		
		JLabel lblMostTeamsIn = new JLabel("Most team in matches");
		GridBagConstraints gbc_lblMostTeamsIn = new GridBagConstraints();
		gbc_lblMostTeamsIn.anchor = GridBagConstraints.EAST;
		gbc_lblMostTeamsIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblMostTeamsIn.gridx = 1;
		gbc_lblMostTeamsIn.gridy = 4;
		add(lblMostTeamsIn, gbc_lblMostTeamsIn);
		
		txtMostTeams = new JTextField();
		txtMostTeams.setEditable(false);
		GridBagConstraints gbc_txtMostTeams = new GridBagConstraints();
		gbc_txtMostTeams.insets = new Insets(0, 0, 5, 5);
		gbc_txtMostTeams.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMostTeams.gridx = 2;
		gbc_txtMostTeams.gridy = 4;
		add(txtMostTeams, gbc_txtMostTeams);
		txtMostTeams.setColumns(10);
		
		txtTeamNumb = new JTextField();
		txtTeamNumb.setEditable(false);
		txtTeamNumb.setText("");
		GridBagConstraints gbc_txtTeamNumb = new GridBagConstraints();
		gbc_txtTeamNumb.insets = new Insets(0, 0, 5, 5);
		gbc_txtTeamNumb.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTeamNumb.gridx = 3;
		gbc_txtTeamNumb.gridy = 4;
		add(txtTeamNumb, gbc_txtTeamNumb);
		txtTeamNumb.setColumns(10);
		
		JLabel lblMostRefereeIn = new JLabel("Most referee in matches");
		GridBagConstraints gbc_lblMostRefereeIn = new GridBagConstraints();
		gbc_lblMostRefereeIn.anchor = GridBagConstraints.EAST;
		gbc_lblMostRefereeIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblMostRefereeIn.gridx = 1;
		gbc_lblMostRefereeIn.gridy = 5;
		add(lblMostRefereeIn, gbc_lblMostRefereeIn);
		
		txtMostRef = new JTextField();
		txtMostRef.setEditable(false);
		GridBagConstraints gbc_txtMostRef = new GridBagConstraints();
		gbc_txtMostRef.insets = new Insets(0, 0, 5, 5);
		gbc_txtMostRef.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMostRef.gridx = 2;
		gbc_txtMostRef.gridy = 5;
		add(txtMostRef, gbc_txtMostRef);
		txtMostRef.setColumns(10);
		
		txtRefereeNumb = new JTextField();
		txtRefereeNumb.setEditable(false);
		txtRefereeNumb.setText("");
		GridBagConstraints gbc_txtRefereeNumb = new GridBagConstraints();
		gbc_txtRefereeNumb.anchor = GridBagConstraints.NORTH;
		gbc_txtRefereeNumb.insets = new Insets(0, 0, 5, 5);
		gbc_txtRefereeNumb.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRefereeNumb.gridx = 3;
		gbc_txtRefereeNumb.gridy = 5;
		add(txtRefereeNumb, gbc_txtRefereeNumb);
		txtRefereeNumb.setColumns(10);
		
		JLabel lblMostLinesmanIn = new JLabel("Most linesman in matches");
		GridBagConstraints gbc_lblMostLinesmanIn = new GridBagConstraints();
		gbc_lblMostLinesmanIn.anchor = GridBagConstraints.EAST;
		gbc_lblMostLinesmanIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblMostLinesmanIn.gridx = 1;
		gbc_lblMostLinesmanIn.gridy = 6;
		add(lblMostLinesmanIn, gbc_lblMostLinesmanIn);
		
		txtMostLinesman = new JTextField();
		txtMostLinesman.setEditable(false);
		GridBagConstraints gbc_txtMostLinesman = new GridBagConstraints();
		gbc_txtMostLinesman.insets = new Insets(0, 0, 5, 5);
		gbc_txtMostLinesman.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMostLinesman.gridx = 2;
		gbc_txtMostLinesman.gridy = 6;
		add(txtMostLinesman, gbc_txtMostLinesman);
		txtMostLinesman.setColumns(10);
		
		txtLinesmanNumb = new JTextField();
		txtLinesmanNumb.setEditable(false);
		GridBagConstraints gbc_txtLinesmanNumb = new GridBagConstraints();
		gbc_txtLinesmanNumb.insets = new Insets(0, 0, 5, 5);
		gbc_txtLinesmanNumb.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLinesmanNumb.gridx = 3;
		gbc_txtLinesmanNumb.gridy = 6;
		add(txtLinesmanNumb, gbc_txtLinesmanNumb);
		txtLinesmanNumb.setColumns(10);
		
		JLabel lblMostTimekeeperIn = new JLabel("Most timekeeper in matches");
		GridBagConstraints gbc_lblMostTimekeeperIn = new GridBagConstraints();
		gbc_lblMostTimekeeperIn.anchor = GridBagConstraints.EAST;
		gbc_lblMostTimekeeperIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblMostTimekeeperIn.gridx = 1;
		gbc_lblMostTimekeeperIn.gridy = 7;
		add(lblMostTimekeeperIn, gbc_lblMostTimekeeperIn);
		
		txtMostTimekeeper = new JTextField();
		txtMostTimekeeper.setEditable(false);
		GridBagConstraints gbc_txtMostTimekeeper = new GridBagConstraints();
		gbc_txtMostTimekeeper.insets = new Insets(0, 0, 5, 5);
		gbc_txtMostTimekeeper.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMostTimekeeper.gridx = 2;
		gbc_txtMostTimekeeper.gridy = 7;
		add(txtMostTimekeeper, gbc_txtMostTimekeeper);
		txtMostTimekeeper.setColumns(10);
		
		
		
		txtTimekeeperNumb = new JTextField();
		txtTimekeeperNumb.setEditable(false);
		txtTimekeeperNumb.setText("");
		GridBagConstraints gbc_txtTimekeeperNumb = new GridBagConstraints();
		gbc_txtTimekeeperNumb.insets = new Insets(0, 0, 5, 5);
		gbc_txtTimekeeperNumb.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTimekeeperNumb.gridx = 3;
		gbc_txtTimekeeperNumb.gridy = 7;
		add(txtTimekeeperNumb, gbc_txtTimekeeperNumb);
		txtTimekeeperNumb.setColumns(10);
		
		/*txtMatchNumb.setText(String.valueOf(zapasServis.CountMatches()));
		txtStadiumNumb.setText(String.valueOf(zapasServis.CountStadiums()));
		txtMostStadiums.setText(zapasServis.MostStadium());
		txtTeamNumb.setText(String.valueOf(zapasServis.CountTeams()));
		txtMostTeams.setText(zapasServis.MostTeam());
		txtMostRef.setText(zapasServis.MostHR());
		txtRefereeNumb.setText(String.valueOf(zapasServis.CountHRs()));
		txtMostLinesman.setText(zapasServis.MostCR());
		txtLinesmanNumb.setText(String.valueOf(zapasServis.CountCRs()));
		txtMostTimekeeper.setText(zapasServis.MostTimekeepers());
		txtTimekeeperNumb.setText(String.valueOf(zapasServis.CountTimekeepers()));*/
	}

}
