import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Renderer;

public class LexiComposer {
	public static Map<Integer, Integer> rowHeights;
	private int maxWidth;
	
	public LexiComposer(int width) {
		rowHeights = new HashMap<Integer, Integer>();
		rowHeights.put(0, 0);
		maxWidth = 720;
	}
	public List<List<Glyph>> arrangeGlyphs(List<List<Glyph>>glyphs) {
		boolean composed = true;
		System.out.println("past it");
		
		//base case, check if glyphs properly fit
		int currWidth = 0;
		for (int i = 0; i < glyphs.size(); i++) {
            for (Glyph glyph : glyphs.get(i)) {
                currWidth += glyph.getWidth();
                if (currWidth > maxWidth) {
                    composed = false;
                }
            }
		}
        if (composed) {
            return glyphs;
        }
        
        // fix glyphs array with a new copy            
        List<List<Glyph>> copyList = new ArrayList<>();
        for (List<Glyph> column : glyphs) {
            copyList.add(new ArrayList<Glyph>());
        }
		currWidth = maxWidth;
		for (int j = 0; j < glyphs.size(); j++) {
			currWidth = currWidth - maxWidth;
			if (currWidth < 0) {
				currWidth = 0;
			}
			for (Glyph g: glyphs.get(j)) {
				currWidth += g.getWidth();
				if (currWidth < maxWidth) {
					copyList.get(j).add(g);
				} else {
					if (j + 1 < glyphs.size()) {
						copyList.get(j+1).add(0, g);
					} else {
						copyList.add(new ArrayList<Glyph>());
						copyList.get(j+1).add(0, g);
						
					    }
				    }   
					
				}
			
		 }
		return arrangeGlyphs(copyList);			
		
	}
	public void calculateMaxHeights(List<List<Glyph>>glyphs) {
	    for (int i = 0; i < glyphs.size(); i++) {
            int rowHeight = 0;
	        for (Glyph glyph : glyphs.get(i)) {
                if (glyph.getHeight() > rowHeight) {
                    rowHeights.put(i, glyph.getHeight());
                }
	        }
	    }
	}
	public int getRowHeight(int row) {
	    return rowHeights.get(row);
	}
	
}


