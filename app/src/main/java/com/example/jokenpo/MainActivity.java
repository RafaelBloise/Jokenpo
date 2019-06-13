package com.example.jokenpo;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewPlayer, imageViewAdversario, imageViewResultado;
    private ImageButton buttonPedra, buttonPapel, buttonTesoura;
    private int jogadaUsuario, jogadaAdversario;
    private MediaPlayer musica;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewPlayer = findViewById(R.id.player);
        imageViewAdversario = findViewById(R.id.advesario);
        imageViewResultado = findViewById(R.id.imageViewResultado);
        frameLayout = findViewById(R.id.frameResultado);

        buttonPapel = findViewById(R.id.papel);
        buttonPedra = findViewById(R.id.pedra);
        buttonTesoura = findViewById(R.id.tesoura);


        buttonPapel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jogadaUsuario = 1;
                jogadaAdversario = (int) (Math.random() * 3 + 1);
                iniciar();
            }
        });
        buttonPedra.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jogadaUsuario = 2;
                jogadaAdversario = (int) (Math.random() * 3 + 1);
                iniciar();
            }
        });
        buttonTesoura.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jogadaUsuario = 3;
                jogadaAdversario = (int) (Math.random() * 3 + 1);
                iniciar();

            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Adeus, kakaroto!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.musica.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void iniciar() {
        jogadaUsuario(jogadaUsuario);
    }

    public void jogadaUsuario(int i) {

        switch (i) {
            case 1:
                imageViewPlayer.setImageResource(R.drawable.papel);
                break;

            case 2:
                imageViewPlayer.setImageResource(R.drawable.pedra);
                break;

            case 3:
                imageViewPlayer.setImageResource(R.drawable.tesoura);
                break;
        }
        jogadaMaquina(jogadaAdversario);

    }

    public void jogadaMaquina(int j) {

        switch (j) {
            case 1:
                imageViewAdversario.setImageResource(R.drawable.papel);
                break;
            case 2:
                imageViewAdversario.setImageResource(R.drawable.pedra);
                break;
            case 3:
                imageViewAdversario.setImageResource(R.drawable.tesoura);
                break;

        }
        resultado();
    }

    public void resultado() {
        final int TEMPO = 2500;
        if (jogadaUsuario != jogadaAdversario) {
            if ((jogadaUsuario == 1 && jogadaAdversario == 2) || (jogadaUsuario == 2 && jogadaAdversario == 3) || (jogadaUsuario == 3 && jogadaAdversario == 1)) {
                Toast.makeText(getApplicationContext(), "Mijou grosso! Ganhou!", Toast.LENGTH_LONG).show();
                this.musica = MediaPlayer.create(this, R.raw.bodybuilderporra);
                musica.start();
                frameLayout.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(R.raw.bambamgif)
                        .fitCenter()
                        .into(imageViewResultado);


            } else {
                Toast.makeText(getApplicationContext(), "Levou mijada! Perdeu!", Toast.LENGTH_LONG).show();
                this.musica = MediaPlayer.create(this, R.raw.gemidao);
                musica.start();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Incompentente...deu empate!", Toast.LENGTH_LONG).show();
            this.musica = MediaPlayer.create(this, R.raw.alex_play);
            musica.start();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                frameLayout.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "NOVA RODADA!", Toast.LENGTH_SHORT).show();
                reiniciar();
            }
        }, TEMPO);


    }

    public void reiniciar() {
        imageViewPlayer.setImageResource(R.drawable.interrogacao);
        imageViewAdversario.setImageResource(R.drawable.interrogacao);
    }


}
