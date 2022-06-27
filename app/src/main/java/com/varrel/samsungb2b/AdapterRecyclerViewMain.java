package com.varrel.samsungb2b;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

class AdapterRecyclerViewMain extends RecyclerView.Adapter<AdapterRecyclerViewMain.ViewHolder> {


   class ViewHolder extends RecyclerView.ViewHolder{
      ImageView imageAvatar;
      TextView txtFullName;
      TextView txtEmail;
      Button buttonDelete;

      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         imageAvatar = itemView.findViewById(R.id.adapter_recyclerview_user_avatar);
         txtFullName = itemView.findViewById(R.id.adapter_recyclerview_user_tv_name);
         txtEmail = itemView.findViewById(R.id.adapter_recyclerview_user_tv_email);
         buttonDelete = itemView.findViewById(R.id.adapter_recyclerview_user_button_delete);

      }

   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recyclerview_user, parent, false);
      return new ViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      DataUser dataUser = Data.Data_User.get(position);

      Glide.with(holder.itemView)
              .load(dataUser.avatar)
              .into(holder.imageAvatar);
      holder.txtFullName.setText(dataUser.first_name +" "+ dataUser.last_name);
      holder.txtEmail.setText(dataUser.email);
      holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Data.Data_User.remove(holder.getAdapterPosition());
            notifyDataSetChanged();
         }
      });
   }

   @Override
   public int getItemCount() {
      return Data.Data_User.size();
   }
}
