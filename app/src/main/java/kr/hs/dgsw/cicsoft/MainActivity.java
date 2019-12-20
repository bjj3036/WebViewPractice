package kr.hs.dgsw.cicsoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import kr.hs.dgsw.cicsoft.databinding.ActivityMainBinding;
import kr.hs.dgsw.cicsoft.presenter.MainPresenter;
import kr.hs.dgsw.cicsoft.view.ShowWebView;

public class MainActivity extends AppCompatActivity implements ShowWebView {

    private static final String BASE_URI = "file:android_asset/";

    private ActivityMainBinding mBinding;
    private WebView mWebView;
    private MainPresenter mainPresenter;

    private Animation animationUp, animationDown;

    private boolean isZoomShowing = false;
    private boolean isLineHeightShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainPresenter = new MainPresenter(this);

        initAnimation();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        hideZoomControl();
        hideLineHeightControl();
    }

    private void initAnimation() {
        animationUp = AnimationUtils.loadAnimation(this, R.anim.anim_up);
        animationUp.setFillAfter(true);
        animationDown = AnimationUtils.loadAnimation(this, R.anim.anim_down);
        animationDown.setFillAfter(true);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        mWebView = mBinding.webView;
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setOnTouchListener((v, e) -> {
            if (isZoomShowing)
                hideZoomControl();
            if (isLineHeightShowing)
                hideLineHeightControl();
            return false;
        });
        loadLocalHtml();
    }

    private void loadLocalHtml() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URI);
        stringBuilder.append("epigraph_001.xhtml");
        mWebView.loadUrl(stringBuilder.toString());
    }

    @Override
    public void setZoom(double zoom) {
        String zoomInScript = "javascript:document.body.style.zoom = " + zoom;
        mWebView.loadUrl(zoomInScript);
    }

    @Override
    public void setLineHeight(double lineHeight) {
        String lineHeightScript = "javascript:document.body.style.lineHeight = '" + lineHeight + "em';undefined";
        mWebView.loadUrl(lineHeightScript);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.appbar_zoom:
                toggleZoomControl();
                break;
            case R.id.appbar_lh:
                toggleLineHeightControl();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Click Events
    public void onZoomInClick(View v) {
        mainPresenter.onZoomInEvent();
    }

    public void onZoomOutClick(View v) {
        mainPresenter.onZoomOutEvent();
    }

    public void onLhUpClick(View v) {
        mainPresenter.onLineHeightUpEvent();
    }

    public void onLhDownClick(View v) {
        mainPresenter.onLineHeightDownEvent();
    }

    //
    private void toggleZoomControl() {
        if (isZoomShowing) {
            hideZoomControl();
        } else {
            showZoomControl();
            if (isLineHeightShowing)
                hideLineHeightControl();
        }
    }

    private void showZoomControl() {
        mBinding.containerZoom.startAnimation(animationUp);
        mBinding.btnZoomIn.setClickable(true);
        mBinding.btnZoomOut.setClickable(true);
        isZoomShowing = true;
    }

    private void hideZoomControl() {
        mBinding.containerZoom.startAnimation(animationDown);
        mBinding.btnZoomIn.setClickable(false);
        mBinding.btnZoomOut.setClickable(false);
        isZoomShowing = false;
    }

    private void toggleLineHeightControl() {
        if (isLineHeightShowing) {
            hideLineHeightControl();
        } else {
            showLineHeightControl();
            if (isZoomShowing)
                hideZoomControl();
        }
    }

    private void showLineHeightControl() {
        mBinding.containerLineHeight.startAnimation(animationUp);
        mBinding.btnLhUp.setClickable(true);
        mBinding.btnLhDown.setClickable(true);
        isLineHeightShowing = true;
    }

    private void hideLineHeightControl() {
        mBinding.containerLineHeight.startAnimation(animationDown);
        mBinding.btnLhUp.setClickable(false);
        mBinding.btnLhDown.setClickable(false);
        isLineHeightShowing = false;
    }
}
