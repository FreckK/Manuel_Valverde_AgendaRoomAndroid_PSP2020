package room.examen.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.regex.Pattern;

import room.examen.prueba.entity.Agenda;
import room.examen.prueba.view.MainViewModel;

public class AddActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ImageView ivContactAdd;
    private Button btGuardar, btCancelar, btBorrar;
    private EditText etNombre, etApellidos, etTelf, etCalle, etNumero, etLocalidad, etFechaNacimiento;

    private int id;
    private int numero;
    private int telefono;
    private String nombre;
    private String apellidos;
    private String localidad;
    private String calle;
    private String fechaNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initData();
        init();
        initEvents();
        Glide.with(getApplicationContext())
                .load(R.drawable.contact)
                .override(500, 500)
                .into(this.ivContactAdd);

    }

    private void init() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        ivContactAdd = findViewById(R.id.ivContactAdd);
        btGuardar = findViewById(R.id.btSaveAdd);
        btCancelar = findViewById(R.id.btCancelAdd);

        etNombre = findViewById(R.id.etNombre);
        etNombre.setText(nombre);

        etApellidos = findViewById(R.id.etApellidos);
        etApellidos.setText(apellidos);

        etTelf = findViewById(R.id.etTelf);
        if (telefono != -1){
            etTelf.setText(String.valueOf(telefono));
        }

        etCalle = findViewById(R.id.etCalle);
        etCalle.setText(calle);

        etNumero = findViewById(R.id.etNumero);
        if (telefono != -1){
            etNumero.setText(String.valueOf(numero));
        }

        etLocalidad = findViewById(R.id.etLocalidad);
        etLocalidad.setText(localidad);

        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etFechaNacimiento.setText(fechaNacimiento);

        btBorrar = findViewById(R.id.btBorrar);
        if (id != -1){
            btBorrar.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        nombre = intent.getStringExtra("nombre");
        apellidos = intent.getStringExtra("apellidos");
        telefono = intent.getIntExtra("telefono", -1);
        calle = intent.getStringExtra("calle");
        numero = intent.getIntExtra("numero", -1);
        localidad = intent.getStringExtra("localidad");
        fechaNacimiento = intent.getStringExtra("fechaNacimiento");

    }


    private void initEvents() {
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regexp = "\\d{1,2}/\\d{1,2}/\\d{4}";
                if (!Pattern.matches(regexp, etFechaNacimiento.getText().toString())){
                    if (etFechaNacimiento.getText().toString().equalsIgnoreCase("")){

                    }else{
                        Toast.makeText(getApplicationContext(), "FORMATO DE FECHA INCORRECTO", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                nombre = etNombre.getText().toString();
                apellidos = etApellidos.getText().toString();
                telefono = Integer.parseInt(etTelf.getText().toString());
                calle = etCalle.getText().toString();
                numero = Integer.parseInt(etNumero.getText().toString());
                localidad = etLocalidad.getText().toString();
                fechaNacimiento = etFechaNacimiento.getText().toString();

                Agenda contacto;
                contacto = new Agenda(numero, telefono, nombre, apellidos, localidad, calle, fechaNacimiento);

                if (id != -1){
                    contacto.setId(id);
                    viewModel.update(contacto);
                }else {
                    viewModel.insert(contacto);
                }
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });
        if (id != -1){
            btBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Agenda contacto;
                    contacto = new Agenda(numero, telefono, nombre, apellidos, localidad, calle, fechaNacimiento);
                    contacto.setId(id);
                    viewModel.delete(contacto);
                    startActivity(new Intent(AddActivity.this, MainActivity.class));
                }
            });
        }
    }

}