package com.example.kopapirollo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button Ko, Papir, Ollo;
    private TextView Player, Computer, Eredmeny;
    private Random random;
    String[] choices = {"Kő", "Papír", "Olló"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Ko = findViewById(R.id.koButton);
        Papir = findViewById(R.id.papirButton);
        Ollo = findViewById(R.id.olloButton);
        Player = findViewById(R.id.teValasztasod);
        Computer = findViewById(R.id.gepValasztasa);
        Eredmeny = findViewById(R.id.eredmeny);

        Ko.setOnClickListener(view -> Game("Kő"));
        Papir.setOnClickListener(view -> Game("Papír"));
        Ollo.setOnClickListener(view -> Game("Olló"));
        }

    public void Game(String playerChoice) {
        String computerChoice = choices[random.nextInt(3)];

        if (playerChoice.equals(computerChoice)) {
            Eredmeny.setText("Döntetlen");
        } else if ((playerChoice.equals("Kő") && computerChoice.equals("Olló")) ||
                (playerChoice.equals("Papír") && computerChoice.equals("Kő")) ||
                (playerChoice.equals("Olló") && computerChoice.equals("Papír"))) {
            Eredmeny.setText("Nyertél!");
        } else {
            Eredmeny.setText("Vesztettél");
        }

    }
}