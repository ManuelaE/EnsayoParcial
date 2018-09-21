package parcial_ensayo.ensayoparcial;

import android.util.Log;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente extends Thread {

    Socket s;


    MainActivity activity;

    public Cliente ( MainActivity activity ) {
        this.activity = activity;
    }

    @Override
    public void run() {

        try{

            s = new Socket("10.0.2.2", 5000);

            Receptor rec = new Receptor( s );
            rec.setObserver(activity);
            rec.start();

        } catch (Exception e){

            e.printStackTrace();
        }
    }

    public void enviar (final String msj) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    OutputStream os = s.getOutputStream();
                    Log.e("ENVIAR", "ME PREPARO PARA ENVIAR UN MENSAJE");

                    PrintWriter mensaje = new PrintWriter( new OutputStreamWriter(os));
                    mensaje.println(msj);

                    mensaje.flush();

                } catch ( Exception e ) {

                    e.printStackTrace();
                }
            }
        }).start();
    }
}
