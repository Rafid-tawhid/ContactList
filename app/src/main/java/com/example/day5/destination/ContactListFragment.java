package com.example.day5.destination;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day5.adapters.ContactAdapter;
import com.example.day5.R;
import com.example.day5.databinding.FragmentContactListBinding;
import com.example.day5.entities.ContactModel;
import com.example.day5.viewmodels.ContactViewModel;

import java.util.List;


public class ContactListFragment extends Fragment implements ContactAdapter.ContactEditDeleteListener {

    private FragmentContactListBinding binding;
    private ContactAdapter adapter;
    private ContactViewModel contactViewModel;
    private String favControlar="fav";


    public ContactListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding=FragmentContactListBinding.inflate(inflater);
        contactViewModel=new ViewModelProvider(getActivity()).get(ContactViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        contactViewModel.getContacts().observe(getViewLifecycleOwner(), new Observer<List<ContactModel>>() {
            @Override
            public void onChanged(List<ContactModel> contactModels) {
                if(contactModels.size()==0)
                {
                    binding.emptyContactmsg.setVisibility(View.VISIBLE);
                }
                else
                {
                    binding.emptyContactmsg.setVisibility(View.GONE);
                }
                adapter=new ContactAdapter(getContext(), contactModels,ContactListFragment.this);
                final LinearLayoutManager llm=new LinearLayoutManager(getContext());
                binding.contactRV.setLayoutManager(llm);
                binding.contactRV.setAdapter(adapter);
            }
        });





        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                final  Bundle bundle=new Bundle();
//                bundle.putString("fav",favControlar);
//
                Navigation.findNavController(v).navigate(R.id.addContactFragment);
            }
        });
    }

    @Override
    public void edit(ContactModel contactModel) {

    }

    public void delete(ContactModel contactModel)
    {

       contactViewModel.deleteContact(contactModel);
    }

}