package gui.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
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
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
import entity.Rozhodcovia;
import entity.Testy;
import facade.RozhodcaBeanRemote;
import facade.TestBeanRemote;


public class FindTestPanel extends JPanel {
	
	Context ctx;
	RozhodcaBeanRemote remote;
	TestBeanRemote remoteTest;
	
	private JTextField txtEvent;
	private JTextField txtReferee;
	
	private JLabel lblReferee;
	private JLabel lblEvent;
	private JLabel lblDateFrom;
	private JLabel lblDateTo;
	private FindTestListener findTestListener;
	private JButton btnPDF;
	
	private JButton btnFind;
	private JButton btnCooperGraf;
	private JButton btnRulesGraf;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	
	private JComboBox<String> combobox;
	PdfServis pdfServis = new PdfServis();
	
	
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	private static final Logger log = Logger.getLogger(FindTestPanel.class.getName());
	private List<Testy> result;
	private List<String> str = new ArrayList<String>();
	private List<String> str1 = new ArrayList<String>();
	
	private Date start;
	private Date end;
	private String ref;
	private String surname;
	
	public FindTestPanel() {
		
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
		dim.height = 60;
		setSize(dim);
		setMinimumSize(new Dimension(200,73));
		
		JDateChooser DoDatumu = new JDateChooser();
		combobox = new JComboBox<String>();
					
		
		List rs = refreshRefs();
		
		
		
		
        Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		
		
		
		spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		portSpinner = new JSpinner(spinnerModel);
		
		
		//Border inBorder = BorderFactory.createTitledBorder();
		Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outBorder, null));
		GridBagConstraints gr = new GridBagConstraints();
				setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				
				
				lblReferee = new JLabel(rb.getString("gui.test.referee"));
				add(lblReferee);
				
				txtReferee = new JTextField(10);
				add(combobox);
				
				lblDateFrom = new JLabel(rb.getString("gui.test.from"));
				lblDateTo = new JLabel(rb.getString("gui.test.to"));
				add(lblDateFrom);
				//date.setTime(calendar.getTimeInMillis());
				
				
				
        JDateChooser od = new JDateChooser();
        
        //chooser.setMinimumSize(d);
        BorderLayout borderLayout = (BorderLayout) od.getLayout();
        
        Dimension size = od.getPreferredSize();
        size.width += 25;
        od.setPreferredSize(size);
        DoDatumu.setPreferredSize(size);
        
        od.setDate(date);
        DoDatumu.setDate(date);
        //dimchooser.height = 500;
        od.getLocale();
        DoDatumu.getLocale();
        add(od);
				
        add(lblDateTo);
        add(DoDatumu);
				
				btnFind = new JButton(rb.getString("gui.test.btnFind"));
				add(btnFind);
				btnCooperGraf = new JButton(rb.getString("gui.test.btnStat"));
				add(btnCooperGraf);
				btnCooperGraf.setEnabled(false);
				
				btnRulesGraf = new JButton(rb.getString("gui.test.btnRulesChart"));
				add(btnRulesGraf);
				btnRulesGraf.setEnabled(false);
				
				btnPDF = new JButton(rb.getString("gui.btnPDF"));
				add(btnPDF);
				btnPDF.setEnabled(false);
				
		btnFind.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            try {
				start = od.getDate();
				 end = DoDatumu.getDate();
				 ref = combobox.getSelectedItem().toString();
				 
				 //System.out.println(ref);
				
				 result = findTest(ref,start,end);
				 btnCooperGraf.setEnabled(true);
				 btnRulesGraf.setEnabled(true);
				 btnPDF.setEnabled(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				log.log(Level.SEVERE, "find exam button fail", e1);
			}
            finally {
            	if(findTestListener != null) {
					findTestListener.testPanelEvent();
				}
            }
             
            
            }
		});
		btnCooperGraf.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	if(result.size()!=0) {
            		
            		String meno = result.get(0).getRozhodcovia().getName() + " " + result.get(0).getRozhodcovia().getSurname();
                    TestGrafCooper testGraf = new TestGrafCooper(meno+" "+rb.getString("gui.test.Cooper.Chart"),meno +" "+rb.getString("gui.test.CooperRes"),result);
                    
             	 		
            	}
            	else {
            		 JOptionPane.showMessageDialog(null,rb.getString("gui.test.NoTests"));
            	}
            
            
            }
		});
		
		btnRulesGraf.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	if(result.size()!=0) {
            		
            		String meno = result.get(0).getRozhodcovia().getName() + " " + result.get(0).getRozhodcovia().getSurname();
                    TestGrafRules testGraf = new TestGrafRules(meno+" "+rb.getString("gui.test.Rules.Chart"),meno+" "+rb.getString("gui.test.RulesRes"),result);
                    
             	 		
            	}
            	else {
            		 JOptionPane.showMessageDialog(null, rb.getString("gui.test.NoTests"));
            	}
            
            
            }
		});
		
		btnPDF.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	String path = null;
            	JFileChooser chooser = new JFileChooser();
            	chooser.addChoosableFileFilter(new FileNameExtensionFilter(".pdf", "pdf")); 
            	int retrival = chooser.showSaveDialog(null);
            	 if (retrival == JFileChooser.APPROVE_OPTION)  {
                    File file = chooser.getSelectedFile();
                    path = file.getAbsolutePath();
                }
            	 if(path==null) {
            		 path="testy";
            	 }
            	pdfServis.PdfTesty(result,path);
            	JOptionPane.showMessageDialog(null, rb.getString("gui.PDFCreated"));
            
            }
		});
		
		
		
	}
	
	public List<Testy> getResult() {
		return this.result;
	}
	
	public List<Testy> findTest(String ref,Date from,Date to) {
		Rozhodcovia rozhodca = (Rozhodcovia) remote.findByName(ref);
		List<Testy> rs = remoteTest.findByDateRef(rozhodca, from, to);
		return rs;
	}
	public List<Rozhodcovia> refreshRefs() {
		List<Rozhodcovia> list = remote.getRefs();			
		combobox.removeAllItems();
		List<String> str = new ArrayList<String>();
		
		for(Rozhodcovia refs : list) {
			str.add(refs.getSurname());
		}
		
		combobox.setModel(new DefaultComboBoxModel(str.toArray()));
		
		
		return list;
	}


public void setFindTestListener(FindTestListener listener) {
	// TODO Auto-generated method stub
	this.findTestListener = listener;
}

}
