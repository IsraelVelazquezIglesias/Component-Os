package igl.vel.isr.appdef1_0.Login;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import igl.vel.isr.appdef1_0.MainActivity;
import igl.vel.isr.appdef1_0.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginOptions extends Fragment implements View.OnClickListener{


    public LoginOptions() {    }
        EditText Mail,Pass;
        Button Return,Next,Rec;
        RequestQueue requestQueue;
        private FirebaseAuth mAuth;
    CheckBox checkBox;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_options, container, false);

                Mail = view.findViewById(R.id.CorreoL);
                Pass = view.findViewById(R.id.ContrasenaL);
                Return = view.findViewById(R.id.RegresarL);
                Next = view.findViewById(R.id.ContinuarL);
                requestQueue = Volley.newRequestQueue(getContext());
                checkBox = view.findViewById(R.id.mostarCl);
                Rec = view.findViewById(R.id.contrL);
                mAuth = FirebaseAuth.getInstance();
                progressDialog = new ProgressDialog(getContext());
                Return.setOnClickListener(this);
                Next.setOnClickListener(this);
                checkBox.setOnClickListener(this);
                Rec.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.RegresarL:
            {
                fragment_login1 frg =  new fragment_login1();

                FragmentManager fragmentManager = getFragmentManager();
                assert fragmentManager != null;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.loginframelay,frg,null);
                fragmentTransaction.remove(this).commit();
            }break;

            case R.id.ContinuarL:
            {
                if(Mail.getText().toString().isEmpty())
                    {
                    Toast.makeText(getContext(), "El Correo No Puede Estar Vacio", Toast.LENGTH_SHORT).show();
                }
                else if(Pass.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "La Contraseña No Puede Estar Vacia", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressDialog.setMessage("Ingresando...");
                    ingresar();
                }
            }break;

            case R.id.mostarCl:
                if(checkBox.isChecked()==true)
                {
                    Pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                if(checkBox.isChecked()==false)
                {
                    Pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }break;

            case R.id.contrL:
                if(Mail.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "El Correo No Puede Estar Vacio", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    recuperarContra(Mail.getText().toString().trim());
                }break;

                default:
                break;
        }

    }

    private void recuperarContra(String correo) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(correo)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Snackbar.make(getView(),"SE HA ENVIADO UN CORREO DE RESTABLECIMIENTO",Snackbar.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Error enviando", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void ingresar() {
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(Mail.getText().toString(), Pass.getText().toString())
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent3 = new Intent(getActivity(), MainActivity.class);
                            getActivity().startActivity(intent3);
                            getActivity().finish();
                        } else {
                            progressDialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getContext(), "Falla al ingresar", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }


}
