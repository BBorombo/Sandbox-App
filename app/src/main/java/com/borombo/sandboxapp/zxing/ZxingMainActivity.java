package com.borombo.sandboxapp.zxing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.activities.CommonActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZxingMainActivity extends CommonActivity {

    @BindView(R.id.name)
    TextView nameData;
    @BindView(R.id.address)
    TextView addressData;
    @BindView(R.id.scanButton)
    Button scanButton;
    @BindView(R.id.generateButton)
    Button generateButton;

    private IntentIntegrator qrScan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing_main);
        setUpActionBar(getString(R.string.zxing));
        ButterKnife.bind(this);

        qrScan = new IntentIntegrator(this);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.initiateScan();
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZxingMainActivity.this, ZxingGenerateActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() != null){
                try{
                    JSONObject object = new JSONObject(result.getContents());
                    nameData.setText(object.getString("name"));
                    addressData.setText(object.getString("address"));
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();

                }
            }else{
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
