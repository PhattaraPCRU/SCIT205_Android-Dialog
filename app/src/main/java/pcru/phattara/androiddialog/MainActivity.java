package pcru.phattara.androiddialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("This is dialog 1.");
                builder.setMessage("Are you OK?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String[] items = {"Apple", "Banana", "Orange"};
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("This is dialog 2.")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String[] items = {"Apple", "Banana", "Orange"};
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("This is dialog 3.")
                        .setSingleChoiceItems(items, -1, null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                ListView lw = ((AlertDialog) dialog).getListView();
                                int i = lw.getCheckedItemPosition();
                                if (i >= 0){
                                    Toast.makeText(MainActivity.this, items[i], Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();
            }
        });
        final ArrayList<Integer> al = new ArrayList<Integer>();
        Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String[] items = {"Apple", "Banana", "Orange"};
                boolean[] isSelected = new boolean[items.length];
                for (int i : al){
                    isSelected[i] = true;
                }
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("This is dialog 4.")
                        .setMultiChoiceItems(items, isSelected, new DialogInterface.OnMultiChoiceClickListener(){
                            @Override
                            public void onClick(DialogInterface dailog, int which, boolean isChecked){
                                if (isChecked){
                                    al.add(which);
                                }else if (al.contains(which)){
                                    al.remove(Integer.valueOf(which));
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                String str = "";
                                for (int i : al){
                                    str += " "+items[i]+"\n";
                                }
                                if(str.length() > 0){
                                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();
            }
        });
        Button btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LayoutInflater lft = LayoutInflater.from(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("This is dialog 5.")
                        .setView(lft.inflate(R.layout.layout_login, null))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
        Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater lft = getLayoutInflater();
                final AlertDialog builder = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("This is dialog 6.")
                        .setView(lft.inflate(R.layout.layout_login, null))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create();
                builder.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog){
                        Button button = builder.getButton(AlertDialog.BUTTON_POSITIVE);
                        button.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                EditText et = (EditText) builder.findViewById(R.id.usr);
                                String str = et.getText().toString();
                                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                                builder.dismiss();
                            }
                        });
                    }
                });
                builder.show();
            }
        });
        Button btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String title = "Downloading Data";
                String msg = "Please wait...";
                final ProgressDialog pd = ProgressDialog.show(MainActivity.this, title, msg);
                Thread thd = new Thread(new Runnable(){
                    @Override
                    public void run(){
                        try{
                            Thread.sleep(5000);
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        pd.dismiss();
                    }
                });
                thd.start();
            }
        });
    }
}