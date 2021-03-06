
package earth.editor;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gwtgl.binding.WebGLCanvas;
import com.googlecode.gwtgl.binding.WebGLContextAttributes;
import com.googlecode.gwtgl.binding.WebGLRenderingContext;

/**
 * Abstract parent class for the Editor that creates an launch button
 * and a Canvas to render on.
 */
public abstract class AbstractEarthEditorViewer extends FlexTable {

	/** The Canvas to render on. */
	protected final WebGLCanvas webGLCanvas;
	/** The Canvas' 3D rendering context. */
	protected final WebGLRenderingContext glContext;

	/**
	 * Constructs a new instance of a Editor with a launch button and a
	 * Canvas to render on.
	 */
	protected AbstractEarthEditorViewer() {
		// create the button to launch the editor
//		final PushButton button = new PushButton("Launch editor!");
//		setWidget(0, 0, button);
		
		WebGLContextAttributes contextAttributes = WebGLContextAttributes.create();
		contextAttributes.setAlpha(false);

		// create the WebGL Canvas
		webGLCanvas = new WebGLCanvas(contextAttributes, "1000px", "1000px");
		glContext = webGLCanvas.getGlContext();
		glContext.viewport(0, 0, 1000, 1000);

		setWidget(1, 0, webGLCanvas);
		
		HTML exampleDescription = new HTML("<h2>Texture2D Binding Example</h2><h3>Controls</h3><p>PAGEGUP - Zoom in<br/>PAGEDOWN - Zoom out</p>");
                VerticalPanel descriptionAndControls = new VerticalPanel();
                descriptionAndControls.add(exampleDescription);
                setWidget(1, 1, descriptionAndControls);
		
//		// add a ClickHandler to start the editor when the button is clicked
//		button.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				button.setEnabled(false);
//				start();
//				webGLCanvas.setFocus(true);
//			}
//		});
	}
	
	public void execute(){
		start();
		webGLCanvas.setFocus(true);
	}

	/**
	 * Starts the execution of the editor. First the editor is initialized
	 * using {@link AbstractEarthEditorViewer#init()}. After that the rendering loop
	 * is executed with 50 fps. Each frame is rendered using
	 * {@link AbstractEarthEditorViewer#draw()}.
	 */
	private void start() {
		init();

		Timer timer = new Timer() {
			@Override
			public void run() {
				draw();
			}
		};
		timer.scheduleRepeating(20);

	}

	/**
	 * Initializes the needed resources of the editor.
	 */
	protected abstract void init();

	/**
	 * Draws the editor.
	 */
	protected abstract void draw();

}
