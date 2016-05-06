package deprecated;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.martinsaad.hackidc.HttpRequest;
import com.example.martinsaad.hackidc.R;

import java.util.List;

public class MainActivityDeprecated extends AppCompatActivity {

    private HttpRequest httpRequest = new HttpRequest();
    ListView list;
    List<Exercise> data;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO erase stack

        if (!isUserLoggedIn()){
            Intent LoginActivity = new Intent(this, deprecated.LoginActivity.class);
            startActivity(LoginActivity);
        }

        data = getExerciseList();

        list = (ListView) findViewById(R.id.listView_exerciseList);
        adapter = new MyAdapter();
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ExerciseDetailsActivity.class);
                intent.putExtra("exercise_id", data.get(position).id);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //adapter class
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.tableview_row_exercise_list,null);
                Log.d("TAG", "create view:" + position);
            }

            else {
                Log.d("TAG", "use convert view:" + position);
            }

            TextView exerciseName = (TextView) convertView.findViewById(R.id.textView_Row_exercise_name);
            return convertView;
        }
    }




    //TODO isUserLoggedIn
    private boolean isUserLoggedIn(){
        return true;
    }


    //TODO getExerciseList
    private List<Exercise> getExerciseList(){
        return null;
    }


    public class Exercise{
        String id;
        String name;
    }

}
