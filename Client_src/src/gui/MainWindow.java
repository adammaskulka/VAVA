package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import facade.CasomeracBeanRemote;
import facade.RozhodcaBeanRemote;
import facade.StadionBeanRemote;
import facade.TestBeanRemote;
import facade.TimBeanRemote;
import facade.ZapasBeanRemote;
import gui.casomerac.CasomeracPanel;
import gui.casomerac.CasomeracTabulka;
import gui.casomerac.NewCasomeracListener;
import gui.rozhodca.NewRefListener;
import gui.rozhodca.NoMatchTabulka;
import gui.rozhodca.RozhodcaPanel;
import gui.rozhodca.RozhodcaTabulka;
import gui.rozhodca.UpdateRefListener;
import gui.rozhodca.UpdateRozhodca;
import gui.stadion.NewStadiumListener;
import gui.stadion.StadionPanel;
import gui.stadion.StadionTabulka;
import gui.test.BadResultTabulka;
import gui.test.FindTestListener;
import gui.test.FindTestPanel;
import gui.test.FindTestTabulka;
import gui.test.NewTestListener;
import gui.test.TestPanel;
import gui.test.TestTabulka;
import gui.tim.NewTimListener;
import gui.tim.TimPanel;
import gui.tim.TimTabulka;
import gui.zapas.NewZapasListener;
import gui.zapas.ZapasPanel;
import gui.zapas.ZapasStatistika;
import gui.zapas.ZapasTabulka;



@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	ResourceBundle rb = ResourceBundle.getBundle("guitext", Locale.getDefault());
	
	
	Context ctx;
	RozhodcaBeanRemote remote;
	TestBeanRemote remoteTest;
	CasomeracBeanRemote remoteCasomerac;
	TimBeanRemote remoteTim;
	StadionBeanRemote remoteStadion;
	ZapasBeanRemote remoteZapas;
	
	
	//super(new GridLayout(1, 1));
	private RozhodcaPanel rozhodcapanel;
	public RozhodcaTabulka rozhodcatabulka;
	
	
	/*private ZapasPanel zapaspanel;
	private ZapasTabulka zapastabulka;
	private StadionPanel stadionpanel;
	private StadionTabulka stadiontabulka;
	private TimPanel timpanel;
	private TimTabulka timtabulka;
	
	private CasomeracPanel casomeracpanel;
	private CasomeracTabulka casomeractabulka;
	private UpdateRozhodca updaterozhodca;
	private ZapasStatistika zapasstatistika;
	private BadResultTabulka badresulttabulka;*/
	private NoMatchTabulka nomatchtabulka;
	private TestPanel testpanel;
	private TestTabulka testtabulka;
	private FindTestTabulka testtabulkaFind;
	private FindTestPanel findtestpanel;
	private CasomeracPanel casomeracpanel;
	private CasomeracTabulka casomeractabulka;
	private TimPanel timpanel;
	private TimTabulka timtabulka;
	private StadionPanel stadionpanel;
	private StadionTabulka stadiontabulka;
	private ZapasPanel zapaspanel;
	private ZapasTabulka zapastabulka;
	private ZapasStatistika zapasstatistika;
	private BadResultTabulka badresulttabulka;
	
	private JSplitPane splitpanelRozhodca;
	private JSplitPane splitpanelZapas;
	private JSplitPane splitpanelStadion;
	private JSplitPane splitpanelTim;
	private JSplitPane splitpanelCasomerac;
	private JSplitPane splitpanelTestFind;
	private JSplitPane splitpanelTest;
	
	/*RozhodcaServis rozhodcaServis = new RozhodcaServis();
	TimServis timServis = new TimServis();
	StadionServis stadionServis = new StadionServis();
	CasomeracServis casomeracServis = new CasomeracServis();
	TestServis testServis = new TestServis();
	ZapasServis zapasServis = new ZapasServis();*/
	private static final Logger log = Logger.getLogger(MainWindow.class.getName());
	
	
	public MainWindow() {
		
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
		
		try {
			remoteCasomerac = (CasomeracBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/CasomeracBean!facade.CasomeracBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Casomerac remote lookup error",e);
		}
		
		try {
			remoteTim = (TimBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/TimBean!facade.TimBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Tim remote lookup error",e);
		}
		
		try {
			remoteStadion = (StadionBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/StadionBean!facade.StadionBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Stadion remote lookup error",e);
		}
		
		try {
			remoteZapas = (ZapasBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/ZapasBean!facade.ZapasBeanRemote");
		} catch (NamingException e) {
			log.log(Level.SEVERE, "Zapas remote lookup error",e);
		}
		
		
		
		//super(rb.getString("gui.MainWindow.rozhodca"));
		setTitle(rb.getString("gui.MainWindow.rozhodca"));
		
		setVisible(true);
		Dimension dim = getPreferredSize();
		dim.width = 1000;
		dim.height = 700;
		setPreferredSize(dim);		
		setMinimumSize(dim);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		Locale locale = Locale.getDefault();
		log.log(Level.CONFIG, "Get locale: " +locale);
		rozhodcapanel = new RozhodcaPanel();
		rozhodcatabulka = new RozhodcaTabulka();
		testpanel = new TestPanel();
		testtabulka = new TestTabulka();
		testtabulkaFind = new FindTestTabulka();
		findtestpanel = new FindTestPanel();
		casomeracpanel = new CasomeracPanel();
		casomeractabulka = new CasomeracTabulka();
		timpanel = new TimPanel();
		timtabulka = new TimTabulka();
		stadionpanel = new StadionPanel();
		stadiontabulka = new StadionTabulka();
		zapaspanel = new ZapasPanel();
		zapastabulka = new ZapasTabulka();
		zapasstatistika = new ZapasStatistika();
		badresulttabulka = new BadResultTabulka();
		
		//nomatchtabulka = new NoMatchTabulka();
		
		
		Dimension mintabulka = new Dimension(400, 110);
		rozhodcatabulka.setMinimumSize(mintabulka);
		testtabulka.setMinimumSize(mintabulka);
		testtabulkaFind.setMinimumSize(mintabulka);
		casomeractabulka.setMinimumSize(mintabulka);
		timtabulka.setMinimumSize(mintabulka);
		stadiontabulka.setMinimumSize(mintabulka);
		zapastabulka.setMinimumSize(mintabulka);
		/*casomeractabulka.setMinimumSize(mintabulka);
		zapastabulka.setMinimumSize(mintabulka);
		stadiontabulka.setMinimumSize(mintabulka);
		timtabulka.setMinimumSize(mintabulka);
		testtabulka.setMinimumSize(mintabulka);
		testtabulkaFind.setMinimumSize(mintabulka);*/
		
		rozhodcatabulka.setData(remote.getRefs());	
		testtabulka.setData(remoteTest.findAll());
		casomeractabulka.setData(remoteCasomerac.findAll());
		timtabulka.setData(remoteTim.findAll());
		stadiontabulka.setData(remoteStadion.findAll());
		zapastabulka.setData(remoteZapas.findAll());
		//badresulttabulka.setData(remote.findBadRefs());
		
		/*timtabulka.setData(timServis.findAll());
		stadiontabulka.setData(stadionServis.findAll());
		casomeractabulka.setData(casomeracServis.findAll());
		zapastabulka.setData(zapasServis.findAll());
		testtabulka.setData(testServis.findAll());
		badresulttabulka.setData(testServis.FindBadRefs());*/
		//nomatchtabulka.setData(remote.NoMatchRefs());
		log.log(Level.FINER, "all table setting data successful");
		
		
		
		splitpanelRozhodca = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,rozhodcatabulka,rozhodcapanel);
		splitpanelTest = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,testtabulka,testpanel);
		splitpanelTestFind = new JSplitPane(JSplitPane.VERTICAL_SPLIT,findtestpanel,testtabulkaFind);
		splitpanelCasomerac = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,casomeractabulka,casomeracpanel);
		splitpanelTim = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,timtabulka,timpanel);
		splitpanelStadion = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,stadiontabulka,stadionpanel);
		splitpanelZapas = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,zapastabulka,zapaspanel);
		//splitpanelZapas = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,zapastabulka,zapaspanel);
		/*splitpanelStadion = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,stadiontabulka,stadionpanel);
		splitpanelTim = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,timtabulka,timpanel);
		splitpanelTestFind = new JSplitPane(JSplitPane.VERTICAL_SPLIT,findtestpanel,testtabulkaFind);
		splitpanelTest = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,testtabulka,testpanel);
		splitpanelCasomerac = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,casomeractabulka,casomeracpanel);*/
		
		rozhodcapanel.setNewRefListener(new NewRefListener() {
			@Override
			public void rozhodcaPanelEvent() {
				// TODO Auto-generated method stub
				rozhodcatabulka.refresh();
				rozhodcatabulka.setData(remote.getRefs());
			}
		});
		
		testpanel.setNewTestListener(new NewTestListener() {

			@Override
			public void testPanelEvent() {
				testtabulka.refresh();
				testtabulka.setData(remoteTest.findAll());		
			}
			
		});
		
		findtestpanel.setFindTestListener(new FindTestListener() {

			public void testPanelEvent() {
				testtabulkaFind.refresh();
				testtabulkaFind.setData(findtestpanel.getResult());				
			}
			
		});
		
		casomeracpanel.setNewCasomeracListener(new NewCasomeracListener() {

			@Override
			public void casomeracPanelEvent() {
				casomeractabulka.refresh();
				casomeractabulka.setData(remoteCasomerac.findAll());				
			}
			
		});
		
		timpanel.setNewTimListener(new NewTimListener() {

			@Override
			public void timPanelEvent() {
				timtabulka.refresh();
				timtabulka.setData(remoteTim.findAll());
				stadionpanel.refreshCombo();
				
			}		
		});
		
		stadionpanel.setNewStadiumListener(new NewStadiumListener() {

			@Override
			public void stadionPanelEvent() {
				stadiontabulka.refresh();
				stadiontabulka.setData(remoteStadion.findAll());				
			}
			
		});
		
		zapaspanel.setNewZapasListener(new NewZapasListener() {

			@Override
			public void zapasPanelEvent() {
				zapastabulka.setData(remoteZapas.findAll());
				zapastabulka.refresh();
			}
			
		});
		
		
	/*	timpanel.setNewTimListener(new NewTimListener() {

			@Override
			public void timPanelEvent() {
				timtabulka.refresh();
				timtabulka.setData(timServis.findAll());
				stadionpanel.refreshCombo();
				
			}		
		});
		
		stadionpanel.setNewStadiumListener(new NewStadiumListener() {

			@Override
			public void stadionPanelEvent() {
				stadiontabulka.refresh();
				stadiontabulka.setData(stadionServis.findAll());				
			}
			
		});
		
		casomeracpanel.setNewCasomeracListener(new NewCasomeracListener() {

			@Override
			public void casomeracPanelEvent() {
				casomeractabulka.refresh();
				casomeractabulka.setData(casomeracServis.findAll());				
			}
			
		});
		
		testpanel.setNewTestListener(new NewTestListener() {

			@Override
			public void testPanelEvent() {
				testtabulka.refresh();
				testtabulka.setData(testServis.findAll());		
			}
			
		});
		zapaspanel.setNewZapasListener(new NewZapasListener() {

			@Override
			public void zapasPanelEvent() {
				zapastabulka.setData(zapasServis.findAll());
				zapastabulka.refresh();
			}
			
		});
		
		updaterozhodca.setUpdateRefListener(new UpdateRefListener() {

			@Override
			public void rozhodcaPanelEvent() {
				rozhodcatabulka.refresh();
				rozhodcatabulka.setData(rozhodcaServis.findAll());				
			}
			
		});
		
		findtestpanel.setFindTestListener(new FindTestListener() {

			public void testPanelEvent() {
				testtabulkaFind.refresh();
				testtabulkaFind.setData(findtestpanel.getResult());				
			}
			
		});*/
		
		ImageIcon iconRef = createImageIcon("icons/Referee-64.png");
		ImageIcon iconStad = createImageIcon("icons/Stadium-48.png");
		ImageIcon iconTeam = createImageIcon("icons/Teams-50.png");
		ImageIcon iconTime = createImageIcon("icons/Time-48.png");
		ImageIcon iconMatch = createImageIcon("icons/Hockey-50.png");
		ImageIcon iconExam = createImageIcon("icons/Test-paper-icon.png");
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JTabbedPane tabbedPaneZapas = new JTabbedPane(JTabbedPane.NORTH);
		JTabbedPane tabbedPaneTest = new JTabbedPane(JTabbedPane.NORTH);
		JTabbedPane tabbedPaneMatchStat = new JTabbedPane(JTabbedPane.NORTH);
		JTabbedPane tabbedPaneRozhodca = new JTabbedPane(JTabbedPane.NORTH);
		//getContentPane().add(tabbedPaneZapas, BorderLayout.CENTER);
		
		tabbedPane.addTab(rb.getString("gui.MainWindow.panelRozhodca"), iconRef , tabbedPaneRozhodca ,rb.getString("gui.MainWindow.panelRozhodcaInfo"));		
		tabbedPane.addTab(rb.getString("gui.MainWindow.panelZapas"), iconMatch , tabbedPaneZapas ,rb.getString("gui.MainWindow.panelZapasInfo"));
		tabbedPane.addTab(rb.getString("gui.MainWindow.panelCas"), iconTime , splitpanelCasomerac,rb.getString("gui.MainWindow.panelCasInfo"));
		tabbedPane.addTab(rb.getString("gui.MainWindow.panelTimy"), iconTeam , splitpanelTim,rb.getString("gui.MainWindow.panelTimInfo"));
		tabbedPane.addTab(rb.getString("gui.MainWindow.panelStadion"), iconStad , splitpanelStadion ,rb.getString("gui.MainWindow.panelStadionInfo"));
		tabbedPane.addTab(rb.getString("gui.MainWindow.panelTesty"),iconExam , tabbedPaneTest, null);
		
		tabbedPaneZapas.addTab(rb.getString("gui.MainWindow.panelZapas.allmatches"), null , zapastabulka ,null);
		tabbedPaneZapas.addTab(rb.getString("gui.MainWindow.panelZapas.addmatch"), null , zapaspanel ,null);
		tabbedPaneZapas.addTab(rb.getString("gui.MainWindow.panelZapas.findmatch"), null , tabbedPaneMatchStat ,null);
		
		tabbedPaneMatchStat.addTab("Statistics", null , zapasstatistika ,null);
		
		/*tabbedPane.addTab(rb.getString("gui.MainWindow.panelZapas"), iconMatch , tabbedPaneZapas ,rb.getString("gui.MainWindow.panelZapasInfo"));
		tabbedPane.addTab(rb.getString("gui.MainWindow.panelStadion"), iconStad , splitpanelStadion ,rb.getString("gui.MainWindow.panelStadionInfo"));
		tabbedPane.addTab(rb.getString("gui.MainWindow.panelTimy"), iconTeam , splitpanelTim,rb.getString("gui.MainWindow.panelTimInfo"));
		tabbedPane.addTab(rb.getString("gui.MainWindow.panelCas"), iconTime , splitpanelCasomerac,rb.getString("gui.MainWindow.panelCasInfo"));
		tabbedPane.addTab(rb.getString("gui.MainWindow.panelTesty"),iconExam , tabbedPaneTest, null);*/
		//tabbedPane.addTab("ZAPAS", null , tabbedPaneZapas,null);
		//tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		//tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
		
		/*tabbedPaneZapas.addTab(rb.getString("gui.MainWindow.panelZapas.allmatches"), null , zapastabulka ,null);
		tabbedPaneZapas.addTab(rb.getString("gui.MainWindow.panelZapas.addmatch"), null , zapaspanel ,null);
		tabbedPaneZapas.addTab(rb.getString("gui.MainWindow.panelZapas.findmatch"), null , tabbedPaneMatchStat ,null);
		
		tabbedPaneTest.addTab(rb.getString("gui.MainWindow.panelTesty.alltests"), null , splitpanelTest ,null);
		tabbedPaneTest.addTab(rb.getString("gui.MainWindow.panelTesty.findtest"), null , splitpanelTestFind ,null);
		tabbedPaneTest.addTab("Refs with bad results", null , badresulttabulka ,null);
		
		tabbedPaneMatchStat.addTab("Statistics", null , zapasstatistika ,null);*/
		
		tabbedPaneTest.addTab(rb.getString("gui.MainWindow.panelTesty.alltests"), null , splitpanelTest ,null);
		tabbedPaneTest.addTab(rb.getString("gui.MainWindow.panelTesty.findtest"), null , splitpanelTestFind ,null);
		tabbedPaneTest.addTab(rb.getString("gui.MainWindow.badRefs"), null , badresulttabulka ,null);
		
		
		tabbedPaneRozhodca.addTab(rb.getString("gui.MainWindow.panelRozhodca.add"), null , splitpanelRozhodca ,null);
	//	tabbedPaneRozhodca.addTab("Referees with no match", null , nomatchtabulka ,null);
		
		
		//setJMenuBar(createMenu());
		}
	private JMenuBar createMenu() {
		JMenuBar menu = new JMenuBar();

		JMenu fileMenu = new JMenu(rb.getString("gui.MainWindow.menuFile"));
	/*	JMenuItem exportItem = new JMenuItem("Export Objednavky");
		JMenuItem importItem = new JMenuItem("Import Objednavky");
		JMenuItem exportZam = new JMenuItem("Export Zamestnanci");
		JMenuItem importZam = new JMenuItem("Import Zamestnanci");*/
		JMenuItem exitItem = new JMenuItem(rb.getString("gui.MainWindow.menuExit"));

		//fileMenu.add(exportItem);
		//fileMenu.add(importItem);
		//fileMenu.addSeparator();
		//fileMenu.add(exportZam);
		//fileMenu.add(importZam);
	//	fileMenu.addSeparator();
		fileMenu.add(exitItem);


		menu.add(fileMenu);

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		/*
		 * @param importItem sluzi na nacitanie objednavok zo suboru cez controller a nasledne sa refreshne tabulka
		 * @throws Vyhadzuje vynimku, ak sa data nepodari nacitat.
		 */
		return menu;
	}
	protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainWindow.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	

		
	}


