package com.example.de.taomi2.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.de.taomi2.Popupwindows.Popupwindows_fenlei;
import com.example.de.taomi2.R;
import com.example.de.taomi2.Util.Bimp;
import com.example.de.taomi2.Util.GraphicsBitmapUtils;
import com.example.de.taomi2.Util.PictureNarrowUtils;
import com.example.de.taomi2.mediachooser.MediaChooser;
import com.example.de.taomi2.mediachooser.activity.HomeFragmentActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 真爱de仙 on 2015/6/19.
 */
public class Activity_new_shop extends Activity {
    private Popupwindows_fenlei popupwindows_fenlei;
    ImageView tupian;
    TextView fenleiTxt;
    EditText name,jianjie;
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
        setContentView(R.layout.activity_new_shop);
        initLayout();
        initListennner();
        initParam();
        imageBroadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                Toast.makeText(Activity_new_shop.this, "Image SIZE :" + intent.getStringArrayListExtra("list").size(), Toast.LENGTH_SHORT).show();
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
    private void initListennner(){
        findViewById(R.id.relativeLayout32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaChooser.setSelectedMediaCount(0);
                MediaChooser.showOnlyImageTab();
                Intent intent = new Intent(Activity_new_shop.this, HomeFragmentActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.relativeLayout311).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.requestFocus();
                InputMethodManager imm6 = (InputMethodManager) name.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm6.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        });
        findViewById(R.id.relativeLayout33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jianjie.requestFocus();
                InputMethodManager imm6 = (InputMethodManager) jianjie.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm6.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        });
        findViewById(R.id.relativeLayout31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupwindows_fenlei = new Popupwindows_fenlei(Activity_new_shop.this, itemsOnClick3);
                popupwindows_fenlei.showAtLocation(Activity_new_shop.this.findViewById(R.id.uploadPictureLayout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置

            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_new_shop.this,Activity_shop.class));            }
        });

    }
    private View.OnClickListener itemsOnClick3 = new View.OnClickListener() {
        public void onClick(View v) {
            popupwindows_fenlei.dismiss();
            switch (v.getId()) {
                case R.id.textView43: {
                    fenleiTxt.setText("IT");
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
        fenleiTxt=(TextView)findViewById(R.id.textView78);
        tupian=(ImageView)findViewById(R.id.imageView59);
        name=(EditText)findViewById(R.id.editText91);
        jianjie=(EditText)findViewById(R.id.editText10);
    }
    private void initParam(){

    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("yunxingle");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            tupian.setImageBitmap((Bitmap) photo);
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
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}
