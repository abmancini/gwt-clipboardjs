package come.jooink.gwt.clipboardjs.client;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(namespace = JsPackage.GLOBAL, isNative = true, name="console")
public final class Console {

	private Console() {};
	public static native void log(Object ms);

	@JsMethod(name="log")
	public static native void logi(int ms);
}
