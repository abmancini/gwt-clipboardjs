package come.jooink.gwt.clipboardjs.client.jsi;

import com.google.gwt.dom.client.Node;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public interface ClipboardEvent {
	
	//the event handler
	@JsFunction
	public interface EventHandler {
		public void onClipboardEvent(ClipboardEvent e);
	}

	
	@JsProperty
	public String getText(); 
	@JsProperty
	public String getAction(); 
	@JsProperty
	public Node getTarget(); 		
	
	public void clearSelection();
}