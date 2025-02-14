package com.techtist.srikasiviswanathar.utils;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.techtist.srikasiviswanathar.api.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Utils {

    private static DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
    private static DateFormat timeFormat = new SimpleDateFormat("K:mma");
    public static String USER_TYPE="PASSENGER";
    public static String USER_ONE;
    public static String USER_TWO;
    private static Animation mAnim;
   /* public static ApiInterface getInterfaceService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final ApiInterface mInterfaceService = retrofit.create(ApiInterface.class);
        return mInterfaceService;
    }*/

 /*  public static void replaceFragment(Fragment fragment, FragmentManager fragmentManager, String currentTag) {

       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
               android.R.anim.fade_out);
       fragmentTransaction.replace(R.id.host_home_fragment, fragment, currentTag);
       fragmentTransaction.addToBackStack(null);
       //fragmentTransaction.commitAllowingStateLoss();
       fragmentTransaction.commit();
       System.out.println("Fragment Name : "+currentTag);
   }
*/
    public static ApiInterface getInterfaceService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        CookieHandler cookieHandler = new CookieManager();
        OkHttpClient.Builder clientHTTP = new OkHttpClient.Builder();
        clientHTTP.cookieJar(new JavaNetCookieJar(cookieHandler));
        clientHTTP.connectTimeout(15, TimeUnit.SECONDS);
        clientHTTP.readTimeout(15, TimeUnit.SECONDS);
        clientHTTP.writeTimeout(15, TimeUnit.SECONDS);
        clientHTTP.addInterceptor(interceptor);

        OkHttpClient client=clientHTTP.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();


        final ApiInterface mInterfaceService = retrofit.create(ApiInterface.class);
        return mInterfaceService;
    }

    private  static String timeAgo( String givenDateString ) {
        long time_ago=Long.getLong("");
        //String givenDateString = "Tue Apr 23 16:08:28 GMT+05:30 2013";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            Date mDate = sdf.parse(givenDateString);
             time_ago = mDate.getTime();
            System.out.println("Date in milli :: " + time_ago);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long cur_time = (Calendar.getInstance().getTimeInMillis()) / 1000;
        long time_elapsed = cur_time - time_ago;
        long seconds = time_elapsed;
        int minutes = Math.round(time_elapsed / 60);
        int hours = Math.round(time_elapsed / 3600);
        int days = Math.round(time_elapsed / 86400);
        int weeks = Math.round(time_elapsed / 604800);
        int months = Math.round(time_elapsed / 2600640);
        int years = Math.round(time_elapsed / 31207680);

        // Seconds
        if (seconds <= 60) {
            return "just now";
        }
        //Minutes
        else if (minutes <= 60) {
            if (minutes == 1) {
                return "one minute ago";
            } else {
                return minutes + " minutes ago";
            }
        }
        //Hours
        else if (hours <= 24) {
            if (hours == 1) {
                return "an hour ago";
            } else {
                return hours + " hrs ago";
            }
        }
        //Days
        else if (days <= 7) {
            if (days == 1) {
                return "yesterday";
            } else {
                return days + " days ago";
            }
        }
        //Weeks
        else if (weeks <= 4.3) {
            if (weeks == 1) {
                return "a week ago";
            } else {
                return weeks + " weeks ago";
            }
        }
        //Months
        else if (months <= 12) {
            if (months == 1) {
                return "a month ago";
            } else {
                return months + " months ago";
            }
        }
        //Years
        else {
            if (years == 1) {
                return "one year ago";
            } else {
                return years + " years ago";
            }
        }
    }
    public static void showToast(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }
    public static void showToastLong(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }
    public static String getCurrentTime() {

        Date today = Calendar.getInstance().getTime();
        return timeFormat.format(today);
    }

    public static String getCurrentDate() {

        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }

    public static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
    public static boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email)) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        return false;
    }

    public static boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return Patterns.PHONE.matcher(phoneNumber).matches();
        }
        return false;
    }

    public static boolean isValidPassword(final String password) {

        if (password.length() > 4) {
            return true;
        }
        return false;
    }
    public static  boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public static void showDialog(String title, String message, Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(
                context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.tick);


        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // Write your code here to execute after dialog closed
                // Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public static void setBlinkingText(TextView textView) {
        mAnim = new AlphaAnimation(0.0f, 1.0f);
        mAnim.setDuration(500); // Time of the blink
        mAnim.setStartOffset(10);
        mAnim.setRepeatMode(Animation.REVERSE);
        mAnim.setRepeatCount(Animation.INFINITE);
        textView.startAnimation(mAnim);
    }
    public static void removeBlinkingText(TextView textView) {
        textView.clearAnimation();
    }

    public static boolean isDouble(String strDouble){
        String decimalPattern = "([0-9]*)\\.([0-9]*)";
        String number=strDouble;
        boolean match = Pattern.matches(decimalPattern, number);
        System.out.println(strDouble+ " isDouble : "+match); //if true then decimal else not
        return match;
    }

    /*public static void showCustomToast(Context mcontext,String message)
    {
        Context context = mcontext.getApplicationContext();
        // Create layout inflator object to inflate toast.xml file
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE );

        // Call toast.xml file for toast layout
        View toastView = inflater.inflate(R.layout.custom_toast, null);
        TextView text = (TextView) toastView.findViewById(R.id.tvCustomToast);
        text.setText(message);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.show();
    }*/
}
