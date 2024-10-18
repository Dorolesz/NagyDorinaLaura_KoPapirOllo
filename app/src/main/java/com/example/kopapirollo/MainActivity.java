package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int jatekosEletek = 3;
    private int gepEletek = 3;

    private ImageView gepValasztasaKep;
    private ImageView teValasztasodKep;

    private ImageView gepSziv1, gepSziv2, gepSziv3;
    private ImageView jatekosSziv1, jatekosSziv2, jatekosSziv3;

    private final String[] valasztasok = {"rock", "paper", "scissors"};
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gepSziv1 = findViewById(R.id.gepSziv1);
        gepSziv2 = findViewById(R.id.gepSziv2);
        gepSziv3 = findViewById(R.id.gepSziv3);

        jatekosSziv1 = findViewById(R.id.jatekosSziv1);
        jatekosSziv2 = findViewById(R.id.jatekosSziv2);
        jatekosSziv3 = findViewById(R.id.jatekosSziv3);

        gepValasztasaKep = findViewById(R.id.gepValasztasaKep);
        teValasztasodKep = findViewById(R.id.teValasztasodKep);
    }

    public void onRockClick(View view) {
        jatek("rock");
    }

    public void onPaperClick(View view) {
        jatek("paper");
    }

    public void onScissorsClick(View view) {
        jatek("scissors");
    }

    private void jatek(String jatekosValasztas) {
        String gepValasztas = gepRandomValasztas();

        setImageForChoice(gepValasztasaKep, gepValasztas);
        setImageForChoice(teValasztasodKep, jatekosValasztas);

        if (jatekosValasztas.equals(gepValasztas)) {
            Toast.makeText(this, "Döntetlen!", Toast.LENGTH_SHORT).show();
        } else if ((jatekosValasztas.equals("rock") && gepValasztas.equals("scissors")) ||
                (jatekosValasztas.equals("paper") && gepValasztas.equals("rock")) ||
                (jatekosValasztas.equals("scissors") && gepValasztas.equals("paper"))) {
            Toast.makeText(this, "Játékos nyert!", Toast.LENGTH_SHORT).show();
            gepEletek--;
            updateLives();
        } else {
            Toast.makeText(this, "Gép nyert!", Toast.LENGTH_SHORT).show();
            jatekosEletek--;
            updateLives();
        }

        if (gepEletek == 0) {
            showEndDialog("Gratulálunk, nyertél!");
        } else if (jatekosEletek == 0) {
            showEndDialog("Sajnos vesztettél!");
        }
    }

    private String gepRandomValasztas() {
        int index = random.nextInt(valasztasok.length);
        return valasztasok[index];
    }

    private void setImageForChoice(ImageView imageView, String choice) {
        switch (choice) {
            case "rock":
                imageView.setImageResource(R.drawable.rock);
                break;
            case "paper":
                imageView.setImageResource(R.drawable.paper);
                break;
            case "scissors":
                imageView.setImageResource(R.drawable.scissors);
                break;
        }
    }

    private void updateLives() {
        if (gepEletek < 3) gepSziv3.setVisibility(View.INVISIBLE);
        if (gepEletek < 2) gepSziv2.setVisibility(View.INVISIBLE);
        if (gepEletek < 1) gepSziv1.setVisibility(View.INVISIBLE);

        if (jatekosEletek < 3) jatekosSziv3.setVisibility(View.INVISIBLE);
        if (jatekosEletek < 2) jatekosSziv2.setVisibility(View.INVISIBLE);
        if (jatekosEletek < 1) jatekosSziv1.setVisibility(View.INVISIBLE);
    }

    private void showEndDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Játék vége")
                .setMessage(message)
                .setPositiveButton("Új játék", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetGame();
                    }
                })
                .setNegativeButton("Kilépés", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    private void resetGame() {
        jatekosEletek = 3;
        gepEletek = 3;

        gepSziv1.setVisibility(View.VISIBLE);
        gepSziv2.setVisibility(View.VISIBLE);
        gepSziv3.setVisibility(View.VISIBLE);

        jatekosSziv1.setVisibility(View.VISIBLE);
        jatekosSziv2.setVisibility(View.VISIBLE);
        jatekosSziv3.setVisibility(View.VISIBLE);
    }
}
