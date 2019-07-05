package com.example.onlinestore;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.onlinestore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment {

    Button login;

    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences preferences=getActivity().getSharedPreferences("UserData",0);
        String tok=preferences.getString("token",null);
        if(tok==null||tok.equals("")){
            closefragment();
            Intent intent = new Intent(getActivity(),Login.class);
            startActivity(intent);
        }


        login = view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
            }
        });

        return view;
    }
    private void closefragment() {
        getActivity().getFragmentManager().popBackStack();
    }

}
