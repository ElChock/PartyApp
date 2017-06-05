package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ayrton on 04/06/2017.
 */

public class SQLHelper extends SQLiteOpenHelper {

    private static final String SDatabaseName ="PSM.db";

    public SQLHelper(Context context) {
        super(context,SDatabaseName, null, 10);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //RAZOR_SQL
        //se llama una sola vez cuando se valla a crear la base de datos.
        String tableContacts="create table contact(idUsuario INTEGER PRIMARY KEY ," +
                                                    "nickName TEXT NOT NULL," +
                                                    "nombre TEXT NOT NULL," +
                                                    "correo TEXT NOT NULL," +
                                                    "contraseña TEXT ," +
                                                    "genero TEXT NOT NULL," +
                                                    "avatar TEXT );";

        db.execSQL(tableContacts);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contact");

        onCreate(db);
    }
}
