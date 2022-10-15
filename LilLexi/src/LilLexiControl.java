import org.eclipse.swt.graphics.Font;

/**
 * Controller
 */
public class LilLexiControl 
{
	private LilLexiDoc currentDoc;

	/**
	 * LilLexiControl
	 */
	public LilLexiControl( LilLexiDoc doc )
	{
		this.currentDoc = doc;
	}
	
	/**
	 * selectCard  user selects a card
	 */
	void addChar(String c, Font font) 
	{	
		currentDoc.addChar(c, font);
	}	
	
	void addShape(String shape) {
	    currentDoc.addShape(shape);
	}
	
	/**
	 * quitEditor  user quits
	 */
	void  quitEditor() 
	{ 
		System.exit(0); 
	}
}






