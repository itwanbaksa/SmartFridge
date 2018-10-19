package kaist.com.smartfridge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InsertActivity extends Activity {
    String barcode_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        Intent intent = getIntent();
        barcode_info = intent.getStringExtra("barcodeNum");

        TextView textView_barcode_num = (TextView) findViewById(R.id.textView);
        textView_barcode_num.setText(barcode_info);

    }
}
