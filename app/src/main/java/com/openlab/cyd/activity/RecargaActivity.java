package com.openlab.cyd.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.openlab.cyd.R;

/**
 * Created by Bryam Soto on 05/05/2017.
 */

public class RecargaActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnRecargar;
    private final int VASO_CHICO = 150, VASO_MEDIANO = 350, VASO_GRANDE = 650;
    private double PRECIO_ML = 1 / 250;
    private final double PRECIO_VASO_CHICO = 0.50, PRECIO_VASO_MEDIANO = 1, PRECIO_VASO_GRANDE = 1.50, PRECIO_PERSONALIZADO = 3;
    private final int EFECTIVO = 1, VISA = 2, PAYPAL = 3;
    private int cantidadConsumir = VASO_CHICO;
    private int metodoPago = EFECTIVO;
    private double precioTotal = PRECIO_VASO_CHICO;
    private LinearLayout lyChico, lyMediano, lyGrande, lyPersonalizado;
    private LinearLayout btnEfectivo, btnVisa, btnPaypal;
    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga);

        tvTotal = (TextView) findViewById(R.id.tv_total);
        tvTotal.setText("S/ " + precioTotal);


        lyChico = (LinearLayout) findViewById(R.id.ly_chico);
        lyMediano = (LinearLayout) findViewById(R.id.ly_mediano);
        lyGrande = (LinearLayout) findViewById(R.id.ly_grande);
        lyPersonalizado = (LinearLayout) findViewById(R.id.ly_personalizado);

        btnEfectivo = (LinearLayout) findViewById(R.id.btn_efectivo);
        btnVisa = (LinearLayout) findViewById(R.id.btn_visa);
        btnPaypal = (LinearLayout) findViewById(R.id.btn_paypal);

        lyChico.setOnClickListener(this);
        lyMediano.setOnClickListener(this);
        lyGrande.setOnClickListener(this);
        lyPersonalizado.setOnClickListener(this);

        btnEfectivo.setOnClickListener(this);
        btnVisa.setOnClickListener(this);
        btnPaypal.setOnClickListener(this);

        btnRecargar = (Button) findViewById(R.id.btn_recargar);
        addOnClickListeners();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("RECARGAR");

    }

    private void addOnClickListeners() {
        btnRecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoRecarga();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void dialogoRecarga() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_recarga_code, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_chico:
                cantidadConsumir = VASO_CHICO;
                precioTotal = PRECIO_VASO_CHICO;
                tvTotal.setText("S/ " + precioTotal);
                lyChico.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                lyMediano.setBackgroundColor(getResources().getColor(R.color.gray));
                lyGrande.setBackgroundColor(getResources().getColor(R.color.gray));
                lyPersonalizado.setBackgroundColor(getResources().getColor(R.color.gray));

                break;
            case R.id.ly_mediano:
                cantidadConsumir = VASO_MEDIANO;
                precioTotal = PRECIO_VASO_MEDIANO;
                tvTotal.setText("S/ " + precioTotal);
                lyChico.setBackgroundColor(getResources().getColor(R.color.gray));
                lyMediano.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                lyGrande.setBackgroundColor(getResources().getColor(R.color.gray));
                lyPersonalizado.setBackgroundColor(getResources().getColor(R.color.gray));
                break;
            case R.id.ly_grande:
                cantidadConsumir = VASO_GRANDE;
                precioTotal = PRECIO_VASO_GRANDE;
                tvTotal.setText("S/ " + precioTotal);
                lyChico.setBackgroundColor(getResources().getColor(R.color.gray));
                lyMediano.setBackgroundColor(getResources().getColor(R.color.gray));
                lyGrande.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                lyPersonalizado.setBackgroundColor(getResources().getColor(R.color.gray));
                break;
            case R.id.ly_personalizado:
                precioTotal = PRECIO_PERSONALIZADO;
                tvTotal.setText("S/ " + precioTotal);
                lyChico.setBackgroundColor(getResources().getColor(R.color.gray));
                lyMediano.setBackgroundColor(getResources().getColor(R.color.gray));
                lyGrande.setBackgroundColor(getResources().getColor(R.color.gray));
                lyPersonalizado.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;

            case R.id.btn_efectivo:
                metodoPago = EFECTIVO;
                btnEfectivo.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                btnVisa.setBackgroundColor(getResources().getColor(R.color.gray));
                btnPaypal.setBackgroundColor(getResources().getColor(R.color.gray));
                break;
            case R.id.btn_visa:
                metodoPago = VISA;
                btnEfectivo.setBackgroundColor(getResources().getColor(R.color.gray));
                btnVisa.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                btnPaypal.setBackgroundColor(getResources().getColor(R.color.gray));
                break;
            case R.id.btn_paypal:
                metodoPago = PAYPAL;
                btnEfectivo.setBackgroundColor(getResources().getColor(R.color.gray));
                btnVisa.setBackgroundColor(getResources().getColor(R.color.gray));
                btnPaypal.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                break;
        }
    }
}
