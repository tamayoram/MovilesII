package com.example.fbooks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fbooks.models.BookModel;

public class InformationDetailFragment extends Fragment {

    private static String code,description,title;
    private boolean available;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv_info_fragment_code,tv_info_fragment_title,tv_info_fragment_description;

        tv_info_fragment_code=view.findViewById(R.id.tv_info_fragment_code);
        tv_info_fragment_title=view.findViewById(R.id.tv_info_fragment_title);
        tv_info_fragment_description=view.findViewById(R.id.tv_info_fragment_description);

        tv_info_fragment_code.setText(code);
        tv_info_fragment_title.setText(title);
        tv_info_fragment_description.setText(description);

        view.findViewById(R.id.btn_info_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(InformationDetailFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

   static void receivedInformation(Bundle bundle){
       BookModel model= (BookModel) bundle.getSerializable("model");

       if(model!=null){
           code=model.getCode();
           title=model.getTitle();
           description=model.getDescription();
       }
    }

}