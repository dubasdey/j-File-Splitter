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

import lombok.Setter;

class Splitter {

	@Setter
	private SplitterListener listener;
	
	
	public void split(String filePath,SplitMode mode) {
		
	}
	
	
	
}
