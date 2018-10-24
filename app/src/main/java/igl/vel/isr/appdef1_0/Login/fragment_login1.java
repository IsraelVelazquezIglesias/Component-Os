package igl.vel.isr.appdef1_0.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import igl.vel.isr.appdef1_0.Login.RegisterOptions;
import igl.vel.isr.appdef1_0.MainActivity;
import igl.vel.isr.appdef1_0.R;


public class fragment_login1 extends Fragment implements View.OnClickListener {

    View vista;
    Button registro,ingreso;

    public fragment_login1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista = inflater.inflate(R.layout.fragment_fragment_login1, container, false);

        registro = (Button) vista.findViewById(R.id.registrar);
        ingreso = (Button) vista.findViewById(R.id.ingresar);

        registro.setOnClickListener(this);
        ingreso.setOnClickListener(this);

        return vista;




    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.registrar:
                Toast.makeText(getContext(), "Registrarse", Toast.LENGTH_SHORT).show();
                RegisterOptions reg = new RegisterOptions();

                FragmentManager fragmentManager = getFragmentManager();
                assert fragmentManager != null;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.loginframelay,reg,null);
                fragmentTransaction.remove(this).commit();
                break;

            case R.id.ingresar:
                Toast.makeText(getContext(), "Ingresar", Toast.LENGTH_SHORT).show();
                LoginOptions reg2 = new LoginOptions();

                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.loginframelay,reg2,null);
                fragmentTransaction1.remove(this).commit();

                /* Intent intent2 = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent2);
                getActivity().finish(); */
                break;

                default:
                    break;
        }

    }



}
