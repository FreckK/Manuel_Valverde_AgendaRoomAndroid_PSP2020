package room.examen.prueba.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import room.examen.prueba.entity.Agenda;
import room.examen.prueba.model.Repository;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Agenda>> agendas;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        agendas = repository.getContactosLive();
    }

    public LiveData<List<Agenda>> getContactos(){
        return agendas;
    }

    public void insert(Agenda agenda){
        repository.insertContacto(agenda);
    }

    public void update(Agenda contacto){
        repository.updateContacto(contacto);
    }

    public void delete(Agenda contacto){
        repository.deleteContacto(contacto);
    }

}
