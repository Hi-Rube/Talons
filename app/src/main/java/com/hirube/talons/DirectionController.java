package com.hirube.talons;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.hirube.talons.net.WebSocketTalons;
import com.hirube.talons.sensor.DirectionTalons;
import com.hirube.talons.sensor.SensorTalonsEventListener;
import com.hirube.talons.sensor.SensorTalonsManager;


public class DirectionController extends ActionBarActivity {

    private WebSocketTalons wsTalons = null;
    private DirectionTalons directionTalons = null;
    private SensorTalonsManager sm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_controller);

        String netAddress = this.getIntent().getBundleExtra("params").getString("netAddress");
        if (netAddress != null && !"".equals(netAddress)){
            wsTalons = WebSocketTalons.get(netAddress, true);
        }

        directionTalons = new DirectionTalons(new SensorTalonsEventListener() {
            @Override
            public void work(Object[] result) {
                Float x = (Float)result[0];
                Float y = (Float)result[1];
                Float z = (Float)result[2];

                if (wsTalons != null) {
                    wsTalons.setTextMessage(x + " " + y + " " + z);
                }
            }
        });

        sm = new SensorTalonsManager(this);
        sm.register(directionTalons);
    }

    @Override
    protected void onDestroy() {
        sm.unregister(directionTalons);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_direction_controller, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
