package room.examen.prueba.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.Date;

@Entity (tableName = "agenda")
public class Agenda {
    /**
     * id
     * nombre
     * apellidos
     * teléfono
     * fecha de nacimiento
     * localidad
     * calle
     * número
     */

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "numero")
    public int numero;

    @ColumnInfo(name = "telefono")
    public int telefono;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "apellidos")
    public String apellidos;

    @ColumnInfo(name = "localidad")
    public String localidad;

    @ColumnInfo(name = "calle")
    public String calle;

    @ColumnInfo(name = "fechaNacimiento")
    public String fechaNacimiento;


        public Agenda(int numero, int telefono, String nombre, String apellidos, String localidad,
                      String calle, String fechaNacimiento){
            this.id = id;
            this.numero = numero;
            this.telefono = telefono;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.localidad = localidad;
            this.calle = calle;
            this.fechaNacimiento = fechaNacimiento;
        }

    @Override
    public String toString() {
        return "Agenda{" +
                "id=" + id +
                ", numero=" + numero +
                ", telefono=" + telefono +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", localidad='" + localidad + '\'' +
                ", calle='" + calle + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }

    public Agenda setId(int id) {
        this.id = id;
        return this;
    }
}
