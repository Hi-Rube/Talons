package com.hirube.talons;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private NetSettingDialog netSettingDialog = null;

    //设置的值
    private String netSettingAddress = "30.34.202.141:8888";

    public static int REQUEST_QR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDirectionController = (Button) this.findViewById(R.id.btn_direction_controller_activity_main);
        btnDirectionController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toDirectionController = new Intent(MainActivity.this, DirectionController.class);
                Bundle params = new Bundle();
                params.putString("netAddress", netSettingAddress);
                toDirectionController.putExtra("params", params);
                startActivity(toDirectionController);
            }
        });

        Button btnGotoQR = (Button) this.findViewById(R.id.btn_goto_qr_activity_main);
        btnGotoQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toQr = new Intent(MainActivity.this, QRActivity.class);
                startActivityForResult(toQr, REQUEST_QR);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_net_setting) {

            if (netSettingDialog == null){
                netSettingDialog = new NetSettingDialog(this) {
                    @Override
                    public void onConfirmNetAddress(String _netAddress) {
                        netSettingAddress = _netAddress;
                    }
                };
                netSettingDialog.create();
            }

            netSettingDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_QR && resultCode == RESULT_OK){
            Toast.makeText(this, data.getStringExtra("return"), Toast.LENGTH_LONG).show();
        }
    }
}
