package com.tnta.iuwebpage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by theanh on 11/1/15.
 */
public class EnterID extends Activity {

    EditText Id;
    TextView finish;

    private Preferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_id);

        Id = (EditText)findViewById(R.id.enter_id);
        finish = (TextView)findViewById(R.id.finish);
        preference = new Preferences(getApplication());

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = Id.getText().toString();
                if(id.matches("")){
                    Id.setHint("Please enter your ID");
                }
                else {
                    preference.setID(id);
                    Intent i = new Intent(EnterID.this, Login.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
