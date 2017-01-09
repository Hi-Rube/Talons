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
    private static String netAddress;

    public static void setNetAddress(String netAddress){
        if (!WebSocketTalons.netAddress.equals(netAddress)){
            WebSocketTalons.netAddress = netAddress;
            talons = new WebSocketTalons(netAddress);
        }
    }

    public static void create(){
        if (talons == null){
            talons = new WebSocketTalons();
        }
    }

    private WebSocketConnection wsc = null;

    public WebSocketTalons(){
    }

    public WebSocketTalons(String netAddress){
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
}
