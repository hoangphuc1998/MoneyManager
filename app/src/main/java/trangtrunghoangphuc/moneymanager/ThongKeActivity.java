package trangtrunghoangphuc.moneymanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ThongKeActivity extends AppCompatActivity {

    TextView txtThoiGian,txtTongThang;
    ImageButton btnThoat;
    ListView lvThongKe;
    ArrayAdapter<String>tienAdapter;
    ArrayList<String>dsTienTheoNgay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        addControls();
    }

    private void addControls() {
        txtThoiGian= (TextView) findViewById(R.id.txtThoiGian);
        txtTongThang= (TextView) findViewById(R.id.txtTongThang);
        btnThoat= (ImageButton) findViewById(R.id.btnThoat);
        lvThongKe= (ListView) findViewById(R.id.lvThongKe);
        dsTienTheoNgay=thongKeTien();
        tienAdapter=new ArrayAdapter<String>(ThongKeActivity.this,android.R.layout.simple_list_item_1,dsTienTheoNgay);
        lvThongKe.setAdapter(tienAdapter);
    }
    private ArrayList<String>thongKeTien()
    {
        int tongThang=0;
        ArrayList<String>dsTien=new ArrayList<String>();
        Intent intent=getIntent();
        int thang=intent.getIntExtra("Thang",1);
        int nam=intent.getIntExtra("Nam",1);
        SharedPreferences preferences=getSharedPreferences("So_Tien_Hang_Ngay",MODE_PRIVATE);
        int soNgay=0;
        switch (thang)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                soNgay=31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                soNgay=30;
                break;
            case 2:
                if((nam%4==0 && nam%100!=0) || nam%400==0)
                    soNgay=29;
                else soNgay=28;
                break;
            default:
                soNgay=0;
                break;
        }
        for (int i=1;i<=soNgay;i++)
        {
            int tien;
            if (i<10)
                if (thang<10) {
                    tien = preferences.getInt("Ngay_0" + i + "/0" + thang + "/" + nam, 0);
                    tongThang += tien;
                }
                else {
                    tien = preferences.getInt("Ngay_0" + i + "/" + thang + "/" + nam, 0);
                    tongThang += tien;
                }
            else {
                tien = preferences.getInt("Ngay_" + i + "/" + thang + "/" + nam, 0);
                tongThang += tien;
            }
            dsTien.add("Ngay "+i+": "+tien);
        }
        txtThoiGian.setText("Tháng "+thang+" năm "+nam);
        txtTongThang.setText("Số tiền sử dụng trong tháng "+thang+": "+tongThang);
        return dsTien;
    }

    public void xuLyThoat(View view) {
        finish();
    }
}
