/**
 * Lil Lexi Document Model
 * 
 */
import java.util.List;

import org.eclipse.swt.graphics.Font;

import java.util.ArrayList;

/**
 * LilLexiDoc
 */
public class LilLexiDoc 
{
	private LilLexiUI ui;
	private List<List<Glyph>> glyphs;
	private LexiCursor cursor;
	private LexiComposer composer;
	
	/**
	 * Ctor
	 */
	public LilLexiDoc() 
	{
		glyphs = new ArrayList<List<Glyph>>();
		glyphs.add(new ArrayList<Glyph>());

		cursor = new LexiCursor();
		composer = new LexiComposer(720);

	}
	
	/**
	 * setUI
	 */
	public void setUI(LilLexiUI ui) {this.ui = ui;}

	/**
	 * add a char
	 */
	public void addChar(String c, Font font) 
	{


	    glyphs.get(cursor.getColumn()).add(cursor.getRow(), new charGlyph(c, font));
	    this.glyphs =  composer.arrangeGlyphs(glyphs);
//	    composer.calculateMaxHeights(glyphs);
	    System.out.println(glyphs);
	    
		ui.updateUI();
	}
	public void addShape(String shape) {
	    glyphs.get(0).add(new shapeGlyph(shape));
        ui.updateUI();
	}
	public void removeAtIndex(int i) 
	{
		glyphs.remove(i);
		ui.updateUI();
	}
	
	/**
	 * gets
	 */
	public List<List<Glyph>> getGlyphs(){return glyphs;}
	
	public LexiCursor getCursor(){
	    return cursor;
	}
	public LexiComposer getComposer() {
	    return composer;
	}
}