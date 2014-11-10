package com.kapampangan.sandoval.attendancechecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Scanner;


public class SearchStudent extends ActionBarActivity {

    Student [] objStudent = new Student[32];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchstudent);

        Intent intent = getIntent();
        String strIntent = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        for (int i = 0; i < objStudent.length; i++) {
            objStudent[i] = new Student();
        }

        Scanner sFile = new Scanner(strIntent).useDelimiter(";");
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

        switch(id) {
            case R.id.action_searchstudent:
                break;
            case R.id.action_liststudent:
                break;
            case R.id.action_settings:
                break;}

        return super.onOptionsItemSelected(item);
    }
    public void Search(View v){

        EditText etName = (EditText) findViewById(R.id.etLastName);
        EditText etSection = (EditText) findViewById(R.id.etSection);

        String strSearch = SearchStudent(etSection.getText().toString(), etName.getText().toString());

        Intent intent = new Intent(this, SearchResult.class);
        intent.putExtra(MainActivity.EXTRA_MESSAGE, strSearch);
        startActivity(intent);
    }

    public String SearchStudent(String Section, String FullName){
        String strResults = "";

        boolean found = false;
        for(int i = 0 ; i<objStudent.length; i++){
           if(objStudent[i].getSection().compareToIgnoreCase(Section) == 0 && objStudent[i].getLastName().compareToIgnoreCase(FullName)==0){
               return objStudent[i].compileRecord();
           }
        }

        return "No results found.";
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.searchstudent,
                    container, false);
            return rootView;
        }
    }
}