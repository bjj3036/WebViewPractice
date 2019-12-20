package kr.hs.dgsw.cicsoft.presenter;

import kr.hs.dgsw.cicsoft.model.WebViewStateModel;
import kr.hs.dgsw.cicsoft.view.ShowWebView;

public class MainPresenter implements PresenterLifeCycle {

    private ShowWebView view;
    private WebViewStateModel webViewStateModel;

    public MainPresenter(ShowWebView view) {
        this.view = view;
        this.webViewStateModel = new WebViewStateModel();
    }

    public void onZoomInEvent() {
        webViewStateModel.zoomIn();
        view.setZoom(webViewStateModel.getZoom());
    }

    public void onZoomOutEvent() {
        webViewStateModel.zoomOut();
        view.setZoom(webViewStateModel.getZoom());
    }

    public void onLineHeightUpEvent() {
        webViewStateModel.lineHeightUp();
        view.setLineHeight(webViewStateModel.getLineHeight());
    }

    public void onLineHeightDownEvent() {
        webViewStateModel.lineHeightDown();
        view.setLineHeight(webViewStateModel.getLineHeight());
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestory() {

    }
}
