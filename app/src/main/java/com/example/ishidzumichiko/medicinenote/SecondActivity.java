package com.example.ishidzumichiko.medicinenote;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener varDateSetListener;

    private SQLiteDatabase db;

    int[] BUTTONS = {R.id.btnInsert, R.id.btnUpdate, R.id.btnDelete, R.id.btnShow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
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

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        EditText etxName1 = (EditText)this.findViewById(R.id.etxName);
        etxName1.setText(name);

        final EditText etxDate = (EditText)findViewById(R.id.etxDate);

        varDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                etxDate.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth +"日");
            }
        };

        ((Button)findViewById(R.id.btnDate)).setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view){
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dateDialog = new DatePickerDialog(
                        SecondActivity.this,
                        varDateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                dateDialog.show();
            }
        });

        final MyOpenHelper helper = new MyOpenHelper(this);
        db = helper.getWritableDatabase();

        final EditText etxHospital = (EditText)findViewById(R.id.etxHospital);
        final EditText etxDoctor = (EditText)findViewById(R.id.etxDoctor);
        final EditText etxMedicine = (EditText)findViewById(R.id.etxMedicine);

        for(int btnId:BUTTONS){
            Button btn = (Button)findViewById(btnId);
            btn.setOnClickListener(new View.OnClickListener(){
                @Override
            public void onClick(View v){

                    String message = "";
                    TextView res = (TextView)findViewById(R.id.txvMessage);

                    String date = etxDate.getText().toString();
                    String hospital = etxHospital.getText().toString();
                    String doctor = etxDoctor.getText().toString();
                    String medicine = etxMedicine.getText().toString();

                    TableLayout t_layout = (TableLayout)findViewById(R.id.list);

                    t_layout.removeAllViews();

                    if (v.getId() == R.id.btnInsert){
                        try{
                            db.beginTransaction();;

                            ContentValues val = new ContentValues();
                            val.put("date", date);
                            val.put("hospital", hospital);
                            val.put("doctor", doctor);
                            val.put("medicine", medicine);

                            db.insert("personal_data", null, val);

                            db.setTransactionSuccessful();

                            db.endTransaction();

                            etxDate.setText("");
                            etxHospital.setText("");
                            etxDoctor.setText("");
                            etxMedicine.setText("");

                            message = "データ登録が完了しました";

                        }catch (Exception e){
                            message = "データ登録に失敗しました";
                            Log.e("登録エラー", e.toString());
                        }
                    }else if (v.getId() == R.id.btnUpdate){
                        try{
                            db.beginTransaction();

                            ContentValues val = new ContentValues();
                            val.put("date", etxDate.getText().toString());
                            val.put("hospital", etxHospital.getText().toString());
                            val.put("doctor", etxDoctor.getText().toString());
                            val.put("medicine", etxMedicine.getText().toString());

                            db.update("personal_data", val, "date=?", new String[]{etxDate.getText().toString()});

                            db.setTransactionSuccessful();

                            db.endTransaction();

                            message = "データの更新をしました";

                        }catch (Exception e){
                            message = "更新に失敗しました";
                            Log.e("更新", e.toString());
                        }
                    }else if (v.getId() == R.id.btnDelete){
                        try{
                            db.beginTransaction();

                            db.delete("personal_data", "date=?", new String[]{etxDate.getText().toString()});

                            db.setTransactionSuccessful();

                            db.endTransaction();

                            message = "データを削除しました";
                        }catch (Exception e){
                            message = "削除に失敗しました";
                            Log.e("削除", e.toString());
                        }
                    }else if (v.getId() == R.id.btnShow){
                        try{
                            db = helper.getReadableDatabase();

                            String[] columns = {"date", "hospital", "doctor", "medicine"};

                            Cursor cursor = db.query("personal_data", columns, null, null, null, null, "date");

                            t_layout.setStretchAllColumns(true);

                            TableRow headrow = new TableRow(SecondActivity.this);
                            TextView headTxt1 = new TextView(SecondActivity.this);
                            headTxt1.setText("処方年月日");
                            headTxt1.setGravity(Gravity.CENTER_HORIZONTAL);
                            headTxt1.setWidth(60);
                            TextView headTxt2 = new TextView(SecondActivity.this);
                            headTxt2.setText("医療機関名");
                            headTxt2.setGravity(Gravity.CENTER_HORIZONTAL);
                            headTxt2.setWidth(60);
                            TextView headTxt3 = new TextView(SecondActivity.this);
                            headTxt3.setText("医師名");
                            headTxt3.setGravity(Gravity.CENTER_HORIZONTAL);
                            headTxt3.setWidth(60);
                            TextView headTxt4 = new TextView(SecondActivity.this);
                            headTxt4.setText("処方名");
                            headTxt4.setGravity(Gravity.CENTER_HORIZONTAL);
                            headTxt4.setWidth(100);
                            headrow.addView(headTxt1);
                            headrow.addView(headTxt2);
                            headrow.addView(headTxt3);
                            headrow.addView(headTxt4);
                            t_layout.addView(headrow);

                            while (cursor.moveToNext()){
                                TableRow row = new TableRow(SecondActivity.this);
                                TextView dateText = new TextView(SecondActivity.this);
                                dateText.setText(cursor.getString(0));
                                dateText.setGravity(Gravity.CENTER_HORIZONTAL);

                                TextView hospitalText = new TextView(SecondActivity.this);
                                hospitalText.setText(cursor.getString(1));
                                hospitalText.setGravity(Gravity.CENTER_HORIZONTAL);

                                TextView doctorText = new TextView(SecondActivity.this);
                                doctorText.setText(cursor.getString(2));
                                doctorText.setGravity(Gravity.CENTER_HORIZONTAL);

                                TextView medicineText = new TextView(SecondActivity.this);
                                medicineText.setText(cursor.getString(3));
                                medicineText.setGravity(Gravity.CENTER_HORIZONTAL);

                                row.addView(dateText);
                                row.addView(hospitalText);
                                row.addView(doctorText);
                                row.addView(medicineText);
                                t_layout.addView(row);

                                message = "データを取得しました";

                            }
                        }catch (Exception e){
                            message = "データ表示に失敗しました";
                            Log.e("表示", e.toString());
                        }
                    }
                    db.close();

                    res.setText(message);
                }
            });
        }
    }

}
