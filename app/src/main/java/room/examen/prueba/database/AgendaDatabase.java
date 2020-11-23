package room.examen.prueba.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import room.examen.prueba.dao.AgendaDAO;
import room.examen.prueba.entity.Agenda;

@Database(entities = {Agenda.class}, version = 1, exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {

    public abstract AgendaDAO getAgendaDAO();

    private static volatile AgendaDatabase INSTANCIA;

    public static AgendaDatabase getDatabase(final Context context){
        if (INSTANCIA == null){
            synchronized (AgendaDatabase.class){
                if (INSTANCIA == null){
                    INSTANCIA = Room.databaseBuilder(context.getApplicationContext(),
                            AgendaDatabase.class, "agenda.sqlite").build();
                }
            }
        }
        return INSTANCIA;
    }
}
