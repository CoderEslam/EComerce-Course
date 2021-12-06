package com.doubleclick.ecommerce.ui.info;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.doubleclick.ecommerce.MainActivity;
import com.doubleclick.ecommerce.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {


    public InfoFragment() {
        // Required empty public constructor
    }


    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        Button Call = view.findViewById(R.id.Call);
        Call.setOnClickListener(v -> {
            sendEmail();
        });

        return view;
    }


    public void sendEmail() {
        String massege = "";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"eslamghazy858@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Ask");
        intent.putExtra(Intent.EXTRA_TEXT, massege);
        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivity(intent);
        }


//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        intent.setData(Uri.parse("tel:+201221930858"));
//        startActivity(intent);


    }

}