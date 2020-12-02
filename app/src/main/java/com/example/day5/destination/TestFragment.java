package com.example.day5.destination;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day5.databinding.FragmentTestBinding;
import com.example.day5.viewmodels.ContactViewModel;


public class TestFragment extends Fragment {

    private FragmentTestBinding binding;
    private ContactViewModel contactViewModel;
    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentTestBinding.inflate(inflater);
        contactViewModel= new ViewModelProvider(getActivity()).get(ContactViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        contactViewModel.getName().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                binding.testTv.setText(s);
//            }
//        });
//
//
//        binding.btnnameChangeId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                contactViewModel.setName("BITM");
//            }
//        });
    }
}