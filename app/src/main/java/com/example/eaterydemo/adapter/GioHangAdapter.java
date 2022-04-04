package com.example.eaterydemo.adapter;

import static com.example.eaterydemo.others.ShowNotifyUser.dismissProgressDialog;
import static com.example.eaterydemo.service.GetRetrofit.getRetrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eaterydemo.R;
import com.example.eaterydemo.fragments.NhaHangChiTietFMDirections;
import com.example.eaterydemo.model.DonHang;
import com.example.eaterydemo.model.DonHangChiTiet;
import com.example.eaterydemo.model.MonAn;
import com.example.eaterydemo.service.ServiceAPI;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {


    List<DonHangChiTiet> arr;
    Context context;

    public GioHangAdapter(List<DonHangChiTiet> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_ItemMonAnGioHang, img_TangSoLuong, img_GiamSoLuong, img_HuyMonAn;
        TextView txt_TenMonAn, txt_GiaMonAn, txt_SoLuong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_TenMonAn = itemView.findViewById(R.id.txt_TenMonAn_ItemThanhToan);
            txt_GiaMonAn = itemView.findViewById(R.id.txt_GiaMonAn_ItemThanhToan);
            img_ItemMonAnGioHang = itemView.findViewById(R.id.img_MonAn_ItemThanhToan);
            img_TangSoLuong = itemView.findViewById(R.id.img_TangSoLuong_ItemThanhToan);
            img_GiamSoLuong = itemView.findViewById(R.id.img_GiamSoLuong_ItemThanhToan);
            txt_SoLuong = itemView.findViewById(R.id.txt_SoLuong_ItemThanhToan);
            img_HuyMonAn = itemView.findViewById(R.id.img_Cancel_ItemThanhToan);
        }

        public ImageView getimg_ItemMonAnGioHang(){
            return img_ItemMonAnGioHang;
        }

        public ImageView getimg_TangSoLuong(){
            return img_TangSoLuong;
        }

        public ImageView getimg_GiamSoLuong(){
            return img_GiamSoLuong;
        }

        public TextView gettxt_TenMonAn(){
            return txt_TenMonAn;
        }

        public TextView gettxt_GiaMonAn(){
            return txt_GiaMonAn;
        }

        public TextView gettxt_SoLuong(){
            return txt_SoLuong;
        }

        public ImageView getimg_cancle(){
            return img_HuyMonAn;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_donhang_thanhtoan, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, int position) {
        DonHangChiTiet model = arr.get(position);
        int sl = (int) model.getSL();
        int i = (int) model.getGiaMA();
        ImageView img_ItemMonAnGioHang = holder.getimg_ItemMonAnGioHang();
        ImageView img_TangSoLuong = holder.getimg_TangSoLuong();
        ImageView img_GiamSoLuong = holder.getimg_GiamSoLuong();
        ImageView img_HuyMonAN = holder.getimg_cancle();
        TextView txt_TenMonAn = holder.gettxt_TenMonAn();
        TextView txt_GiaMonAn = holder.gettxt_GiaMonAn();
        TextView txt_SoLuong = holder.gettxt_SoLuong();
        Glide.with(context).load(model.getHinhAnh()).centerCrop().into(img_ItemMonAnGioHang);
        txt_TenMonAn.setText(model.getTenMA());
        txt_GiaMonAn.setText(i + " đ");
        txt_SoLuong.setText(sl + "");

        //Giảm số lượng
        img_GiamSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Tăng số lượng
        img_TangSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Hủy món ăn
        img_HuyMonAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}