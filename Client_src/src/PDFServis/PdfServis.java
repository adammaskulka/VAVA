package PDFServis;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import entity.Rozhodcovia;
import entity.Testy;
import entity.Zapasy;
import facade.RozhodcaBeanRemote;
import gui.rozhodca.RozhodcaPanel;

public class PdfServis {
	
	Context ctx;
	RozhodcaBeanRemote remote;
	private static final Logger log = Logger.getLogger(PdfServis.class.getName());
	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	
	List<Rozhodcovia>  roz = null;
	
	//List<Testy>  testy = null;
	
	public void PdfRozhodca(String path) {
		
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
		
		
			roz = remote.getRefs();
			Document document = new Document();

		    try {
		      PdfWriter.getInstance(document, new FileOutputStream(path+".pdf"));
		      document.open();
		      
		      
		      PdfPTable table = new PdfPTable(4);
		     
		      PdfPCell c1 = new PdfPCell(new Phrase(rb.getString("gui.rozhodca.tabMeno")));
		      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		      table.addCell(c1);

		      c1 = new PdfPCell(new Phrase(rb.getString("gui.rozhodca.tabPriezvisko")));
		      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		      table.addCell(c1);

		      c1 = new PdfPCell(new Phrase("Mail"));
		      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		      table.addCell(c1);
		      
		      c1 = new PdfPCell(new Phrase(rb.getString("gui.rozhodca.tabMesto")));
		      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		      table.addCell(c1);
		      
		      for(Rozhodcovia r : roz) {
		    	  
		    	  table.addCell(r.getName());
		    	  table.addCell(r.getSurname());
		    	  if(r.getMail()==null) {
		    		  table.addCell(" ");
		    	  }
		    	  else {
		    		  table.addCell(r.getMail());
		    	  }
		    	  if(r.getCity()==null) {
		    		  table.addCell(" ");
		    	  }
		    	  else {
		    		  table.addCell(r.getCity());
		    	  }
		    	  
		      }
		       
		      document.add(table);
		      document.close();
		    } catch (Exception e) {
		    	log.log(Level.SEVERE, "Rozhodca PDF error",e);
		    }
	}
	
public void PdfTesty(List<Testy> testy,String path) {
		
		
		
			
			Document document = new Document();

		    try {
		      PdfWriter.getInstance(document, new FileOutputStream(path+".pdf"));
		      document.open();
		      
		      
		      PdfPTable table = new PdfPTable(4);
		     
		      PdfPCell c1 = new PdfPCell(new Phrase(rb.getString("gui.MainWindow.rozhodca")));
		      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		      table.addCell(c1);

		      c1 = new PdfPCell(new Phrase(rb.getString("gui.zapas.tabDate")));
		      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		      table.addCell(c1);

		      c1 = new PdfPCell(new Phrase("Cooper"));
		      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		      table.addCell(c1);
		      
		      c1 = new PdfPCell(new Phrase(rb.getString("gui.test.rule")));
		      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		      table.addCell(c1);
		      
		      for(Testy t : testy) {
		    	  
		    	  table.addCell(t.getRozhodcovia().getSurname());
		    	  table.addCell(t.getExamdate().toGMTString());
		    	  if(t.getCooper()==null) {
		    		  table.addCell(" ");
		    	  }
		    	  else {
		    		  table.addCell(t.getCooper().toString());
		    	  }
		    	  if(t.getRules()==null) {
		    		  table.addCell(" ");
		    	  }
		    	  else {
		    		  table.addCell(t.getRules().toString());
		    	  }
		    	  
		      }
		       
		      document.add(table);
		      document.close();
		    } catch (Exception e) {
		    	log.log(Level.SEVERE, "testy PDF error",e);
		    }
	}


public void PdfZapasy(List<Zapasy> testy,String path) {
	
	
	
	
	Document document = new Document();
	PdfPTable table;

    try {
      PdfWriter.getInstance(document, new FileOutputStream(path+".pdf"));
      document.open();
      
      
      
     
      
      for(Zapasy t : testy) {
    	  
    	  table = new PdfPTable(2);
    	  table.setSpacingBefore(5);
          table.setSpacingAfter(5);
          
       /*   gui.zapas.newmatch=Nov\u00FD zapas
        		  gui.zapas.tabAway=Dom\u00E1ci
        		  gui.zapas.tabCR=\u010Ciarov\u00FD rozhodca
        		  gui.zapas.tabDate=D\u00E1tum
        		  gui.zapas.tabHR=Hlavn\u00FD rozhodca
        		  gui.zapas.tabHome=Dom\u00E1ci
        		  gui.zapas.tabStadium=\u0160tadi\u00F3n*/
    	  
    	  table.addCell(rb.getString("gui.zapas.tabDate"));
    	  table.addCell(t.getDate().toGMTString().substring(0,11));
    	  table.addCell(rb.getString("gui.zapas.tabHome"));
    	  table.addCell(t.getTimyByHometeam().getName());
    	  table.addCell(rb.getString("gui.zapas.tabAway"));
    	  table.addCell(t.getTimyByAwayteam().getName());
    	  table.addCell(rb.getString("gui.zapas.tabHR"));
    	  table.addCell(t.getRozhodcoviaByHr1().getSurname()+" "+t.getRozhodcoviaByHr1().getName());
    	  if(t.getRozhodcoviaByHr2() == null) {
    		  table.addCell(rb.getString("gui.zapas.tabHR"));
    		  table.addCell("");
    	  }
    	  else {   		  
    		  table.addCell(rb.getString("gui.zapas.tabHR"));
        	  table.addCell(t.getRozhodcoviaByHr2().getSurname()+" "+t.getRozhodcoviaByHr2().getName());  		  
    	  }
    	  
    	  if(t.getRozhodcoviaByCr1() == null) {
    		  table.addCell(rb.getString("gui.zapas.tabCR"));
    		  table.addCell("");
    	  }
    	  else {   		  
    		  table.addCell(rb.getString("gui.zapas.tabCR"));
        	  table.addCell(t.getRozhodcoviaByCr1().getSurname()+" "+t.getRozhodcoviaByCr1().getName());  		  
    	  }
    	  
    	  if(t.getRozhodcoviaByCr2() == null) {
    		  table.addCell(rb.getString("gui.zapas.tabCR"));
    		  table.addCell("");
    	  }
    	  else {   		  
    		  table.addCell(rb.getString("gui.zapas.tabCR"));
        	  table.addCell(t.getRozhodcoviaByCr2().getSurname()+" "+t.getRozhodcoviaByCr2().getName());  		  
    	  }
    	  table.addCell(rb.getString("gui.zapas.tabStadium"));
    	  table.addCell(t.getStadiony().getName());
    	  table.addCell(rb.getString("gui.lblCasomerac"));
    	  table.addCell(t.getCasomeraci().getSurname()+" "+t.getCasomeraci().getName());
    	  
    	  
    	  document.add(table);
    	  
    	  
      }
       
     // document.add(table);
      document.close();
    } catch (Exception e) {
    	log.log(Level.SEVERE, "zapasy PDF error",e);
    }
}
        
      

}
