package com.example.dfloresalarcon.domoticapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cargamos el layout que solo tiene un Framelayout
        setContentView(R.layout.activity_main);

        //****************************
        //*****Añadido por Robert-1*******

        //**Activo bluetooth
        bluetoothAdapter= BluetoothAdapter.getDefaultAdapter();
        Toast.makeText(this, "Bluetooth available: "+ BluetoothAvailable(),
                Toast.LENGTH_LONG).show();
        if (BluetoothAvailable())
            EnableBluetooth();
        MyBTBroadcastReceiver mReceiver = new MyBTBroadcastReceiver();
        IntentFilter intentFilter = new
                IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        registerReceiver(mReceiver, intentFilter);

        //******Fin Añadido por Robert-1********
        //*************************************

        final Bundle estado = savedInstanceState;

        final Button temperatura = (Button)findViewById(R.id.btnTem);
        final  Button humedad = (Button)findViewById(R.id.btnHum);
        final Button luz1 = (Button)findViewById(R.id.btnLuz1);
        final Button luz2 = (Button)findViewById(R.id.btnLuz2);
        final Button luz3 = (Button)findViewById(R.id.btnLuz3);
        final Button luz4 = (Button)findViewById(R.id.btnLuz4);
        final Button salir = (Button)findViewById(R.id.btnSal);
        final Switch portal = (Switch)findViewById(R.id.abrircerrar);
        final Button ayuda = (Button)findViewById(R.id.btnAyu);
        final Button acercade = (Button)findViewById(R.id.btnAce);

        /**************** TEMPERATURA *******************************/
        temperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cargamos el fragment en el 'container'
                // este esta en el layout de la Activity
                if (estado == null) {
                    getFragmentManager().beginTransaction()
                            .add(R.id.container, new FragmentTemperatura())
                            .commit();
                }
            }
        });

        /**************** HUMEDAD *******************************/
        humedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cargamos el fragment en el 'container'
                // este esta en el layout de la Activity
                if (estado == null) {
                    getFragmentManager().beginTransaction()
                            .add(R.id.container, new FragmentHumedad())
                            .commit();
                }
            }
        });

        /**************** LUZ *******************************/
        luz1.setOnClickListener(new View.OnClickListener() {
            int num1;
            @Override
            public void onClick(View view) {
                if(num1%2==0)
                {
                    luz1.setBackgroundResource(R.drawable.iconsluz);
                    Toast.makeText(getApplicationContext(), "Apagando Bombilla 1", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    luz1.setBackgroundResource(R.drawable.iconluz);
                    Toast.makeText(getApplicationContext(), "Encendiendo Bombilla 1", Toast.LENGTH_SHORT).show();
                }
                num1++;
            }
        });

        /**************** LUZ *******************************/
        luz2.setOnClickListener(new View.OnClickListener() {
            int num2;
            @Override
            public void onClick(View view) {
                if(num2%2==0)
                {
                    luz2.setBackgroundResource(R.drawable.iconsluz);
                    Toast.makeText(getApplicationContext(), "Apagando Bombilla 2", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    luz2.setBackgroundResource(R.drawable.iconluz);
                    Toast.makeText(getApplicationContext(), "Encendiendo Bombilla 2", Toast.LENGTH_SHORT).show();
                }
                num2++;
            }
        });

        /**************** LUZ *******************************/
        luz3.setOnClickListener(new View.OnClickListener() {
            int num3;
            @Override
            public void onClick(View view) {
                if(num3%2==0)
                {
                    luz3.setBackgroundResource(R.drawable.iconsluz);
                    Toast.makeText(getApplicationContext(), "Apagando Bombilla 3", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    luz3.setBackgroundResource(R.drawable.iconluz);
                    Toast.makeText(getApplicationContext(), "Encendiendo Bombilla 3", Toast.LENGTH_SHORT).show();
                }
                num3++;
            }
        });

        /**************** LUZ *******************************/
        luz4.setOnClickListener(new View.OnClickListener() {
            int num4;
            @Override
            public void onClick(View view) {
                if(num4%2==0)
                {
                    luz4.setBackgroundResource(R.drawable.iconsluz);
                    Toast.makeText(getApplicationContext(), "Apagando Bombilla 4", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    luz4.setBackgroundResource(R.drawable.iconluz);
                    Toast.makeText(getApplicationContext(), "Encendiendo Bombilla 4", Toast.LENGTH_SHORT).show();
                }
                num4++;
            }
        });


        /**************** PORTAL *******************************/
        //*******Metodo modificado por Robert para que envie datos ó arduino****
        portal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    try {
                        sendData("Y");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(),"Activado",Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        sendData("N");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(),"Desactivado",Toast.LENGTH_SHORT).show();
                }

            }
        });

        /**************** SALIR *******************************/
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cargamos el fragment en el 'container'
                // este esta en el layout de la Activity
                if (estado == null) {
                    getFragmentManager().beginTransaction()
                            .add(R.id.container, new FragmentSalir())
                            .commit();
                }
            }
        });

        /**************** AYUDA *******************************/
        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cargamos el fragment en el 'container'
                // este esta en el layout de la Activity
                if (estado == null) {
                    getFragmentManager().beginTransaction()
                            .add(R.id.container, new FragmentAyuda())
                            .commit();
                }
            }
        });

        /**************** ACERCA DE *******************************/
        acercade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cargamos el fragment en el 'container'
                // este esta en el layout de la Activity
                if (estado == null) {
                    getFragmentManager().beginTransaction()
                            .add(R.id.container, new FragmentAcercaDe())
                            .commit();
                }
            }
        });

    }
    //******************************************
    //***Añadido por Robert-2:************************

    //Metodos para a xestión do Bluetooth (Activación do bluetooth no dispositivo)
    BluetoothAdapter bluetoothAdapter;
    static final int REQUEST_ENABLE_BT = 0;
    //---Método donde Evaluo si el  bluetooth está disponible en el dispositivoe---
    private boolean BluetoothAvailable()
    {
        if (bluetoothAdapter== null)
            return false;
        else
            return true;
    }
    //---Método para Activar bluetooth en el dispositivo---
    private void EnableBluetooth() {
        if (BluetoothAvailable() && !bluetoothAdapter.isEnabled()) {
            Intent i = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(i,
                    REQUEST_ENABLE_BT);
        }
    }
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        if (requestCode== REQUEST_ENABLE_BT) {
            if (resultCode== RESULT_OK)
            {
                Toast.makeText(this, "Bluetooth turned on!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    //Defino Variables para hacer funcionar la conexión Bluetooth
    //Creo Socket para que el Dispositivo Android funcione como cliente.
    private static final String TAG = "LEDOnOff";
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    // SPP UUID para comunicaciones Bluetooth (es siempre el mismo nº para comunicaciones BLUETOOTH)
    private static final UUID MY_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // Direccion MAC del servidor con el que me quiero conectar (Bluetooth del Arduino)
    private static String address = "30:14:12:02:35:42";

    //Metodo onREsume que se executa en bulce onde establece conexión mediante socket co servidor Bluetooth do Arduino
    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "...In onResume - Attempting client connect...");

        // Set up a pointer to the remote node using it's address.
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);

        // Two things are needed to make a connection:
        //   A MAC address, which we got above.
        //   A Service ID or UUID.  In this case we are using the
        //     UUID for SPP.
        try {
            btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
        }

        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        bluetoothAdapter.cancelDiscovery();

        // Establish the connection.  This will block until it connects.
        Log.d(TAG, "...Connecting to Remote...");
        try {
            btSocket.connect();
            Log.d(TAG, "...Connection established and data link opened...");
        } catch (IOException e) {
            try {
                btSocket.close();
            } catch (IOException e2) {
                errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
            }
        }

        // Create a data stream so we can talk to server.
        Log.d(TAG, "...Creating Socket...");

        try {
            outStream = btSocket.getOutputStream();
        } catch (IOException e) {
            errorExit("Fatal Error", "In onResume() and output stream creation failed:" + e.getMessage() + ".");
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, "...In onPause()...");

        if (outStream != null) {
            try {
                outStream.flush();
            } catch (IOException e) {
                errorExit("Fatal Error", "In onPause() and failed to flush output stream: " + e.getMessage() + ".");
            }
        }

        try     {
            btSocket.close();
        } catch (IOException e2) {
            errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
        }
    }
    //Clase MyBTBroadcastReceiver, que xestiona o estado do BlueTooth
    public class MyBTBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getExtras().getInt(BluetoothAdapter.EXTRA_STATE);
            switch (state) {
                case BluetoothAdapter.STATE_OFF:
                    Toast.makeText(context, "Off", Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    Toast.makeText(context, "Turning Off",
                            Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothAdapter.STATE_ON:
                    Toast.makeText(context, "On", Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    Toast.makeText(context, "Turning On",
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
    //--
    //Metodo para enviar mensaxes do Android ao Arduino:
    private void sendData(String message) throws IOException {
        byte[] msgBuffer = message.getBytes();

        Log.d(TAG, "...Sending data: " + message + "...");

        try {
            outStream.write(msgBuffer);
        } catch (IOException e) {
            String msg = "In onResume() and an exception occurred during write: " + e.getMessage();
            if (address.equals("30:14:12:02:35:42"))
                msg = msg + ".\n\nUpdate your server address from 30:14:12:02:35:42 to the correct address on line 37 in the java code";
            msg = msg +  ".\n\nCheck that the SPP UUID: " + MY_UUID.toString() + " exists on server.\n\n";

            errorExit("Fatal Error", msg);
        }
    }
    private void errorExit(String title, String message){
        Toast msg = Toast.makeText(getBaseContext(),
                title + " - " + message, Toast.LENGTH_SHORT);
        msg.show();
        finish();
    }
    //--

    //****FIN de lo Añadido por Robert
    //********************************


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
