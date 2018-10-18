package kaist.com.smartfridge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends Activity {
    ConnectThread connectThread;
    Handler handler = new Handler();
    Button button_connect;
    Button button_putInTheFridge;           // 음식 넣기
    TextView textView_connection_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_connection_status = (TextView) findViewById(R.id.textView_connection_status);
        button_connect = (Button) findViewById(R.id.button_connect);

        // 음식 넣기 버튼
        button_putInTheFridge = (Button) findViewById(R.id.button_putInTheFridge);
        button_putInTheFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setCaptureActivity(ScannerActivity.class);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
            }
        });

    }

    class ConnectThread extends Thread {
        public void run() {
            Log.e("wan", "ConnectThread :: call connect!!!");
            connect();
        }

        private void connect() {
            try {
                Log.e("wan", "ConnectThread :: connect is called!!!");
                printLog("냉장고에 접속을 시도합니다.");
                Socket socket = new Socket("210.89.160.88", 80);  //www.naver.com
                boolean result = socket.isConnected() && !socket.isClosed();
                if (result) {
                    printLog("냉장고와 연결되었습니다.");
                } else {
                    printLog("냉장고와의 연결이 실패했습니다.");
                }

//                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//                oos.writeUTF("클라이언트 접속 요청...");
//                oos.flush();
//                printLog("서버에 접속을 요청하는 중입니다..");

//                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//                String data = ois.readUTF();
//                printLog("서버가 보낸 메세지: " + data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void printLog(String msg) {
            final String text = msg;
            handler.post(new Runnable() {
                public void run() {
                    textView_connection_status.append("\n" + text);
                }
            });
        }
    }

//    냉장고 연결하기
    public void onButtonConnect(View v) {
        Log.e("wan", "onButtonConnect is called!!!");
        //todo :: 서버와 연결 요청
        connectThread = new ConnectThread();
        connectThread.start();
    }

//    음식 넣기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // QR code, barcode scan result
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // result.getFormatName() : 바코드 종류
        // result.getContents()   : 바코드 값
        Toast.makeText(getApplicationContext(), result.getContents(), Toast.LENGTH_LONG).show();
    }
}