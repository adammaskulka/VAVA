package gui;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
	
	private static final Logger log = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
		
		/*TestServis testServis = new TestServis();
		
		List<Testy> testy = testServis.findAll();
				
		//Casomeraci timekeeper = (Casomeraci) casomeracServis.findByName("Maskulka");
		
		System.out.println(testy.get(0).getRozhodcovia());

		//SessionFactory sf = new Configuration()
			//	  .configure(new java.io.File("C:\\Users\\User\\workspace\\Databaza rozhodcov\\src\\hibernate.cfg.xml")).buildSessionFactory();
		//SessionFactory sf = HibernateUtil.getSessionFactory();*/
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				try {
				   // UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
				    //UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
					 UIManager.setLookAndFeel(
							 UIManager.getCrossPlatformLookAndFeelClassName());
					    
				    new MainWindow();
				    log.log(Level.INFO, "initiation of main window and look and feel successful");
				} catch (Exception e) {
					log.log(Level.SEVERE, "initiation of main window fail", e);
				}
			}
		
		});
		//SessionFactory sf = HibernateUtil.getSessionFactory();
		/*Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();

		Rozhodcovia r1 = new Rozhodcovia("Adam","Ma�kulka","adammaskulka95@gmail.com","Spi�sk� Teplica","Cintor�nska 503",7,'D');
			//session.persist(r1);
		
			
		t.commit();
		session.close();*/
	}

}
