/**
 * UI for Lil Lexi
 * 
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Font;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.ScrolledComposite;
import java.io.*;
/**
 * LilLexiUI
 * 
 */
public class LilLexiUI
{
	private LilLexiDoc currentDoc;
	private LilLexiControl lexiControl;
	private Display display;
	private Shell shell;
	private Label statusLabel;
	private Canvas canvas;
	private Font globalFont;
	List<String> dictionary = new ArrayList<>();
	/**
	 * Ctor
	 * @throws IOException 
	 */
	public LilLexiUI()
	{
		//---- create the window and the shell
		Display.setAppName("Lil Lexi");
		display = new Display();  
		shell = new Shell(display);
	    shell.setText("Lil Lexi");
		shell.setSize(900,900);
		shell.setLayout(new GridLayout());
		File file = new File("words.txt");
		BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		String str;
        try {
            while ((str = br.readLine()) != null) {
                dictionary.add(str);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
		
	/**
	 * start the editor
	 */
	public void start()
	{	
		//---- create widgets for the interface
	    Composite upperComp = new Composite(shell, SWT.NO_FOCUS);
	    Composite lowerComp = new Composite(shell, SWT.NO_FOCUS);
	    
	    //---- canvas for the document
		canvas = new Canvas(upperComp, SWT.NONE);
		canvas.setSize(800,800);
		globalFont = new Font(display, "Courier", 24, SWT.BOLD );

		canvas.addPaintListener(e -> {
			LexiCursor cursor = currentDoc.getCursor();
		    System.out.println("PaintListener");
			Rectangle rect = shell.getClientArea();
			e.gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE)); 
            e.gc.fillRectangle(rect.x, rect.y, rect.width, rect.height);
            e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLUE)); 
    		List<List<Glyph>> glyphs = currentDoc.getGlyphs();
    		int column = 0; int row = 0;
    		for (List<Glyph> list: glyphs){ 
    		    for (Glyph g: list) {
    		        Font tempfont = g.getFont();
                    e.gc.setFont(tempfont);
                    g.draw(e, cursor.getXCoord(), cursor.getYCoord());
                    cursor.moveRight(glyphs);
                    //e.gc.drawString("" + g.getChar() , column, row + 10);    
//                    column = (column + 18) % (40*18);
//                    if (column == 0) row += 32;
                    System.out.println(g.getChar());
    		        
    		    }
    		}
    		    {
    			
    		}
		});	
		
        canvas.addMouseListener(new MouseListener() {
            public void mouseDown(MouseEvent e) {
            	System.out.println("mouseDown in canvas");
            } 
            public void mouseUp(MouseEvent e) {} 
            public void mouseDoubleClick(MouseEvent e) {} 
        });
        
        canvas.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent e) {
        		System.out.println("key " + e.character);
        		if (e.character == SWT.BS) {
        			
        		}
        		else {
        			lexiControl.addChar(Character.toString(e.character) , globalFont);
        		}
        	}
        	public void keyReleased(KeyEvent e) {}
        });
		Slider slider = new Slider (canvas, SWT.VERTICAL);
		Rectangle clientArea = canvas.getClientArea ();
		slider.setBounds (clientArea.width - 40, clientArea.y + 10, 32, clientArea.height);
		slider.addListener (SWT.Selection, event -> {
			String string = "SWT.NONE";
			switch (event.detail) {
				case SWT.DRAG: string = "SWT.DRAG"; break;
				case SWT.HOME: string = "SWT.HOME"; break;
				case SWT.END: string = "SWT.END"; break;
				case SWT.ARROW_DOWN: string = "SWT.ARROW_DOWN"; break;
				case SWT.ARROW_UP: string = "SWT.ARROW_UP"; break;
				case SWT.PAGE_DOWN: string = "SWT.PAGE_DOWN"; break;
				case SWT.PAGE_UP: string = "SWT.PAGE_UP"; break;
			}
			System.out.println ("Scroll detail -> " + string);
		});
		        
        //---- status label
        lowerComp.setLayout(new RowLayout());
        statusLabel = new Label(lowerComp, SWT.NONE);		

		FontData[] fD = statusLabel.getFont().getFontData();
		fD[0].setHeight(24);
		statusLabel.setFont( new Font(display,fD[0]));
		statusLabel.setText("Ready to edit!");
		
		//---- main menu
		Menu menuBar, fileMenu, fontMenu, insertMenu, helpMenu, insertImageMenu;
		MenuItem fileMenuHeader, fontMenuHeader, insertMenuHeader, helpMenuHeader, insertImageHeader;
		MenuItem fileExitItem, fileSaveItem, helpGetHelpItem;
		MenuItem fontTimesNewRoman, fontCourier, fontAriel;
		MenuItem insertRectItem, insertTriItem, insertCirItem, insertAvaImageItem, insertDogImageItem, insertPeachImageItem;
		

		menuBar = new Menu(shell, SWT.BAR);
		
		fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("File");
		fileMenu = new Menu(shell, SWT.DROP_DOWN);
		fileMenuHeader.setMenu(fileMenu);

	    fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileSaveItem.setText("Save");
	    fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileExitItem.setText("Exit");
	    
	    //working
		fontMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fontMenuHeader.setText("Font");
		fontMenu = new Menu(shell, SWT.DROP_DOWN);
		fontMenuHeader.setMenu(fontMenu);

	    fontTimesNewRoman = new MenuItem(fontMenu, SWT.RADIO);
	    fontTimesNewRoman.setText("Times New Roman");
	    fontCourier = new MenuItem(fontMenu, SWT.RADIO);
	    fontCourier.setText("Courier");
	    fontAriel = new MenuItem(fontMenu, SWT.RADIO);
	    fontAriel.setText("Ariel");
	    
	    MenuItem[] mi = fontMenu.getItems();
	    for(MenuItem item : mi) {
	    	item.addSelectionListener(new SelectionListener() {
	    		public void widgetSelected(SelectionEvent e) {
	    			fontSelection(e);
		    	}
		    	public void widgetDefaultSelected(SelectionEvent e) {
		    		fontSelection(e);
		    	}
		    });
	    }
	    
	    
	    //Default font
	    fontCourier.setSelection(true);
	    
		insertMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		insertMenuHeader.setText("Insert");
		
		insertMenu = new Menu(shell, SWT.DROP_DOWN);
		insertMenuHeader.setMenu(insertMenu);
		
		insertImageHeader = new MenuItem(insertMenu, SWT.CASCADE);
		insertImageHeader.setText("Image");
		insertImageMenu = new Menu(shell, SWT.DROP_DOWN);
		insertImageHeader.setMenu(insertImageMenu);
		insertAvaImageItem = new MenuItem(insertImageMenu, SWT.PUSH);
		insertAvaImageItem.setText("Avacado");
		insertDogImageItem = new MenuItem(insertImageMenu, SWT.PUSH);
		insertDogImageItem.setText("Dog");
		insertPeachImageItem =new MenuItem(insertImageMenu, SWT.PUSH);
		insertPeachImageItem.setText("Peach");
		
	    insertRectItem = new MenuItem(insertMenu, SWT.PUSH);
	    insertRectItem.setText("Rectangle");
        insertCirItem = new MenuItem(insertMenu, SWT.PUSH);
        insertCirItem.setText("Circle");
        
        mi = insertMenu.getItems();
        for(MenuItem item : mi) {
            item.addSelectionListener(new SelectionListener() {
                public void widgetSelected(SelectionEvent e) {
                    insertSelection(e);
                }
                public void widgetDefaultSelected(SelectionEvent e) {
                    insertSelection(e);
                }
            });
        }

	    helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    helpMenuHeader.setText("Help");
	    helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);

	    helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetHelpItem.setText("Get Help");
	    
	    MenuItem helpSpellCheckItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpSpellCheckItem.setText("SpellCheck");
	    
	    fileExitItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    		shell.close();
	    		display.dispose();
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    		shell.close();
	    		display.dispose();
	    	}
	    });
	    fileSaveItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    	}	    		
	    });

	    helpGetHelpItem.addSelectionListener(new SelectionListener() {
	    	public void widgetSelected(SelectionEvent event) {
	    	}
	    	public void widgetDefaultSelected(SelectionEvent event) {
	    	}	    		
	    });	
	    helpSpellCheckItem.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent event) {
                spellcheck();
            }
            public void widgetDefaultSelected(SelectionEvent event) {
                spellcheck();
            }               
        });
	    
	    shell.setMenuBar(menuBar);
	      
		//---- event loop
		shell.open();
		while( !shell.isDisposed())
			if(!display.readAndDispatch()){}
		display.dispose();
	} 

	/**
	 * updateUI
	 */
	public void updateUI()
	{
		System.out.println("updateUI");
		canvas.redraw();
	}
	
	/**
	 * setCurrentDoc
	 */
	public void setCurrentDoc(LilLexiDoc cd) { currentDoc = cd; }
	/**
	 * setController
	 */
	public void setController(LilLexiControl lc) { lexiControl = lc; }
	
	public void fontSelection(SelectionEvent e) {
		MenuItem m = (MenuItem) e.getSource();
		if (m.getSelection()) {
			globalFont = new Font(display, m.getText(), 24, SWT.BOLD );
		}
	}
	public void insertSelection(SelectionEvent e) {
	    MenuItem m = (MenuItem) e.getSource();
	    if (m.getText() != "Image") {
	        lexiControl.addShape(m.getText());   
	    }
	    else if (m.getText() == "Image") {
	        //lexiControl.addImage(m.getText());
	    }
	}
	public void spellcheck() {
        List<List<Glyph>> glyphs = currentDoc.getGlyphs();
        for (List<Glyph> m: glyphs)
        {   for (int x = 0; x < m.size(); x++) {
            String word = "";
            if ( m.get(x).getChar() != null && Character.isLetter(m.get(x).getChar().charAt(0))) {
                while (x<m.size() && m.get(x).getChar() != null && Character.isLetter(m.get(x).getChar().charAt(0))) {
                    word+= m.get(x).getChar();
                    x++;
                }
            }
            if (word.length() != 0) {
                if (!dictionary.contains(word)){
                    statusLabel.setText("Misspelled Word!");
                }
            }
        }
    };
	}
}

