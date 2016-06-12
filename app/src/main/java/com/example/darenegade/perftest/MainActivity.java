package com.example.darenegade.perftest;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import fr.xebia.android.freezer.Freezer;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  TodoEntityManager manager = new TodoEntityManager();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Freezer.onCreate(getApplication());
  }

  public void startTest(View view) {
    Toast.makeText(getApplicationContext(), "Started!!", Toast.LENGTH_SHORT).show();
    new PerfTest().execute();

  }

  private class PerfTest extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
      final long start = System.currentTimeMillis();

      final int count = 10000;
      int[] array = new int[count];


      Random random = new Random();
      for (int i = 0; i < count; i++)
        array[i] = random.nextInt();

      int temp;
      for (int i = 1; i < array.length; i++) {
        for (int j = 0; j < array.length - i; j++) {
          if (array[j] > array[j + 1]) {
            temp = array[j];
            array[j] = array[j + 1];
            array[j + 1] = temp;
          }
        }
      }

      for (int i = 0; i < count; i++) {
        Todo todo = new Todo();
        todo.text = "PerfTest";
        manager.add(todo);
      }


      List<Todo> todos = manager.select()
          .text().equalsTo("PerfTest")
          .asList();

      manager.delete(todos);

      runOnUiThread(new Runnable() {
        @Override
        public void run() {

          AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
          alertDialog.setTitle("Done");
          alertDialog.setMessage("Finished with " + count + " Elements after: " + (System.currentTimeMillis() - start) + "ms");
          alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
              new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                  dialog.dismiss();
                }
              });
          alertDialog.show();
        }});

      return null;
    }
  }
}
