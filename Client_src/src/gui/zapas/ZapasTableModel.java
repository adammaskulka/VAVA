package gui.zapas;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;

import entity.Zapasy;

public class ZapasTableModel extends AbstractTableModel {
	
	private List<Zapasy> databaza;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	private String[] colNames = {rb.getString("gui.zapas.tabDate"), 
			rb.getString("gui.zapas.tabHome"),
			rb.getString("gui.zapas.tabAway"),rb.getString("gui.zapas.tabHR"), 
			rb.getString("gui.zapas.tabHR") ,rb.getString("gui.zapas.tabCR"),
			rb.getString("gui.zapas.tabCR") ,rb.getString("gui.zapas.tabStadium") };
	
	
	
	List<Color> rowColours = Arrays.asList(
	        Color.RED,
	        Color.GREEN,
	        Color.CYAN
	    );

	    public void setRowColour(int row, Color c) {
	        rowColours.set(row, c);
	        fireTableRowsUpdated(row, row);
	    }

	    public Color getRowColour(int row) {
	        return rowColours.get(row);
	    }
	
	public ZapasTableModel() {
		
	}
	
	
	
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 8;
	}

	public void setData(List<Zapasy> databaza) {
		this.databaza = databaza;
	}
	@Override
	public int getRowCount() {
		//return databaza.size();
		return databaza.size();
	}

	

	
	public Object getValueAt(int riadok, int stlpec) {
		Zapasy rozhodca = databaza.get(riadok);
		
		switch(stlpec) {
		case 0: return rozhodca.getDate().toGMTString().substring(0,11);
		case 1: return rozhodca.getTimyByHometeam().getName();
		case 2: return rozhodca.getTimyByAwayteam().getName();
		case 3:	return rozhodca.getRozhodcoviaByHr1().getSurname();
		case 4: if(rozhodca.getRozhodcoviaByHr2() == null)
					return null;
				else 
					return rozhodca.getRozhodcoviaByHr2().getSurname();
		case 5: if(rozhodca.getRozhodcoviaByCr1() == null)
					return null;
				else
					return rozhodca.getRozhodcoviaByCr1().getSurname();
		case 6: if(rozhodca.getRozhodcoviaByCr2() == null)
					return null;
				else 
					return rozhodca.getRozhodcoviaByCr2().getSurname();
		case 7: return rozhodca.getStadiony().getName();
		
		}
		fireTableRowsUpdated(riadok, stlpec);
		return null;
	}

}
