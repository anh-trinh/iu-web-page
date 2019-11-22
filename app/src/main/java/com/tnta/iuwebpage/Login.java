package com.tnta.iuwebpage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by theanh on 10/31/15.
 */
public class Login extends Activity {

    TextView ID;
    EditText edu_pass;
    EditText bb_pass;
    EditText m_pass;
    Button save_button;
    Button clear_button;
    TextView home;
    TextView note;

    private Preferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ID = (TextView)findViewById(R.id.textView8);
        edu_pass = (EditText)findViewById(R.id.edu_pass);
        bb_pass = (EditText)findViewById(R.id.bb_pass);
        m_pass = (EditText)findViewById(R.id.m_pass);
        save_button = (Button)findViewById(R.id.save_button);
        clear_button = (Button)findViewById(R.id.clear_button);
        home = (TextView)findViewById(R.id.home);
        note = (TextView)findViewById(R.id.note);
        preference = new Preferences(getApplication());

        ID.setText(preference.getID());

        if(preference.getEduUserPassword()!=null){
            edu_pass.setHint("Saved");
        }
        if(preference.getBBUserPassword()!=null){
            bb_pass.setHint("Saved");
        }
        if(preference.getMUserPassword()!=null){
            m_pass.setHint("Saved");
        }

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edu_password = "" + edu_pass.getText();
                String bb_password = "" + bb_pass.getText();
                String m_password = "" + m_pass.getText();
                if(!edu_password.matches("")) {
                    preference.setEduUserPassword(edu_password);
                    edu_pass.setText("");
                    edu_pass.setHint("Saved");
                }
                else {
                    if(edu_password.matches("")&&preference.getEduUserPassword()==null){
                        edu_pass.setText("");
                        edu_pass.setHint("Enter Password");
                    }
                    else {
                        edu_pass.setText("");
                        edu_pass.setHint("Saved");
                    }
                }
                if(!bb_password.matches("")) {
                    preference.setBBUserPassword(bb_password);
                    bb_pass.setText("");
                    bb_pass.setHint("Saved");
                }
                else {
                    if(bb_password.matches("")&&preference.getBBUserPassword()==null){
                        bb_pass.setText("");
                        bb_pass.setHint("Enter Password");
                    }
                    else {
                        bb_pass.setText("");
                        bb_pass.setHint("Saved");
                    }
                }
                if(!m_password.matches("")) {
                    preference.setMUserPassword(m_password);
                    m_pass.setText("");
                    m_pass.setHint("Saved");
                }
                else {
                    if(m_password.matches("")&&preference.getMUserPassword()==null){
                        m_pass.setText("");
                        m_pass.setHint("Enter Password");
                    }
                    else {
                        m_pass.setText("");
                        m_pass.setHint("Saved");
                    }
                }
                if(!edu_password.matches("")||!bb_password.matches("")||!m_password.matches("")){
                    note.setText("Your passwords are saved");
                }
                else {
                    note.setText("Please enter new passwords");
                }
            }
        });

        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                preference.setEduUserPassword(null);
                                preference.setBBUserPassword(null);
                                preference.setMUserPassword(null);
                                edu_pass.setText("");
                                edu_pass.setHint("Enter Password");
                                bb_pass.setText("");
                                bb_pass.setHint("Enter Password");
                                m_pass.setText("");
                                m_pass.setHint("Enter Password");
                                note.setText("Your passwords are cleared");
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.dismiss();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(Login.this, R.style.AlertDialog_AppCompat));
                builder.setTitle("Clear All Passwords").setMessage("All your passwords will be cleared. Do you want to continue?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, TabChange.class);
                startActivity(intent);
            }
        });

        RelativeLayout web_layout = (RelativeLayout) findViewById(R.id.login_layout);
        web_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openOptionsMenu();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        //add(groupId, itemId, order, name)
        menu.add(1, 1, 1, "Home");
        menu.add(1, 2, 2, "Change Account");
        menu.add(1, 3, 3, "Exit");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        Intent i;
        switch (item.getItemId()) {

            case 1:
                Intent new_intent = new Intent(Login.this, TabChange.class);
                startActivity(new_intent);
                return true;

            case 2:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                preference.setID(null);
                                preference.setEduUserPassword(null);
                                preference.setBBUserPassword(null);
                                preference.setMUserPassword(null);
                                preference.setTab(null);
                                Intent i = new Intent(Login.this, EnterID.class);
                                startActivity(i);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.dismiss();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(Login.this, R.style.AlertDialog_AppCompat));
                builder.setTitle("Change Account").setMessage("All your account information will be cleared. Do you want to change your ID?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return true;

            case 3:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
