package gui.stadion;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import entity.Rozhodcovia;
import entity.Stadiony;
import entity.Timy;
import facade.StadionBeanRemote;
import facade.TimBeanRemote;

public class StadionPanel extends JPanel {
	
	Context ctx;
	StadionBeanRemote remote;
	TimBeanRemote remoteTim;
	
	private JTextField txtMeno;
	private JTextField txtUlica;
	private JTextField txtMesto;
	private JLabel lblUlica;
	private JLabel lblMesto;
	private JLabel lblStadHome;
	private JLabel lblStadMeno;
	
	private JButton btnOK;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private NewStadiumListener newStadiumListener;
	
	private JComboBox<String> combobox;
	
	
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	private static final Logger log = Logger.getLogger(StadionPanel.class.getName());
	
	
	public StadionPanel() {
		
		try {
			ctx = new InitialContext();
		} catch (Exception e) {
			
			log.log(Level.SEVERE, "InitialContex error",e);
			
		}
		
		
		try {
			remoteTim = (TimBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/TimBean!facade.TimBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Tim remote lookup error",e);
		}
		
		try {
			remote = (StadionBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/StadionBean!facade.StadionBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Tim remote lookup error",e);
		}
		
		Dimension dim = getPreferredSize();
		dim.width = 300;
		dim.height = 500;
		setMinimumSize(dim);
		
		
		lblStadMeno = new JLabel(rb.getString("gui.lblStadMeno"));
		lblUlica = new JLabel(rb.getString("gui.lblUlica"));
		lblMesto = new JLabel(rb.getString("gui.lblMesto"));
		lblStadHome = new JLabel(rb.getString("gui.lblStadHome"));
		txtMeno = new JTextField(10);
		txtMesto = new JTextField(10);
		txtUlica = new JTextField(10);
		btnOK = new JButton("OK");
		combobox = new JComboBox<String>();
		
		txtMeno.setText("     ");
		txtMesto.setText("     ");
		txtUlica.setText("     ");
		
		
		spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		portSpinner = new JSpinner(spinnerModel);
		/*
		 * Po kliknuti na tlacidlo sa nacitaju udaje z formulara pre noveho zamestnanca. Udaje su osetrene, ci nie ss nulove alebo ci neprekracuju
		 * urcitu dlzku. Ak nie tak sa poslu do ZamPanelEvents.
		 * @param btnOK tlacidlo na potvrdenie pridania noveho zamestnanca.
		 * @throws vynimka o nulovych alebo velmi dlhych udajoch o zamestnancovi.
		 */
		
		List rs = refreshCombo();
		/*combobox.addItem("");
		combobox.setModel(new DefaultComboBoxModel(rs.toArray()));*/
		
		

		
		combobox.setSelectedIndex(0);
		
		
		btnOK.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                String meno = txtMeno.getText();
                String ulica = txtUlica.getText();
                String mesto = txtMesto.getText();
                String st = combobox.getSelectedItem().toString();
                
               
                
                try {
                	
                	if(meno.equals(""))
                		throw new Exception();
                		
					if(st.isEmpty()) {
						Stadiony stadion = new Stadiony(meno,mesto,ulica);				
						remote.persist(stadion);
					}
					else {
				     	 Timy tim = (Timy) remoteTim.findByName(st);
				     	// int id = tim.getId();
				     	log.log(Level.FINE, "team loaded"+tim.toString());
						Stadiony stadion = new Stadiony(meno,tim,mesto,ulica);		
						remote.persist(stadion);
					}
                	
					
					if(newStadiumListener != null) {
						newStadiumListener.stadionPanelEvent();
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					log.log(Level.SEVERE, "add stadium save button fail", e1);
					JOptionPane.showMessageDialog(null, "Field 'Name' must be filled", "Error", JOptionPane.ERROR_MESSAGE);
				}
               txtMeno.setText("");         
               txtUlica.setText("");
               txtMesto.setText("");
               
                
            }
        });    
		
		Border inBorder = BorderFactory.createTitledBorder(rb.getString("gui.stadion.newstadium"));
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
		add(lblStadMeno, gr);
		
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
		add(lblStadHome,gr);
		
		gr.gridx = 1;
		
		gr.anchor = GridBagConstraints.LINE_START;
		add(combobox,gr);
		
		//NEXT riadok////////////////////
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
		gr.gridy = 6;
		gr.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btnOK,gr);
	}
	public List<Timy> refreshCombo() {
		List<Timy> list = remoteTim.findAll();			
			
		combobox.removeAllItems();
		List<String> str = new ArrayList<String>();
		
		for(Timy refs : list) {
			str.add(refs.getName());
		}
		combobox.setModel(new DefaultComboBoxModel(str.toArray()));
		combobox.addItem("");
		return list;
	}

public void setNewStadiumListener(NewStadiumListener listener) {
		
		this.newStadiumListener = listener;
	}
}
