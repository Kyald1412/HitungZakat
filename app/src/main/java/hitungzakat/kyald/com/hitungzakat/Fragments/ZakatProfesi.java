package hitungzakat.kyald.com.hitungzakat.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import hitungzakat.kyald.com.hitungzakat.R;

public class ZakatProfesi extends Fragment {

    View v;

    EditText penghasilan,hutang;

    public ZakatProfesi() {
        // Required empty public constructor
    }

    public static ZakatProfesi newInstance(String param1, String param2) {
        ZakatProfesi fragment = new ZakatProfesi();
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
        v =  inflater.inflate(R.layout.fragment_zakat_profesi, container, false);


        penghasilan = (EditText) v.findViewById(R.id.input_harga);

        hutang = (EditText) v.findViewById(R.id.input_harga1);

        Button btn_hasil = (Button) v.findViewById(R.id.button);


        btn_hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()){

                    final Dialog dialog = new Dialog(getContext());
                    // Include dialog.xml file
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_hasil);
                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
                    dialog.setCanceledOnTouchOutside(false);

                    WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
                    wmlp.gravity = Gravity.TOP | Gravity.LEFT;
                    wmlp.x = 50;   //x position
                    wmlp.y = 170;   //y position

                    wmlp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    wmlp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                    // Set dialog title
                    // set values for custom dialog components - text, image and button
                    TextView token_text = (TextView) dialog.findViewById(R.id.token_text);
                    Button btn_done = (Button) dialog.findViewById(R.id.btn_done);
                    TextView title = (TextView) dialog.findViewById(R.id.textView8);

                    int intpenghasilan = Integer.valueOf(String.valueOf(penghasilan.getText()));
                    int inthutang = Integer.valueOf(String.valueOf(hutang.getText()));

                    double hasil = (0.025 * (intpenghasilan - inthutang));

                    token_text.setText("Rp. "+String.valueOf(Integer.valueOf((int) hasil)));
                    title.setText("Rumus : 2.5% x (Penghasilan total - hutang/cicilan)");

                    dialog.show();

                    // if decline button is clicked, close the custom dialog
                    btn_done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();

                        }
                    });


                }

            }
        });


        return v;
    }

    private boolean validate() {
        if (penghasilan.getText().toString().trim().isEmpty()) {
            penghasilan.setError("Tidak boleh kosong");
            penghasilan.requestFocus();
            return false;
        } else {
            penghasilan.setError(null);
        }

        if (hutang.getText().toString().trim().isEmpty()) {
            hutang.setError("Tidak boleh kosong");
            hutang.requestFocus();
            return false;
        } else {
            hutang.setError(null);
        }

        return true;
    }

}
