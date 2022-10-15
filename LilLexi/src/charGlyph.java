import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;

/**
 * Glyph
 */
public class charGlyph extends Glyph
{
	private String c;
	private int size;
	private Font font;
	private int width;
	private int height;
	private Point coords;
	
	public charGlyph(String c, Font font)
	{
		this.c = c;
		this.setFont(font);
		this.coords = new Point(0, 0);
	}
	
	public void draw(PaintEvent e, int column, int row) {
		Point point = e.gc.textExtent(c);
		this.setWidth(point.x);
		this.setHeight(point.y);
		coords.x = column;
		coords.y = row;
		e.gc.drawString(c, column, row);
	}
	public Point bounds(PaintEvent e) {
		Point point = e.gc.textExtent(c);
		this.setWidth(point.x);
		this.setHeight(point.y);
		return point;
	}
	
	public String getChar() {
		return c;
	}
	public void setChar(String c) {
		this.c = c;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * @param font the font to set
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
}
