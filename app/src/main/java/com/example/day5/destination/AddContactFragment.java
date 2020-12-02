package com.example.day5.destination;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.day5.entities.ContactModel;
import com.example.day5.R;
import com.example.day5.databinding.FragmentAddContactBinding;
import com.example.day5.viewmodels.ContactViewModel;

import java.util.Arrays;
import java.util.List;


public class AddContactFragment extends Fragment {
    public static String[] cities={"Dhaka","Comilla","Noakhali","Borishal"};
    public static String[] bloodGroups={"A+","A-","B+","B-","AB+","AB-","O+","O-"};
    public static List<String> cities2= Arrays.asList("Dhaka","Comilla","Noakhali","Borishal");
    public static List<String> bloodGroups2= Arrays.asList("A+","A-","B+","B-","AB+","AB-","O+","O-");

    private FragmentAddContactBinding binding;
    private ContactViewModel contactViewModel;
    private String gender="Male";
    private String city;
    private String bloodgroup;
    private long id=0;

    public AddContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentAddContactBinding.inflate(inflater);
        // Inflate the layout for this fragment

        contactViewModel=new ViewModelProvider(getActivity()).get(ContactViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle bundle=getArguments();

        if (bundle!=null)
        {
            id=bundle.getLong("id");


        }


        contactViewModel.getContactsById(id).observe(getViewLifecycleOwner(), contactModel -> {


            if(id!=0) {
                binding.saveBtn.setVisibility(View.GONE);
                binding.updateBtn.setVisibility(View.VISIBLE);
                binding.addContactName.setText(contactModel.getName());
                binding.addContactEmail.setText(contactModel.getEmail());
                binding.addContactPhone.setText(contactModel.getPhone());
                binding.addContactStreetAddress.setText(contactModel.getAddress());
                gender = contactModel.getGender();

                if (gender.equals(" Female")) {
                    binding.femailRG.setChecked(true);
                }


                city = contactModel.getCity();
                final int position = cities2.indexOf(city);
                binding.SpinnerID.setSelection(position);

//                    bloodgroup = contactModel.getBloodGroup();
//                    final int position2 = bloodGroups2.indexOf(bloodgroup);
//                    binding.SpinnerID.setSelection(position2);

//                    binding.bloodGroupSP.setSelection(bloodGroups2.indexOf(contactModel.getBloodGroup()));





                binding.GenderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        final RadioButton rb=group.findViewById(checkedId);

                        gender=rb.getText().toString();

                    }
                });


                binding.SpinnerID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view1, int position, long id) {
                        city = cities[position];

                        Toast.makeText(getContext(),city,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }




                });



//
//                    binding.bloodGroupSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            bloodgroup = bloodGroups[position2];
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });





            }

            else {

                binding.GenderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        final RadioButton rb=group.findViewById(checkedId);

                        gender=rb.getText().toString();

                    }
                });




                final ArrayAdapter<String> city_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, cities2);
//                    final ArrayAdapter<String> blood_group_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, bloodGroups2);

                binding.SpinnerID.setAdapter(city_adapter);
//                    binding.bloodGroupSP.setAdapter(blood_group_adapter);

                binding.SpinnerID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view1, int position, long id) {
                        city = cities[position];

                        Toast.makeText(getContext(), city, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

//                    binding.bloodGroupSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            bloodgroup = bloodGroups[position];
//
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });




            }





        });


        final ArrayAdapter<String> city_adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, cities2);

        binding.SpinnerID.setAdapter(city_adapter);
//        final ArrayAdapter<String> blood_group_adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, bloodGroups2);
//
//        binding.SpinnerID.setAdapter(blood_group_adapter);















        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=binding.addContactName.getText().toString();
                final String phone=binding.addContactPhone.getText().toString();
                final String email=binding.addContactEmail.getText().toString();
                final String streetAddress=binding.addContactStreetAddress.getText().toString();
                final ContactModel contactModel=new ContactModel(name,email,streetAddress,phone,city,gender,bloodgroup);
                contactViewModel.setContact(contactModel);

                Toast.makeText(getContext(), name+" has been saved Succesfully",Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.contactListFragment);

            }
        });

        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=binding.addContactName.getText().toString();
                final String phone=binding.addContactPhone.getText().toString();
                final String email=binding.addContactEmail.getText().toString();
                final String streetAddress=binding.addContactStreetAddress.getText().toString();
                final ContactModel contactModel=new ContactModel(name,email,streetAddress,phone,city,gender,bloodgroup);
                  contactModel.setId(id);
                contactViewModel.updateContact(contactModel);

                    Toast.makeText(getContext(), name+" has been Updated Succesfully",Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.contactListFragment);
            }
        });
    }
}