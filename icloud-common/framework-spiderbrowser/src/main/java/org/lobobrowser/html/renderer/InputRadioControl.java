/*
    GNU LESSER GENERAL PUBLIC LICENSE
    Copyright (C) 2006 The Lobo Project

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

    Contact info: xamjadmin@users.sourceforge.net
*/
/*
 * Created on Jan 15, 2006
 */
package org.lobobrowser.html.renderer;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import org.lobobrowser.html.domimpl.HTMLBaseInputElement;
import org.lobobrowser.html.domimpl.HTMLElementImpl;
import org.lobobrowser.util.gui.WrapperLayout;

class InputRadioControl extends BaseInputControl {
	private final JRadioButton widget; 
	
	public InputRadioControl(HTMLBaseInputElement modelNode) {
		super(modelNode);
		this.setLayout(WrapperLayout.getInstance());
		JRadioButton radio = new JRadioButton();
		radio.setOpaque(false);
		this.widget = radio; 
		this.add(radio);
	}	
	
	private ButtonGroup buttonGroup;
	
	public void reset(int availWidth, int availHeight) {
		super.reset(availWidth, availHeight);
		HTMLElementImpl controlElement = this.controlElement;
		String name = controlElement.getAttribute("name");
		JRadioButton radio = this.widget;
		ButtonGroup prevGroup = this.buttonGroup;
		if(prevGroup != null) {
			prevGroup.remove(radio);
		}
		if(name != null) {
			String key = "cobra.radio.group." + name;
			ButtonGroup group = (ButtonGroup) controlElement.getDocumentItem(key);
			if(group == null) {
				group = new ButtonGroup();
				controlElement.setDocumentItem(key, group);
			}
			group.add(radio);
			this.buttonGroup = group;
		}
		else {
			this.buttonGroup = null;
		}
		String checkedText = controlElement.getAttribute("checked");
		radio.setSelected("checked".equalsIgnoreCase(checkedText));		
	}
	
	/* (non-Javadoc)
	 * @see org.xamjwg.html.domimpl.InputContext#click()
	 */
	public void click() {
		this.widget.doClick();
	}

	/* (non-Javadoc)
	 * @see org.xamjwg.html.domimpl.InputContext#getChecked()
	 */
	public boolean getChecked() {
		return this.widget.isSelected();
	}

	/* (non-Javadoc)
	 * @see org.xamjwg.html.domimpl.InputContext#setChecked(boolean)
	 */
	public void setChecked(boolean checked) {
		this.widget.setSelected(checked);
	}

	/* (non-Javadoc)
	 * @see org.xamjwg.html.domimpl.InputContext#setDisabled(boolean)
	 */
	public void setDisabled(boolean disabled) {
		super.setDisabled(disabled);
		this.widget.setEnabled(!disabled);
	}
	
	public void resetInput() {
		String checkedText = controlElement.getAttribute("checked");
		this.widget.setSelected("checked".equalsIgnoreCase(checkedText));				
	}
	
	public String getValue() {
		return this.controlElement.getAttribute("value");
	}
}
