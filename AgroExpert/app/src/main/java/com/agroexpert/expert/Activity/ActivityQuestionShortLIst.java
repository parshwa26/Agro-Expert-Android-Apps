package com.agroexpert.expert.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.agroexpert.expert.Adapter.AdapterCategory;
import com.agroexpert.expert.Adapter.AdapterMyQuestionActivity;
import com.agroexpert.expert.Adapter.AdepterQuestionShortList;
import com.agroexpert.expert.Bean.BeanCategory;
import com.agroexpert.expert.Bean.BeanSelectMyQuestion;
import com.agroexpert.expert.R;
import com.agroexpert.expert.Utils.AppConstants;
import com.agroexpert.expert.Utils.ContextWrapper;
import com.agroexpert.expert.Utils.Utility;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.refactor.lib.colordialog.PromptDialog;

import static com.agroexpert.expert.Activity.ActivityQuestionsList.beanSelectAnswered;
import static com.agroexpert.expert.Activity.ActivityQuestionsList.beanSelectUnAnswered;
import static com.agroexpert.expert.Activity.ActivityQuestionsList.beanStarredQuestion;
import static com.agroexpert.expert.Activity.WholeQuestion.beanSelectSearch;

public class ActivityQuestionShortLIst extends Activity implements SwipeRefreshLayout.OnRefreshListener{

    static Activity activity;
    ListView lv_question;
    static  String Flag;
    static SwipeRefreshLayout swipeRefreshLayout;
    static RecyclerView rv_home_activity;
    LinearLayout lnback;
    TextView tv_header;
    Spinner spn_category;
    ArrayList<BeanCategory> beanCategories = new ArrayList<>();
    String c_id,category_name;
    AdepterQuestionShortList adepterQuestionShortList;
    AdapterCategory adapterCategory;
    static ProgressDialog pd;

    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = ContextWrapper.wrap(newBase, new Locale(Utility.get_lang(newBase)));
        super.attachBaseContext(context);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_short_list);
        lv_question = findViewById(R.id.lv_question);
        lnback = findViewById(R.id.lnback);
        tv_header = findViewById(R.id.tv_header);
        activity = this;
        pd = Utility.getDialog(activity);
        Bundle bundle = getIntent().getExtras();
        Flag = bundle.getString("Flag");


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        lnback = (LinearLayout) findViewById(R.id.lnback);
        tv_header = findViewById(R.id.tv_header);

        lnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        rv_home_activity.setLayoutManager(llm);
//
        swipeRefreshLayout.setOnRefreshListener(ActivityQuestionShortLIst.this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                    }
                                }
        );


        if (Flag.equalsIgnoreCase("PQ")) {
            tv_header.setText(getApplicationContext().getResources().getString(R.string.pending_questions));
            // get_question();
            get_unanswered_questions();
        } else if (Flag.equalsIgnoreCase("AQ")) {
            tv_header.setText(getApplicationContext().getResources().getString(R.string.answered_questions));
            get_answered_questions();
        }
        else if(Flag.equalsIgnoreCase("SQ")){
            tv_header.setText(getApplicationContext().getResources().getString(R.string.search_question));
            spn_category.setVisibility(View.VISIBLE);
            getCatList();
            get_search_questions(c_id);
    }


        else if (Flag.equalsIgnoreCase("STQ")) {
            tv_header.setText(getApplicationContext().getResources().getString(R.string.starred_questions));
            get_starred_questions();
          /*  adapter = new AdapterMyQuestionActivity(beanStarredQuestion,getApplicationContext(),Flag);
            rv_home_activity.setAdapter(adapter);*/
        } else if (Flag.equalsIgnoreCase("FAQ")) {
            tv_header.setText(getApplicationContext().getResources().getString(R.string.FAQs));
            //get_faq_questions();


        }
    }


        @Override
        public void onRefresh () {
            if (Flag.equalsIgnoreCase("UQ")) {
              //  get_question();
            } else if (Flag.equalsIgnoreCase("AQ")) {
                get_answered_questions();
            } else if (Flag.equalsIgnoreCase("SQ")) {
                // get_search_questions();
                swipeRefreshLayout.setRefreshing(false);
            } else if (Flag.equalsIgnoreCase("STQ")) {
                get_starred_questions();
                swipeRefreshLayout.setRefreshing(false);

            } else if (Flag.equalsIgnoreCase("FAQ")) {
              //  get_faq_questions();
            }
        }

    public void onBackPressed() {
        finish();
    }


        private void get_answered_questions () {
            swipeRefreshLayout.setRefreshing(true);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstants.exansweredquestion, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    pd.show();
                    Log.e("Answered_Question", "" + response);

                    try {
                        JSONObject obj = new JSONObject(response);
                        JSONArray jsonArray = obj.getJSONArray("answerquestion");

                        if (jsonArray != null) {

                            beanSelectAnswered.clear();
                            beanSelectAnswered.addAll((Collection<? extends BeanSelectMyQuestion>) new Gson().fromJson(String.valueOf(jsonArray), new TypeToken<ArrayList<BeanSelectMyQuestion>>() {
                            }.getType()));
                            adepterQuestionShortList = new AdepterQuestionShortList(beanSelectAnswered, ActivityQuestionShortLIst.this,Flag);
                            lv_question.setAdapter(adepterQuestionShortList);
                        }
                        //rv_home_activity.setAdapter(adapter);
                        pd.dismiss();

                          swipeRefreshLayout.setRefreshing(false);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Select_Question", "Error at sign in : " + error.getMessage());
                    pd.dismiss();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("lid", Utility.get_uid(activity.getApplicationContext()));
                    map.put("key", AppConstants.key);
                    map.put("lang_flag", Utility.get_lang_no(getApplicationContext()));


                    Log.e("SHA-1 Key", AppConstants.key);
                    Log.e("lid", "" + Utility.get_uid(activity.getApplicationContext()));

                    return map;
                }
            };

            Utility.SetvollyTime30Sec(stringRequest);
            Utility.getRequestQueue(activity.getApplicationContext()).add(stringRequest);


        }

    public void get_unanswered_questions(){

        swipeRefreshLayout.setRefreshing(true);
        //   pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstants.exunansweredquestion, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                pd.show();

                Log.e("UnAnswered_Question","" + response);

                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("unanswerquestion");

                    if (jsonArray != null) {

                        beanSelectUnAnswered.clear();
                        beanSelectUnAnswered.addAll((Collection<? extends BeanSelectMyQuestion>) new Gson().fromJson(String.valueOf(jsonArray), new TypeToken<ArrayList<BeanSelectMyQuestion>>(){}.getType()));

                        adepterQuestionShortList = new AdepterQuestionShortList(beanSelectUnAnswered, ActivityQuestionShortLIst.this, Flag);
                        lv_question.setAdapter(adepterQuestionShortList);
                    }


                    //rv_home_activity.setAdapter(adapter);
                    pd.dismiss();

                   swipeRefreshLayout.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Select_Question", "Error at sign in : " + error.getMessage());
                pd.dismiss();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("lid", Utility.get_uid(activity.getApplicationContext()));
                map.put("key",AppConstants.key);
                map.put("lang_flag",Utility.get_lang_no(getApplicationContext()));


                Log.e("SHA-1 Key",AppConstants.key);
                Log.e("lid",""+ Utility.get_uid(activity.getApplicationContext()));

                return map;
            }
        };

        Utility.SetvollyTime30Sec(stringRequest);
        Utility.getRequestQueue(activity.getApplicationContext()).add(stringRequest);


    }
    public void get_search_questions(final String cid){

        swipeRefreshLayout.setRefreshing(true);
        //   pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstants.searchquestion, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Search_Questions","" + response);

                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("searchquestion");
                    //Log.e("JSON Array",status.toString());
                    //String status=obj.getString("status").toString();

                    //Log.e("Status: ",status);

                    if(jsonArray.length()==0){
                        showPromptDlg();
                    }

                    if (jsonArray != null) {

                        beanSelectSearch.clear();
                        beanSelectSearch.addAll((Collection<? extends BeanSelectMyQuestion>) new Gson().fromJson(String.valueOf(jsonArray), new TypeToken<ArrayList<BeanSelectMyQuestion>>(){}.getType()));

                        adepterQuestionShortList = new AdepterQuestionShortList(beanSelectSearch,activity,Flag);
                        lv_question.setAdapter(adepterQuestionShortList);
                    }
                    //rv_home_activity.setAdapter(adapter);
                    //pd.dismiss();

                    swipeRefreshLayout.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Select_Question", "Error at sign in : " + error.getMessage());
                pd.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("lid", Utility.get_uid(activity.getApplicationContext()));
                map.put("cid",cid);
                map.put("key",AppConstants.key);
                map.put("lang_flag",Utility.get_lang_no(getApplicationContext()));

                Log.e("SHA-1 Key",AppConstants.key);
                Log.e("lid",""+ Utility.get_uid(activity.getApplicationContext()));
                Log.e("cid",""+cid);


                return map;
            }
        };

        Utility.SetvollyTime30Sec(stringRequest);
        Utility.getRequestQueue(activity.getApplicationContext()).add(stringRequest);


    }

    public void getCatList(){

        pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstants.category, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("RESPONSE","" + response);

                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("category");

                    if (jsonArray != null) {

                        beanCategories.clear();

                        beanCategories.add(new BeanCategory("0","Select Category"));

                        beanCategories.addAll((Collection<? extends BeanCategory>) new Gson().fromJson(String.valueOf(jsonArray), new TypeToken<ArrayList<BeanCategory>>(){}.getType()));

                        adapterCategory = new AdapterCategory(beanCategories,getApplicationContext());
                        spn_category.setAdapter(adapterCategory);
                        pd.dismiss();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    pd.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.toString());
                pd.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("lid",Utility.get_uid(getApplicationContext()));
                map.put("key",AppConstants.key);
                map.put("lang_flag",Utility.get_lang_no(getApplicationContext()));


                Log.e("SHA-1 Key",AppConstants.key);
                Log.e("lid",Utility.get_uid(getApplicationContext()));
                return map;
            }
        };

        Utility.SetvollyTime30Sec(stringRequest);
        Utility.getRequestQueue(getApplicationContext()).add(stringRequest);

    }


    public void get_starred_questions(){

        swipeRefreshLayout.setRefreshing(true);
        //   pd.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstants.starredlist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Starred_Question","" + response);

                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonArray = obj.getJSONArray("answerquestion");

                    if (jsonArray != null) {

                        beanStarredQuestion.clear();
                        beanStarredQuestion.addAll((Collection<? extends BeanSelectMyQuestion>) new Gson().fromJson(String.valueOf(jsonArray), new TypeToken<ArrayList<BeanSelectMyQuestion>>(){}.getType()));

                        adepterQuestionShortList = new AdepterQuestionShortList(beanStarredQuestion, ActivityQuestionShortLIst.this, Flag);
                        lv_question.setAdapter(adepterQuestionShortList);
//                        adapter = new AdapterMyQuestionActivity(beanStarredQuestion,activity,Flag);
//                        rv_home_activity.setAdapter(adapter);
                    }
                    //rv_home_activity.setAdapter(adapter);
                    //pd.dismiss();

                    swipeRefreshLayout.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Select_Question", "Error at sign in : " + error.getMessage());
                pd.dismiss();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String, String>();
                map.put("lid", Utility.get_uid(activity.getApplicationContext()));
                map.put("key",AppConstants.key);
                map.put("lang_flag",Utility.get_lang_no(getApplicationContext()));

                Log.e("SHA-1 Key",AppConstants.key);
                Log.e("lid",""+ Utility.get_uid(activity.getApplicationContext()));



                return map;
            }
        };

        Utility.SetvollyTime30Sec(stringRequest);
        Utility.getRequestQueue(activity.getApplicationContext()).add(stringRequest);


    }
    private void showPromptDlg() {
        new PromptDialog(this)
                .setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
                .setAnimationEnable(true)
                .setTitleText("AGRO EXPERT")
                .setContentText("No Question")
                .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }

    }

