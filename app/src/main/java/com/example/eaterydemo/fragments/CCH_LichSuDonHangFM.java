package com.example.eaterydemo.fragments;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.others.ShowNotifyUser.showProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eaterydemo.adapter.CCH_Item_QuanLyDanhSachMonAnAdapter;
import com.example.eaterydemo.adapter.CCH_LichSuDonHangAdapter;
import com.example.eaterydemo.databinding.FragmentCchLichsudonhangBinding;

import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.KhuyenMai;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.service.ServiceAPI;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CCH_LichSuDonHangFM extends Fragment {
    FragmentCchLichsudonhangBinding fmBinding;
    NavController navController;
    View _view;
    List<DonHang> arr;
    CCH_LichSuDonHangAdapter adapter;

    List<DonHang> arrThem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fmBinding = FragmentCchLichsudonhangBinding.inflate(getLayoutInflater());
        _view = container;
        fmBinding.txtdateLichSuCCH.setInputType(InputType.TYPE_NULL);
        fmBinding.ivLichSuCCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datetimepicker();
                int ma = CCH_QuanLyNhaHangFM.MaNH;
                String ngay = fmBinding.txtdateLichSuCCH.toString().trim();
                XoaMaKhuyenMLichSuNhungDonHangTrong1NgayCuaNHai(ma,ngay);
            }
        });


        return fmBinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showProgressDialog(getContext(), "Đang tải dữ liệu");
        GetAllMonAnTheoNhaHang();
    }

    private void GetAllMonAnTheoNhaHang() {
        String date = fmBinding.txtdateLichSuCCH.getText().toString().trim();
        SimpleDateFormat ngay = new SimpleDateFormat("yyyy/MM/dd");
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.LichSuNhungDonHangTrong1NgayCuaNH(CCH_QuanLyNhaHangFM.MaNH,date);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arr = (List<DonHang>) response.body();
                Log.d("arr", arr.size() + "");
                adapter = new CCH_LichSuDonHangAdapter(arr, getContext());
                fmBinding.rvLichSuCCh.setAdapter(adapter);
                fmBinding.rvLichSuCCh.setLayoutManager(new LinearLayoutManager(getContext()));
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void datetimepicker(){

        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String thang;
                if (month < 10) {
                     thang = "0" + (month+1);
                }else {
                     thang = (month+1) +"";
                }
                    fmBinding.txtdateLichSuCCH.setText(year + "-" + thang + "-" + dayOfMonth);

            }
        };

        new DatePickerDialog(getContext() ,dateSetListener,calendar.get(calendar.YEAR),calendar.get(calendar.MONTH),calendar.get(calendar.DAY_OF_MONTH)).show();
    }

    private void XoaMaKhuyenMLichSuNhungDonHangTrong1NgayCuaNHai(int MaNH,String NgayMua) {
        ServiceAPI serviceAPI = getRetrofit().create(ServiceAPI.class);
        Call call = serviceAPI.LichSuNhungDonHangTrong1NgayCuaNH(CCH_QuanLyNhaHangFM.MaNH,NgayMua);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrThem = (List<DonHang>) response.body();
//                refreshRecycler();
                dismissProgressDialog();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgressDialog();
                Log.e("erroo", t.toString());
            }
        });
    }

    private void refreshRecycler(){
        arr.clear();
        arr.addAll(arrThem);
        adapter.notifyDataSetChanged();
    }

}
