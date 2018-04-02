package com.example.rishabh.sidebar3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Belal on 18/09/16.
 */


public class menu2 extends Fragment {
Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.nav_menu2, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 2");
        Button button = (Button) view.findViewById(R.id.btnhawa);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tripadvisor.in/Attraction_Review-g304555-d317345-Reviews-Hawa_Mahal_Palace_of_Wind-Jaipur_Jaipur_District_Rajasthan.html"));
               startActivity(intent);
             }
        });
    }



}