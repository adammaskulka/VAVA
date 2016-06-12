package gui.zapas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import PDFServis.PdfServis;
import facade.CasomeracBeanRemote;
import facade.RozhodcaBeanRemote;
import facade.StadionBeanRemote;
import facade.TimBeanRemote;
import facade.ZapasBeanRemote;
import entity.Casomeraci;
import entity.Rozhodcovia;
import entity.Stadiony;
import entity.Timy;
import entity.Zapasy;

public class ZapasPanel extends JPanel {
	
	Context ctx;
	ZapasBeanRemote remote;
	TimBeanRemote remoteTim;
	RozhodcaBeanRemote remoteRozhodca;
	CasomeracBeanRemote remoteCasomerac;
	StadionBeanRemote remoteStadion;
	
	private JTextField txtMeno;
	private JTextField txtPriezvisko;
	private JLabel lblDate;
	private JLabel lblTime;
	private JLabel lblHome;
	private JLabel lblAway;
	private JLabel lblHR2;
	private JLabel lblCR2;
	private JLabel lblHR1;
	private JLabel lblCR1;
	private JLabel lblStadium;
	private JLabel lblCasomerac;
	
	private JButton btnOK;
	private JButton btnPDF;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	
	private JComboBox<String> comboHome;
	private JComboBox<String> comboAway;
	private JComboBox<String> comboHR1;
	private JComboBox<String> comboHR2;
	private JComboBox<String> comboCR2;
	private JComboBox<String> comboCR1;
	private JComboBox<String> comboStadium;
	private JComboBox<String> comboCasomerac;
	

	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	private static final Logger log = Logger.getLogger(ZapasPanel.class.getName());
	NewZapasListener newZapasListener;
	String strHR1;
	String strHR2;
	String strCR1;
	String strCR2;
	List<String> listHR1 = new ArrayList<String>();
	List<String> listHR2 = new ArrayList<String>();
	List<String> listCR1 = new ArrayList<String>();
	List<String> listCR2 = new ArrayList<String>();
	
	
	private JButton btnOk;
	private JLabel lblNewLabel;
	
	public ZapasPanel() {
			
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
			remote = (ZapasBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/ZapasBean!facade.ZapasBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Zapas remote lookup error",e);
		}
		
		try {
			remoteRozhodca = (RozhodcaBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/RozhodcaBean!facade.RozhodcaBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Rozhodca remote lookup error",e);
		}
		
		try {
			remoteCasomerac = (CasomeracBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/CasomeracBean!facade.CasomeracBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Casomerac remote lookup error",e);
		}
		
		try {
			remoteStadion = (StadionBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/StadionBean!facade.StadionBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Stadion remote lookup error",e);
		}
		
		
		
		Dimension dim = getPreferredSize();
		dim.width = 200;
		dim.height = 600;
		setMinimumSize(dim);
		
		txtMeno = new JTextField(10);
		JDateChooser chooser = new JDateChooser();
		
		/*JDateChooser chooser = new JDateChooser();
		chooser.getLocale();
		
		/*chooser.addPropertyChangeListener("date", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		        JDateChooser chooser = (JDateChooser)evt.getSource();
		        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		        textField.setText(formatter.format(chooser.getDate()));
		    }
		});*/
		
		
		btnOk = new JButton("OK"); //$NON-NLS-1$
		btnPDF = new JButton(rb.getString("gui.btnPDF"));
		spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		portSpinner = new JSpinner(spinnerModel);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		//date.setTime(calendar.getTimeInMillis());
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		int currentDate= Calendar.getInstance().get(Calendar.DATE);

		calendar.set(currentYear  , currentMonth , currentDate);
		date.setTime(calendar.getTimeInMillis());
		Dimension dimchooser = getPreferredSize();
		dimchooser.width = 50;
		
		
		
		//List rs = refreshCombo();
		/*combobox.addItem("");
		combobox.setModel(new DefaultComboBoxModel(rs.toArray()));*/
		
		DefaultComboBoxModel<String> combomodel = new DefaultComboBoxModel<String>();
		//combomodel.addElement("Administrator");
		combomodel.addElement("Klasicka objednavka");
		combomodel.addElement("Objednavka s darcekom");
		combomodel.addElement("Objednavka so zlavou");
		

		
		
		
		
		//comboCasomerac.setModel(combomodel);
		
		Border inBorder = BorderFactory.createTitledBorder(rb.getString("gui.zapas.newmatch"));
		Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outBorder, inBorder));
		GridBagConstraints gr = new GridBagConstraints();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{177, 107, 107, 107, 107, 0};
		gridBagLayout.rowHeights = new int[]{33, 40, 40, 40, 40, 41, 40, 40, 40, 40, 19, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		lblDate = new JLabel(rb.getString("gui.lblDate"));
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 1;
		gbc_lblDate.gridy = 1;
		add(lblDate, gbc_lblDate);
		
		
		
		
		chooser.setDate(date);
		//dimchooser.height = 500;
		chooser.getLocale();
		GridBagConstraints gbc_chooser = new GridBagConstraints();
		gbc_chooser.fill = GridBagConstraints.BOTH;
		gbc_chooser.insets = new Insets(0, 0, 5, 5);
		gbc_chooser.gridx = 2;
		gbc_chooser.gridy = 1;
		add(chooser, gbc_chooser);
		
		lblHome = new JLabel(rb.getString("gui.lblHome"));
		GridBagConstraints gbc_lblHome = new GridBagConstraints();
		gbc_lblHome.fill = GridBagConstraints.BOTH;
		gbc_lblHome.insets = new Insets(0, 0, 5, 5);
		gbc_lblHome.gridx = 1;
		gbc_lblHome.gridy = 2;
		add(lblHome, gbc_lblHome);
		
		
		comboHome = new JComboBox<String>();
		comboHome.setModel(combomodel);
		comboHome.setSelectedIndex(0);
		GridBagConstraints gbc_comboHome = new GridBagConstraints();
		gbc_comboHome.fill = GridBagConstraints.BOTH;
		gbc_comboHome.insets = new Insets(0, 0, 5, 5);
		gbc_comboHome.gridx = 2;
		gbc_comboHome.gridy = 2;
		add(comboHome, gbc_comboHome);
		lblAway = new JLabel(rb.getString("gui.lblAway"));
		GridBagConstraints gbc_lblAway = new GridBagConstraints();
		gbc_lblAway.fill = GridBagConstraints.BOTH;
		gbc_lblAway.insets = new Insets(0, 0, 5, 5);
		gbc_lblAway.gridx = 1;
		gbc_lblAway.gridy = 3;
		add(lblAway, gbc_lblAway);
		comboAway = new JComboBox<String>();
		comboAway.setModel(combomodel);
		comboAway.setSelectedIndex(0);
		GridBagConstraints gbc_comboAway = new GridBagConstraints();
		gbc_comboAway.fill = GridBagConstraints.BOTH;
		gbc_comboAway.insets = new Insets(0, 0, 5, 5);
		gbc_comboAway.gridx = 2;
		gbc_comboAway.gridy = 3;
		add(comboAway, gbc_comboAway);
		lblHR1 = new JLabel(rb.getString("gui.lblHR"));
		GridBagConstraints gbc_lblHR1 = new GridBagConstraints();
		gbc_lblHR1.fill = GridBagConstraints.BOTH;
		gbc_lblHR1.insets = new Insets(0, 0, 5, 5);
		gbc_lblHR1.gridx = 1;
		gbc_lblHR1.gridy = 4;
		add(lblHR1, gbc_lblHR1);
		comboHR1 = new JComboBox<String>();
		comboHR1.setModel(combomodel);
		comboHR1.setSelectedIndex(0);
		GridBagConstraints gbc_comboHR1 = new GridBagConstraints();
		gbc_comboHR1.fill = GridBagConstraints.BOTH;
		gbc_comboHR1.insets = new Insets(0, 0, 5, 5);
		gbc_comboHR1.gridx = 2;
		gbc_comboHR1.gridy = 4;
		add(comboHR1, gbc_comboHR1);
		lblHR2 = new JLabel(rb.getString("gui.lblHR"));
		GridBagConstraints gbc_lblHR2 = new GridBagConstraints();
		gbc_lblHR2.fill = GridBagConstraints.BOTH;
		gbc_lblHR2.insets = new Insets(0, 0, 5, 5);
		gbc_lblHR2.gridx = 1;
		gbc_lblHR2.gridy = 5;
		add(lblHR2, gbc_lblHR2);
		comboHR2 = new JComboBox<String>();
		comboHR2.setModel(combomodel);
		comboHR2.setSelectedIndex(0);
		GridBagConstraints gbc_comboHR2 = new GridBagConstraints();
		gbc_comboHR2.fill = GridBagConstraints.BOTH;
		gbc_comboHR2.insets = new Insets(0, 0, 5, 5);
		gbc_comboHR2.gridx = 2;
		gbc_comboHR2.gridy = 5;
		add(comboHR2, gbc_comboHR2);
		lblCR2 = new JLabel(rb.getString("gui.lblCR"));
		GridBagConstraints gbc_lblCR2 = new GridBagConstraints();
		gbc_lblCR2.fill = GridBagConstraints.BOTH;
		gbc_lblCR2.insets = new Insets(0, 0, 5, 5);
		gbc_lblCR2.gridx = 1;
		gbc_lblCR2.gridy = 6;
		add(lblCR2, gbc_lblCR2);
		comboCR2 = new JComboBox<String>();
		comboCR2.setModel(combomodel);
		comboCR2.setSelectedIndex(0);
		GridBagConstraints gbc_comboCR2 = new GridBagConstraints();
		gbc_comboCR2.fill = GridBagConstraints.BOTH;
		gbc_comboCR2.insets = new Insets(0, 0, 5, 5);
		gbc_comboCR2.gridx = 2;
		gbc_comboCR2.gridy = 6;
		add(comboCR2, gbc_comboCR2);
		lblCR1 = new JLabel(rb.getString("gui.lblCR"));
		GridBagConstraints gbc_lblCR1 = new GridBagConstraints();
		gbc_lblCR1.fill = GridBagConstraints.BOTH;
		gbc_lblCR1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCR1.gridx = 1;
		gbc_lblCR1.gridy = 7;
		add(lblCR1, gbc_lblCR1);
		comboCR1 = new JComboBox<String>();
		comboCR1.setModel(combomodel);
		comboCR1.setSelectedIndex(0);
		GridBagConstraints gbc_comboCR1 = new GridBagConstraints();
		gbc_comboCR1.fill = GridBagConstraints.BOTH;
		gbc_comboCR1.insets = new Insets(0, 0, 5, 5);
		gbc_comboCR1.gridx = 2;
		gbc_comboCR1.gridy = 7;
		add(comboCR1, gbc_comboCR1);
		lblStadium = new JLabel(rb.getString("gui.lblStadium"));
		GridBagConstraints gbc_lblStadium = new GridBagConstraints();
		gbc_lblStadium.fill = GridBagConstraints.BOTH;
		gbc_lblStadium.insets = new Insets(0, 0, 5, 5);
		gbc_lblStadium.gridx = 1;
		gbc_lblStadium.gridy = 8;
		add(lblStadium, gbc_lblStadium);
		comboStadium = new JComboBox<String>();
		comboStadium.setModel(combomodel);
		comboStadium.setSelectedIndex(0);
		GridBagConstraints gbc_comboStadium = new GridBagConstraints();
		gbc_comboStadium.fill = GridBagConstraints.BOTH;
		gbc_comboStadium.insets = new Insets(0, 0, 5, 5);
		gbc_comboStadium.gridx = 2;
		gbc_comboStadium.gridy = 8;
		add(comboStadium, gbc_comboStadium);
		lblCasomerac = new JLabel(rb.getString("gui.lblCasomerac"));
		
		GridBagConstraints gbc_lblCasomerac = new GridBagConstraints();
		gbc_lblCasomerac.anchor = GridBagConstraints.WEST;
		gbc_lblCasomerac.gridx = 1;
		gbc_lblCasomerac.gridy = 9;
		gbc_lblCasomerac.insets = new Insets(0, 0, 5, 5);
		add(lblCasomerac, gbc_lblCasomerac);
		
		comboCasomerac = new JComboBox<String>();
		comboCasomerac.setModel(combomodel);
		comboCasomerac.setSelectedIndex(0);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 9;
		add(comboCasomerac, gbc_lblNewLabel);
		
		
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 0, 5);
		gbc_btnOk.gridx = 2;
		gbc_btnOk.gridy = 11;
		add(btnOk, gbc_btnOk);
		
		GridBagConstraints gbc_btnPDF = new GridBagConstraints();
		gbc_btnPDF.insets = new Insets(0, 0, 0, 5);
		gbc_btnPDF.gridx = 2;
		gbc_btnPDF.gridy = 14;
		add(btnPDF, gbc_btnPDF);
		
		List teams = refreshTeams();
		List refs = refreshRefs();
		List stads = refreshStadiums();
		List times = refreshTimes();
		
		comboHR2.addItem("");
		//comboHR1.addItem("");
		comboCR1.addItem("");
		comboCR2.addItem("");
		
		PdfServis pdfServis = new PdfServis();
		
		
		
		
		
		btnOk.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                Date date = chooser.getDate();
                String hr1 = comboHR1.getSelectedItem().toString();
                String hr2 = comboHR2.getSelectedItem().toString();
                String cr1 = comboCR1.getSelectedItem().toString();
                String cr2 = comboCR2.getSelectedItem().toString();
                String stadium = comboStadium.getSelectedItem().toString();
                String home = comboHome.getSelectedItem().toString();
                String away = comboAway.getSelectedItem().toString();
                String casomerac = comboCasomerac.getSelectedItem().toString();
                             
                try {
					
				     	 Timy hometeam = (Timy) remoteTim.findByName(home);
				     	 Timy awayteam = (Timy) remoteTim.findByName(away);
				     	Rozhodcovia HR1 = (Rozhodcovia) remoteRozhodca.findByName(hr1);
				     	Rozhodcovia HR2 = (Rozhodcovia) remoteRozhodca.findByName(hr2);
				     	Rozhodcovia CR1 = (Rozhodcovia) remoteRozhodca.findByName(cr1);
				     	Rozhodcovia CR2 = (Rozhodcovia) remoteRozhodca.findByName(cr2);
				     	Stadiony stadion = (Stadiony) remoteStadion.findByName(stadium);
				     	Casomeraci timekeeper = (Casomeraci) remoteCasomerac.findByName(casomerac);
				     	// int id = tim.getId();
				     	/*log.log(Level.FINE, "data loaded"+hometeam.toString()+awayteam.toString()+HR1.toString()+HR2.toString()+CR1.toString()
				     	+CR2.toString()+stadion.toString()+timekeeper.toString());*/
								
				     	Zapasy zapas = new Zapasy(timekeeper,CR1,HR2,
				    			CR2, HR1, stadion, awayteam,
				    			hometeam, date);
				     	
						remote.persist(zapas);
					
                	
					
					if(newZapasListener != null) {
						newZapasListener.zapasPanelEvent();
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					log.log(Level.SEVERE, "add match save button fail", e1);
				}
	               
	               
            } 
	            
	        });  
		
		btnPDF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = null;
            	JFileChooser chooser = new JFileChooser();
            	chooser.addChoosableFileFilter(new FileNameExtensionFilter(".pdf", "pdf")); 
            	int retrival = chooser.showSaveDialog(null);
            	 if (retrival == JFileChooser.APPROVE_OPTION)  {
                    File file = chooser.getSelectedFile();
                    path = file.getAbsolutePath();
                }
            	 if(path==null) {
            		 path="zapasy";
            	 }
            	pdfServis.PdfZapasy(remote.findAll(),path);
            	JOptionPane.showMessageDialog(null, rb.getString("gui.PDFCreated"));
				
			}
			
		});
		
		
		
	}
	public List<Timy> refreshTeams() {
		List<Timy> list = remoteTim.findAll();			
		
		List<String> str = new ArrayList<String>();
		
		for(Timy refs : list) {
			str.add(refs.getName());
		}			
		comboHome.removeAllItems();
		comboAway.removeAllItems();
		
		comboHome.setModel(new DefaultComboBoxModel(str.toArray()));
		comboAway.setModel(new DefaultComboBoxModel(str.toArray()));
		
		return list;
	}
	public List<Rozhodcovia> refreshRefs() {
		List<Rozhodcovia> list = remoteRozhodca.getRefs();			
		
		List<String> str = new ArrayList<String>();
		
		for(Rozhodcovia refs : list) {
			str.add(refs.getSurname());
		}
		comboHR1.removeAllItems();
		comboHR2.removeAllItems();
		comboCR1.removeAllItems();
		comboCR2.removeAllItems();
		
		comboHR1.setModel(new DefaultComboBoxModel(str.toArray()));
		comboHR2.setModel(new DefaultComboBoxModel(str.toArray()));
		comboCR1.setModel(new DefaultComboBoxModel(str.toArray()));
		comboCR2.setModel(new DefaultComboBoxModel(str.toArray()));
		
		return list;
	}
	public List<Stadiony> refreshStadiums() {
		List<Stadiony> list = remoteStadion.findAll();		
		
		List<String> str = new ArrayList<String>();
		
		for(Stadiony refs : list) {
			str.add(refs.getName());
		}
		comboStadium.removeAllItems();
		
		
		comboStadium.setModel(new DefaultComboBoxModel(str.toArray()));
		
		return list;
	}
	public List<Casomeraci> refreshTimes() {
    List<Casomeraci> list = remoteCasomerac.findAll();		
		
		List<String> str = new ArrayList<String>();
		
		for(Casomeraci refs : list) {
			str.add(refs.getSurname());
		}	
		comboCasomerac.removeAllItems();
		
		
		comboCasomerac.setModel(new DefaultComboBoxModel(str.toArray()));
		
		return list;
	}
public void setNewZapasListener(NewZapasListener listener) {
	
	this.newZapasListener = listener;
}
	
}
