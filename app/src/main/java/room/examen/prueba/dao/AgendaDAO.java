package room.examen.prueba.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

import room.examen.prueba.entity.Agenda;

@Dao
public interface AgendaDAO {

    @Delete
    void delete(Agenda agenda);

    @Update
    int update2(Agenda... agenda);
/*

    */
    @Query("UPDATE agenda SET numero = :numero, telefono = :telefono, nombre = :nombre, apellidos = :apellidos, " +
            "localidad = :localidad, calle = :calle, fechaNacimiento = :fechaNacimiento WHERE id =:id")
    void update(int id, int numero, int telefono, String nombre, String apellidos, String localidad, String calle, String fechaNacimiento);

    @Insert
    void insert(Agenda agenda);

    @Query("select * from agenda where id = :id")
    Agenda get(int id);

    @Query("select * from agenda order by id asc")
    LiveData<List<Agenda>> getAllLive();

}