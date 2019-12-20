package kr.hs.dgsw.cicsoft.model;

public class WebViewStateModel {

    public static final double ZOOM_STEP = 0.1;
    public static final double LINEHEIGHT_STEP = 0.1;

    public static final double MAX_ZOOM = 2;
    public static final double MIN_ZOOM = 0.3;
    public static final double MAX_LINEHEIGHT = 2.5;
    public static final double MIN_LINEHEIGHT = 1;

    private double zoom;
    private double lineHeight;

    public WebViewStateModel() {
        this(1, 1.33);
    }

    public WebViewStateModel(double zoom, double lineHeight) {
        this.zoom = zoom;
        this.lineHeight = lineHeight;
    }

    public void zoomIn() {
        zoom += ZOOM_STEP;
        zoom = zoom > MAX_ZOOM ? MAX_ZOOM : zoom;
    }

    public void zoomOut() {
        zoom -= ZOOM_STEP;
        zoom = zoom < MIN_ZOOM ? MIN_ZOOM : zoom;
    }

    public void lineHeightUp() {
        lineHeight += LINEHEIGHT_STEP;
        lineHeight = lineHeight > MAX_LINEHEIGHT ? MAX_LINEHEIGHT : lineHeight;
    }

    public void lineHeightDown() {
        lineHeight -= LINEHEIGHT_STEP;
        lineHeight = lineHeight < MIN_LINEHEIGHT ? MIN_LINEHEIGHT : lineHeight;
    }

    public double getZoom() {
        return zoom;
    }

    public double getLineHeight() {
        return lineHeight;
    }
}
