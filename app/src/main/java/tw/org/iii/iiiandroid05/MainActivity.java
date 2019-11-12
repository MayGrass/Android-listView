package tw.org.iii.iiiandroid05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    //ArrayList適合不常變動的資料，LinkedList適合常常異動的資料
    private LinkedList<HashMap<String,String>> data; //元素是HashMap，Key的資料型態是String，Value也是String
    private String[] from = {"title", "mesg"};
    private int[] to = {R.id.item_title, R.id.item_mesg};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        initListView();
    }

    private void initListView() {
        data = new LinkedList<>();

        for (int i=0; i<30; i++) {
            HashMap<String,String> dd = new HashMap<>();
            int rand = (int)(Math.random()*49+1);
            dd.put(from[0], "Title" + rand);
            dd.put(from[1], "Content" + rand);
            dd.put("detail", "Detail" + rand);
            data.add(dd);
        }
        adapter = new SimpleAdapter(
                this, data,
                R.layout.item_test1,
                from, to);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.v("DCH", "pos"+position);
                StringBuffer sb = new StringBuffer();
                sb.append("Title" + data.get(position).get("title") + "\n");
                sb.append("Mesg" + data.get(position).get("mesg") + "\n");
                sb.append("Detail" + data.get(position).get("detail") + "\n");
                displayDetail(sb.toString());
            }
        });
    }

    //顯示詳細資料
    private void displayDetail(String mesg) {
        new AlertDialog.Builder(this)
                .setMessage(mesg)
                .create()
                .show();
    }

    //新增資料
    public void addItem(View view) {
        HashMap<String,String> dd = new HashMap<>();
        int rand = (int)(Math.random()*49+1);
        dd.put(from[0], "Title" + rand);
        dd.put(from[1], "Content" + rand);
        dd.put("detail", "Detail" + rand);
        data.add(dd);
        adapter.notifyDataSetChanged(); //資料有異動必須呼叫，否則會閃退
    }
}
