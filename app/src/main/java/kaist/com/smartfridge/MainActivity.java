package kaist.com.smartfridge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonTestClicked(View v) {
        //todo :: 테스트 코드를 작성합니다
        //Toast.makeText(getBaseContext(), "테스트!!!", Toast.LENGTH_SHORT).show();
    }
}
