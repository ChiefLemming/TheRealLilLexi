import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;

/**
 * Glyph
 */
abstract class Glyph
{
	
	public Glyph() 
	{
		
	}

	public abstract void draw(PaintEvent e, int column, int row);
	public Point bounds(PaintEvent e) {
		return null;
	}

	public String getChar() {
		// TODO Auto-generated method stub
		return null;
	}

	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	
}
