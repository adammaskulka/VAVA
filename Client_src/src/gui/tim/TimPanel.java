package gui.tim;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;


import facade.RozhodcaBeanRemote;
import facade.TimBeanRemote;
import entity.Timy;

public class TimPanel extends JPanel {
	
	Context ctx;
	TimBeanRemote remote;
	
	private JTextField txtMeno;
	private JTextField txtHomeCol;
	private JTextField txtAwayCol;
	private JLabel lblMeno;
	private JLabel lblStadion;
	private JLabel lblCombo;
	private JLabel lblAwayCol;
	private JLabel lblHomeCol;
	private JButton btnOK;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private NewTimListener newTimListener;
	private JComboBox<String> combobox;
	private JTextField txtLogin;
	private JPasswordField txtPassword;

	
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	private static final Logger log = Logger.getLogger(TimPanel.class.getName());
	
	public TimPanel() {
		
		try {
			ctx = new InitialContext();
		} catch (Exception e) {
			
			log.log(Level.SEVERE, "InitialContex error",e);
			
		}
		
		
		try {
			remote = (TimBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/TimBean!facade.TimBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Tim remote lookup error",e);
		}
		
		Dimension dim = getPreferredSize();
		dim.width = 280;
		dim.height = 500;
		setMinimumSize(dim);
		
		
		
		lblMeno = new JLabel(rb.getString("gui.tim.tabMeno"));
		lblStadion = new JLabel(rb.getString("gui.tim.tabStadion"));
		lblHomeCol = new JLabel(rb.getString("gui.tim.tabHomeCol"));
		lblAwayCol = new JLabel(rb.getString("gui.tim.tabAwayCol"));
		txtMeno = new JTextField(10);
		txtHomeCol = new JTextField(10);
		txtAwayCol = new JTextField(10);
		
		btnOK = new JButton("OK");
		combobox = new JComboBox<String>();
		
		
		
		spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		portSpinner = new JSpinner(spinnerModel);
		
		btnOK.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                String meno = txtMeno.getText();
                String homecol = txtHomeCol.getText();
                String awaycol = txtAwayCol.getText();
                
                
                
                try {
                	
                	if(meno.equals(""))
                		throw new Exception();
					Timy tim = new Timy(meno,homecol,awaycol);				
					remote.persist(tim);
					
					if(newTimListener != null) {
						newTimListener.timPanelEvent();
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					log.log(Level.SEVERE, "add team save button fail", e1);
					JOptionPane.showMessageDialog(null, "Field 'Name' must be filled", "Error", JOptionPane.ERROR_MESSAGE);
				}
               txtMeno.setText("");
               txtHomeCol.setText("");
               txtAwayCol.setText("");
               
                
            }
        });    
		
		
		
		
		Border inBorder = BorderFactory.createTitledBorder(rb.getString("gui.tim.newteam"));
		Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outBorder, inBorder));
		
		setLayout(new GridBagLayout());
		GridBagConstraints gr = new GridBagConstraints();
		
		//PRVY riadok//////////////////
		gr.gridy = 0;
		gr.weightx = 1;
		gr.weighty = 0.1; 	
		gr.gridx = 0;
		
		gr.fill = GridBagConstraints.NONE;	
		gr.anchor = GridBagConstraints.LINE_END;
		gr.insets = new Insets(0 , 0 ,0 , 5);
		add(lblMeno, gr);
		
		gr.gridx = 1;
		gr.gridy = 0;
		gr.anchor = GridBagConstraints.LINE_START;
		add(txtMeno,gr);
		
	
		
		//NEXT riadok//////////////////////
		gr.gridy++;
		gr.weightx = 1;
		gr.weighty = 0.1;
		gr.gridx = 0;
		
		gr.anchor = GridBagConstraints.LINE_END;
		gr.insets = new Insets(0 , 0 ,0 , 5);
		add(lblHomeCol,gr);
		
		gr.gridx = 1;
		
		gr.anchor = GridBagConstraints.LINE_START;
		add(txtHomeCol,gr);
		
		//NEXT riadok//////////////////////
				gr.gridy++;
				gr.weightx = 1;
				gr.weighty = 0.1;
				gr.gridx = 0;
				
				gr.anchor = GridBagConstraints.LINE_END;
				gr.insets = new Insets(0 , 0 ,0 , 5);
				add(lblAwayCol,gr);
				
				gr.gridx = 1;
				
				gr.anchor = GridBagConstraints.LINE_START;
				add(txtAwayCol,gr);
		
				
		
		//TRETI RIADOK
		gr.weighty++;
		gr.weightx = 1;
		
		gr.gridx = 1;
		gr.gridy = 6;
		gr.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btnOK,gr);
	}

public void setNewTimListener(NewTimListener listener) {
		
		this.newTimListener = listener;
	}
	
}

