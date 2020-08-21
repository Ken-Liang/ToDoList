package com.example.to_do_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter{

    LayoutInflater mInflater;
    List<Date> already = new ArrayList<>();

    Date[] finalDates;
    String[] finalTags;
    String[] finalCourses;
    String[] finalTitles;


    public ItemAdapter(Context c, Date[] dates, String[] tags, String[] courses, String[] titles){
        finalDates = dates;
        finalTags = tags;
        finalCourses = courses;
        finalTitles = titles;

        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return finalTags.length;
    }

    @Override
    public Object getItem(int i) {
        return finalTitles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
         View finalView = mInflater.inflate(R.layout.list_view_format,null);
         TextView course_display = (TextView) finalView.findViewById(R.id.course_display);
         TextView tag_and_title_display = (TextView) finalView.findViewById(R.id.tag_and_title_display);

        TextView header = finalView.findViewById(R.id.header);

        header.setText(finalDates[i].toString());

/*      Beginning to further implement the display of dates.

        ViewHolder holder;

        if(view == null){
             view = mInflater.inflate(R.layout.list_view_format, viewGroup, false);
             holder = new ViewHolder();
             view.setTag(holder);
         }
         else{
             holder = (ViewHolder) view.getTag();
         }*/

         course_display.setText(finalCourses[i]);
         tag_and_title_display.setText(finalTags[i] +": " + finalTitles[i]);

         return finalView;

    }
}



