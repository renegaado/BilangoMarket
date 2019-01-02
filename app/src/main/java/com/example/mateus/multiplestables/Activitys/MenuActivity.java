package com.example.mateus.multiplestables.Activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mateus.multiplestables.DATA.UsuarioDAO;
import com.example.mateus.multiplestables.R;
import com.example.mateus.multiplestables.Usuario;

public class MenuActivity extends AppCompatActivity {

    public static Activity menuActivity;
    String usuario_email;
    TextView texto_NomeUsuario;
    Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menuActivity = this;
        texto_NomeUsuario = (TextView)findViewById(R.id.txt_NomeUsuario);
        Bundle bundle = getIntent().getExtras();

        if (bundle!= null){
            usuario_email = bundle.getString("usuario_email");
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuario = usuarioDAO.getUsuarioByEmail(usuario_email);
            texto_NomeUsuario.setText(usuario.getNome());
        }
    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder deslogar = new AlertDialog.Builder(this);
        deslogar.setTitle("Aviso");
        deslogar
                .setIcon(R.mipmap.ic_aviso)
                .setMessage("Voce deseja deslogar?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent it = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(it);
                        finish();
                    }
                })
                .setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alertDialog = deslogar.create();
        alertDialog.show();
    }


    public void act_novoAnuncio(View view){
        Intent it = new Intent(this, RegistrarAnunciosActivity.class);
        it.putExtra("usuario_email", usuario_email);
        startActivity(it);
    }

    public void act_meusAnuncios(View view){
        Intent it = new Intent(this, ListaDeAnunciosActivity.class);
        it.putExtra("usuario_email", usuario_email);
        startActivity(it);
    }

   public void act_editarUsuarioMenu(View view){
        Intent it = new Intent(this, EditarUsuarioMenuActivity.class);
        it.putExtra("usuario_email", usuario_email);
        startActivity(it);
        finish();
    }

}
