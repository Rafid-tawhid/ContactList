package com.example.day5.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day5.R;
import com.example.day5.entities.ContactModel;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {


    private Context context;
    private List<ContactModel> contactModelList;
    private ContactEditDeleteListener listener;

    public ContactAdapter(Context context, List<ContactModel> contactModelList, Fragment fragment) {
        this.context = context;
        this.contactModelList = contactModelList;
        listener= (ContactEditDeleteListener) fragment;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);


        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        holder.nameTv.setText(contactModelList.get(position).getName());
        holder.addressTv.setText(contactModelList.get(position).getAddress());
        holder.bloodTv.setText(contactModelList.get(position).getBloodGroup());


        holder.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Uri phoneUri=Uri.parse("tel:" + contactModelList.get(position).getPhone());
                final Intent intent = new Intent(Intent.ACTION_DIAL,phoneUri);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }


            }
        });
        holder.smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+contactModelList.get(position).getPhone()));  // This ensures only SMS apps respond
                intent.putExtra("sms_body","Hellow" );

                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }

            }
        });
        holder.emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+contactModelList.get(position).getEmail())); // only email apps should handle this

                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);

                }

            }
        });
        holder.menuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final PopupMenu popupMenu=new PopupMenu(context,v);
                popupMenu.getMenuInflater().inflate(R.menu.contact_menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId())
                        {
                            case R.id.contact_edit:
                                final  Bundle bundle=new Bundle();
                                bundle.putLong("id",contactModelList.get(position).getId());

                                Navigation.findNavController(v).navigate(R.id.addContactFragment,bundle);

                            break;
                            case R.id.contact_delete:
                                showAlertDialog(position);
                                break;
                        }
                        return false;
                    }
                });

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Bundle bundle=new Bundle();
                bundle.putLong("id",contactModelList.get(position).getId());

                Navigation.findNavController(v).navigate(R.id.contactDetailsFragment,bundle);
            }
        });

    }

    private void showAlertDialog(int position) {

        final AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Delete "+contactModelList.get(position).getName()+"?");
        builder.setMessage("Are you sure to delete this contact ?");
        builder.setIcon(R.drawable.delete);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                listener.delete(contactModelList.get(position));
                Toast.makeText(context,"Item has been Deleted",Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("Cancel",null);

        final AlertDialog dialog=builder.create();
        dialog.show();

    }

    @Override
    public int getItemCount() {
        return contactModelList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

         TextView nameTv,addressTv,menuTv,bloodTv;
         ImageView callBtn,smsBtn,emailBtn;

         public ContactViewHolder(@NonNull View itemView) {
             super(itemView);


             nameTv=itemView.findViewById(R.id.row_contact_name);
             addressTv=itemView.findViewById(R.id.row_contact_address);
             bloodTv=itemView.findViewById(R.id.row_contact_bldGrp);
             menuTv=itemView.findViewById(R.id.menuBtn);
             callBtn=itemView.findViewById(R.id.callBtn);
             emailBtn=itemView.findViewById(R.id.mailBtn);
             smsBtn=itemView.findViewById(R.id.smsBtn);
         }
     }

     public interface ContactEditDeleteListener
     {

         void edit(ContactModel contactModel);
         void delete(ContactModel contactModel);
     }
}
