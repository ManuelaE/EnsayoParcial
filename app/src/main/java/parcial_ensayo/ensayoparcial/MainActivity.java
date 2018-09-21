package parcial_ensayo.ensayoparcial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Receptor.OnMessage {

    Cliente c;
    Button btnMensaje;
    TextView tv;
    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = new Cliente(this);
        c.start();

        tv =findViewById(R.id.tv_tv);
        ed = findViewById(R.id.ed_este);

        btnMensaje = findViewById(R.id.btn_mensaje);
        btnMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c.enviar(ed.getText().toString());
            }
        });
    }

    @Override
    public void recibido(final String mensaje) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                tv.setText(tv.getText().toString()+"\n"+mensaje);
            }
        });

        Log.e("OBSERVER", "LLEGÓ "+ mensaje);
    }
}
