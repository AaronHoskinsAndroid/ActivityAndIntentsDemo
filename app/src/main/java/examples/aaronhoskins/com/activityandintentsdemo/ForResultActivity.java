package examples.aaronhoskins.com.activityandintentsdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;

public class ForResultActivity extends AppCompatActivity {
    public static final int RESULT_CODE = 676;
    EditText etDataInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);
        etDataInput = findViewById(R.id.etResultInfo);
        if(savedInstanceState != null) {
            etDataInput.setText(savedInstanceState.getString("data"));
        }
    }

    public void onClick(View view) {
        final String dataForResult = etDataInput.getText().toString();
        if(!dataForResult.isEmpty()) {
            Intent intent = new Intent();
            intent.putExtra("result", dataForResult);
            setResult(RESULT_CODE, intent);
            finish();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        final String dataInput = etDataInput.getText().toString();
        if(!dataInput.isEmpty()) {
            outState.putString("data", dataInput);
        }
        super.onSaveInstanceState(outState, outPersistentState);
    }


}
