package room.examen.prueba.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;

import java.util.List;

import room.examen.prueba.MainActivity;
import room.examen.prueba.R;
import room.examen.prueba.entity.Agenda;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder>{

    private OnItemClickListener listener;
    private Context context;
    private Activity activity;
    private MainViewModel viewModel;


    public interface OnItemClickListener{
        void onItemClick(Agenda contact);
    }

    private LayoutInflater inflater;
    private List<Agenda> contactList;

    public AgendaAdapter(Context context, OnItemClickListener listener){
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.context = context;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public AgendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.contacto_card, parent, false);
        return new AgendaViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull AgendaViewHolder holder, int position) {
        if (contactList != null){
            final Agenda current = contactList.get(position);
            Glide.with(context)
                    .load(R.drawable.contact)
                    .override(350, 350)
                    .into(holder.ivContact);

            holder.tvNombre.setText(current.nombre);
            holder.tvApellidos.setText(current.apellidos);
            holder.tvTelf.setText(String.valueOf(current.telefono));
            holder.tvDireccion.setText(current.calle + ", " + current.numero + "\n(" + current.localidad + ")");
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(current);
                }
            });
            /*
            holder.imDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel = ViewModelProviders.of((FragmentActivity) activity).get(MainViewModel.class);
                    viewModel.delete(current);
                }
            });
            */

        }else{
            holder.tvNombre.setText("ERROR");
        }
    }

    @Override
    public int getItemCount() {
        int elementos = 0;
        if (contactList != null){
            elementos = contactList.size();
        }
        return elementos;
    }

    public void setContactos(List<Agenda> contactList){
        this.contactList = contactList;
        notifyDataSetChanged();
    }

    public class AgendaViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivContact;
        private TextView tvNombre, tvApellidos, tvTelf, tvDireccion;
        LinearLayout layout;
        ImageView imDelete;

        public AgendaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivContact = itemView.findViewById(R.id.ivContact);// añadido
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvApellidos = itemView.findViewById(R.id.tvApellidos);
            tvTelf = itemView.findViewById(R.id.tvTelf);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            layout = itemView.findViewById(R.id.lyCard);
            //imDelete = itemView.findViewById(R.id.imDelete);
        }
    }
}
