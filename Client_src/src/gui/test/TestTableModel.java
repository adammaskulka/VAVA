package gui.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.AbstractTableModel;


import entity.Testy;

public class TestTableModel extends AbstractTableModel {
	private List<Testy> testy = new ArrayList<Testy>();
	

	 
	  //private List<Testy> tests = testServis.findAll();
	  
	 
	  
	  ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	  private String[] columns = {rb.getString("gui.test.date"), 
				rb.getString("gui.test.referee"), rb.getString("gui.test.event") };

	  
	  
	  	  
	  public TestTableModel(){
	    super();
	    
	    
	    
	    //System.out.println(columns[0]);
	  }
	  
	  

	  // Number of column of your table
	  public int getColumnCount() {
	    return 3 ;
	  }
	  
	  public void setData(List<Testy> databaza) {
			this.testy = databaza;
		}

	  // Number of row of your table
	  public int getRowCount() {
		 // System.out.println("table "+testy.size());
	    return testy.size();
	  }

	  // The object to render in a cell
	  public Object getValueAt(int row, int col) {
	    Testy club = testy.get(row);
	    switch(col) {
	      case 0: return club.getExamdate();
	      case 1: return club.getRozhodcovia().getSurname();
	      case 2: return club.getEvent();
	      // to complete here...
	      default: return null;
	    }
	  }

	  public String getColumnName(int column) {
			// TODO Auto-generated method stub
		  //System.out.println(column);
			return columns[column];
		}
	 
}
