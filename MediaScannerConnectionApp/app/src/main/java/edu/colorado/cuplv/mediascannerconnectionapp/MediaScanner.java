package edu.colorado.cuplv.mediascannerconnectionapp;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

public class MediaScanner extends AppCompatActivity implements MediaScannerConnection.OnScanCompletedListener {

    private String mScanDir = "/storage/sdcard0/";
    private String[] paths;
    private MediaScannerConnection mediaScannerConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_scanner);

        File[] files = new File(mScanDir).listFiles();
        paths = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            paths[i] = files[i].getAbsolutePath();
        }

        mediaScannerConnection = new MediaScannerConnection(getApplicationContext(), new MediaScannerConnection.MediaScannerConnectionClient() {
            @Override
            public void onMediaScannerConnected() {
                int i;
                for(i=0;i<paths.length;i++){
                    mediaScannerConnection.scanFile(paths[i], null);
                }
            }

            @Override
            public void onScanCompleted(String path, Uri uri) {
                this.onScanCompleted(path, uri);
            }
        });
        mediaScannerConnection.connect();
    }


    @Override
    public void onScanCompleted(String path, Uri uri) {
        // ignore
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_media_scanner, menu);
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

class __fresh__MSCClient__ implements MediaScannerConnection.MediaScannerConnectionClient {
    private MediaScannerConnection.OnScanCompletedListener __fresh__mListener__;
    private MediaScannerConnection __fresh__mConnection__;
    private String[] __fresh__mPath__;
    private String[] __fresh__mMimeType__;
    private int __fresh__pathsRemaining__;

    public __fresh__MSCClient__(MediaScannerConnection.OnScanCompletedListener __fresh__mListener__, String[] __fresh__mPath__, String[] __fresh__mMimeType__) {
        this.__fresh__mListener__ = __fresh__mListener__;
        this.__fresh__mPath__ = __fresh__mPath__;
        this.__fresh__mMimeType__ = __fresh__mMimeType__;
    }

    public void startConnection(MediaScannerConnection __fresh__msc__) {
        __fresh__mConnection__ = __fresh__msc__;
        __fresh__mConnection__.connect();
    }

    @Override
    public void onMediaScannerConnected() {
        int i;
        for (i = 0; i < __fresh__mPath__.length; i++) {
            __fresh__mConnection__.scanFile(__fresh__mPath__[i], __fresh__mMimeType__[i]);
        }
    }

    @Override
    public void onScanCompleted (String path, Uri uri){
        if (__fresh__mListener__ != null) __fresh__mListener__.onScanCompleted(path, uri);
        __fresh__pathsRemaining__--;
        if (__fresh__pathsRemaining__ == 0){
            __fresh__mConnection__.disconnect();
        }
    }
}
