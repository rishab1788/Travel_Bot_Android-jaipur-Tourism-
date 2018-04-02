package com.example.rishabh.sidebar3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Belal on 18/09/16.
 */


public class menu1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments


        return inflater.inflate(R.layout.nav_menu1, container, false);




    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles


        getActivity().setTitle("Menu 1");
/*

        WebView myWebView = (WebView) getView().findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        //myWebView.loadUrl("http://tourism.rajasthan.gov.in/");

        myWebView.loadUrl("");
*/

    }
}