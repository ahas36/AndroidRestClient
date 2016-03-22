package edu.monash.androidrestclient;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import edu.monash.androidrestclient.entity.Course;


/**
 * Created by ahas36 on 11/03/16.
 */
public class RestClient {
    //your web service address. note that you need to change the address based on your ip address, glassfish port, and you project name
    private static final String BASE_URI = "http://192.168.43.104:52123/RESTQuerySample/webresources";

    public static String findAllCourses() {
        final String methodPath = "/edu.monash.course";
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        // Making HTTP request
        try {

            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");

            //add HTTP headers to set your respond type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());

            //read the inputsteream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        //return null if an exception happened
        return textResult;

    }

    public static Course findCourseById(int courseId) {
        Course course = null;
        final String methodPath = "/edu.monash.course/" + courseId;
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        // Making HTTP request
        try {

            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");

            //add HTTP headers to set your respond type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());

            //read the inputsteream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
            //get instance of Gson
            Gson gson = new Gson();
            //convert the json string to course entity
            course = gson.fromJson(textResult, Course.class);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return course;

    }

    public static String createCourse(Course course) {
        final String methodPath = "/edu.monash.course";
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        // Making HTTP request
        try {
            //get instance of Gson class
            Gson gson = new Gson();
            //convert course entity to string json by calling toJson method
            String stringCourseJson = gson.toJson(course);
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            //set the connection method to POST
            conn.setRequestMethod("POST");

            //set the output to true
            conn.setDoOutput(true);

            //set length of the data you are sending
            conn.setFixedLengthStreamingMode(stringCourseJson.getBytes().length);

            //add HTTP headers to set your respond type to json
            conn.setRequestProperty("Content-Type", "application/json");

            //send the POST out
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(stringCourseJson);
            out.close();

            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());

            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }
    public static String updateCourse(Course course) {
        final String methodPath = "/edu.monash.course/"+course.getCourseid();
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        // Making HTTP request
        try {
            //get instance of Gson class
            Gson gson = new Gson();
            //convert course entity to string json by calling toJson method
            String stringCourseJson = gson.toJson(course);
            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            //set the output to true
            conn.setDoOutput(true);

            //set the connection method to PUT
            conn.setRequestMethod("PUT");

            //set length of the data you are sending
            conn.setFixedLengthStreamingMode(stringCourseJson.getBytes().length);

            //add HTTP headers to set your respond type to json
            conn.setRequestProperty("Content-Type", "application/json");

            //send the POST out
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(stringCourseJson);
            out.close();

            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());

            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }


    public static String deleteCourse(int courseId)
    {
        Course course = null;
        final String methodPath = "/edu.monash.course/" + courseId;
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        // Making HTTP request
        try {

            url = new URL(BASE_URI + methodPath);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to Delete
            conn.setRequestMethod("DELETE");

            //add HTTP headers to set your respond type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());

            //read the inputsteream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return textResult;
    }
}
