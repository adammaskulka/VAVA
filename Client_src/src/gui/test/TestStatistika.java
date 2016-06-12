package gui.test;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


import entity.Rozhodcovia;

public class TestStatistika extends JFrame {
	private JTextField txtNumb;
	private JTextField txtAvgRules;
	private JTextField txtAvgCooper;
	private JTextField txtBestRules;
	private JTextField txtBestCooper;
	private JTextField txtNumbTotal;
	private JTextField txtTotalRulesAvg;
	private JTextField txtTotalCooperAvg;
	private JTextField txtTotalBestRules;
	private JTextField txtTotalBestCooper;
	private JTextField txtRulesDif;
	private JTextField txtCooperDif;
	private JTextField txtTotalRulesDif;
	private JTextField txtTotalCooperDif;
	
	
	
	public TestStatistika(String ref,Date start,Date end) {
		
	//	Rozhodcovia rozhodca = (Rozhodcovia) rozhodcaServis.findByName(ref);
		
		setSize(784,332);
		
		/*setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{42, 53, 55, 67, 57, 68, 68, 77, 51, 0};
		gridBagLayout.rowHeights = new int[]{33, 30, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
			
			JLabel lblDifference = new JLabel("Difference");
			GridBagConstraints gbc_lblDifference = new GridBagConstraints();
			gbc_lblDifference.insets = new Insets(0, 0, 5, 5);
			gbc_lblDifference.anchor = GridBagConstraints.NORTHWEST;
			gbc_lblDifference.gridx = 7;
			gbc_lblDifference.gridy = 0;
			getContentPane().add(lblDifference, gbc_lblDifference);
		
		JLabel lblNumberOfTests = new JLabel("Number of tests");
		GridBagConstraints gbc_lblNumberOfTests = new GridBagConstraints();
		gbc_lblNumberOfTests.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumberOfTests.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNumberOfTests.gridx = 1;
		gbc_lblNumberOfTests.gridy = 1;
		getContentPane().add(lblNumberOfTests, gbc_lblNumberOfTests);
		
		txtNumb = new JTextField();
		txtNumb.setEditable(false);
		txtNumb.setColumns(10);
		
		txtNumb.setText(String.valueOf(testServis.CountRefTests(rozhodca,start,end)));
		GridBagConstraints gbc_txtNumb = new GridBagConstraints();
		gbc_txtNumb.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNumb.insets = new Insets(0, 0, 5, 5);
		gbc_txtNumb.anchor = GridBagConstraints.NORTH;
		gbc_txtNumb.gridx = 2;
		gbc_txtNumb.gridy = 1;
		getContentPane().add(txtNumb, gbc_txtNumb);
		
		JLabel lblTotalNumberOf = new JLabel("Total number of tests");
		GridBagConstraints gbc_lblTotalNumberOf = new GridBagConstraints();
		gbc_lblTotalNumberOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalNumberOf.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTotalNumberOf.gridx = 4;
		gbc_lblTotalNumberOf.gridy = 1;
		getContentPane().add(lblTotalNumberOf, gbc_lblTotalNumberOf);
		
		txtNumbTotal = new JTextField();
		txtNumbTotal.setEditable(false);
		txtNumbTotal.setColumns(10);
		txtNumbTotal.setText(String.valueOf(testServis.CountTests()));
		
		GridBagConstraints gbc_txtNumbTotal = new GridBagConstraints();
		gbc_txtNumbTotal.fill = GridBagConstraints.BOTH;
		gbc_txtNumbTotal.insets = new Insets(0, 0, 5, 5);
		gbc_txtNumbTotal.gridx = 5;
		gbc_txtNumbTotal.gridy = 1;
		getContentPane().add(txtNumbTotal, gbc_txtNumbTotal);
		
		JLabel lblBestRulesResult = new JLabel("Best rules result");
		GridBagConstraints gbc_lblBestRulesResult = new GridBagConstraints();
		gbc_lblBestRulesResult.insets = new Insets(0, 0, 5, 5);
		gbc_lblBestRulesResult.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblBestRulesResult.gridx = 1;
		gbc_lblBestRulesResult.gridy = 2;
		getContentPane().add(lblBestRulesResult, gbc_lblBestRulesResult);
		
		txtBestRules = new JTextField();
		txtBestRules.setEditable(false);
		txtBestRules.setColumns(10);
		txtBestRules.setText(String.valueOf(testServis.BestRefRules(rozhodca,start,end)));
		GridBagConstraints gbc_txtBestRules = new GridBagConstraints();
		gbc_txtBestRules.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBestRules.insets = new Insets(0, 0, 5, 5);
		gbc_txtBestRules.anchor = GridBagConstraints.NORTH;
		gbc_txtBestRules.gridx = 2;
		gbc_txtBestRules.gridy = 2;
		getContentPane().add(txtBestRules, gbc_txtBestRules);
		
		JLabel lblTotalBestRules = new JLabel("Total best rules");
		GridBagConstraints gbc_lblTotalBestRules = new GridBagConstraints();
		gbc_lblTotalBestRules.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalBestRules.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTotalBestRules.gridx = 4;
		gbc_lblTotalBestRules.gridy = 2;
		getContentPane().add(lblTotalBestRules, gbc_lblTotalBestRules);
		
		txtTotalBestRules = new JTextField();
		txtTotalBestRules.setEditable(false);
		txtTotalBestRules.setText("");
		txtTotalBestRules.setColumns(10);
		txtTotalBestRules.setText(String.valueOf(testServis.TotalBestRules()));
		GridBagConstraints gbc_txtTotalBestRules = new GridBagConstraints();
		gbc_txtTotalBestRules.fill = GridBagConstraints.BOTH;
		gbc_txtTotalBestRules.insets = new Insets(0, 0, 5, 5);
		gbc_txtTotalBestRules.gridx = 5;
		gbc_txtTotalBestRules.gridy = 2;
		getContentPane().add(txtTotalBestRules, gbc_txtTotalBestRules);
		
		txtTotalRulesDif = new JTextField();
		txtTotalRulesDif.setEditable(false);
		txtTotalRulesDif.setText("");
		txtTotalRulesDif.setColumns(10);
		txtTotalRulesDif.setText(String.valueOf(testServis.TotalDifferenceRefRules(rozhodca,start,end)));
		GridBagConstraints gbc_txtTotalRulesDif = new GridBagConstraints();
		gbc_txtTotalRulesDif.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTotalRulesDif.insets = new Insets(0, 0, 5, 5);
		gbc_txtTotalRulesDif.anchor = GridBagConstraints.NORTH;
		gbc_txtTotalRulesDif.gridx = 7;
		gbc_txtTotalRulesDif.gridy = 2;
		getContentPane().add(txtTotalRulesDif, gbc_txtTotalRulesDif);
		
		JLabel lblBestCooperResult = new JLabel("Best cooper result");
		GridBagConstraints gbc_lblBestCooperResult = new GridBagConstraints();
		gbc_lblBestCooperResult.insets = new Insets(0, 0, 5, 5);
		gbc_lblBestCooperResult.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblBestCooperResult.gridx = 1;
		gbc_lblBestCooperResult.gridy = 3;
		getContentPane().add(lblBestCooperResult, gbc_lblBestCooperResult);
		
		txtBestCooper = new JTextField();
		txtBestCooper.setEditable(false);
		txtBestCooper.setColumns(10);
		txtBestCooper.setText(String.valueOf(testServis.BestRefCooper(rozhodca,start,end)));
		GridBagConstraints gbc_txtBestCooper = new GridBagConstraints();
		gbc_txtBestCooper.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBestCooper.insets = new Insets(0, 0, 5, 5);
		gbc_txtBestCooper.anchor = GridBagConstraints.NORTH;
		gbc_txtBestCooper.gridx = 2;
		gbc_txtBestCooper.gridy = 3;
		getContentPane().add(txtBestCooper, gbc_txtBestCooper);
		
		JLabel lblTotalBestCooper = new JLabel("Total best cooper");
		GridBagConstraints gbc_lblTotalBestCooper = new GridBagConstraints();
		gbc_lblTotalBestCooper.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalBestCooper.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTotalBestCooper.gridx = 4;
		gbc_lblTotalBestCooper.gridy = 3;
		getContentPane().add(lblTotalBestCooper, gbc_lblTotalBestCooper);
		
		txtTotalBestCooper = new JTextField();
		txtTotalBestCooper.setEditable(false);
		txtTotalBestCooper.setColumns(10);
		txtTotalBestCooper.setText(String.valueOf(testServis.TotalBestCooper()));
		GridBagConstraints gbc_txtTotalBestCooper = new GridBagConstraints();
		gbc_txtTotalBestCooper.fill = GridBagConstraints.BOTH;
		gbc_txtTotalBestCooper.insets = new Insets(0, 0, 5, 5);
		gbc_txtTotalBestCooper.gridx = 5;
		gbc_txtTotalBestCooper.gridy = 3;
		getContentPane().add(txtTotalBestCooper, gbc_txtTotalBestCooper);
		
		txtTotalCooperDif = new JTextField();
		txtTotalCooperDif.setEditable(false);
		txtTotalCooperDif.setColumns(10);
		txtTotalCooperDif.setText(String.valueOf(testServis.TotalDifferenceRefCoopers(rozhodca,start,end)));
		GridBagConstraints gbc_txtTotalCooperDif = new GridBagConstraints();
		gbc_txtTotalCooperDif.insets = new Insets(0, 0, 5, 5);
		gbc_txtTotalCooperDif.fill = GridBagConstraints.BOTH;
		gbc_txtTotalCooperDif.gridx = 7;
		gbc_txtTotalCooperDif.gridy = 3;
		getContentPane().add(txtTotalCooperDif, gbc_txtTotalCooperDif);
		
		JLabel lblAverageCooperResult = new JLabel("Average cooper result");
		GridBagConstraints gbc_lblAverageCooperResult = new GridBagConstraints();
		gbc_lblAverageCooperResult.insets = new Insets(0, 0, 5, 5);
		gbc_lblAverageCooperResult.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblAverageCooperResult.gridx = 1;
		gbc_lblAverageCooperResult.gridy = 4;
		getContentPane().add(lblAverageCooperResult, gbc_lblAverageCooperResult);
		
		txtAvgCooper = new JTextField();
		txtAvgCooper.setEditable(false);
		txtAvgCooper.setText("");
		txtAvgCooper.setColumns(10);
		txtAvgCooper.setText(String.valueOf(testServis.AvgCooperTests(rozhodca,start,end)));
		GridBagConstraints gbc_txtAvgCooper = new GridBagConstraints();
		gbc_txtAvgCooper.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAvgCooper.insets = new Insets(0, 0, 5, 5);
		gbc_txtAvgCooper.anchor = GridBagConstraints.NORTH;
		gbc_txtAvgCooper.gridx = 2;
		gbc_txtAvgCooper.gridy = 4;
		getContentPane().add(txtAvgCooper, gbc_txtAvgCooper);
		
		JLabel lblTotalCooperAverage = new JLabel("Total cooper average");
		GridBagConstraints gbc_lblTotalCooperAverage = new GridBagConstraints();
		gbc_lblTotalCooperAverage.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalCooperAverage.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTotalCooperAverage.gridx = 4;
		gbc_lblTotalCooperAverage.gridy = 4;
		getContentPane().add(lblTotalCooperAverage, gbc_lblTotalCooperAverage);
		
		txtTotalCooperAvg = new JTextField();
		txtTotalCooperAvg.setEditable(false);
		txtTotalCooperAvg.setText("");
		txtTotalCooperAvg.setColumns(10);
		txtTotalCooperAvg.setText(String.valueOf(testServis.AvgTotalCooperTests()));
		GridBagConstraints gbc_txtTotalCooperAvg = new GridBagConstraints();
		gbc_txtTotalCooperAvg.fill = GridBagConstraints.BOTH;
		gbc_txtTotalCooperAvg.insets = new Insets(0, 0, 5, 5);
		gbc_txtTotalCooperAvg.gridx = 5;
		gbc_txtTotalCooperAvg.gridy = 4;
		getContentPane().add(txtTotalCooperAvg, gbc_txtTotalCooperAvg);
		
		txtCooperDif = new JTextField();
		txtCooperDif.setEditable(false);
		txtCooperDif.setColumns(10);
		txtCooperDif.setText(String.valueOf(testServis.AvgDifferenceRefCoopers(rozhodca,start,end)));
		GridBagConstraints gbc_txtCooperDif = new GridBagConstraints();
		gbc_txtCooperDif.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCooperDif.insets = new Insets(0, 0, 5, 5);
		gbc_txtCooperDif.anchor = GridBagConstraints.NORTH;
		gbc_txtCooperDif.gridx = 7;
		gbc_txtCooperDif.gridy = 4;
		getContentPane().add(txtCooperDif, gbc_txtCooperDif);
		
		JLabel lblAverageRulesResult = new JLabel("Average rules result");
		GridBagConstraints gbc_lblAverageRulesResult = new GridBagConstraints();
		gbc_lblAverageRulesResult.insets = new Insets(0, 0, 5, 5);
		gbc_lblAverageRulesResult.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblAverageRulesResult.gridx = 1;
		gbc_lblAverageRulesResult.gridy = 5;
		getContentPane().add(lblAverageRulesResult, gbc_lblAverageRulesResult);
		
		txtAvgRules = new JTextField();
		txtAvgRules.setEditable(false);
		txtAvgRules.setText("");
		txtAvgRules.setColumns(10);
		txtAvgRules.setText(String.valueOf(testServis.AvgRefTests(rozhodca,start,end)));
		GridBagConstraints gbc_txtAvgRules = new GridBagConstraints();
		gbc_txtAvgRules.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAvgRules.insets = new Insets(0, 0, 5, 5);
		gbc_txtAvgRules.anchor = GridBagConstraints.NORTH;
		gbc_txtAvgRules.gridx = 2;
		gbc_txtAvgRules.gridy = 5;
		getContentPane().add(txtAvgRules, gbc_txtAvgRules);
		
		JLabel lblTotalRulesAverage = new JLabel("Total rules average");
		GridBagConstraints gbc_lblTotalRulesAverage = new GridBagConstraints();
		gbc_lblTotalRulesAverage.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalRulesAverage.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblTotalRulesAverage.gridx = 4;
		gbc_lblTotalRulesAverage.gridy = 5;
		getContentPane().add(lblTotalRulesAverage, gbc_lblTotalRulesAverage);
		
		txtTotalRulesAvg = new JTextField();
		txtTotalRulesAvg.setEditable(false);
		txtTotalRulesAvg.setColumns(10);
		txtTotalRulesAvg.setText(String.valueOf(testServis.AvgTotalRulesTests()));
		GridBagConstraints gbc_txtTotalRulesAvg = new GridBagConstraints();
		gbc_txtTotalRulesAvg.fill = GridBagConstraints.BOTH;
		gbc_txtTotalRulesAvg.insets = new Insets(0, 0, 5, 5);
		gbc_txtTotalRulesAvg.gridx = 5;
		gbc_txtTotalRulesAvg.gridy = 5;
		getContentPane().add(txtTotalRulesAvg, gbc_txtTotalRulesAvg);
		
		txtRulesDif = new JTextField();
		txtRulesDif.setEditable(false);
		txtRulesDif.setColumns(10);
		txtRulesDif.setText(String.valueOf(testServis.AvgDifferenceRefTests(rozhodca,start,end)));
		GridBagConstraints gbc_txtRulesDif = new GridBagConstraints();
		gbc_txtRulesDif.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRulesDif.insets = new Insets(0, 0, 5, 5);
		gbc_txtRulesDif.anchor = GridBagConstraints.NORTH;
		gbc_txtRulesDif.gridx = 7;
		gbc_txtRulesDif.gridy = 5;
		getContentPane().add(txtRulesDif, gbc_txtRulesDif);
		
		*/
		
	}

}
