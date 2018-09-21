package parcial_ensayo.ensayoparcial;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receptor extends Thread{
	
	Socket s;
	OnMessage observer;
	
	public Receptor ( Socket s ) {
		this.s = s;
	}
	
	@Override
	public void run() {
		
		try {
			
			InputStream is = s.getInputStream();
			BufferedReader lector = new BufferedReader ( new InputStreamReader (is) );
			
			while (true) {
				
				String mensaje = lector.readLine();
				Log.e("RECIBIDO", mensaje);

				observer.recibido(mensaje);
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public interface OnMessage {

		public void recibido ( String mensaje );
	}

	public void setObserver ( OnMessage cualquiernombre ){

		this.observer = cualquiernombre;
	}

}
