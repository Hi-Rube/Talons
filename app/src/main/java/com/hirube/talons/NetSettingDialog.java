package com.hirube.talons;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Rube on 17/1/9.
 */
public abstract class NetSettingDialog extends Dialog {

    Context context;
    String netAddress = "";
    EditText netInput = null;
    Button okBtn = null;

    public NetSettingDialog(Context context) {
        super(context);
        this.context = context;
    }

    public void create(){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_netsetting, null);
        this.addContentView(layout, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        this.setTitle(R.string.action_net_setting);

        netInput = (EditText) this.findViewById(R.id.dialog_netsetting_netinput);

        okBtn = (Button) this.findViewById(R.id.dialog_netsetting_okbtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dealConfirmNetAddress();
                hide();
            }
        });
    }

    @Override
    public void show(){
        netInput.setText(netAddress);
        this.show();
    }

    private void dealConfirmNetAddress(){
        netAddress = netInput.getText().toString();
        onConfirmNetAddress(netAddress);
    }

    public abstract void onConfirmNetAddress(String netAddress);
}
