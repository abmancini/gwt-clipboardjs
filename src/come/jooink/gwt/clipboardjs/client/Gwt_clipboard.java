package come.jooink.gwt.clipboardjs.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.dom.client.PreElement;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import come.jooink.gwt.clipboardjs.client.Clipboard.Action;
import come.jooink.gwt.clipboardjs.client.Clipboard.ClipHandler;
import come.jooink.gwt.clipboardjs.client.jsi.ClipboardEvent;
import come.jooink.gwt.clipboardjs.client.jsi.ClipboardOptions;

public class Gwt_clipboard implements EntryPoint {


	private ClipHandler clipHandler = new  ClipHandler() {

		@Override
		public void onSuccess(ClipboardEvent e) {			
			DivElement div = Document.get().createDivElement();
			ParagraphElement p = Document.get().createPElement();
			p.setInnerHTML("Success, clipboaed content should be: ");
			PreElement pre = Document.get().createPreElement();			
			pre.setInnerText(e.getText());

			div.appendChild(p);
			div.appendChild(pre);

			logPanelDiv.insertFirst(div);
		}


		@Override
		public void onError(ClipboardEvent e) {
			Window.alert("Error: " + e.getText());		
		}
	};


	private DivElement logPanelDiv;


	@Override
	public void onModuleLoad() {



		DockLayoutPanel panel = new DockLayoutPanel(Unit.PX);


		SplitLayoutPanel consoles = new SplitLayoutPanel();

		SimplePanel logPanel = new SimplePanel();
		logPanel.getElement().getStyle().setBackgroundColor("lightGray");
		logPanel.getElement().getStyle().setPadding(5,Unit.PX);
		logPanel.setWidth("100%");
		logPanel.getElement().getStyle().setPropertyPx("minHeight", 250);
		logPanel.getElement().getStyle().setOverflow(Overflow.SCROLL);

		logPanelDiv = Document.get().createDivElement();

		logPanel.getElement().appendChild(logPanelDiv);

		consoles.addEast(logPanel, 450);


		TextArea pasteArea = new TextArea();
		pasteArea.getElement().setAttribute("placeholder", "Paste here");
		pasteArea.getElement().getStyle().setBackgroundColor("beige");

		pasteArea.setWidth("100%");
		consoles.add(pasteArea);




		panel.addSouth(consoles, 250);

		ScrollPanel samplesPanel = new ScrollPanel();

		panel.add(samplesPanel);


		RootLayoutPanel.get().add(panel);




		VerticalPanel vp = new VerticalPanel();

		{
			HorizontalPanel hp = new HorizontalPanel();
			hp.add(new HTML("Trivial Case, text speccified via data-*"));
			Button b = new Button( "Click Me");
			Clipboard c = new Clipboard(b);
			c.addClipHandler( clipHandler);
			b.getElement().setAttribute("data-clipboard-text", "Text from Button 1, using data-clipboard-text attr.");

			hp.add(b);

			vp.add(hp);
		}
		
		{
			HorizontalPanel hp = new HorizontalPanel();
			hp.add(new HTML("Text in constructor"));
			Button b = new Button( "Click Me");
			Clipboard c = new Clipboard(b, "Text specified in constructor");
			c.addClipHandler( clipHandler);
			hp.add(b);

			vp.add(hp);
		}
		
		
		{
			HorizontalPanel hp = new HorizontalPanel();
			hp.add(new HTML("From target Widget"));
			Button b = new Button( "Click Me");

			HTML target = new HTML("text <b>bold</b> in a widget");
			
			Clipboard c = new Clipboard(b, target);
			c.addClipHandler( clipHandler);
			hp.add(b);
			hp.add(target);

			vp.add(hp);
		}
		
		{
			HorizontalPanel hp = new HorizontalPanel();
			hp.add(new HTML("From TextArea"));
			Button b = new Button( "Click Me");

			TextArea target = new TextArea();
			target.setText("Text in the area, feel free to change");
			
			Clipboard c = new Clipboard(b, target);
			c.addClipHandler( clipHandler);
			hp.add(b);
			hp.add(target);

			vp.add(hp);
		}
		
		
		{
			HorizontalPanel hp = new HorizontalPanel();
			hp.add(new HTML("From TextArea (cut)"));
			Button b = new Button( "Click Me");

			TextArea target = new TextArea();
			target.setText("Text in the area, feel free to change");
			
			Clipboard c = new Clipboard(b, target, Action.CUT);
			c.addClipHandler( clipHandler);
			hp.add(b);
			hp.add(target);

			vp.add(hp);
		}
		
		{
			HorizontalPanel hp = new HorizontalPanel();
			hp.add(new HTML("From TextArea (copy) only selected text"));
			Button b = new Button( "Click Me");

			TextArea target = new TextArea();
			target.setText("Text in the area, feel free to change");
			
			Clipboard c = new Clipboard(b, new ClipboardOptions().data( 
					trigger -> {
						String txt = target.getSelectedText();
						if(txt == null || txt.trim().length() == 0) {
							Window.alert("please select part of the text");
							return null;
						} else {
							return txt;
						}
					} ));
		
			c.addClipHandler( clipHandler);
			hp.add(b);
			hp.add(target);

			vp.add(hp);
		}
		
		
		
		


		samplesPanel.add(vp);

		//
		//		
		//		
		//		RootPanel.get().add(vp);
		//		//		
		//		//
		//		//		ClipboardOptions o = new ClipboardOptions().data( new DataSource() {
		//		//			
		//		//			@Override
		//		//			public String getData(Element trigger) {
		//		//				return "TESTO COPIATO";
		//		//			}
		//		//		});
		//		//		
		//		//		Console.log(o);
		//		//		
		//		//		Button b = new Button("boh, proviamo :|");
		//		//		
		//		//		b.addClickHandler( new ClickHandler() {
		//		//			
		//		//			@Override
		//		//			public void onClick(ClickEvent event) {
		//		//				Window.alert("evvai");
		//		//			}
		//		//		});
		//		//		RootPanel.get().add(b);
		//		//		
		//		//		Clipboard.ensureInjected();
		//		//		
		//		//		//.getElement().setPropertyString("text", "TEXT");
		//		//		//b.getElement().setAttribute("data-clipboard-text", "XXX YYY ZZZZ");
		//		//		//b.getElement().setAttribute("data-clipboard-text", "XXX YYY ZZZZ");
		//		//		
		//		//		ClipboardJS c =  new ClipboardJS(b.getElement(), o);
		//		//		
		//		//		c.on("success",  new EventHandler() {
		//		//			
		//		//			@Override
		//		//			public void onSuccessOrError(ClipboardEvent e) {
		//		//				Console.log(e);
		//		//			}
		//		//		});
		//		//		
		//		////		, new DataSource() {
		//		////			
		//		////			@Override
		//		////			public String getData(Element trigger) {
		//		////				return "COPIATO ATO ATO ATO";
		//		////			}
		//		////		}); 
		//		//		Console.log(c);
		//		//
		//		////		Window.alert("c: " + c);
		//		////		
		//		////		ClipboardJS c2 = new ClipboardJS(".btn");
		//		////		
		//		////		Console.log(c2);
		//		////		Window.alert("c: " + c2);
		//		////		
		//		////		
	}





}
