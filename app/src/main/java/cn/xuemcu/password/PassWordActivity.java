package cn.xuemcu.password;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by 朱红晨 on 2017/5/28.
 */

public class PassWordActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        // 进入该界面表明指纹或则密码验证通过
        Toast.makeText(this,"验证通过！",Toast.LENGTH_SHORT).show();
    }
}
