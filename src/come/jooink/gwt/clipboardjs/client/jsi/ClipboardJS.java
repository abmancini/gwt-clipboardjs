package come.jooink.gwt.clipboardjs.client.jsi;

import com.google.gwt.dom.client.Element;

import come.jooink.gwt.clipboardjs.client.jsi.ClipboardEvent.EventHandler;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

//maps directly to the js native clipboard object
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Clipboard")
public class ClipboardJS {


	
	public ClipboardJS(String selector) {} //cannot be native so ... unused dummy dumb implementation is needed
	public ClipboardJS(Element source) {} //cannot be native so ... unused dummy dumb implementation is needed

	public ClipboardJS(String selector,  ClipboardOptions o) {} //cannot be native so ... unused dummy dumb implementation is needed
	public ClipboardJS(Element source, ClipboardOptions o) {} //cannot be native so ... unused dummy dumb implementation is needed


	public native void on(String event, EventHandler handler);

}
