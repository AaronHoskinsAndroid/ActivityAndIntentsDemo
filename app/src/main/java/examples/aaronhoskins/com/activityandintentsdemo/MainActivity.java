package examples.aaronhoskins.com.activityandintentsdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static examples.aaronhoskins.com.activityandintentsdemo.ForResultActivity.RESULT_CODE;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG_MAIN";
    public static final int REQUEST_CODE = 666;
    EditText etUserInput;
    TextView tvResultInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUserInput = findViewById(R.id.etUserInput);
        tvResultInfo = findViewById(R.id.tvResultDisplay);
        tvResultInfo.setVisibility(View.GONE);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGotoResultActivity:
                final String inputToSend = etUserInput.getText().toString();
                if(!inputToSend.isEmpty()) {
                    //Explicit Intent
                    Intent explicitIntent = new Intent(this, ResultActivity.class);
                    explicitIntent.putExtra("key", inputToSend);
                    startActivity(explicitIntent);
                }
                break;

            case R.id.btnStartImplicitActivity:
                Intent implicitIntent = new Intent("start_implicit_intent");
                startActivity(implicitIntent);
                break;

            case R.id.btnStartActivityForResult:
                Intent forResultIntent = new Intent(this, ForResultActivity.class);
                startActivityForResult(forResultIntent, REQUEST_CODE);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tvResultInfo.setVisibility(View.VISIBLE);
        final String resultData = data.getStringExtra("result");
        switch(resultCode) {
            case RESULT_CODE:
                tvResultInfo.setText(resultData);
        }
    }
}
