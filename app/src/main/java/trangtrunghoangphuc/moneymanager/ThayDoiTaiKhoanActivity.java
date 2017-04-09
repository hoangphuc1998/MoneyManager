package trangtrunghoangphuc.moneymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ThayDoiTaiKhoanActivity extends AppCompatActivity {

    RadioButton radTang,radGiam;
    EditText txtSoTienThayDoi;
    Button btnThayDoiSoTien;
    TextView txtTaiKhoanActivity;
    final int thayDoiTaiKhoanResultCode=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_tai_khoan);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnThayDoiSoTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thayDoiTaiKhoan();
            }
        });
    }

    private void thayDoiTaiKhoan() {
        int soTien=0;
        if (txtSoTienThayDoi.getText().toString().length()==0)
        {
            Toast.makeText(ThayDoiTaiKhoanActivity.this,"Bạn vui lòng nhập số tiền cần thay đổi",Toast.LENGTH_SHORT).show();
        }
        else
        {
            soTien=Integer.parseInt(txtSoTienThayDoi.getText().toString());
            if (radGiam.isChecked())
            {
                soTien=-soTien;
            }
            Intent intent=getIntent();
            intent.putExtra("Tai_Khoan_Thay_Doi",soTien);
            setResult(thayDoiTaiKhoanResultCode,intent);
            finish();
        }
    }

    private void addControls() {
        radTang= (RadioButton) findViewById(R.id.radTang);
        radGiam= (RadioButton) findViewById(R.id.radGiam);
        txtSoTienThayDoi= (EditText) findViewById(R.id.txtSoTienThayDoi);
        btnThayDoiSoTien= (Button) findViewById(R.id.btnThayDoiSoTien);
        txtTaiKhoanActivity= (TextView) findViewById(R.id.txtTaiKhoanActivity);
        Intent intent=getIntent();
        int taiKhoan=intent.getIntExtra("Tai_Khoan",0);
        txtTaiKhoanActivity.setText(""+taiKhoan);
    }
}
