package com.example.grawkosci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int punktyGra = 0;

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

        ImageView kosc1 = findViewById(R.id.imageKosc1);
        ImageView kosc2 = findViewById(R.id.imageKosc2);
        ImageView kosc3 = findViewById(R.id.imageKosc3);
        ImageView kosc4 = findViewById(R.id.imageKosc4);
        ImageView kosc5 = findViewById(R.id.imageKosc5);

        ImageView[] kosci = {kosc1,kosc2,kosc3,kosc4,kosc5};

        Button RzutButton = findViewById(R.id.buttonRzut);
        Button ResetButton = findViewById(R.id.buttonReset);

        TextView punktyLosowanieTextView = findViewById(R.id.textViewWynikLosowania);
        TextView punktyGraTextView = findViewById(R.id.textViewWynikGry);

        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                punktyGra = 0;
                punktyLosowanieTextView.setText("Wynik tego losowania: 0");
                punktyGraTextView.setText("Wynik gry: " + punktyGra);

                for (int i = 0; i < kosci.length; i++) {
                    kosci[i].setImageResource(getResources().getIdentifier("question","drawable", getPackageName()));
                }

            }
        });

        RzutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rnd = new Random();

                int[] liczby = new int[5];
                int punktyRzut = 0;

                for (int i = 0; i < liczby.length; i++) {
                    liczby[i] = rnd.nextInt(6) + 1;
                }

                for (int i = 0; i < kosci.length; i++) {
                    kosci[i].setImageResource(getResources().getIdentifier("k" + liczby[i],"drawable", getPackageName()));
                }

                for (int i = 0; i < liczby.length; i++) {
                    int wystapienia = 0;
                    boolean juzSprawdzono = false;

                    for (int k = 0; k < i; k++) {
                        if (liczby[i] == liczby[k]) {
                            juzSprawdzono = true;
                            break;
                        }
                    }

                    if (!juzSprawdzono) {
                        for (int j = 0; j < liczby.length; j++) {
                            if (liczby[i] == liczby[j]) {
                                wystapienia++;
                            }
                        }

                        if (wystapienia >= 2) {
                            punktyRzut += liczby[i] * wystapienia;
                        }
                    }
                }

                punktyLosowanieTextView.setText("Wynik tego losowania: " + punktyRzut);

                punktyGra += punktyRzut;

                punktyGraTextView.setText("Wynik gry: " + punktyGra);
            }
        });


    }
}