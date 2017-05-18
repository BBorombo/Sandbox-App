package com.borombo.sandboxapp.zxing;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.borombo.sandboxapp.R;
import com.borombo.sandboxapp.common.activities.CommonActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZxingGenerateActivity extends CommonActivity {

    @BindView(R.id.data)
    EditText data;
    @BindView(R.id.createQRCode)
    Button createButton;
    @BindView(R.id.qrCodeImage)
    ImageView qrCodeImage;

    public final static int QRcodeWidth = 500;
    private  static final String IMAGE_DIRECORY = "/QRCodeSandbox";
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing_generate);
        ButterKnife.bind(this);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.getText().toString().trim().length() !=0){
                    try{
                        bitmap = TextToImageEncode(data.getText().toString());
                        qrCodeImage.setImageBitmap(bitmap);
                        String path = saveImage(bitmap);
                        Toast.makeText(ZxingGenerateActivity.this, "QRCode saved to -> ", Toast.LENGTH_SHORT).show();
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(ZxingGenerateActivity.this, "Enter Data!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Bitmap TextToImageEncode(String value) throws WriterException {
        BitMatrix matrix;
        try{
            matrix = new MultiFormatWriter().encode(value, BarcodeFormat.DATA_MATRIX.QR_CODE, QRcodeWidth, QRcodeWidth, null);
        }catch (IllegalArgumentException e){
            return null;
        }

        int matrixWidth = matrix.getWidth();
        int matrixHeight = matrix.getHeight();
        int[] pixels = new int[matrixWidth * matrixHeight];

        for (int y = 0; y < matrixHeight; y++){
            int offset = y * matrixWidth;
            for (int x = 0; x < matrixWidth; x++){
                pixels[offset + x] = matrix.get(x,y) ?
                        ContextCompat.getColor(this, R.color.black) :
                        ContextCompat.getColor(this, R.color.white);

            }
        }
        Bitmap bitmap = Bitmap.createBitmap(matrixWidth, matrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, matrixWidth, matrixHeight);
        return  bitmap;
    }

    public String saveImage(Bitmap img){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECORY);

        if (!wallpaperDirectory.exists()){
            Log.d("DIR", "" + wallpaperDirectory.mkdirs());
            wallpaperDirectory.mkdirs();
        }

        try{
            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("SAVE", "File Saved:: --->" + f.getAbsolutePath());

            return  f.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
