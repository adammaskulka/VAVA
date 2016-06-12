package gui.rozhodca;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import gui.MainWindow;
import entity.Rozhodcovia;
import facade.RozhodcaBeanRemote;

public class UpdateRozhodca extends JFrame {
	
	Context ctx;
	RozhodcaBeanRemote remote;
	
	private JTextField txtMeno;
	private JTextField txtPriezvisko;
	private JTextField txtMail;
	private JTextField txtUlica;
	private JTextField txtMesto;
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
	private UpdateRefListener updateRefListener;
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	JButton btnSave = new JButton();
	JButton btnDelete = new JButton();
	
	
	//RozhodcaServis rozhodcaServis = new RozhodcaServis();
	private static final Logger log = Logger.getLogger(UpdateRozhodca.class.getName());
	
	Rozhodcovia rozhodca,update;
	JComboBox comboBox = new JComboBox();
	
	public UpdateRozhodca() {
		
	}
	
	public UpdateRozhodca(String priezvisko) {
		
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
		
		setSize(350, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		ImageIcon iconSave = createImageIcon("icons/save.png");
		ImageIcon iconDelete = createImageIcon("icons/delete.png");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{35, 10, 84, 85, 56, 0};
		gridBagLayout.rowHeights = new int[]{44, 0, 0, 0, 0, 0, 0, 0, 55, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		
		lblMeno = new JLabel(rb.getString("gui.lblMeno"));
		lblPriezvisko = new JLabel(rb.getString("gui.lblPriezvisko"));
		lblUlica = new JLabel(rb.getString("gui.lblUlica"));
		lblMesto = new JLabel(rb.getString("gui.lblMesto"));
		lblTelefon = new JLabel(rb.getString("gui.lblTelefon"));
		lblMail = new JLabel(rb.getString("gui.lblMail"));
		lblLicence = new JLabel(rb.getString("gui.lblLicence"));
		
		
		GridBagConstraints gbc_lblMeno = new GridBagConstraints();
		gbc_lblMeno.anchor = GridBagConstraints.EAST;
		gbc_lblMeno.insets = new Insets(0, 0, 5, 5);
		gbc_lblMeno.gridx = 2;
		gbc_lblMeno.gridy = 1;
		getContentPane().add(lblMeno, gbc_lblMeno);
		
		txtMeno = new JTextField();
		GridBagConstraints gbc_txtMeno = new GridBagConstraints();
		gbc_txtMeno.anchor = GridBagConstraints.NORTH;
		gbc_txtMeno.insets = new Insets(0, 0, 5, 5);
		gbc_txtMeno.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMeno.gridx = 3;
		gbc_txtMeno.gridy = 1;
		getContentPane().add(txtMeno, gbc_txtMeno);
		txtMeno.setColumns(10);
		
		
		GridBagConstraints gbc_lblPriezvisko = new GridBagConstraints();
		gbc_lblPriezvisko.anchor = GridBagConstraints.EAST;
		gbc_lblPriezvisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriezvisko.gridx = 2;
		gbc_lblPriezvisko.gridy = 2;
		getContentPane().add(lblPriezvisko, gbc_lblPriezvisko);
		
		txtPriezvisko = new JTextField();
		txtPriezvisko.setText("");
		GridBagConstraints gbc_txtPriezvisko = new GridBagConstraints();
		gbc_txtPriezvisko.insets = new Insets(0, 0, 5, 5);
		gbc_txtPriezvisko.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPriezvisko.gridx = 3;
		gbc_txtPriezvisko.gridy = 2;
		getContentPane().add(txtPriezvisko, gbc_txtPriezvisko);
		txtPriezvisko.setColumns(10);
		
		
		GridBagConstraints gbc_lblMail = new GridBagConstraints();
		gbc_lblMail.anchor = GridBagConstraints.EAST;
		gbc_lblMail.insets = new Insets(0, 0, 5, 5);
		gbc_lblMail.gridx = 2;
		gbc_lblMail.gridy = 3;
		getContentPane().add(lblMail, gbc_lblMail);
		
		txtMail = new JTextField();
		txtMail.setText("");
		GridBagConstraints gbc_txtMail = new GridBagConstraints();
		gbc_txtMail.insets = new Insets(0, 0, 5, 5);
		gbc_txtMail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMail.gridx = 3;
		gbc_txtMail.gridy = 3;
		getContentPane().add(txtMail, gbc_txtMail);
		txtMail.setColumns(10);
		
		
		GridBagConstraints gbc_lblUlica = new GridBagConstraints();
		gbc_lblUlica.anchor = GridBagConstraints.EAST;
		gbc_lblUlica.insets = new Insets(0, 0, 5, 5);
		gbc_lblUlica.gridx = 2;
		gbc_lblUlica.gridy = 4;
		getContentPane().add(lblUlica, gbc_lblUlica);
		
		txtUlica = new JTextField();
		txtUlica.setText("");
		GridBagConstraints gbc_txtUlica = new GridBagConstraints();
		gbc_txtUlica.insets = new Insets(0, 0, 5, 5);
		gbc_txtUlica.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUlica.gridx = 3;
		gbc_txtUlica.gridy = 4;
		getContentPane().add(txtUlica, gbc_txtUlica);
		txtUlica.setColumns(10);
		
		
		GridBagConstraints gbc_lblMesto = new GridBagConstraints();
		gbc_lblMesto.anchor = GridBagConstraints.EAST;
		gbc_lblMesto.insets = new Insets(0, 0, 5, 5);
		gbc_lblMesto.gridx = 2;
		gbc_lblMesto.gridy = 5;
		getContentPane().add(lblMesto, gbc_lblMesto);
		
		txtMesto = new JTextField();
		GridBagConstraints gbc_txtMesto = new GridBagConstraints();
		gbc_txtMesto.insets = new Insets(0, 0, 5, 5);
		gbc_txtMesto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMesto.gridx = 3;
		gbc_txtMesto.gridy = 5;
		getContentPane().add(txtMesto, gbc_txtMesto);
		txtMesto.setColumns(10);
		
		
		GridBagConstraints gbc_lblTelefon = new GridBagConstraints();
		gbc_lblTelefon.anchor = GridBagConstraints.EAST;
		gbc_lblTelefon.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefon.gridx = 2;
		gbc_lblTelefon.gridy = 6;
		getContentPane().add(lblTelefon, gbc_lblTelefon);
		
		txtTelefon = new JTextField();
		txtTelefon.setText("");
		GridBagConstraints gbc_txtTelefon = new GridBagConstraints();
		gbc_txtTelefon.anchor = GridBagConstraints.NORTH;
		gbc_txtTelefon.insets = new Insets(0, 0, 5, 5);
		gbc_txtTelefon.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefon.gridx = 3;
		gbc_txtTelefon.gridy = 6;
		getContentPane().add(txtTelefon, gbc_txtTelefon);
		txtTelefon.setColumns(10);
		
		
		GridBagConstraints gbc_lblLicencia = new GridBagConstraints();
		gbc_lblLicencia.anchor = GridBagConstraints.EAST;
		gbc_lblLicencia.insets = new Insets(0, 0, 5, 5);
		gbc_lblLicencia.gridx = 2;
		gbc_lblLicencia.gridy = 7;
		getContentPane().add(lblLicence, gbc_lblLicencia);
		
		
		DefaultComboBoxModel<String> combomodel = new DefaultComboBoxModel<String>();
		combomodel.addElement("A");
		combomodel.addElement("B");
		combomodel.addElement("C");
		combomodel.addElement("D");
		comboBox.setModel(combomodel);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 7;
		getContentPane().add(comboBox, gbc_comboBox);
		
		
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 8;
		getContentPane().add(btnSave, gbc_btnSave);
		
		
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 3;
		gbc_btnDelete.gridy = 8;
		getContentPane().add(btnDelete, gbc_btnDelete);
		
		btnSave.setIcon(iconSave);
		btnDelete.setIcon(iconDelete);
		
		rozhodca = remote.findByName(priezvisko);
		update = rozhodca;
		txtMeno.setText(rozhodca.getName());
		txtPriezvisko.setText(rozhodca.getSurname());
		txtMail.setText(rozhodca.getMail());
		txtTelefon.setText(rozhodca.getPhone());
		txtUlica.setText(rozhodca.getStreet());
		txtMesto.setText(rozhodca.getCity());
		
		btnSave.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	try {
					String meno = txtMeno.getText();
					String priez = txtPriezvisko.getText();
					if(meno.equals("") || priezvisko.equals(""))
                		throw new Exception();
					String mail = txtMail.getText();
					String telefon = txtTelefon.getText();
					String ulica = txtUlica.getText();
					String mesto = txtMesto.getText();
					String licencia = comboBox.getSelectedItem().toString();
					
					rozhodca.setName(meno);
					rozhodca.setSurname(priez);
					rozhodca.setMail(mail);
					rozhodca.setPhone(telefon);
					rozhodca.setStreet(ulica);
					rozhodca.setCity(mesto);
					rozhodca.setLicence(licencia);
					
				
					
					remote.update(rozhodca);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					log.log(Level.SEVERE, "update referee "+rozhodca.toString()+" fail " , e1);
					JOptionPane.showMessageDialog(null, "Fields 'Name' and 'Surname' must be filled", "Error", JOptionPane.ERROR_MESSAGE);
				}
            	finally {
            		log.log(Level.INFO, "update referee "+rozhodca.toString()+" successfull ");
            		if(updateRefListener != null) {
						updateRefListener.rozhodcaPanelEvent();
					}
            		setVisible(false);
            	}
            	
            }
        });  
		btnDelete.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	try {
            		
            		remote.delete(rozhodca);           		
            	}
            	catch (Exception e1) {
            		log.log(Level.SEVERE, "delete referee "+rozhodca.toString()+" fail " , e1);
            		
            	}
            	finally {
            		log.log(Level.INFO, "delete referee successfull ");
            		if(updateRefListener != null) {
						updateRefListener.rozhodcaPanelEvent();
					}
            		setVisible(false);
            	}
            }
		});
		
		
	}
public void setUpdateRefListener(UpdateRefListener listener) {
		
		this.updateRefListener = listener;
	}
protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = MainWindow.class.getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL);
    } else {
    	log.log(Level.WARNING, "couldnt load icon " + path);
        return null;
    }
}
}
