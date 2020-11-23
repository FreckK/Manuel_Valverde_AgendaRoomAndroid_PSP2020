package room.examen.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.LocalDateTime;
import java.util.List;

import room.examen.prueba.entity.Agenda;
import room.examen.prueba.view.AgendaAdapter;
import room.examen.prueba.view.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private Button btInsertar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initEvents();
    }

    private void init() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        btInsertar = findViewById(R.id.btInsertar);
        RecyclerView rvContactList = findViewById(R.id.rvContactos);
        rvContactList.setLayoutManager(new LinearLayoutManager(this));
        final AgendaAdapter contactAdapter = new AgendaAdapter(this, new AgendaAdapter.OnItemClickListener() {
            //Este Listener es con el que actuamos cuando le damos a algun item
            @Override
            public void onItemClick(Agenda contacto) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("nombre", contacto.nombre);
                intent.putExtra("apellidos", contacto.apellidos);
                intent.putExtra("telefono", contacto.telefono);
                intent.putExtra("fechaNacimiento", contacto.fechaNacimiento);
                intent.putExtra("localidad", contacto.localidad);
                intent.putExtra("calle", contacto.calle);
                intent.putExtra("numero", contacto.numero);
                intent.putExtra("id", contacto.id);
                intent.putExtra("edit", true);
                startActivity(intent);
            }
        });
        contactAdapter.setActivity(this);
        rvContactList.setAdapter(contactAdapter);
        viewModel.getContactos().observe(MainActivity.this, new Observer<List<Agenda>>() {
            @Override
            public void onChanged(List<Agenda> agendas) {
                for (Agenda contacto : agendas){
                    //tvAgendas.append(contacto.toString() + "\n");
                    contactAdapter.setContactos(agendas);
                }
            }
        });
    }

    private void initEvents() {
        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Agenda agenda = null;
                    agenda = new Agenda(1, 657305293, "Pepo", "Delpino", "Fuente Vaqueros", "Avda Andalucia", String.valueOf(LocalDateTime.now()));
                //viewModel.insert(agenda);
                //agenda.setId(2);
                //viewModel.update(agenda);
                //viewModel.delete(agenda);
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
    }


}