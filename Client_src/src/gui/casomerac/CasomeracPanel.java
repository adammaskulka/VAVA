package gui.casomerac;

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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;


import entity.Casomeraci;
import facade.CasomeracBeanRemote;
import facade.RozhodcaBeanRemote;

public class CasomeracPanel extends JPanel {
	
	Context ctx;
	CasomeracBeanRemote remote;
	
	private JTextField txtMeno;
	private JTextField txtPriezvisko;
	private JTextField txtUlica;
	private JTextField txtMesto;
	private JTextField txtMail;
	private JTextField txtTelefon;
	private JLabel lblMeno;
	private JLabel lblPriezvisko;
	private JLabel lblCombo;
	private JLabel lblUlica;
	private JLabel lblMesto;
	private JLabel lblMail;
	private JLabel lblTelefon;
	private JLabel lblPassword;
	private JLabel lblLicence;
	private JButton btnOK;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JComboBox<String> combobox;
	private NewCasomeracListener newCasomeracListener;
	
	
	
	private static final Logger log = Logger.getLogger(CasomeracPanel.class.getName());
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	public CasomeracPanel() {
		
		try {
			ctx = new InitialContext();
		} catch (Exception e) {
			
			log.log(Level.SEVERE, "InitialContex error",e);
			
		}
		
		
		try {
			remote = (CasomeracBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/CasomeracBean!facade.CasomeracBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Casomerac remote lookup error",e);
		}
		
		Dimension dim = getPreferredSize();
		dim.width = 250;
		dim.height = 500;
		setMinimumSize(dim);
		
		
		lblMeno = new JLabel(rb.getString("gui.lblMeno"));
		lblPriezvisko = new JLabel(rb.getString("gui.lblPriezvisko"));
		lblUlica = new JLabel(rb.getString("gui.lblUlica"));
		lblMesto = new JLabel(rb.getString("gui.lblMesto"));
		lblTelefon = new JLabel(rb.getString("gui.lblTelefon"));
		lblMail = new JLabel(rb.getString("gui.lblMail"));
		lblLicence = new JLabel(rb.getString("gui.lblLicence"));
		
		txtMeno = new JTextField(10);
		txtPriezvisko = new JTextField(10);
		txtUlica = new JTextField(10);
		txtMesto = new JTextField(10);
		txtMail = new JTextField(10);
		txtTelefon = new JTextField(10);
		btnOK = new JButton("OK");
		
		combobox = new JComboBox<String>();
		spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		portSpinner = new JSpinner(spinnerModel);
		

		btnOK.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                String meno = txtMeno.getText();
                String priezvisko = txtPriezvisko.getText();
                String mail = txtMail.getText();
                String ulica = txtUlica.getText();
                String mesto = txtMesto.getText();
                String telefon = txtTelefon.getText();
               
                
                
                try {
                	if(meno.equals("") || priezvisko.equals(""))
                		throw new Exception();
                	
					Casomeraci casomerac = new Casomeraci(meno,priezvisko,mail,mesto,ulica,telefon);				
					remote.persist(casomerac);
					
					if(newCasomeracListener != null) {
						newCasomeracListener.casomeracPanelEvent();
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					log.log(Level.SEVERE, "add timekeeper save button fail", e1);
					JOptionPane.showMessageDialog(null, "Fields 'Name' and 'Surname' must be filled", "Error", JOptionPane.ERROR_MESSAGE);
				}
               txtMeno.setText("");
               txtPriezvisko.setText("");
               txtMail.setText("");
               txtUlica.setText("");
               txtMesto.setText("");
               txtTelefon.setText("");
                
            }
        });    
		
		
		Border inBorder = BorderFactory.createTitledBorder(rb.getString("gui.casomerac.newcas"));
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
		
		//NEXT riadok////////////////////
		gr.gridy++;
		gr.weightx = 1;
		gr.weighty = 0.1;
		gr.gridx = 0;
		
		gr.anchor = GridBagConstraints.LINE_END;
		gr.insets = new Insets(0 , 0 ,0 , 5);
		add(lblPriezvisko,gr);
		
		gr.gridx = 1;
		
		gr.anchor = GridBagConstraints.LINE_START;
		add(txtPriezvisko,gr);
		
		//NEXT riadok//////////////////////
		gr.gridy++;
		gr.weightx = 1;
		gr.weighty = 0.1;
		gr.gridx = 0;
		
		gr.anchor = GridBagConstraints.LINE_END;
		gr.insets = new Insets(0 , 0 ,0 , 5);
		add(lblMail,gr);
		
		gr.gridx = 1;
		
		gr.anchor = GridBagConstraints.LINE_START;
		add(txtMail,gr);
		
		//NEXT riadok//////////////////////
				gr.gridy++;
				gr.weightx = 1;
				gr.weighty = 0.1;
				gr.gridx = 0;
				
				gr.anchor = GridBagConstraints.LINE_END;
				gr.insets = new Insets(0 , 0 ,0 , 5);
				add(lblTelefon,gr);
				
				gr.gridx = 1;
				
				gr.anchor = GridBagConstraints.LINE_START;
				add(txtTelefon,gr);
		
				//NEXT riadok//////////////////////
				gr.gridy++;
				gr.weightx = 1;
				gr.weighty = 0.1;
				gr.gridx = 0;
				
				gr.anchor = GridBagConstraints.LINE_END;
				gr.insets = new Insets(0 , 0 ,0 , 5);
				add(lblUlica,gr);
				
				gr.gridx = 1;
				
				gr.anchor = GridBagConstraints.LINE_START;
				add(txtUlica,gr);
				
				//NEXT riadok//////////////////////
				gr.gridy++;
				gr.weightx = 1;
				gr.weighty = 0.1;
				gr.gridx = 0;
				
				gr.anchor = GridBagConstraints.LINE_END;
				gr.insets = new Insets(0 , 0 ,0 , 5);
				add(lblMesto,gr);
				
				gr.gridx = 1;
				
				gr.anchor = GridBagConstraints.LINE_START;
				add(txtMesto,gr);
				
			
		
		
		//TRETI RIADOK
		gr.weighty++;
		gr.weightx = 1;
		
		gr.gridx = 1;
		gr.gridy = 10;
		gr.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btnOK,gr);
	}
	
	public void setNewCasomeracListener(NewCasomeracListener listener) {
		
		this.newCasomeracListener = listener;
	}

	
}
