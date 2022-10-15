import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Point;

public class shapeGlyph extends Glyph {
    
    private String shape;
    private int width;
    private int height;
    private Point coords;
    
    public shapeGlyph(String shape) {
        this.setShape(shape);
        this.width = 20;
        this.height = 32;
        this.coords = new Point(0, 0);
    }
    public void draw(PaintEvent e, int column, int row) {
        coords.x = column;
        coords.y = row;
        switch (shape) {
            case "Rectangle":
                e.gc.drawRectangle(column, row, width, height);
                break;
            case "Circle":
                e.gc.drawOval(column, row, width, height);
                break;
        }
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
	 * @return the shape
	 */
	public String getShape() {
		return shape;
	}
	/**
	 * @param shape the shape to set
	 */
	public void setShape(String shape) {
		this.shape = shape;
	}

}
