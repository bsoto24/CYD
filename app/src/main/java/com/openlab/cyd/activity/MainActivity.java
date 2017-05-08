package com.openlab.cyd.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.openlab.cyd.R;
import com.openlab.cyd.widget.WaterProgress;

public class MainActivity extends AppCompatActivity {

    private WaterProgress waterProgress;
    private Button bntConsumir, btnRecargar;
    private TextView tvProgress;
    private int progress = 60;
    private int cantidadTotal = progress;
    private AlertDialog alert;
    private LinearLayout lyRecargar, lyConsumir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getSupportActionBar().hide();

        waterProgress = (WaterProgress) findViewById(R.id.water_progress);
        tvProgress = (TextView) findViewById(R.id.tv_progress);
        bntConsumir = (Button) findViewById(R.id.btn_consumir);
        btnRecargar = (Button) findViewById(R.id.btn_recargar);
        waterProgress.setProgress(progress);

        lyConsumir = (LinearLayout) findViewById(R.id.ly_consumir);
        lyRecargar = (LinearLayout) findViewById(R.id.ly_recargar);

        lyConsumir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servir(20, view);
            }
        });

        lyRecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recargar(40, view);
            }
        });
        onClickMethods();

    }

    private void servir(int cantidad, View view) {
        if (cantidadTotal - cantidad >= 0) {
            cantidadTotal -= cantidad;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (progress >= cantidadTotal) {
                        try {
                            progress--;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    waterProgress.setProgress(progress);
                                    tvProgress.setText(progress + " mL");
                                }
                            });
                            Thread.sleep(60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } else {
            Snackbar.make(view, "No hay suficiente liquido", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    private void recargar(int cantidad, final View view) {
        if (cantidadTotal + cantidad < 99) {
            cantidadTotal += cantidad;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (progress <= cantidadTotal) {
                        try {
                            progress++;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    waterProgress.setProgress(progress);
                                    tvProgress.setText(progress + " mL");
                                }
                            });
                            Thread.sleep(60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } else {
            Snackbar.make(view, "La botella esta muy llena", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    private void onClickMethods() {
        bntConsumir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConsumir();
            }
        });

        btnRecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecargar();
            }
        });

    }

    private void showConsumir() {
        Intent intent = new Intent(MainActivity.this, ConsumirActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void showRecargar() {
        Intent intent = new Intent(MainActivity.this, RecargaActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}