package com.example.ishidzumichiko.medicinenote;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener varDateSetListener;

    private EditText etxPhonetic, etxName, etxBirthday, etxAddress, etxPhone,
    etxContact, etxPhone1, etxNote, etxSideEffect;
    private RadioButton rbtMale, rbtFemale, rbtBloodA, rbtBloodB, rbtBloodO, rbtBloodAB,
    rbtYes, rbtNo, rbtYes1, rbtNo1, rbtYes2, rbtNo2;
    private CheckBox ckbAsthma, ckbUler, ckbKidney, ckbLiver, ckbDm, ckbHeart,
    ckbProstate, ckbGlaucoma, ckbOther;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        etxBirthday = (EditText)findViewById(R.id.etxBirthday);
        varDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                etxBirthday.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
            }
        };

        ((Button)findViewById(R.id.btnBirthday)).setOnClickListener(new View.OnClickListener(){
           @Override
        public void onClick(View view){
               Calendar calendar = Calendar.getInstance();
               DatePickerDialog dateDialog = new DatePickerDialog(
                       MainActivity.this,
                       varDateSetListener,
                       calendar.get(Calendar.YEAR),
                       calendar.get(Calendar.MONTH),
                       calendar.get(Calendar.DAY_OF_MONTH));
               dateDialog.show();
           }
        });

        etxPhonetic = (EditText)findViewById(R.id.etxPhonetic);
        etxName = (EditText)findViewById(R.id.etxName);
        etxAddress = (EditText)findViewById(R.id.etxAddress);
        etxPhone = (EditText)findViewById(R.id.etxPhone);
        etxContact = (EditText)findViewById(R.id.etxContact);
        etxPhone1 = (EditText)findViewById(R.id.etxPhone1);
        etxNote = (EditText)findViewById(R.id.etxNote);
        etxSideEffect = (EditText)findViewById(R.id.etxSideEffect);
        rbtMale = (RadioButton)findViewById(R.id.rbtMale);
        rbtFemale = (RadioButton)findViewById(R.id.rbtFemale);
        rbtBloodA = (RadioButton)findViewById(R.id.rbtBloodB);
        rbtBloodO = (RadioButton)findViewById(R.id.rbtBloodO);
        rbtBloodAB = (RadioButton)findViewById(R.id.rbtBloodAB);
        rbtYes = (RadioButton)findViewById(R.id.rbtYes);
        rbtNo = (RadioButton)findViewById(R.id.rbtNo);
        rbtYes1 = (RadioButton)findViewById(R.id.rbtYes1);
        rbtNo1 = (RadioButton)findViewById(R.id.rbtNo1);
        rbtYes2 = (RadioButton)findViewById(R.id.rbtYes2);
        rbtNo2 = (RadioButton)findViewById(R.id.rbtNo2);
        ckbAsthma = (CheckBox)findViewById(R.id.ckbAsthma);
        ckbUler = (CheckBox)findViewById(R.id.ckbUlcer);
        ckbKidney = (CheckBox)findViewById(R.id.ckbKidney);
        ckbLiver = (CheckBox)findViewById(R.id.ckbLiver);
        ckbDm = (CheckBox)findViewById(R.id.ckbDm);
        ckbHeart = (CheckBox)findViewById(R.id.ckbHeart);
        ckbProstate = (CheckBox)findViewById(R.id.ckbProstate);
        ckbGlaucoma = (CheckBox)findViewById(R.id.ckbGlaucoma);
        ckbOther = (CheckBox)findViewById(R.id.ckbOther);

        preferences = getSharedPreferences("shared", MODE_PRIVATE);

        Button btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view){
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("phonetic", etxPhonetic.getText().toString());
                editor.putString("name", etxName.getText().toString());
                editor.putString("birthday", etxBirthday.getText().toString());
                editor.putString("address", etxAddress.getText().toString());
                editor.putString("phone", etxPhone.getText().toString());
                editor.putString("contact", etxContact.getText().toString());
                editor.putString("phone1", etxPhone1.getText().toString());
                editor.putString("note", etxNote.getText().toString());
                editor.putString("sideEffect", etxSideEffect.getText().toString());
                editor.putBoolean("male", rbtMale.isChecked());
                editor.putBoolean("female", rbtFemale.isChecked());
                editor.putBoolean("bloodA", rbtBloodA.isChecked());
                editor.putBoolean("bloodB", rbtBloodB.isChecked());
                editor.putBoolean("bloodO", rbtBloodO.isChecked());
                editor.putBoolean("bloodAB", rbtBloodAB.isChecked());
                editor.putBoolean("yes", rbtYes.isChecked());
                editor.putBoolean("no", rbtNo.isChecked());
                editor.putBoolean("yes1", rbtYes1.isChecked());
                editor.putBoolean("no1", rbtNo1.isChecked());
                editor.putBoolean("yes2", rbtYes2.isChecked());
                editor.putBoolean("no2", rbtNo2.isChecked());
                editor.putBoolean("asthma", ckbAsthma.isChecked());
                editor.putBoolean("uler", ckbUler.isChecked());
                editor.putBoolean("kidney", ckbKidney.isChecked());
                editor.putBoolean("liver", ckbLiver.isChecked());
                editor.putBoolean("dm", ckbDm.isChecked());
                editor.putBoolean("heart", ckbHeart.isChecked());
                editor.putBoolean("prostate", ckbProstate.isChecked());
                editor.putBoolean("glaucoma", ckbGlaucoma.isChecked());
                editor.putBoolean("other", ckbOther.isChecked());
                editor.commit();
            }
        });

        preferences = getSharedPreferences("shared", MODE_PRIVATE);
        etxPhonetic.setText(preferences.getString("phonetic", ""));
        etxName.setText(preferences.getString("name", ""));
        etxBirthday.setText(preferences.getString("birthday", ""));
        etxAddress.setText(preferences.getString("address", ""));
        etxPhone.setText(preferences.getString("phone", ""));
        etxContact.setText(preferences.getString("contact", ""));
        etxPhone1.setText(preferences.getString("phone1", ""));
        etxNote.setText(preferences.getString("note", ""));
        etxSideEffect.setText(preferences.getString("sideEffect", ""));
        rbtMale.setChecked(preferences.getBoolean("male", false));
        rbtFemale.setChecked(preferences.getBoolean("female", false));
        rbtBloodA.setChecked(preferences.getBoolean("bloodA", false));
        rbtBloodB.setChecked(preferences.getBoolean("bloodB", false));
        rbtBloodO.setChecked(preferences.getBoolean("bloodO", false));
        rbtBloodAB.setChecked(preferences.getBoolean("bloodAB", false));
        rbtYes.setChecked(preferences.getBoolean("yes", false));
        rbtNo.setChecked(preferences.getBoolean("no", false));
        rbtYes1.setChecked(preferences.getBoolean("yes1", false));
        rbtNo1.setChecked(preferences.getBoolean("no1", false));
        rbtYes2.setChecked(preferences.getBoolean("yes2", false));
        rbtNo2.setChecked(preferences.getBoolean("no2", false));
        ckbAsthma.setChecked(preferences.getBoolean("asthma", false));
        ckbUler.setChecked(preferences.getBoolean("uler", false));
        ckbKidney.setChecked(preferences.getBoolean("kidney", false));
        ckbLiver.setChecked(preferences.getBoolean("liver", false));
        ckbDm.setChecked(preferences.getBoolean("dm", false));
        ckbHeart.setChecked(preferences.getBoolean("heart", false));
        ckbProstate.setChecked(preferences.getBoolean("prostate", false));
        ckbGlaucoma.setChecked(preferences.getBoolean("glaucoma", false));
        ckbOther.setChecked(preferences.getBoolean("other", false));

        final Activity activity = this;
        Button btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view){
                EditText etxName =(EditText)findViewById(R.id.etxName);
                String name = etxName.getText().toString();
                Intent intent = new Intent(activity,SecondActivity.class);
                intent.putExtra("name", etxName.getText().toString());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
