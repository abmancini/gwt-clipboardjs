package come.jooink.gwt.clipboardjs.client.jsi;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name="Object")
public class ClipboardOptions {
	
	
	
	//JsFunctions needed to create the Clipboard.js Options Object
	@JsFunction
	public interface TargetSelector {
		public Node getTarget(Element trigger);
	}

	@JsFunction
	public interface ActionSelector {
		public String getAction(Element trigger); // returns "cut" | "copy" 
	}

	@JsFunction
	public interface DataSource  {
		public String getData(Element trigger);
	}
	
	
	
	@JsProperty(name="text")
	public native void setData(DataSource ds); 
	
	@JsProperty(name="action")
	public native void setAction(ActionSelector as); 
	
	@JsProperty(name="target")
	public native void setTarget(TargetSelector ts); 
	
	
	//fluent API
	@JsOverlay 
	public final ClipboardOptions data(DataSource ds) {
		this.setData(ds);
		return this;
	}
	@JsOverlay 
	public final ClipboardOptions action(ActionSelector as) {
		this.setAction(as);
		return this;
	}
	@JsOverlay 
	public final ClipboardOptions target(TargetSelector ts) {
		this.setTarget(ts);
		return this;
	}
	
}