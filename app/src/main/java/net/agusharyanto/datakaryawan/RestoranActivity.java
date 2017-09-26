package net.agusharyanto.datakaryawan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RestoranActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextNama;
    private Spinner spinnerMenu;
    private CheckBox checkBoxTempe;
    private CheckBox checkBoxKerupuk;
    private CheckBox checkBoxTahu;
    private RadioGroup radioGroupPembayaran;
    private RadioButton radioButtonTunai;
    private RadioButton radioButtonNonTunai;
    private Button buttonSave;
    private Button buttonReset;
    private Button buttonSetData;
    private TextView textViewHasil;

    private String nama = "";

    private String tambahan = "";
    private String menu = "";
    private int positionSpinnerMenu = 0;

    private RadioButton radioButtonPembayaran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restoran);
        findViews();
        initData();
    }


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-09-26 10:47:06 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        editTextNama = (EditText)findViewById( R.id.editTextNama );
        spinnerMenu = (Spinner)findViewById( R.id.spinnerMenu );
        checkBoxTempe = (CheckBox)findViewById( R.id.checkBoxTempe );
        checkBoxKerupuk = (CheckBox)findViewById( R.id.checkBoxKerupuk );
        checkBoxTahu = (CheckBox)findViewById( R.id.checkBoxTahu );
        radioGroupPembayaran = (RadioGroup)findViewById( R.id.radioGroupPembayaran );
        radioButtonTunai = (RadioButton)findViewById( R.id.radioButtonTunai );
        radioButtonNonTunai = (RadioButton)findViewById( R.id.radioButtonNonTunai );
        buttonSave = (Button)findViewById( R.id.buttonSave );
        buttonReset = (Button)findViewById( R.id.buttonReset );
        buttonSetData = (Button)findViewById( R.id.buttonSetData );
        textViewHasil = (TextView)findViewById( R.id.textViewHasil );

        radioButtonTunai.setOnClickListener( this );
        radioButtonNonTunai.setOnClickListener( this );
        buttonSave.setOnClickListener( this );
        buttonReset.setOnClickListener( this );
        buttonSetData.setOnClickListener( this );
    }


    private void initData(){
        //mengisi data spinner
        List<String> positions = new ArrayList<String>();
        positions.add("Soto Betawi");
        positions.add("Rawon");
        positions.add("Soto Makasar");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item
                        , positions);

        // Drop down layout style - list view with radio button
        //dataAdapter.setDropDownViewResource
        //       (android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerMenu.setAdapter(dataAdapter);


    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-09-26 10:47:06 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == buttonSave ) {
            // Handle clicks for buttonSave
            readForm();
        } else if ( v == buttonReset ) {
            // Handle clicks for buttonReset
            resetForm();
        } else if ( v == buttonSetData ) {
            // Handle clicks for buttonSetData
            setForm();
        }
    }

    public void resetForm() {
        editTextNama.setText("");
        //radioButtonPria.setChecked(true);
        radioGroupPembayaran.clearCheck();
        checkBoxTempe.setChecked(false);
        checkBoxTahu.setChecked(false);
        checkBoxKerupuk.setChecked(false);
        spinnerMenu.setSelection(0);
        textViewHasil.setText("");
    }

    private void setForm() {
        // Log.d(TAG,"readForm");
        editTextNama.setText(nama);
        spinnerMenu.setSelection(positionSpinnerMenu);

        radioGroupPembayaran.check(radioButtonPembayaran.getId());
        // find the radiobutton by returned id


        String[] arrtambahan = tambahan.split(",");
        for (int i = 0; i < arrtambahan.length; i++) {
            if (arrtambahan[i].equals("Tempe")) {
                checkBoxTempe.setChecked(true);
            } else if (arrtambahan[i].equals("Kerupuk")) {
                checkBoxKerupuk.setChecked(true);
            } else if (arrtambahan[i].equals("Tahu")) {
                checkBoxTahu.setChecked(true);
            }
        }


    }

    private void readForm(){

        nama = editTextNama.getText().toString();
        menu = spinnerMenu.getSelectedItem().toString();

        positionSpinnerMenu = spinnerMenu.getSelectedItemPosition();

        int selectedId = radioGroupPembayaran.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioButtonPembayaran = (RadioButton) findViewById(selectedId);
        String pembayaran = radioButtonPembayaran.getText().toString();

        tambahan = "";
        if (checkBoxTempe.isChecked()) {
            tambahan += "" + checkBoxTempe.getText();
        }
        if (checkBoxKerupuk.isChecked()) {
            tambahan += "," + checkBoxKerupuk.getText();
        }
        if (checkBoxTahu.isChecked()) {
            tambahan += "," + checkBoxTahu.getText();
        }
        String hasil = "Nama  : " + nama + "\nMenu : " + menu + "\nPembayaran : " + pembayaran + "\ntambahan : " + tambahan ;
        textViewHasil.setText(hasil);


    }



}
