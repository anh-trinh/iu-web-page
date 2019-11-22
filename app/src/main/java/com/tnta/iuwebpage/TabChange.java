package com.tnta.iuwebpage;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

/**
 * Created by theanh on 10/25/15.
 */
public class TabChange extends TabActivity {

    Resources res;
    TabHost tabHost;
    TabHost.TabSpec spec;
    Intent intent;

    private Preferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_change);

        preference = new Preferences(getApplication());

        res = getResources();
        tabHost = getTabHost();

        /**Create Tab A**/
        intent = new Intent().setClass(TabChange.this, Edusoft.class);
        spec = tabHost.newTabSpec("A")
                .setIndicator("EDUSOFT")
                .setContent(intent);
        tabHost.addTab(spec);

        /**Create Tab B**/
        intent = new Intent().setClass(this, BlackBoard.class);
        spec = tabHost.newTabSpec("B")
                .setIndicator("BLACKBOARD")
                .setContent(intent);
        tabHost.addTab(spec);

        /**Create Tab C**/
        intent = new Intent().setClass(this, Email.class);
        spec = tabHost.newTabSpec("C")
                .setIndicator("EMAIL")
                .setContent(intent);
        tabHost.addTab(spec);

        //set default tab 0 for A, 1 for B, 2 for C
        if(preference.getTab()!=null) {
            int tabNumber = Integer.parseInt(preference.getTab());
            tabHost.setCurrentTab(tabNumber);
        }
        else {
            tabHost.setCurrentTab(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        //add(groupId, itemId, order, name)
        menu.add(1, 1, 1, "Manage Password");
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
                i = new Intent(TabChange.this, Login.class);
                startActivity(i);
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
                                Intent i = new Intent(TabChange.this, EnterID.class);
                                startActivity(i);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.dismiss();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(TabChange.this, R.style.AlertDialog_AppCompat));
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
