package edu.monash.androidrestclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.monash.androidrestclient.entity.Course;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private EditText courseIdFind;
    private EditText courseId;
    private EditText courseTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find needed views
        resultTextView = (TextView) findViewById(R.id.tvResult);

        courseId=(EditText) findViewById(R.id.etCourseId);
        courseTitle=(EditText)findViewById(R.id.etCourseTitle);

        courseIdFind = (EditText) findViewById(R.id.etCourseIdFind);

        Button findAllStudentBtn = (Button) findViewById(R.id.btnFindAll);

        findAllStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create an anonymous AsyncTask
                new AsyncTask<Void, Void, String>() {

                    @Override
                    protected String doInBackground(Void... params) {
                        return RestClient.findAllCourses();
                    }

                    @Override
                    protected void onPostExecute(String courses) {
                        resultTextView.setText(courses);
                    }
                }.execute();
            }
        });


        Button getCourseByIdBtn = (Button) findViewById(R.id.btnFindCourseById);
        getCourseByIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int courseIdValue = Integer.valueOf(courseIdFind.getText().toString());
                //create an anonymous AsyncTask
                new AsyncTask<Integer, Void, Course>() {
                    @Override
                    protected Course doInBackground(Integer... params) {

                        return RestClient.findCourseById(params[0]);
                    }

                    @Override
                    protected void onPostExecute(Course course) {
                        if (course == null) {
                            resultTextView.setText("no course found");
                        } else {
                            resultTextView.setText(course.getCoursename());
                        }
                    }
                }.execute(courseIdValue);
            }

        });

        Button addCourse=(Button)findViewById(R.id.btnAddCourse);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String, Void, String>() {
                    @Override
                    protected String doInBackground(String... params) {
                        Course course=new Course(Integer.valueOf(params[0]),params[1]);
                        return RestClient.createCourse(course);
                    }

                    @Override
                    protected void onPostExecute(String response) {
                            resultTextView.setText(response);

                    }
                }.execute(courseId.getText().toString(),courseTitle.getText().toString());
            }
        });

        Button editCourse=(Button)findViewById(R.id.btnEditCourse);
        editCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String, Void, String>() {
                    @Override
                    protected String doInBackground(String... params) {
                        Course course=new Course(Integer.valueOf(params[0]),params[1]);
                        return RestClient.updateCourse(course);
                    }

                    @Override
                    protected void onPostExecute(String response) {
                        resultTextView.setText(response);

                    }
                }.execute(courseId.getText().toString(),courseTitle.getText().toString());
            }
        });

        Button deleteCourse=(Button)findViewById(R.id.btnDeleteCourseById);
        deleteCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Integer, Void, String>() {
                    @Override
                    protected String doInBackground(Integer... params) {
                        return RestClient.deleteCourse(params[0]);
                    }

                    @Override
                    protected void onPostExecute(String response) {
                        resultTextView.setText(response);

                    }
                }.execute(Integer.valueOf(courseIdFind.getText().toString()));
            }
        });
    }
}
