package come.jooink.gwt.clipboardjs.client;

import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

import come.jooink.gwt.clipboardjs.client.jsi.ClipboardEvent;
import come.jooink.gwt.clipboardjs.client.jsi.ClipboardJS;
import come.jooink.gwt.clipboardjs.client.jsi.ClipboardOptions;



//This class 'wraps' ClipboardJS with a better (in our opinion) api.
//This class handle js loading too, ClipboardJS does not

public class Clipboard {


	//js loading
	public interface Resources extends ClientBundle {
		public static final Resources INSTANCE = GWT.create(Resources.class);
		@Source("clipboard/dist/clipboard.js")
		TextResource clipboardjs(); 
	}

	private static boolean injected = false;
	public static void ensureInjected() {
		if(!injected) {
			injected=true;			
			ScriptInjector.fromString(Resources.INSTANCE.clipboardjs().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();			
		}		
	}


	public enum Action {
		COPY("copy"),
		CUT("cut");

		private String value;
		Action(String value) {
			this.value = value;
		}
	}



	public interface ClipHandler {
		public void onSuccess(ClipboardEvent e);
		public void onError(ClipboardEvent e);		
	}


	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	private ClipboardJS clJS = null;


	public void addClipHandler(ClipHandler handler) {
		clJS.on(SUCCESS,  new ClipboardEvent.EventHandler() {
			@Override
			public void onClipboardEvent(ClipboardEvent e) {
				handler.onSuccess(e);
			}
		});
		clJS.on(ERROR,  new ClipboardEvent.EventHandler() {
			@Override
			public void onClipboardEvent(ClipboardEvent e) {
				handler.onError(e);
			}
		});
	}

	public void addSuccessHandler(ClipboardEvent.EventHandler handler) {
		clJS.on(SUCCESS,  handler);
	}
	
	public void addErrorHandler(ClipboardEvent.EventHandler handler) {
		clJS.on(SUCCESS,  handler);
	}



	//minimalistic constructos, text, target and action have to be be specified via 
	//data-clipboard-action
	//data-clipboard-target
	//data-clipboard-text 
	//attributes
	//(see  https://clipboardjs.com/)

	public Clipboard(String  selector) {
		ensureInjected();
		clJS = new ClipboardJS(selector);
	}
	public Clipboard(Element e) {
		ensureInjected();
		clJS = new ClipboardJS(e);
	}

	//full featured
	public Clipboard(Element e, ClipboardOptions options) {
		ensureInjected();
		clJS = new ClipboardJS(e,options);
	}


	//widget variants
	public Clipboard(Widget w) {
		this(w.getElement());
	}	

	public Clipboard(Widget w, ClipboardOptions options) {
		this(w.getElement(), options);
	}


	//simplified constructors, only for widgets
	//with specified the text content
	public Clipboard(Widget w, String text) {
		this(w.getElement(), new ClipboardOptions().data( trigger-> text ));
	}

	//with specified the target widget
	public Clipboard(Widget w, Widget target) {
		this(w.getElement(), new ClipboardOptions().target( trigger -> target.getElement() ));
	}

	//with specified the target widget and action
	public Clipboard(Widget w, TextArea target, Action action) {
		this(w.getElement(), new ClipboardOptions()
				.target( trigger->target.getElement() )
				.action( trigger->action.value ));
	}


}
