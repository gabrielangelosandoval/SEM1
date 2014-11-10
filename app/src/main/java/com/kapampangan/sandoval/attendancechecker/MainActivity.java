package com.kapampangan.sandoval.attendancechecker;

import android.content.Intent;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.kapampangan.sandoval.attendancechecker.MESSAGE";

    private ImageView image;
    private String[] seats;
    private Spinner spinner;
    private TypedArray imgs;
    private RadioGroup rg = null;

    private TextView tvSurname;
    private static int seatNumberSelected;
    private static String curSection;
    private static int recordType;

    public static String MASTER;
    private static Student [] objStudent = new Student[32];
    private String strSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        curSection ="3CSD";

        MASTER = "1;Sandoval, Gabriel Angelo;3CSD;1;1;1;Good;\n" +
                "2;Dela Cruz, Rossandro Jaime;3CSD;0;2;2;Good;\n" +
                "3;Lim, Theo Nicolo;3CSD;2;0;0;Good;\n" +
                "4;Sagun, Chiara Mariella;3CSD;0;2;2;Good;\n" +
                "5;Say, Luis Paolo;3CSD;0;2;2;Good;\n" +
                "1;Abellera, Marc Ezekiel;3CSC;1;1;1;Good;\n" +
                "2;Cirineo, Christian;3CSC;2;0;0;Good;\n" +
                "3;Villafranca, Seiji;3CSC;2;0;0;Good;\n" +
                "4;Samonte, Reden Dino R.;3CSC;3;1;2;Good;\n" +
                "5;Legaspi, Criselle Amor P.;3CSC;0;0;1;Good;\n" +
                "1;Dela Cruz, Joan;3CSB;0;1;0;Good;\n" +
                "2;Aldeosa, Lance ;3CSB;2;0;1;Good;\n" +
                "3;Boydon, Sarah ;3CSB;2;2;1;Good;\n" +
                "4;Cabrera, Raims ;3CSB;2;0;1;Good;\n" +
                "5;Guillo, Bianca ;3CSB;2;0;1;Good;\n";

        try{
            ReadFromFile(objStudent);
        }
        catch(Exception e){

        }

        seats = getResources().getStringArray(R.array.seatNumbers);

        getSectionPictures(curSection);

        image = (ImageView) findViewById(R.id.studentImage);
        spinner = (Spinner) findViewById(R.id.studentNumber);
        tvSurname = (TextView) findViewById(R.id.tvSurname);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, seats);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                seatNumberSelected = spinner.getSelectedItemPosition();
                if(!objStudent[seatNumberSelected].getLastName().isEmpty()){
                    image.setImageResource(imgs.getResourceId(
                            seatNumberSelected, -1));
                    tvSurname.setText(objStudent[seatNumberSelected].getLastName());
                }
                else{
                    image.setImageResource(imgs.getResourceId(32, -1));
                    tvSurname.setText("unknown");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.clearCheck();

        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                //Toast.makeText(MainActivity.this, rb.getText(),
                //        Toast.LENGTH_SHORT).show();
                if(rb.getText().toString().compareToIgnoreCase("Present") == 0){
                    recordType = 0;
                }
                else if(rb.getText().toString().compareToIgnoreCase("Late") == 0){
                    recordType = 1;
                }
                else{
                    recordType = 2;
                }
            }
        };

        RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb1.setOnClickListener(listener);

        RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb2.setOnClickListener(listener);

        RadioButton rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb3.setOnClickListener(listener);

    }

    public void getSectionPictures(String curSection){
        int SectionValue;

        if(curSection.compareToIgnoreCase("3CSB") == 0){
            SectionValue = 32;
        }
        else if(curSection.compareToIgnoreCase("3CSC") == 0){
            SectionValue = 33;
        }
        else if(curSection.compareToIgnoreCase("3CSD") == 0){
            SectionValue = 34;
        }
        else{
            SectionValue = 31;
        }

        switch(SectionValue){
            case 31:imgs = getResources().obtainTypedArray(R.array.imageList);
                break;
            case 32:imgs = getResources().obtainTypedArray(R.array.imageList_3csb);
                break;
            case 33:imgs = getResources().obtainTypedArray(R.array.imageList_3csc);
                break;
            case 34:imgs = getResources().obtainTypedArray(R.array.imageList_3csd);
                break;
        }
    }

    public void setSection(View v){
        Button btn = (Button) v;
        curSection = btn.getText().toString();
        Toast.makeText(MainActivity.this, btn.getText(), Toast.LENGTH_SHORT).show();

        updateMaster(objStudent);

        try{
            ReadFromFile(objStudent);
        }
        catch(Exception e){

        }

        getSectionPictures(curSection);

                spinner.setSelection(0);
        seatNumberSelected = spinner.getSelectedItemPosition();
        if(!objStudent[seatNumberSelected].getLastName().isEmpty()){
            tvSurname.setText(objStudent[seatNumberSelected].getLastName());
            image.setImageResource(imgs.getResourceId(seatNumberSelected, -1));
            //totalAbsences = totalAbsences + objStudent[seatNumberSelected].getTotalNumAbsences();
            //tvTotal.setText(totalAbsences);
        }
        else{
            image.setImageResource(imgs.getResourceId(32, -1));
            tvSurname.setText("unknown");
        }

        rg.clearCheck();

    }

    public void updateMaster(Student[] objStudent){

        Student[]objMaster = new Student[32];

        for(int i = 0 ; i < objMaster.length; i++){
            objMaster[i] =  new Student();
        }

        Scanner sFile = new Scanner(MASTER).useDelimiter(";");

        int j = 0;

        int seatno;
        String StuName;
        String Section;
        int numLates;
        int numAbsences;
        int total;
        String status;

        while (sFile.hasNext()) {

            seatno = Integer.parseInt(sFile.next());
            StuName = sFile.next();
            Section = sFile.next();
            numLates = Integer.parseInt(sFile.next());
            numAbsences = Integer.parseInt(sFile.next());
            total = Integer.parseInt(sFile.next());
            status = sFile.next();
            objMaster[j].setSeatNo(seatno);
            objMaster[j].setStuName(StuName);
            objMaster[j].setSection(Section);
            objMaster[j].setNumLates(numLates);
            objMaster[j].setNumAbsences(numAbsences);
            objMaster[j].setTotalNumAbsences(total);
            objMaster[j].setStatus(status);
            j++;
            sFile.nextLine();
        }

        for(int i = 0 ; i < objMaster.length && !objMaster[i].getLastName().isEmpty() ; i++){
            for(int k = 0 ; k < objStudent.length; k++){
                if(objStudent[k].getLastName().compareToIgnoreCase(objMaster[i].getLastName()) == 0 && objStudent[k].getSeatNo() == objMaster[i].getSeatNo()){
                    objMaster[i].setSeatNo(objStudent[k].getSeatNo());
                    objMaster[i].setStuName(objStudent[k].getFullName());
                    objMaster[i].setSection(objStudent[k].getSection());
                    objMaster[i].setNumLates(objStudent[k].getNumLates());
                    objMaster[i].setNumAbsences(objStudent[k].getNumAbsences());
                    objMaster[i].setTotalNumAbsences(objStudent[k].getTotalNumAbsences());
                    objMaster[i].setStatus(objStudent[k].getStatus());
                }
            }
        }

        MASTER = "";

        for(int i = 0 ; i < objMaster.length && !objMaster[i].getLastName().isEmpty() ; i++){
            MASTER = MASTER + objMaster[i].compileRecord();
        }
    }

    public void updateRecord(View v){

        if(!objStudent[seatNumberSelected].getLastName().isEmpty()){
        switch(recordType){
            case 0:
                break;
            case 1:
                objStudent[seatNumberSelected].addLate();
                break;
            case 2:
                objStudent[seatNumberSelected].addAbsence();
                break;
        }

        Toast.makeText(MainActivity.this, "Recorded!", Toast.LENGTH_SHORT).show();
            updateMaster(objStudent);
        }
        else{
            Toast.makeText(MainActivity.this, "Student is unknown.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void ReadFromFile(Student [] objStudent){

        for (int i = 0; i < objStudent.length; i++) {
            objStudent[i] = new Student();
        }

        Scanner sFile = new Scanner(MASTER).useDelimiter(";");

        int j = 0;

        int seatno;
        String StuName;
        String Section;
        int numLates;
        int numAbsences;
        int total;
        String status;

        while (sFile.hasNext()) {

            seatno = Integer.parseInt(sFile.next());
            StuName = sFile.next();
            Section = sFile.next();
            numLates = Integer.parseInt(sFile.next());
            numAbsences = Integer.parseInt(sFile.next());
            total = Integer.parseInt(sFile.next());
            status = sFile.next();

            if(Section.compareToIgnoreCase(curSection) == 0){
            objStudent[j].setSeatNo(seatno);
            objStudent[j].setStuName(StuName);
            objStudent[j].setSection(Section);
            objStudent[j].setNumLates(numLates);
            objStudent[j].setNumAbsences(numAbsences);
            objStudent[j].setTotalNumAbsences(total);
            objStudent[j].setStatus(status);
            j++;}

            sFile.nextLine();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;
        String sectionData = "";

        switch(id) {
            case R.id.action_searchstudent:

                intent = new Intent(this, SearchStudent.class);
                intent.putExtra(EXTRA_MESSAGE, MASTER);
                startActivity(intent);
                break;

            case R.id.action_liststudent:
                for(int i = 0 ; i < objStudent.length && !objStudent[i].getLastName().isEmpty() ; i++){
                    sectionData = sectionData + objStudent[i].compileRecord();
                }

                intent = new Intent(this, ListStudent.class);
                intent.putExtra(EXTRA_MESSAGE, sectionData);
                startActivity(intent);
                break;

            case R.id.action_settings:
                updateMaster(objStudent);
                intent = new Intent(this, Settings.class);
                intent.putExtra(EXTRA_MESSAGE, MASTER);
                startActivity(intent);
                break;}


        return super.onOptionsItemSelected(item);
    }
}
