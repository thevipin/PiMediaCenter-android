package mediacenter.pi.vipin.pimediacenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        final SharedPreferences preferance = MainActivity.getThis().getSharedPreferences("Network", Context.MODE_PRIVATE);
        final EditText editTextIP=(EditText)findViewById(R.id.toolbar3);
        editTextIP.setText(preferance.getString("ServerIpAddress","192.168.1.122"));
        editTextIP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SharedPreferences.Editor editor=preferance.edit();
                editor.putString("ServerIpAddress",editTextIP.getText().toString());
            }
        });

    }
}
