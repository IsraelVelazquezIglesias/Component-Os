package igl.vel.isr.appdef1_0.Login;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import igl.vel.isr.appdef1_0.Entidades.Usuario;
import igl.vel.isr.appdef1_0.MainActivity;
import igl.vel.isr.appdef1_0.R;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterOptions extends Fragment implements View.OnClickListener{
    View vista;
    Button regresar,continuar;
    EditText nom,apll,corr,num,contr;
    RequestQueue requestQueue;
    String nombres,apellidoss,correos,numeros,contrasenias,correoc,nombrec,apellidoc;
    ProgressDialog progressDialog;
    CheckBox checkBox;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    JsonObjectRequest ObjectRequest;

    private FirebaseAuth mAuth;

    public RegisterOptions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_register_options, container, false);


        regresar = (Button) vista.findViewById(R.id.Regresar);
        continuar = (Button) vista.findViewById(R.id.Continuar);

        nom = (EditText) vista.findViewById(R.id.Nombre);
        apll = (EditText) vista.findViewById(R.id.Apellidos);
        corr = (EditText) vista.findViewById(R.id.Correo);
        num = (EditText) vista.findViewById(R.id.Numero);
        contr = (EditText) vista.findViewById(R.id.Contrasena);
        requestQueue = Volley.newRequestQueue(getContext());
        checkBox = vista.findViewById(R.id.mostarC);
        progressDialog = new ProgressDialog(getContext());

        nombres = nom.getText().toString();
        apellidoss = apll.getText().toString();
        correos = corr.getText().toString();
        numeros = num.getText().toString();
        contrasenias = contr.getText().toString();

        mAuth = FirebaseAuth.getInstance();


        regresar.setOnClickListener(this);
        continuar.setOnClickListener(this);
        checkBox.setOnClickListener(this);

        return vista;


    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Regresar:
            fragment_login1 frg =  new fragment_login1();

            FragmentManager fragmentManager = getFragmentManager();
                assert fragmentManager != null;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.loginframelay,frg,null);
            fragmentTransaction.remove(this).commit();
                break;
            case R.id.Continuar:
                if(nom.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "El Nombre(s) No Puede Estar Vacio", Toast.LENGTH_SHORT).show();
                }
                else if(apll.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "Los Apellidos No Pueden Estar Vacios", Toast.LENGTH_SHORT).show();
                }
                else if(corr.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "El Correo No Puede Estar Vacio", Toast.LENGTH_SHORT).show();
                }
                else if(corr.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+"))
                {

                }
                else {
                    Toast.makeText(getContext(), "El correo es invalido", Toast.LENGTH_SHORT).show();
                    corr.setText("");
                }

                if(num.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "El Numero Telefonico No puede Estar Vacio", Toast.LENGTH_SHORT).show();
                }
                if(contr.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "La Contrasenia No puede Estar Vacia", Toast.LENGTH_SHORT).show();
                }
                else if(contr.length()<6)
                {
                    Toast.makeText(getContext(), "La Contrasenia Debe Tener 6 o Mas Caracteres", Toast.LENGTH_SHORT).show();
                    contr.setText("");
                }
                else{
                    //cargarWebService();
                    String c = corr.getText().toString();
                    String cn = contr.getText().toString();
                    progressDialog.setMessage("Registrando...");
                    registrar(c,cn);
                }


                break;
            case R.id.mostarC:
                                if(checkBox.isChecked()==true)
                {
                    contr.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                if(checkBox.isChecked()==false)
                {
                    contr.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
                default:


                    break;
        }
    }


    void registrar(final String correot, String contraseniat)
    {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(correot, contraseniat)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            String nombre = nom.getText().toString().trim()+" " + apll.getText().toString().trim();
                            UserProfileChangeRequest updates = new UserProfileChangeRequest.Builder().setDisplayName(nombre).build();

                            mAuth.getCurrentUser().updateProfile(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                progressDialog.dismiss();
                                                Toast.makeText(getContext(), "Registrado Exitosamente", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                            Map<String, Object> user = new HashMap<>();
                            user.put("Nombre", nombre);
                            user.put("Numero", num.getText().toString().trim());
                            user.put("Correo",corr.getText().toString().trim());

                            db.collection("users")
                                    .add(user)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error adding document", e);
                                        }
                                    });



                            Intent intent3 = new Intent(getActivity(), MainActivity.class);
                            intent3.putExtra("nombre",nombre);
                            getActivity().startActivity(intent3);
                            getActivity().finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }




}
