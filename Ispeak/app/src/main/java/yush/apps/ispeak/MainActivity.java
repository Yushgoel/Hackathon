package yush.apps.ispeak;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private TextView message;
    private Button connect;

    private ArrayList<String> mDeviceList = new ArrayList<String>();
    private BluetoothAdapter mBluetoothAdapter;
    InputStream mmInputStream;

    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    private String remoteDeviceMac;
    private String address;

    ArrayList<BluetoothDevice> deviceArrayList;

    private static TextToSpeech mtts;

    Thread workerThread;
    volatile boolean stopWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = (TextView) findViewById(R.id.message);
        connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    connect_to_glove();
                }
                catch (Exception e)
                {
                    // pass
                }
            }
        });

        mtts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {

                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (!pairedDevices.isEmpty()) {
            mmDevice = pairedDevices.iterator().next();
        }
    }

    private void connect_to_glove() throws IOException
    {
        Log.d("BT_TEST", "Device selected is " + mmDevice.getName());

        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID

        mmSocket = mmDevice.createInsecureRfcommSocketToServiceRecord(uuid);

        try{
            mmSocket.connect();
            Thread.sleep(5000);
            mmInputStream = mmSocket.getInputStream();

            Log.w("BT", "Receive data");

            message.setText("ISpeak connected! You can now speak!");
            mtts.speak("I Speak Glove Connected! You can speak now!", TextToSpeech.QUEUE_FLUSH, null);
            Thread.sleep(2500);
            GetData();
        }
        catch (IOException ioException){
            Log.w("BT", "Could not connect first time", ioException);
            message.setText("Failed to connect to ISpeak. Try again");
            mtts.speak("Unable to connect to I Speak. Try again.", TextToSpeech.QUEUE_FLUSH, null);


        }
        catch (InterruptedException e1) {
            Log.w("BT", "THREAD Exception " + e1.getMessage(), e1);
            message.setText("Failed to connect to ISpeak. Try again");
            mtts.speak("Unable to connect to I Speak. Try again.", TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    void GetData()
    {
        Log.d("TAG", " beginListenForData() - ");

        stopWorker = false;


        workerThread = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopWorker)
                {
                    try
                    {
                        int bytesAvailable = 2;

                        byte[] packetBytes = new byte[bytesAvailable];
                        mmInputStream.read(packetBytes);

                        for(int i=0;i<bytesAvailable;i++)
                        {
                            byte b = packetBytes[i];
                            char a = (char) b;
                            if (a == '1') {
                                //Log.d("Spica", " data byte " + a);
                                //message.setText("MY");
                                mtts.speak("My", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == '2')
                            {
                                //message.setText("NAME");
                                mtts.speak("name", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == '3')
                            {
                                //message.setText("IS");
                                mtts.speak("is", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == '4')
                            {
                                //message.setText("AM");
                                mtts.speak("am", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == '5')
                            {
                                //message.setText("GOOD");
                                mtts.speak("good", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == '6')
                            {
                                //message.setText("HI");
                                mtts.speak("hi", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == '7')
                            {
                                //message.setText("HOW");
                                mtts.speak("how", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == '8')
                            {
                                //message.setText("ARE");
                                mtts.speak("are", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == '9')
                            {
                                //message.setText("YOU");
                                mtts.speak("you", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == 'a')
                            {
                                //message.setText("I");
                                mtts.speak("I", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == 'b')
                            {
                                //message.setText("LOVE");
                                mtts.speak("love", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == 'c')
                            {
                                //message.setText("SLEEP");
                                mtts.speak("sleep", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == 'd')
                            {
                                //message.setText("HATE");
                                mtts.speak("hate", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == 'e')
                            {
                                //message.setText("ME");
                                mtts.speak("me", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == 'f')
                            {
                                //message.setText("FRIEND");
                                mtts.speak("friend", TextToSpeech.QUEUE_FLUSH, null);
                            }
                            else if (a == 'g'){
                                //message.setText("THANK");
                                mtts.speak("Thank", TextToSpeech.QUEUE_FLUSH, null);
                            }
                        }

                    }
                    catch (IOException ex)
                    {
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
    }

}
