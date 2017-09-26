package net.agusharyanto.datakaryawan;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNIK, editTextNama, editTextTanggalLahir;
    private Spinner spinnerJabatan;
    private RadioGroup radioGroupJenisKelamin;
    private RadioButton radioButtonPria, radioButtonWanita;
    private RadioButton radioButtonJenisKelamin;
    private CheckBox checkBoxOlahraga, checkBoxBaca, checkBoxNonton;
    private Button buttonSimpan, buttonReset, buttonSetData;
    private TextView textViewHasil;
    private String nik = "";
    private String nama = "";
    private String tanggallahir = "";
    private String jabatan = "";
    private int positionSpinnerjabatan = 0;
    private static final String TAG = "MainActivity";
    private String hobi = "";
    private DatePickerDialog fromDatePickerDialog;
    private Calendar newCalendar;
    private Date date;
    private SimpleDateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        date = new Date();

        initUI();
        initData();
        initEvent();

    }


    private void initUI() {
        editTextNIK = (EditText) findViewById(R.id.editTextNIK);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextTanggalLahir = (EditText) findViewById(R.id.editTextTanggalLahir);
        spinnerJabatan = (Spinner) findViewById(R.id.spinnerJabatan);
        radioGroupJenisKelamin = (RadioGroup) findViewById(R.id.radioGroupJenisKelamin);
        radioButtonPria = (RadioButton) findViewById(R.id.radioButtonPria);
        radioButtonWanita = (RadioButton) findViewById(R.id.radioButtonWanita);
        checkBoxOlahraga = (CheckBox) findViewById(R.id.checkBoxOlahraga);
        checkBoxBaca = (CheckBox) findViewById(R.id.checkBoxBaca);
        checkBoxNonton = (CheckBox) findViewById(R.id.checkBoxNonton);
        buttonSimpan = (Button) findViewById(R.id.buttonSave);
        buttonReset = (Button) findViewById(R.id.buttonReset);
        buttonSetData = (Button) findViewById(R.id.buttonSetData);
        textViewHasil = (TextView) findViewById(R.id.textViewHasil);

    }

    private void initData() {
        List<String> positions = new ArrayList<String>();
        positions.add("Junior Programmer");
        positions.add("Senior Programmer");
        positions.add("Supervisor");
        positions.add("Manager");
        positions.add("General Manager");
        positions.add("CEO");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item
                        , positions);

        // Drop down layout style - list view with radio button
        //dataAdapter.setDropDownViewResource
         //       (android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerJabatan.setAdapter(dataAdapter);
    }

    public void resetForm() {
        editTextNIK.setText("");
        editTextNama.setText("");
        editTextTanggalLahir.setText("");
        radioButtonPria.setChecked(true);
        radioGroupJenisKelamin.clearCheck();
        checkBoxOlahraga.setChecked(false);
        checkBoxBaca.setChecked(false);
        checkBoxNonton.setChecked(false);
        spinnerJabatan.setSelection(0);
        textViewHasil.setText("");
    }

    private void initEvent() {
        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "save",Toast.LENGTH_SHORT).show();
                if (isValidInput()) {
                    readForm();
                }
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "reset",Toast.LENGTH_SHORT).show();
                resetForm();
            }
        });
        buttonSetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setForm();
            }
        });
        setDateTimeField();

    }

    private void readForm() {
        // Log.d(TAG,"readForm");

      //  EditText editTextNIK = (EditText) findViewById(R.id.editTextNIK);
         nik = editTextNIK.getText().toString();
       // editTextNIK.setText("20161011001");

        nama = editTextNama.getText().toString();
        tanggallahir = editTextTanggalLahir.getText().toString();
        jabatan = spinnerJabatan.getSelectedItem().toString();

        positionSpinnerjabatan = spinnerJabatan.getSelectedItemPosition();

        int selectedId = radioGroupJenisKelamin.getCheckedRadioButtonId();
        // find the radiobutton by returned id
        radioButtonJenisKelamin = (RadioButton) findViewById(selectedId);
        String jeniskelamin = radioButtonJenisKelamin.getText().toString();

        hobi = "";
        if (checkBoxOlahraga.isChecked()) {
            hobi += "" + checkBoxOlahraga.getText();
        }
        if (checkBoxBaca.isChecked()) {
            hobi += "," + checkBoxBaca.getText();
        }
        if (checkBoxNonton.isChecked()) {
            hobi += "," + checkBoxNonton.getText();
        }
        String hasil = "NIK : " + nik + "\nNama  : " + nama + "\nJabatan : " + jabatan + "\nJenis Kelamin : " + jeniskelamin + "\nhobi : " + hobi + "\nTanggal Lahir : " + tanggallahir;
        textViewHasil.setText(hasil);


    }


    private void setForm() {
        // Log.d(TAG,"readForm");
        editTextNIK.setText(nik);
        editTextNama.setText(nama);
        editTextTanggalLahir.setText(tanggallahir);
        spinnerJabatan.setSelection(positionSpinnerjabatan);

        radioGroupJenisKelamin.check(radioButtonJenisKelamin.getId());
        // find the radiobutton by returned id


        String[] arrhobi = hobi.split(",");
        for (int i = 0; i < arrhobi.length; i++) {
            if (arrhobi[i].equals("OlahRaga")) {
                checkBoxOlahraga.setChecked(true);
            } else if (arrhobi[i].equals("Baca")) {
                checkBoxBaca.setChecked(true);
            } else if (arrhobi[i].equals("Nonton")) {
                checkBoxNonton.setChecked(true);
            }
        }


    }

    private boolean isValidInput() {
        if (editTextNIK.getText().toString().equals("")) {
            editTextNIK.setError(getResources().getString(R.string.errornik));
            return false;
        }
        return true;
    }

    private void setDateTimeField() {


        editTextTanggalLahir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();

            }
        });



        //Calendar newCalendar = Calendar.getInstance();
        newCalendar = Calendar.getInstance();
        newCalendar.setTime(date);
        fromDatePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        Calendar currdate=Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);

                        editTextTanggalLahir.setText(dateFormatter.format(newDate
                                .getTime()));

                    }

                }, newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));


    }


}
