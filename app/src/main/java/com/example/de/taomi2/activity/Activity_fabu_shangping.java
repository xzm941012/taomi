package com.example.de.taomi2.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.de.taomi2.Application.MyApplication;
import com.example.de.taomi2.Popupwindows.Popupwindows_fenlei;
import com.example.de.taomi2.R;
import com.example.de.taomi2.Util.Bimp;
import com.example.de.taomi2.Util.GraphicsBitmapUtils;
import com.example.de.taomi2.Util.PictureNarrowUtils;
import com.example.de.taomi2.Util.UploadFile;
import com.example.de.taomi2.View.ListViewForScrollView;
import com.example.de.taomi2.adapter.EditSeeAdapter;
import com.example.de.taomi2.http.Manage;
import com.example.de.taomi2.mediachooser.MediaChooser;
import com.example.de.taomi2.mediachooser.activity.HomeFragmentActivity;
import com.example.de.taomi2.obj.Label_edit;
import com.example.de.taomi2.obj.ShangPing;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class Activity_fabu_shangping extends Activity {
    private Popupwindows_fenlei popupwindows_fenlei;
    TextView fenleiTxt;
    Gson gson=new Gson();
    private ProgressDialog dialog;
    public EditSeeAdapter adapter;
    EditText jiageEdit,nameTxt;
    String names,fenleis,jiages;
    ListViewForScrollView editListview;
    List<Label_edit> edits=new ArrayList<>();
    BroadcastReceiver imageBroadcastReceiver;
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private byte[] photodata=null;
    public List<String> imageArray=new ArrayList<>();
    public List<String> imageArray2=new ArrayList<>();
    public File tempFile = new File(Environment.getExternalStorageDirectory(),
            getPhotoFileName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fabu_shangping);
        dialog = new ProgressDialog(Activity_fabu_shangping.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("加载中");
        dialog.setIndeterminate(false);
        dialog.setCanceledOnTouchOutside(false);
        initLayout();
        initListennner();
        initParam();
        MediaChooser.setSelectionLimit(1);
        imageBroadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                Toast.makeText(Activity_fabu_shangping.this, "Image SIZE :" + intent.getStringArrayListExtra("list").size(), Toast.LENGTH_SHORT).show();
                imageArray=(intent.getStringArrayListExtra("list"));
                if(imageArray.size()!=0) {
                    imageArray2.add(imageArray.get(0));
                    System.out.println("imageArray1:"+imageArray.size());
                    startPhotoZoom(Uri.fromFile(new File(imageArray.get(0))), 150);
                }
            }
        };

        IntentFilter imageIntentFilter = new IntentFilter(MediaChooser.IMAGE_SELECTED_ACTION_FROM_MEDIA_CHOOSER);
        registerReceiver(imageBroadcastReceiver, imageIntentFilter);
    }

    // 使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
    private void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 400);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
    private View.OnClickListener itemsOnClick3 = new View.OnClickListener() {
        public void onClick(View v) {
            popupwindows_fenlei.dismiss();
            switch (v.getId()) {
                case R.id.textView43: {
                    fenleiTxt.setText("零食");
                    break;
                }
                case R.id.textView44: {
                    fenleiTxt.setText("美食");
                    break;

                }
                case R.id.textView45: {
                    fenleiTxt.setText("手工");
                    break;

                }
                case R.id.textView46: {
                    fenleiTxt.setText("运动");
                    break;

                }
                case R.id.textView47: {
                    fenleiTxt.setText("艺术");
                    break;
                }
                case R.id.textView48: {
                    fenleiTxt.setText("舞蹈");
                    break;
                }
                case R.id.textView66: {
                    fenleiTxt.setText("音乐");
                    break;
                }
                case R.id.textView67: {
                    fenleiTxt.setText("生活");
                    break;
                }
                case R.id.textView68: {
                    fenleiTxt.setText("学习");
                    break;
                }
                case R.id.textView71: {
                    fenleiTxt.setText("其他");
                    break;
                }
                default:
                    break;
            }
        }
    };
    private void initLayout(){
        nameTxt=(EditText)findViewById(R.id.editText39);
        fenleiTxt=(TextView)findViewById(R.id.textView149);
        jiageEdit=(EditText)findViewById(R.id.editText8);
        editListview=(ListViewForScrollView)findViewById(R.id.listView2);
    }
    private void initListennner(){
        findViewById(R.id.textView143).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names=nameTxt.getText().toString().trim();
                fenleis=fenleiTxt.getText().toString().trim();
                jiages=jiageEdit.getText().toString().trim();
                imageArray=imageArray2;
                if(imageArray.size()==0){
                    Toast.makeText(Activity_fabu_shangping.this,"选择一张封面",Toast.LENGTH_SHORT).show();
                }
                if(names.equals("")){
                    Toast.makeText(Activity_fabu_shangping.this,"请输入商品名",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(fenleis.equals("")){
                    Toast.makeText(Activity_fabu_shangping.this,"请选择分类",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(jiages.equals("")){
                    Toast.makeText(Activity_fabu_shangping.this,"价格呢！！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (adapter.mItems.size()==0) {
                    Toast.makeText(Activity_fabu_shangping.this,"写一些简介吧",Toast.LENGTH_SHORT).show();
                    return ;
                }
                loading();
                MyTask myTask=new MyTask();
                myTask.execute();
            }
        });
        findViewById(R.id.background_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaChooser.setSelectedMediaCount(0);
                MediaChooser.showOnlyImageTab();
                Intent intent = new Intent(Activity_fabu_shangping.this, HomeFragmentActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.fenleilayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupwindows_fenlei = new Popupwindows_fenlei(Activity_fabu_shangping.this, itemsOnClick3);
                popupwindows_fenlei.showAtLocation(Activity_fabu_shangping.this.findViewById(R.id.uploadPictureLayout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置

            }
        });
        findViewById(R.id.relativeLayout69).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterReceiver(imageBroadcastReceiver);
                startActivityForResult(new Intent(Activity_fabu_shangping.this,Activity_edit.class),166);
            }
        });
        findViewById(R.id.relativeLayout26).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiageEdit.requestFocus();
                InputMethodManager imm6 = (InputMethodManager) jiageEdit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm6.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        });
        jiageEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        jiageEdit.setText(s);
                        jiageEdit.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    jiageEdit.setText(s);
                    jiageEdit.setSelection(2);
                }

                if (s.toString().startsWith("0")&& s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        jiageEdit.setText(s.subSequence(0, 1));
                        jiageEdit.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

        });

    }

    private void initParam(){
        adapter=new EditSeeAdapter(Activity_fabu_shangping.this,edits);
        editListview.setAdapter(adapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("yunxingle");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==999){
            Gson gson=new Gson();
            String result=data.getStringExtra("edit");
            adapter.mItems=gson.fromJson(result, new TypeToken<List<Label_edit>>() {}.getType());
            adapter.notifyDataSetChanged();
            //setListViewHeightBasedOnChildren(editListview);
            return;
        }
        switch (requestCode) {
            case PHOTO_REQUEST_CUT:
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }

    }
    private void setPicToView(Intent picdata) {
        if(picdata==null){
            return;
        }
        System.out.println("imageArray4:"+imageArray.size());
        Bundle bundle = picdata.getExtras();
        if (bundle != null) {
            System.out.println("imageArray5:"+imageArray.size());
            //Bitmap photo = bundle.getParcelable("data");
            Bitmap photo=null;
            photo = Bimp.getimage(tempFile.getAbsolutePath());
            String newStr = tempFile.getAbsolutePath().substring(tempFile.getAbsolutePath().lastIndexOf("/") + 1, tempFile.getAbsolutePath().lastIndexOf("."));
            PictureNarrowUtils.saveBitmap2(photo, "" + newStr);
            photodata = GraphicsBitmapUtils.Bitmap2Bytes(photo);
            System.out.println("imageArray6:"+imageArray.size());
            Boolean isUploadSuccess;
            ((ImageView)findViewById(R.id.background_img)).setImageBitmap((Bitmap)photo);
            findViewById(R.id.textView225).setVisibility(View.GONE);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(imageBroadcastReceiver);
    }
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter imageIntentFilter = new IntentFilter(MediaChooser.IMAGE_SELECTED_ACTION_FROM_MEDIA_CHOOSER);
        registerReceiver(imageBroadcastReceiver, imageIntentFilter);
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    class MyTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置显示格式
            String nowTime="";
            nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
            String upstr=tempFile.getAbsolutePath().substring(tempFile.getAbsolutePath().lastIndexOf("/") + 1, tempFile.getAbsolutePath().lastIndexOf("."));
            UploadFile.uploadFileStruct(PictureNarrowUtils.SDPATH + upstr + ".JPEG", "imageid" + MyApplication.user.getId() + "time" + nowTime  + ".JPEG");
            StringBuffer fenmianUrl=new StringBuffer();
            fenmianUrl.append("imageid" + MyApplication.user.getId() + "time" + nowTime  + ".JPEG");
            List<Label_edit> edits2=adapter.mItems;
            int i=1;
            for(Label_edit label_edit:edits2){
                if(label_edit.getType()==1){
                    File mediaFile = new File(label_edit.getBitmapPath());
                    upstr=mediaFile.getAbsolutePath().substring(mediaFile.getAbsolutePath().lastIndexOf("/") + 1, mediaFile.getAbsolutePath().lastIndexOf("."));
                    UploadFile.uploadFileStruct(PictureNarrowUtils.SDPATH + upstr+ ".JPEG", "imageid"+MyApplication.user.getId()+"time"+nowTime+i+".JPEG");
                    label_edit.setUrlPath("imageid"+MyApplication.user.getId()+"time"+nowTime+i+".JPEG"+"");
                    i++;
                }
            }
            ShangPing sp=new ShangPing();
            sp.setName(names);
            sp.setType(fenleis);
            sp.setBuyNum(0);
            sp.setImageUrl(fenmianUrl.toString());
            sp.setJiage(jiages);
            sp.setJianJie(gson.toJson(edits2));
            sp.setUserid(MyApplication.user.getId());
            sp.setZhuangtai(1);
            sp.setSchool(MyApplication.school);
            String result=null;
            try {
                result=Manage.addShangping(sp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
        protected void onPostExecute(String result){
            dialog.dismiss();
            if(!result.equals("")){
                Toast.makeText(Activity_fabu_shangping.this,"发布成功",Toast.LENGTH_SHORT).show();
                ((MyApplication)getApplication()).setEdits(new ArrayList<Label_edit>());
                finish();
            }else{
                Toast.makeText(Activity_fabu_shangping.this,"发布异常",Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected void onPreExecute(){
            dialog.show();
        }
    }
    public void loading() {
        /*
        for (int i=0;i<photoList.size();i++) {
            try {
                String path=photoList.get(i).getPhotoPath();
                Bitmap bm = Bimp.revitionImageSize(path);
                String newStr = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
                PictureNarrowUtils.saveBitmap(bm, "" + newStr);

                //upLoadhand.sendEmptyMessage(0x8);
            } catch (IOException e) {
                DialogUtil.showDialog(Activity_fabu_huodong_step3.this,e.toString(), true);
            }
        }
        */
        for (int i=0;i<adapter.mItems.size();i++) {
            try {
                if(adapter.mItems.get(i).getType()==1) {
                    String path = adapter.mItems.get(i).getBitmapPath();
                    Bitmap bm = Bimp.revitionImageSize(path);
                    String newStr = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
                    PictureNarrowUtils.saveBitmap(bm, "" + newStr);
                }

                //upLoadhand.sendEmptyMessage(0x8);
            } catch (IOException e) {
                Toast.makeText(Activity_fabu_shangping.this,"保存图片失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
