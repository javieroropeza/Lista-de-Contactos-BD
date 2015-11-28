package javier.example.com.listarcontactosbd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Javier on 11/11/2015.
 */
public class Agregar extends Activity  implements View.OnClickListener {

    private EditText etno, ette,etid;
    Button btnAgregar, read_bt;
    SQLControlador dbconeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar);
        etno = (EditText) findViewById(R.id.etnom);
        ette = (EditText) findViewById(R.id.ettel);
        btnAgregar = (Button) findViewById(R.id.btngu);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
        btnAgregar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btngu:
                String name = etno.getText().toString();
               String phone = ette.getText().toString();
                dbconeccion.insertarDatos(name,phone);
                Intent main = new Intent(Agregar.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;

            default:
                break;
        }
    }
}
