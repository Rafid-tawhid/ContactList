package com.example.day5.destination;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.day5.R;
import com.example.day5.databinding.FragmentContactDetailsBinding;
import com.example.day5.entities.ContactModel;
import com.example.day5.viewmodels.ContactViewModel;


public class ContactDetailsFragment extends Fragment {


    private FragmentContactDetailsBinding binding;
    private ContactViewModel contactViewModel;
    private long id=0;

    public ContactDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentContactDetailsBinding.inflate(inflater);
        contactViewModel=new ViewModelProvider(requireActivity()).get(ContactViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final Bundle bundle=getArguments();

        if (bundle !=null)
        {
            id=bundle.getLong("id");

        }

        contactViewModel.getContactsById(id).observe(getViewLifecycleOwner(), new Observer<ContactModel>() {
            @Override
            public void onChanged(ContactModel contactModel) {

//                String s=contactModel.getName()+"\n"+contactModel.getCity()+"\n"+contactModel.getEmail()+"\n"+contactModel.getGender()+"\n"+contactModel.getAddress()+"\n"+contactModel.getPhone();
//
//                binding.detailsTV.setText(s);

                String name=contactModel.getName();
                binding.showNameId.setText(name);
                String email=contactModel.getEmail();
                binding.showEmailId.setText(email);
                String address=contactModel.getAddress();
                binding.showAddressId.setText(address);
                String city=contactModel.getCity();
                binding.showCityId.setText(city);
                String gender=contactModel.getGender();
                binding.showGenderId.setText(gender);
                String numb=contactModel.getPhone();
                binding.showPhoneId.setText(numb);
                String bld=contactModel.getBloodGroup();
                binding.showBloodgrpId.setText(bld);



                showAlertDialog(contactModel);

            }

            private void showAlertDialog(ContactModel contactModel) {

                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());

                builder.setTitle("Personal Details");

                builder.setIcon(R.drawable.profile_icon);

                builder.setMessage("Name :"+contactModel.getName()+"\n"+"City :"+contactModel.getCity()+"\n"+"Email :"+contactModel.getEmail()+"\n"+"Gender :"+contactModel.getGender()+"\n"+"Address :"+contactModel.getAddress()+"\n"+"Phone :"+contactModel.getPhone()+"\n"+"Blood Group :"+contactModel.getBloodGroup());


                final AlertDialog dialog=builder.create();
                dialog.show();

            }
        });

    }
}