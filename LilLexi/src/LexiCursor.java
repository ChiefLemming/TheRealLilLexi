import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import java.util.List;

public class LexiCursor {
	int row;
	int column;
	int xCoord;
	int yCoord;
	
	public LexiCursor() {
		this.row = 0;
		this.column = 0;
		xCoord = 0;
		yCoord = 0;
	}
	
	public void moveRight(List<List<Glyph>> glyphs) {
		if (column + 1 < glyphs.get(row).size()) {
		    column++;
		} else if (row + 1 < glyphs.size() && column == glyphs.get(row).size()) {
		    row++;
		    column = 0;
		} 
		updateCoords(glyphs);
	   
	}
	
	
	public void moveLeft(List<List<Glyph>> glyphs) {
	    if (column - 1 > -1) {
            column--;
        }
        updateCoords(glyphs);
	}
	
	public void moveDown(List<List<Glyph>> glyphs) {
	    if (row - 1 > -1) {
            row--;
        }
	    column = glyphs.get(row).size() -1;
        updateCoords(glyphs);
		
	}
	
	public void moveUp(List<List<Glyph>> glyphs){
	    if (row + 1 < glyphs.size()) {
            row++;
        }
        column = glyphs.get(row).size() -1;
        updateCoords(glyphs);
	}
	
	public int getRow() {
	    return row;
		
	}
	
	public int getColumn() {
		return column;
	}
	
	public void updateCoords(List<List<Glyph>> glyphs) {
	    xCoord = 0;
        yCoord = 0;
	    for (int i = 0; i < row; i++) {
            yCoord += LexiComposer.rowHeights.get(i);
            for (int r = 0; r < column; r++) {
                xCoord += glyphs.get(i).get(r).getHeight();
            }
        }
	    
	}
	public void draw(PaintEvent e, Display d) {
	    Rectangle rectangle = new Rectangle(xCoord, yCoord, 25, 25);
	    e.gc.setBackground(d.getSystemColor(SWT.COLOR_RED)); 
	    e.gc.fillRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	    e.gc.drawRectangle(rectangle);
	    
	}
	
	public int getXCoord() {
	    return xCoord;
	}
	public int getYCoord() {
	    return yCoord;
	}

	
}
