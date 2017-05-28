package cn.xuemcu.password;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends Activity implements Runnable {
    private static final int MainActivity_TOAST = 0;
    private boolean threadFlag = true;
    private EditText editText = null;
    private Message message = null;
    private Calendar calendar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edtext);
        message = new Message();
        calendar = Calendar.getInstance();
        threadFlag = true;
        new Thread(this).start();
    }

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MainActivity_TOAST:
                    //Toast.makeText(MainActivity.this,msg.obj.toString(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, PassWordActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    public void run() {
        while(this.threadFlag) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String str = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
            str += Integer.toString(calendar.get(Calendar.MINUTE));
            if(editText.getText().toString().equals(str)) {
                this.threadFlag = false;

                message.what = MainActivity_TOAST;
                message.obj = "密码正确";
                this.mHandler.sendMessage(this.message);
            }
        }
    }
}
