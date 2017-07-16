package com.poojanshah.assignment_two.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.poojanshah.assignment_two.MainActivity;
import com.poojanshah.assignment_two.R;
import com.poojanshah.assignment_two.Service.DummyUserSerivce;
import com.poojanshah.assignment_two.Service.IUserService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogIn#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogIn extends Fragment {
    IUserService iUserService;
    Button btnLogIn;
    EditText email;
    EditText password;
    TextView tvEmail;
    TextView tvPassword;
    TextView TvMessage;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogIn = (Button) view.findViewById(R.id.btnLogIn);
        email = (EditText) view.findViewById(R.id.etEmail);
        password = (EditText) view.findViewById(R.id.etPassword);
        tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        tvPassword = (TextView) view.findViewById(R.id.tvPassword);
        TvMessage = (TextView) view.findViewById(R.id.tvMessage);
        TvMessage.setVisibility(View.INVISIBLE);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iUserService.logIn(email.getText().toString(),password.getText().toString())){
                    MainActivity.logIn();
                    Toast.makeText(getContext(),"You have logged in Successfully",Toast.LENGTH_LONG);
                    btnLogIn.setVisibility(View.INVISIBLE);
                    email.setVisibility(View.INVISIBLE);
                    password.setVisibility(View.INVISIBLE);
                    tvEmail.setVisibility(View.INVISIBLE);
                    tvPassword.setVisibility(View.INVISIBLE);


                    TvMessage.setText("You have logged in Successfully");
                    TvMessage.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getContext(),"You have Not logged in Successfully",Toast.LENGTH_LONG);
                }

            }
        });
    }

    public LogIn() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Login.
     */
    // TODO: Rename and change types and number of parameters
    public static LogIn newInstance(String param1, String param2) {
        LogIn fragment = new LogIn();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iUserService = new DummyUserSerivce();
//        if (getArguments() != null) {
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

}
