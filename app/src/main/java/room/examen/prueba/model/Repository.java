package room.examen.prueba.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import room.examen.prueba.dao.AgendaDAO;
import room.examen.prueba.database.AgendaDatabase;
import room.examen.prueba.entity.Agenda;

public class Repository {

    private AgendaDAO agendaDAO;
    private LiveData<List<Agenda>> agendaLive;

    public Repository(Context context){
        AgendaDatabase db = AgendaDatabase.getDatabase(context);
        agendaDAO = db.getAgendaDAO();
        agendaLive = agendaDAO.getAllLive();
    }

    public LiveData<List<Agenda>> getContactosLive(){
        return agendaLive;
    }

    public void insertContacto(Agenda agenda){
        new InsertThread().execute(agenda);
    }

    private class InsertThread extends AsyncTask<Agenda, Void, Void> {

        @Override
        protected Void doInBackground(Agenda... agendas) {
            agendaDAO.insert(agendas[0]);
            return null;
        }
    }

    public void updateContacto(Agenda contacto){
        new UpdateThread().execute(contacto);
    }

    private class UpdateThread extends AsyncTask<Agenda, Void, Void>{

        @Override
        protected Void doInBackground(Agenda... contactos) {
            agendaDAO.update(contactos[0].id, contactos[0].numero, contactos[0].telefono, contactos[0].nombre, contactos[0].apellidos, contactos[0].localidad, contactos[0].calle, contactos[0].fechaNacimiento);
            return null;
        }
    }

    public void deleteContacto(Agenda contacto){
        new DeleteThread().execute(contacto);
    }

    private class DeleteThread extends AsyncTask<Agenda, Void, Void>{

        @Override
        protected Void doInBackground(Agenda... contactos) {
            agendaDAO.delete(contactos[0]);
            return null;
        }
    }
}
