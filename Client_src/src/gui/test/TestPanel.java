package gui.test;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;


import entity.Rozhodcovia;
import entity.Testy;
import facade.RozhodcaBeanRemote;
import facade.TestBeanRemote;

public class TestPanel extends JPanel {
	
	Context ctx;
	RozhodcaBeanRemote remote;
	TestBeanRemote remoteTest;
	
	private JTextField txtEvent;
	private JTextField txtEvent_1;
	private JTextField txtHeight;
	private JTextField txtWeight;
	private JTextField txtMeno;
	private JTextField txtCooper;
	private JTextField txt40;
	private JTextField txtNote;
	private JTextField txtBreaks;
	private JTextField txtSlope;
	private JTextField txt40back;
	private JTextField txtCrossSmall;
	private JTextField txtCrossBig;
	private JTextField txtRules;
	private JLabel lblReferee;
	private JLabel lblEvent;
	private JLabel lblHeight;
	private JLabel lblWeight;
	private JLabel lblCooper;
	private JLabel lbl40;
	private JLabel lbl40back;
	private JLabel lblSlope;
	private JLabel lblBreaks;
	private JLabel lblNote;
	private JLabel lblCrossSmall;
	private JLabel lblCrossBig;
	private JLabel lblDate;
	private JLabel lblRules;
	private String strCR2;
	private List<String> listCR2 = new ArrayList<String>();
	
	private JButton btnOK;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private NewTestListener newTestListener;
	private JComboBox<String> combobox;
	
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	private static final Logger log = Logger.getLogger(TestPanel.class.getName());
	private JCheckBox checkBox;
	
	public TestPanel() {
		
		try {
			ctx = new InitialContext();
		} catch (Exception e) {
			
			log.log(Level.SEVERE, "InitialContex error",e);
			
		}
		
		
		try {
			remote = (RozhodcaBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/RozhodcaBean!facade.RozhodcaBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Rozhodca remote lookup error",e);
		}
		
		try {
			remoteTest = (TestBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/TestBean!facade.TestBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Test remote lookup error",e);
		}
		
		
		Dimension dim = getPreferredSize();
		dim.width = 200;
		dim.height = 500;
		setMinimumSize(dim);
		
		
		lblReferee = new JLabel(rb.getString("gui.test.referee"));
		lblEvent = new JLabel(rb.getString("gui.test.event"));
		lblHeight = new JLabel(rb.getString("gui.test.height"));
		lblWeight = new JLabel(rb.getString("gui.test.weight"));
		lbl40 = new JLabel(rb.getString("gui.test.40"));
		lbl40back = new JLabel(rb.getString("gui.test.40back"));
		lblCooper = new JLabel(rb.getString("gui.test.cooper"));
		lblSlope = new JLabel(rb.getString("gui.test.slope"));
		lblNote = new JLabel(rb.getString("gui.test.note"));
		lblCrossBig = new JLabel(rb.getString("gui.test.bigCross"));
		lblCrossSmall = new JLabel(rb.getString("gui.test.smallCross"));
		lblBreaks = new JLabel(rb.getString("gui.test.breaks"));
		lblDate = new JLabel(rb.getString("gui.test.date"));
		lblRules = new JLabel(rb.getString("gui.test.rule"));
		
		txtEvent = new JTextField(10);
		txtHeight = new JTextField(5);
		txtWeight = new JTextField(5);
		txtEvent_1 = new JTextField(10);
		txtCooper = new JTextField(5);
		txt40 = new JTextField(5);
		txt40back = new JTextField(5);
		txtNote = new JTextField(15);
		txtBreaks = new JTextField(5);
		txtCrossSmall = new JTextField(5);
		txtCrossBig = new JTextField(5);
		txtRules = new JTextField(5);
		txtSlope = new JTextField(5);
		
		SpinnerModel modelW = new SpinnerNumberModel(75, 0, 150, 1); //(value, min, max, step)
		SpinnerModel modelH = new SpinnerNumberModel(175, 0, 250, 1);
        JSpinner spinnerHeight = new JSpinner(modelH);
        ((JSpinner.NumberEditor) spinnerHeight.getEditor()).getFormat().setMaximumFractionDigits(4);
        JSpinner spinnerWeight = new JSpinner(modelW);
        ((JSpinner.NumberEditor) spinnerWeight.getEditor()).getFormat().setMaximumFractionDigits(4);
		
        Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		//date.setTime(calendar.getTimeInMillis());
        JDateChooser chooser = new JDateChooser();
		
		chooser.setDate(date);
		//dimchooser.height = 500;
		chooser.getLocale();
		
		btnOK = new JButton("OK");
		combobox = new JComboBox<String>();
		
		
		spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		portSpinner = new JSpinner(spinnerModel);
		
		
		List rs = refreshCombo();
		
		
	
		
		
		/*combobox.addItem("");
		combobox.setEditable(true);	
		combobox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyReleased(KeyEvent event) {
		    	
		    	if(event.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
		    		
		    	}
		    	else {
		    	strCR2 = combobox.getEditor().getItem().toString();
		               // System.out.println(surname);
		    	listCR2 = servis.findRef(strCR2);
		    	
		    	combobox.setModel(new DefaultComboBoxModel(listCR2.toArray()));
		    	combobox.setSelectedItem(strCR2);
				
		    	}		   
		    	
		    }
		});*/
		
		
	
		btnOK.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                Date date = chooser.getDate();
                String ref = combobox.getSelectedItem().toString();
                String event = txtEvent.getText();
                String note = txtNote.getText();
                
        		//Integer height = Integer.parseInt(txtHeight.getText());
        		Integer height = (Integer) spinnerHeight.getValue();
        		Integer weight = (Integer) spinnerWeight.getValue();
        		Integer cooper = 0;
        		Float sprint40 = (float) 0;
        		Float sprint40back = (float) 0;
        		Float breaks =(float) 0;
        		Float slope =(float) 0;
        		Float crossbig =(float) 0;
        		Float crosssmall =(float) 0;
        		Integer rules = 0;
        		
        		if(checkBox.isSelected()) {
        			height = 160 + (int)(Math.random() * ((210 - 160) + 1));
        			weight = 70 + (int)(Math.random() * ((105 - 70) + 1));
        			cooper = 1700 + (int)(Math.random() * ((3300 - 2000) + 1));
        			sprint40 = 5 + (float)(Math.random() * ((14 - 5) + 1));
        			sprint40back = 8 + (float)(Math.random() * ((23 - 8) + 1));
        			breaks = 12 + (float)(Math.random() * ((25 - 12) + 1));
        			slope = 17 + (float)(Math.random() * ((33 - 17) + 1));
        			crossbig = 60 + (float)(Math.random() * ((100 - 60) + 1));
        			crosssmall = 20 + (float)(Math.random() * ((40 - 20) + 1));
        			rules = 1 + (int)(Math.random() * ((73 - 1) + 1));
        		}
        		else {
        			
        		
        		if(txtCooper.getText().equals("")) {
        			cooper = 0;
        		}
        		else {
        			 cooper = Integer.parseInt(txtCooper.getText());
        		}
        		if(txt40.getText().equals("")) {
        			 sprint40 = (float) 0;
        		}
        		else {
        			 sprint40 = Float.parseFloat(txt40.getText());
        		}
        		if(txt40back .getText().equals("")) {
        			 sprint40back = (float) 0;
        		}
        		else {
        			 sprint40back = Float.parseFloat(txt40back.getText());
        		}       		
        		 note = txtNote.getText();
        		if(txtBreaks.getText().equals("")) {
        			 breaks =(float) 0;
        		}
        		else {
        			 breaks = Float.parseFloat(txtBreaks.getText());
        		}
        		if(txtSlope .getText().equals("")) {
        			 slope =(float) 0;
        		}
        		else {
        			 slope = Float.parseFloat(txtSlope.getText());
        		}
        		if(txtRules .getText().equals("")) {
        			 rules = 0;
        		}
        		else {
        			rules = Integer.parseInt(txtRules.getText());
        		}
        		if(txtCrossBig .getText().equals("")) {
        			 crossbig =(float) 0;
        		}
        		else {
        			 crossbig = Float.parseFloat(txtCrossBig.getText());
        		}
        		if(txtCrossSmall .getText().equals("")) {
        			 crosssmall =(float) 0;
        		}
        		else {
        			 crosssmall = Float.parseFloat(txtCrossSmall.getText());
        		}
        		
        		}
                
                
                try {
                	
                	if(height<130 || height>230 || weight>160 || weight<40 || rules<0 ||rules>75||cooper<0||sprint40<0||
                			sprint40back<0||crosssmall<0||crossbig<0||breaks<0||slope<0)
                		throw new Exception();
                	
                	Rozhodcovia rozhodca = (Rozhodcovia) remote.findByName(ref);
			     	// int id = tim.getId();
			     	log.log(Level.FINE, "ref loaded"+rozhodca.toString());
		
			     	
					Testy test = new Testy(rozhodca,date,event,height,weight,rules,cooper,sprint40,sprint40back,crosssmall,crossbig,breaks,slope,note);		
			     	
					System.out.println(test.toString());
					
					remoteTest.persist(test);
					
					if(newTestListener != null) {
						newTestListener.testPanelEvent();
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					log.log(Level.SEVERE, "add exam save button fail", e1);
					JOptionPane.showMessageDialog(null, "Not valid input", "Error", JOptionPane.ERROR_MESSAGE);
				}
               
               txtEvent.setText("");
       		txtHeight.setText("");
       		txtWeight.setText("");
       		txtEvent_1.setText("");
       		txtCooper.setText("");       		
       		txt40.setText("");
       		txt40back.setText("");
       		txtNote.setText("");
       		txtBreaks.setText("");
       		txtCrossSmall.setText("");
       		txtCrossBig.setText("");
       		txtRules.setText("");
       		txtSlope.setText("");
               
                
            }
        });  
		
		Border inBorder = BorderFactory.createTitledBorder(rb.getString("gui.test.newtest"));
		Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outBorder, inBorder));
		GridBagConstraints gr = new GridBagConstraints();
		add(txtSlope);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(lblReferee);
		
		
		add(combobox);
		add(lblDate);
		add(chooser);
		add(lblEvent);
		add(txtEvent_1);
		add(lblHeight);
		add(spinnerHeight);
		add(lblWeight);
		add(spinnerWeight);
		add(lblRules);
		add(txtRules);
		add(lblCooper);
		add(txtCooper);
		add(lbl40);
		add(txt40);
		add(lbl40back);
		add(txt40back);
		add(lblCrossSmall);
		add(txtCrossSmall);
		add(lblCrossBig);
		add(txtCrossBig);
		add(lblBreaks);
		add(txtBreaks);
		add(lblSlope);
		add(txtSlope);
		add(lblNote);
		add(txtNote);
		
		checkBox = new JCheckBox(rb.getString("gui.test.random")); //$NON-NLS-1$
		add(checkBox);
		add(btnOK);
	
	}
	public List<Rozhodcovia> refreshCombo() {
		List<Rozhodcovia> list = remote.getRefs();			
		combobox.removeAllItems();
		List<String> str = new ArrayList<String>();
		
		for(Rozhodcovia refs : list) {
			str.add(refs.getSurname());
		}
		
		
		combobox.setModel(new DefaultComboBoxModel(str.toArray()));
		
		return list;
	}
		public void setNewTestListener(NewTestListener listener) {
		
		this.newTestListener = listener;
	}
}