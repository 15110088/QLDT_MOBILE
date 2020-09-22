package stnmt.ttcntt.qldt_mobile.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import stnmt.ttcntt.qldt_mobile.Interface.IEventImageButton;
import stnmt.ttcntt.qldt_mobile.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link loaibando#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loaibando extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static IEventImageButton iEventImageButton;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public loaibando(IEventImageButton _iEventImageButton) {
        this.iEventImageButton=_iEventImageButton;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loaibando.
     */
    // TODO: Rename and change types and number of parameters
    public static loaibando newInstance(String param1, String param2) {
        loaibando fragment = new loaibando(iEventImageButton);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_loaibando, container, false);
        ImageButton  btnVetinh =view.findViewById(R.id.btnVetinh);
        ImageButton  btnDuong =view.findViewById(R.id.btnDuong);
        LinearLayout lytduong =view.findViewById(R.id.lyt_duong);
        LinearLayout lytvetinh =view.findViewById(R.id.lyt_vetinh);

        btnVetinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iEventImageButton.OnClick(v,1);
                lytvetinh.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.radius_select));
                lytduong.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.radius_noselect));

            }
        });

        btnDuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iEventImageButton.OnClick(v,2);
                lytduong.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.radius_select));
                lytvetinh.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.radius_noselect));
            }
        });
        return view;
    }
}