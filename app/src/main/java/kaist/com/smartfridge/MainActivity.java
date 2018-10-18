package kaist.com.smartfridge;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends Activity {
    ConnectThread connectThread;
    Handler handler = new Handler();
    Button button_connect;
    TextView textView_connection_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_connection_status = (TextView) findViewById(R.id.textView_connection_status);
        button_connect = (Button) findViewById(R.id.button_connect);

    }

    public void onButtonConnect(View v) {
        Log.e("wan", "onButtonConnect is called!!!");
        //todo :: 서버와 연결 요청
        connectThread = new ConnectThread();
        connectThread.start();
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
                Socket socket = new Socket("210.89.160.88", 80);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeUTF("클라이언트 접속 요청...");
                oos.flush();
                printLog("서버에 접속을 요청하는 중입니다..");

                Log.e("wan", "ConnectThread :: 1");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Log.e("wan", "ConnectThread :: 2");
                String data = ois.readUTF();
                Log.e("wan", "ConnectThread :: 3");
                printLog("서버가 보낸 메세지: " + data);
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
}