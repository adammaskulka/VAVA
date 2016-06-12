package gui.test;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import entity.Testy;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class TestGrafCooper  {
	
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	private static final Logger log = Logger.getLogger(TestGrafCooper.class.getName());
	
	ChartPanel chartPanel;
	JFrame f;
	JPanel panel;
	private JFreeChart lineChart;
	
	

	public TestGrafCooper(String applicationTitle , String chartTitle,List<Testy> testy) {
		 f = new JFrame(applicationTitle);
		
        f.setTitle(applicationTitle);
        f.setVisible(true);
        f.setSize(1000, 400);
        f.setMinimumSize(new Dimension(600, 250));
		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLayout(new BorderLayout(0, 5));
       
	       lineChart = ChartFactory.createLineChart(
	         chartTitle,
	         "Date","Distance (m)",
	         createDataset(testy),
	         PlotOrientation.VERTICAL,
	         true,true,false);
	       
	       
	         
	       chartPanel = new ChartPanel( lineChart );
	      chartPanel.setPreferredSize( new java.awt.Dimension( 1500 , 500 ) );
	      f.add(chartPanel, BorderLayout.CENTER);
	        chartPanel.setMouseWheelEnabled(true);
	        chartPanel.setHorizontalAxisTrace(true);
	        chartPanel.setVerticalAxisTrace(true);
	        
	        panel = new JPanel(new FlowLayout(FlowLayout.CENTER)); 
	        panel.add(createTrace());
	       
	        panel.add(createZoom());
	        panel.add(createImage());
	        f.add(panel, BorderLayout.SOUTH);
	        f.pack();
	        f.setLocationRelativeTo(null);
	        f.setVisible(true);
	        
	        
	      
	}
	
	private JComboBox createTrace() {
        final JComboBox trace = new JComboBox();
        final String[] traceCmds = {rb.getString("gui.EnableTrace"), rb.getString("gui.DisableTrace")};
        trace.setModel(new DefaultComboBoxModel(traceCmds));
        trace.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (traceCmds[0].equals(trace.getSelectedItem())) {
                    chartPanel.setHorizontalAxisTrace(true);
                    chartPanel.setVerticalAxisTrace(true);
                    chartPanel.repaint();
                } else {
                    chartPanel.setHorizontalAxisTrace(false);
                    chartPanel.setVerticalAxisTrace(false);
                    chartPanel.repaint();
                }
            }
        });
        return trace;
    }

   

    private JButton createZoom() {
        final JButton auto = new JButton(new AbstractAction("Reset Zoom") {

            @Override
            public void actionPerformed(ActionEvent e) {
                chartPanel.restoreAutoBounds();
            }
        });
        return auto;
    }
    
    private JButton createImage() {
        final JButton auto = new JButton(rb.getString("gui.createImage"));
        
        auto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String path = null;
            	JFileChooser chooser = new JFileChooser();
            	try {
					chooser.addChoosableFileFilter(new FileNameExtensionFilter(".jpeg", "jpeg")); 
					int retrival = chooser.showSaveDialog(null);
					 if (retrival == JFileChooser.APPROVE_OPTION)  {
					    File file = chooser.getSelectedFile();
					    path = file.getAbsolutePath();
					}
					 if(path==null) {
						 path="graf";
					 }
					 int width = f.getWidth(); /* Width of the image */
					 int height = f.getHeight(); /* Height of the image */ 
					 File cesta = new File(path); 
					 ChartUtilities.saveChartAsJPEG(cesta ,lineChart, width ,height);
					
					JOptionPane.showMessageDialog(null, rb.getString("gui.ImageCreated"));
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					log.log(Level.SEVERE, "jpeg chart create fail", e1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					log.log(Level.SEVERE, "jpeg chart create fail", e1);
				}
				
			}
        	
        });
        
        return auto;
    }
	
	
	private DefaultCategoryDataset createDataset(List<Testy> testy )
	   {
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      
	      for(Testy t : testy) {
	    	  dataset.addValue( t.getCooper() , "Cooper" , t.getExamdate().toLocaleString().substring(0,4) );
	      }
	     
	      return dataset;
	   }
	

}
