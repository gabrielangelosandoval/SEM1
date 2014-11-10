package com.kapampangan.sandoval.attendancechecker;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Scanner;
import java.util.StringTokenizer;


public class SearchResult extends ActionBarActivity {

    private TypedArray imgs;
    private static Student objStudent;
    private static String strIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresult);

        Student objStudent = new Student();

        Intent intent = getIntent();
        strIntent = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView tvResult = (TextView) findViewById(R.id.tvSearchResult);


        if(strIntent.compareToIgnoreCase("No results found.") != 0){
        try{
            ReadFromFile(objStudent);
        }
        catch(Exception e){
        }

        getSectionPictures(objStudent.getSection());

        ImageView image = (ImageView) findViewById(R.id.searchImage);
        image.setImageResource(imgs.getResourceId(objStudent.getSeatNo()-1, -1));

        tvResult.setText(objStudent.getDetails());}

        else{
            tvResult.setText("No results found.");
        }
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

    public static void ReadFromFile(Student objStudent){

        Scanner sFile = new Scanner(strIntent).useDelimiter(";");

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

                objStudent.setSeatNo(seatno);
                objStudent.setStuName(StuName);
                objStudent.setSection(Section);
                objStudent.setNumLates(numLates);
                objStudent.setNumAbsences(numAbsences);
                objStudent.setTotalNumAbsences(total);
                objStudent.setStatus(status);

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.searchresult,
                    container, false);
            return rootView;
        }
    }
}