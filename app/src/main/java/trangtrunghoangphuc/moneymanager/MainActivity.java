package trangtrunghoangphuc.moneymanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView txtTaiKhoan,txtDate;
    EditText txtTien;
    ImageButton btnDatePicker;
    Button btnThayDoiActiviy,btnThemTien,btnThongKe;
    EditText Test;
    final int requestCodeActivity=1;
    final int thayDoiTaiKhoanResultCode=3;
    Calendar cal=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnThayDoiActiviy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thayDoiActivity();
            }
        });
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thayDoiNgay();
            }
        });
        btnThemTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThemTien();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThongKe();
            }
        });
    }

    private void xuLyThongKe() {
        int thang=cal.get(Calendar.MONTH)+1;
        int nam=cal.get(Calendar.YEAR);
        Intent intent=new Intent(MainActivity.this,ThongKeActivity.class);
        intent.putExtra("Thang",thang);
        intent.putExtra("Nam",nam);
        startActivity(intent);
    }

    private void xuLyThemTien() {
        String tienString=txtTien.getText().toString();
        if (tienString.length()==0)
        {
            Toast.makeText(MainActivity.this,"Bạn vui lòng nhập số tiền cần thêm",Toast.LENGTH_LONG).show();
        }
        else
        {
            int tien=Integer.parseInt(tienString);
            SharedPreferences preferences=getSharedPreferences("So_Tien_Hang_Ngay",MODE_PRIVATE);
            int tienLucDau=preferences.getInt("Ngay_"+txtDate.getText().toString(),0);
            SharedPreferences.Editor editor=preferences.edit();
            editor.putInt("Ngay_"+txtDate.getText().toString(),tien);
            editor.commit();
            int taiKhoanThayDoi=-tien+tienLucDau;
            int taiKhoan=Integer.parseInt(txtTaiKhoan.getText().toString());
            taiKhoan+=taiKhoanThayDoi;
            txtTaiKhoan.setText(""+taiKhoan);
        }
    }

    private void thayDoiNgay() {
        DatePickerDialog.OnDateSetListener callBack=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cal.set(Calendar.YEAR,year);
                cal.set(Calendar.MONTH,month);
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                txtDate.setText(sdf.format(cal.getTime()));
                SharedPreferences preferences=getSharedPreferences("So_Tien_Hang_Ngay",MODE_PRIVATE);
                int tien=preferences.getInt("Ngay_"+txtDate.getText().toString(),0);
                txtTien.setText(""+tien);
            }
        };
        DatePickerDialog datePickerDialog=new DatePickerDialog(
                MainActivity.this,
                callBack,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();

    }

    private void thayDoiActivity() {
        Intent intent=new Intent(MainActivity.this,ThayDoiTaiKhoanActivity.class);
        intent.putExtra("Tai_Khoan",Integer.parseInt(txtTaiKhoan.getText().toString()));
        startActivityForResult(intent,requestCodeActivity);
    }

    private void addControls() {
        txtTaiKhoan= (TextView) findViewById(R.id.txtTaiKhoan);
        txtDate= (TextView) findViewById(R.id.txtDate);
        txtTien= (EditText) findViewById(R.id.txtTien);
        btnDatePicker= (ImageButton) findViewById(R.id.btnDatePicker);
        btnThayDoiActiviy= (Button) findViewById(R.id.btnThayDoiActivity);
        btnThemTien= (Button) findViewById(R.id.btnThemTien);
        btnThongKe= (Button) findViewById(R.id.btnThongKe);
        //Ngay hien tai
        Date date=cal.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        txtDate.setText(sdf.format(date));
        SharedPreferences preferences=getSharedPreferences("So_Tien_Hang_Ngay",MODE_PRIVATE);
        int tien=preferences.getInt("Ngay_"+txtDate.getText().toString(),0);
        txtTien.setText(""+tien);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==requestCodeActivity && resultCode==thayDoiTaiKhoanResultCode)
        {
            int taiKhoanThayDoi=data.getIntExtra("Tai_Khoan_Thay_Doi",0);
            int taiKhoan=Integer.parseInt(txtTaiKhoan.getText().toString());
            taiKhoan+=taiKhoanThayDoi;
            txtTaiKhoan.setText(""+taiKhoan);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences=getSharedPreferences("Tai_Khoan_Preference",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("Tai_Khoan",Integer.parseInt(txtTaiKhoan.getText().toString()));
        editor.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Tai khoan con lai
        SharedPreferences preferences=getSharedPreferences("Tai_Khoan_Preference",MODE_PRIVATE);
        txtTaiKhoan.setText(""+preferences.getInt("Tai_Khoan",0));
    }

}
