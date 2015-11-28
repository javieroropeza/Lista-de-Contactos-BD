package javier.example.com.listarcontactosbd;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity{

    Button btnAgregarMiembro;
    ListView list;
    SQLControlador dbconeccion;
    TextView tv_miemID, tv_miemNombre,tvmiemTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
        btnAgregarMiembro = (Button) findViewById(R.id.btnagregar);
        list = (ListView) findViewById(R.id.lista);

        //acción del boton agregar miembro
        btnAgregarMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iagregar = new Intent(MainActivity.this, Agregar.class);
                startActivity(iagregar);
        }
    });
    // Tomar los datos desde la base de datos para poner en el curso y después en el adapter
    Cursor c = dbconeccion.leerDatos();
    String[] from = new String[] {
            BdHelper.MIEMBRO_ID,
            BdHelper.MIEMBRO_NOMBRE,
            BdHelper.MIEMBRO_TELEFONO
    };
    int[] to = new int[] {
            R.id.miembro_id,
            R.id.miembro_nombre,
            R.id.miembro_telefono
    };
    SimpleCursorAdapter adapter = new SimpleCursorAdapter(
            MainActivity.this, R.layout.formato_fila, c, from, to);
    adapter.notifyDataSetChanged();

    list.setAdapter(adapter);

    }
}