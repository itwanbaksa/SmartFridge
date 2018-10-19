package kaist.com.smartfridge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {
    private WebView mWebView;
    private WebSettings mWebSettings;
    int reqId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        reqId = intent.getIntExtra("reqid", 000);

        // 웹뷰 세팅
        mWebView = (WebView)findViewById(R.id.webview); //레이어와 연결
        mWebView .setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebSettings = mWebView.getSettings(); //세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true); // 자바스크립트 사용 허용

        if (reqId == 1) {
            mWebView.loadUrl("http://m.naver.com"); //원하는 URL  입력
        } else if (reqId == 2) {
            mWebView.loadUrl("http://m.nate.com");
        }
    }
}
