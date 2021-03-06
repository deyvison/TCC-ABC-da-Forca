package tcc.ufpb.com.br.tcc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import tcc.ufpb.com.br.tcc.R;
import tcc.ufpb.com.br.tcc.entity.Contexto;
import tcc.ufpb.com.br.tcc.entity.Niveis;

public class NivelActivity extends AppCompatActivity {

    //back key
    private Toast toast;
    private long lastBackPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Escolha o nível");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        final Contexto contexto = (Contexto) intent.getExtras().getSerializable("contexto");

        ImageView botaoFacil = (ImageView) findViewById(R.id.botaoFacil);
        ImageView botaoMedio = (ImageView) findViewById(R.id.botaoMedio);
        ImageView botaoDificil = (ImageView) findViewById(R.id.botaoDificil);

        if (botaoFacil != null) {
            botaoFacil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (contexto != null) {
                        if(contexto.getPalavraPorNivel(Niveis.FACIL).size() < 5){
                            Toast.makeText(v.getContext(),"Palavras insuficientes. Cadastre mais palavras neste nível para jogar.",Toast.LENGTH_LONG).show();
                        }else{
                            Intent i = new Intent(v.getContext(),JogoActivity.class);
                            i.putExtra("nivel",Niveis.FACIL);
                            i.putExtra("contexto",contexto);
                            startActivity(i);
                            finish();
                        }
                    }
                }
            });
        }

        if (botaoMedio != null) {
            botaoMedio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (contexto != null) {
                        if(contexto.getPalavraPorNivel(Niveis.MEDIO).size() < 5){
                            Toast.makeText(v.getContext(),"Palavras insuficientes. Cadastre mais palavras neste nível para jogar.",Toast.LENGTH_LONG).show();
                        }else{
                            Intent i = new Intent(v.getContext(),JogoActivity.class);
                            i.putExtra("nivel",Niveis.MEDIO);
                            i.putExtra("contexto",contexto);
                            startActivity(i);
                            finish();
                        }
                    }
                }
            });
        }

        if (botaoDificil != null) {
            botaoDificil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (contexto != null) {
                        if(contexto.getPalavraPorNivel(Niveis.DIFICIL).size() < 5){
                            Toast.makeText(v.getContext(),"Palavras insuficientes. Cadastre mais palavras neste nível para jogar.",Toast.LENGTH_LONG).show();
                        }else{
                            Intent i = new Intent(v.getContext(),JogoActivity.class);
                            i.putExtra("nivel",Niveis.DIFICIL);
                            i.putExtra("contexto",contexto);
                            startActivity(i);
                            finish();
                        }
                    }
                }
            });
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            Intent i = new Intent(this,ContextoActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", Toast.LENGTH_SHORT);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }
}
