/*
 * Copyright 2008-2009 Adam Tacy <adam.tacy AT gmail.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * Copyright 2011 Vancouver Ywebb Consulting Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.adamtacy.client.ui;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * Very simple class allowing access to a browser's Canvas object (where it exists) until
 * someone comes along with a more native implementation within GWT itself.
 * 
 * This class is only used by the Reflect effect.
 *  
 * @author adam
 *
 */
public class Canvas extends Widget { 

  public JavaScriptObject getContext() {
    return getCanvasContext(getElement());
  }

  public Canvas() {
    setElement(DOM.createElement("canvas"));
  }

  public void setSize(int width, int height) {
    this.setPixelSize(width, height);
  }

  private native JavaScriptObject getCanvasContext(Element d)/*-{
  		var ctx = d.getContext("2d");
  		return ctx;
  	}-*/;
}
