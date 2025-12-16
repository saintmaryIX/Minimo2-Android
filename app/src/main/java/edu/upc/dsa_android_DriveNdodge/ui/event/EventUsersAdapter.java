package edu.upc.dsa_android_DriveNdodge.ui.event;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.dsa_android_DriveNdodge.R;
import edu.upc.dsa_android_DriveNdodge.models.EventUser;

public class EventUsersAdapter extends RecyclerView.Adapter<EventUsersAdapter.EventUserViewHolder> {

    //copio adapter del proyecto
    private final List<EventUser> users;

    public EventUsersAdapter(List<EventUser> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public EventUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_event_user, parent, false);
        return new EventUserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventUserViewHolder holder, int position) {
        EventUser u = users.get(position);

        holder.txtName.setText(u.getNombre() + " " + u.getApellido());

        Picasso.get()
                .load(u.getImagen())
                .into(holder.imgUser);
    } //usamos librería picasso y no una random de chat

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    static class EventUserViewHolder extends RecyclerView.ViewHolder {

        ImageView imgUser;
        TextView txtName;

        EventUserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.imgUser);
            txtName = itemView.findViewById(R.id.txtUserName);
        }
    }
}
