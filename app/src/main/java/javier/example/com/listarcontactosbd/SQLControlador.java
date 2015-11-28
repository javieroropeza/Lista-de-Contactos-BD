package javier.example.com.listarcontactosbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Javier on 11/11/2015.
 */

public class SQLControlador {

        private BdHelper dbhelper;
        private Context ourcontext;
        private SQLiteDatabase database;

        public SQLControlador(Context c) {
            ourcontext = c;
        }

        public SQLControlador abrirBaseDeDatos() throws android.database.SQLException {
            dbhelper = new BdHelper(ourcontext);
            database = dbhelper.getWritableDatabase();
            return this;
        }

        public void cerrar() {
            dbhelper.close();
        }

        public void insertarDatos(String name, String phone) {
            ContentValues cv = new ContentValues();
            cv.put(BdHelper.MIEMBRO_NOMBRE, name);
            cv.put(BdHelper.MIEMBRO_TELEFONO, phone);
            database.insert(BdHelper.TABLE_MEMBER, null, cv);
        }

        public Cursor leerDatos() {
            String[] todasLasColumnas = new String[] {
                    BdHelper.MIEMBRO_ID,
                    BdHelper.MIEMBRO_NOMBRE,
                    BdHelper.MIEMBRO_TELEFONO
            };
            Cursor c = database.query(BdHelper.TABLE_MEMBER, todasLasColumnas, null,
                    null, null, null, null);
            if (c != null) {
                c.moveToFirst();
            }
            return c;
        }

        public int actualizarDatos(String memberName, String memberPhone) {
            ContentValues cvActualizar = new ContentValues();
            cvActualizar.put(BdHelper.MIEMBRO_NOMBRE, memberName);
            cvActualizar.put(BdHelper.MIEMBRO_TELEFONO, memberPhone);
            int i = database.update(BdHelper.TABLE_MEMBER, cvActualizar,
                    BdHelper.MIEMBRO_ID + " = " + memberName, null);
            return i;
        }

        public void deleteData(long memberID) {
            database.delete(BdHelper.TABLE_MEMBER, BdHelper.MIEMBRO_ID + "="
                    + memberID, null);
        }
}

