package com.example.grantme2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class penerima_home extends AppCompatActivity {

    private int selectedTab = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.penerima_home);
        final LinearLayout penerima_home = findViewById(R.id.page_home);
        final LinearLayout penerima_beasiswa = findViewById(R.id.page_beasiswa);
        final LinearLayout penerima_status = findViewById(R.id.page_status);
        final LinearLayout penerima_profil = findViewById(R.id.page_profile);

        final ImageView homeicon = findViewById(R.id.icon_home);
        final ImageView beasiswaicon = findViewById(R.id.icon_beasiswa);
        final ImageView statusicon = findViewById(R.id.icon_status);
        final ImageView profileicon = findViewById(R.id.icon_profil);

        final TextView texthome = findViewById(R.id.text_home);
        final TextView textbeasiswa = findViewById(R.id.text_beasiswa);
        final TextView textstatus = findViewById(R.id.text_status);
        final TextView textprofil = findViewById(R.id.text_profile);

        // mengambil data login
        Intent intent = getIntent();
        String namaLengkap = intent.getStringExtra("namaLengkap");
        String email = intent.getStringExtra("email");
        String ttl = intent.getStringExtra("ttl");
        String jenKel = intent.getStringExtra("jenKel");
        String noTelp = intent.getStringExtra("noTelp");
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        String ktp = intent.getStringExtra("ktp");
        String kk = intent.getStringExtra("kk");
        String fotoProfil = intent.getStringExtra("fotoProfil");
        String transkrip = intent.getStringExtra("transkrip");
        String ijazah = intent.getStringExtra("ijazah");
        String id = intent.getStringExtra("userId");




        getSupportFragmentManager()
                .beginTransaction().setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, penerima_FragHome.class,null)
                .commit();

        penerima_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTab != 1){
                    getSupportFragmentManager()
                            .beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, penerima_FragHome.class,null)
                            .commit();
                    textbeasiswa.setVisibility(View.GONE);
                    textprofil.setVisibility(View.GONE);
                    textstatus.setVisibility(View.GONE);

                    beasiswaicon.setImageResource(R.drawable.icon_beasiswa);
                    statusicon.setImageResource(R.drawable.icon_status);
                    profileicon.setImageResource(R.drawable.icon_profile);

                    penerima_beasiswa.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    penerima_status.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    penerima_profil.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    texthome.setVisibility(View.VISIBLE);
                    homeicon.setImageResource(R.drawable.icon_selected_home);
                    penerima_home.setBackgroundResource(R.drawable.round_icon);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);

                   penerima_home.startAnimation(scaleAnimation);
                   selectedTab = 1;
                }
            }
        });
        penerima_beasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTab != 2){
                    getSupportFragmentManager()
                            .beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, penerima_FragBeasiswa.class,null)
                            .commit();
                    texthome.setVisibility(View.GONE);
                    textstatus.setVisibility(View.GONE);
                    textprofil.setVisibility(View.GONE);

                    homeicon.setImageResource(R.drawable.icon_home);
                    statusicon.setImageResource(R.drawable.icon_status);
                    profileicon.setImageResource(R.drawable.icon_profile);

                    penerima_home.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    penerima_status.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    penerima_profil.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    textbeasiswa.setVisibility(View.VISIBLE);
                    beasiswaicon.setImageResource(R.drawable.icon_selected_beasiswa);
                    penerima_beasiswa.setBackgroundResource(R.drawable.round_icon);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);

                    penerima_beasiswa.startAnimation(scaleAnimation);
                    selectedTab = 2;
                }

            }
        });
        penerima_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTab != 3){
                    getSupportFragmentManager()
                            .beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, penerima_FragStatus.class,null)
                            .commit();
                    texthome.setVisibility(View.GONE);
                    textbeasiswa.setVisibility(View.GONE);
                    textprofil.setVisibility(View.GONE);

                    homeicon.setImageResource(R.drawable.icon_home);
                    beasiswaicon.setImageResource(R.drawable.icon_beasiswa);
                    profileicon.setImageResource(R.drawable.icon_profile);

                    penerima_home.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    penerima_beasiswa.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    penerima_profil.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    textstatus.setVisibility(View.VISIBLE);
                    statusicon.setImageResource(R.drawable.icon_selected_status);
                    penerima_status.setBackgroundResource(R.drawable.round_icon);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);

                    penerima_status.startAnimation(scaleAnimation);
                    selectedTab = 3;
                }

            }
        });
        penerima_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mengirim ke halaman informasi pribadi
                if (selectedTab != 4){
                    getSupportFragmentManager()
                            .beginTransaction().setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer, penerima_FragProfil.class,null)
                            .commit();
                    textbeasiswa.setVisibility(View.GONE);
                    texthome.setVisibility(View.GONE);
                    textstatus.setVisibility(View.GONE);

                    beasiswaicon.setImageResource(R.drawable.icon_beasiswa);
                    statusicon.setImageResource(R.drawable.icon_status);
                    homeicon.setImageResource(R.drawable.icon_home);

                    penerima_beasiswa.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    penerima_status.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    penerima_home.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    textprofil.setVisibility(View.VISIBLE);
                    profileicon.setImageResource(R.drawable.icon_selected_profile);
                    penerima_profil.setBackgroundResource(R.drawable.round_icon);

                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f,1.0f,1f,1f, Animation.RELATIVE_TO_SELF,0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    penerima_profil.startAnimation(scaleAnimation);
                    selectedTab = 4;
                }

            }
        });

    }
}