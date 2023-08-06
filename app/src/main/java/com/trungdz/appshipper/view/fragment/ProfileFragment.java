package com.trungdz.appshipper.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trungdz.appshipper.view.activity.LoginActivity;
import com.trungdz.appshipper.databinding.FragmentProfileBinding;
import com.trungdz.appshipper.service.model.UserInfo;
import com.trungdz.appshipper.viewmodel.ProfileFragmentViewmodel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    FragmentProfileBinding binding;
    ProfileFragmentViewmodel profileFragmentViewmodel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentProfileBinding.inflate(inflater,container,false);
        profileFragmentViewmodel= ViewModelProviders.of(this).get(ProfileFragmentViewmodel.class);

        initUI();

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    void initUI(){
        UserInfo userInfo=profileFragmentViewmodel.getUserInfo();

        binding.textName.setText(userInfo.getName());
        binding.txtPhone.setText(userInfo.getPhone());
        binding.txtEmail.setText(userInfo.getEmail());
        binding.txtAddress.setText(userInfo.getAddress());

        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();

            }
        });
    }

    void showAlertDialog(){
        new AlertDialog.Builder(getContext()).setMessage("Bạn muốn đăng xuất").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent myIntent = new Intent(getActivity(), LoginActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(myIntent);
            }
        }).setNegativeButton(android.R.string.no,null).show();
    }
}