/**
    This file is part of J-File-Splitter.

    J-File-Splitter is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    J-File-Splitter is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with J-File-Splitter.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.erc.jfilesplitter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class AWTProgressBar.
 */
public class AWTProgressBar extends Panel{

	/** UUID */
	private static final long serialVersionUID = -8089019872758585483L;
	
	@Getter
	@Setter
	private double max=0;
	
	@Getter
	private double current=0;
	
	public AWTProgressBar() {
		this.setBackground(Color.GRAY);
	}
	
	public void setCurrent(double current) {
		this.current = current;
		if(this.current>max) {
			this.current = max;
		}
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		if(max>0) {
			double perc = (current/max);
			Double inBoxSize =0D;
			if(perc >0) {
				inBoxSize = this.getWidth() * perc;
			}
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, inBoxSize.intValue(), this.getHeight());
		}
		super.paint(g);
	}
	

}
