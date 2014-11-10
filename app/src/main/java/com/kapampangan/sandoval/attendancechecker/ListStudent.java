package com.kapampangan.sandoval.attendancechecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Scanner;


public class ListStudent extends ActionBarActivity {

    Student [] objStudent = new Student[32];
    public static String f3CSD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liststudent);

        Intent intent = getIntent();
        String strString = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        f3CSD = strString;
        try{
            ReadFromFile(objStudent);

            TextView tv_SeatNo = (TextView) findViewById(R.id.tvSeatNo);
            TextView tv_Surname = (TextView) findViewById(R.id.tvLastName);
            TextView tv_Late = (TextView) findViewById(R.id.tvLate);
            TextView tv_Absent = (TextView) findViewById(R.id.tvAbsent);
            TextView tv_Total = (TextView) findViewById(R.id.tvTotal);
            TextView tv_Status = (TextView) findViewById(R.id.tvStatus);

            String strAllSeatNo = "";
            String strAllSurname = "";
            String strAllLate = "";
            String strAllAbsent = "";
            String strAllTotal = "";
            String strAllStatus = "";

            for(int i = 0 ; i < objStudent.length &&!objStudent[i].getLastName().isEmpty() ; i++){
                strAllSeatNo = strAllSeatNo + objStudent[i].getSeatNo() + "\n";
                strAllSurname = strAllSurname + objStudent[i].getLastName() + "\n";
                strAllLate = strAllLate + objStudent[i].getNumLates() + "\n";
                strAllAbsent = strAllAbsent + objStudent[i].getNumAbsences() + "\n";
                strAllTotal = strAllTotal + objStudent[i].getTotalNumAbsences() + "\n";
                strAllStatus = strAllStatus + objStudent[i].getStatus() + "\n";
            }

            tv_SeatNo.setText(strAllSeatNo);
            tv_Surname.setText(strAllSurname);
            tv_Late.setText(strAllLate);
            tv_Absent.setText(strAllAbsent);
            tv_Total.setText(strAllTotal);
            tv_Status.setText(strAllStatus);

        }
        catch(Exception e){

        }
    }

    public static void ReadFromFile(Student [] objStudent){

        for (int i = 0; i < objStudent.length; i++) {
            objStudent[i] = new Student();
        }

        Scanner sFile = new Scanner(f3CSD).useDelimiter(";");
        int j = 0;
        while (sFile.hasNext()) {
            objStudent[j].setSeatNo(Integer.parseInt(sFile.next()));
            objStudent[j].setStuName(sFile.next());
            objStudent[j].setSection(sFile.next());
            objStudent[j].setNumLates(Integer.parseInt(sFile.next()));
            objStudent[j].setNumAbsences(Integer.parseInt(sFile.next()));
            objStudent[j].setTotalNumAbsences(Integer.parseInt(sFile.next()));
            objStudent[j].setStatus(sFile.next());
            j++;
            sFile.nextLine();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_searchstudent:

                break;
            case R.id.action_liststudent:
                break;
            case R.id.action_settings:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.liststudent,
                    container, false);
            return rootView;
        }
    }
}