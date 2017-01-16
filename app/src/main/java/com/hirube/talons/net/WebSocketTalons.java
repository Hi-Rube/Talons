package com.hirube.talons.net;

import android.util.Log;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

/**
 * Created by Rube on 17/1/9.
 */
public class WebSocketTalons {

    private static WebSocketTalons talons = null;
    private static String netAddress = "";

    public static WebSocketTalons get(String _netAddress, boolean isRefresh){

        if (isRefresh){
            talons = new WebSocketTalons(_netAddress);
            netAddress = _netAddress;
            return talons;
        } else {
            return WebSocketTalons.get(_netAddress);
        }
    }
    public static WebSocketTalons get(String _netAddress){

        if (!_netAddress.equals(netAddress)){
            talons = new WebSocketTalons(_netAddress);
        } else if (talons == null){
            talons = new WebSocketTalons(netAddress);
        }
        netAddress = _netAddress;

        return talons;
    }

    private WebSocketConnection wsc = null;

    private WebSocketTalons(String netAddress){
        wsc = new WebSocketConnection();
        try {
            wsc.connect("ws://" + netAddress, new WebSocketHandler() {

                @Override
                public void onOpen() {
                }

                @Override
                public void onTextMessage(String payload) {
                }

                @Override
                public void onClose(int code, String reason) {
                }
            });
        } catch (WebSocketException e) {
            Log.d(this.getClass().getName(), e.getMessage().toString());
        }
    }

    public void setTextMessage(String message){
        if (wsc.isConnected()){
            try {
                wsc.sendTextMessage(message);
            } catch(Exception e){
                //maybe have Exception
            }
        }
    }

    public void close(){
        wsc.disconnect();
    }
}
