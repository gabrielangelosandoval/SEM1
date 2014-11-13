package com.kapampangan.sandoval.attendancechecker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Scanner;


public class Settings extends ActionBarActivity {

    Student [] objStudent = new Student[32];
    public static int numStudents = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Intent intent = getIntent();
        String strIntent = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        String MASTER = strIntent;

        for (int i = 0; i < objStudent.length; i++) {
            objStudent[i] = new Student();
        }

        Scanner sFile = new Scanner(MASTER).useDelimiter(";");
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
            numStudents++;
            sFile.nextLine();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_searchstudent:
                break;
            case R.id.action_liststudent:
                break;
            case R.id.action_settings:
                break;}

        return super.onOptionsItemSelected(item);
    }

    public void addStudent(View v){

        Context context = getApplicationContext();
        CharSequence text = "The student has been added.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    public void setAbsences(View v){

        Context context = getApplicationContext();
        CharSequence text = "Absences has been set.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void confirmSettings(View v){

        Context context = getApplicationContext();
        CharSequence text = "Changes saved!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.settings,
                    container, false);
            return rootView;
        }
    }
}