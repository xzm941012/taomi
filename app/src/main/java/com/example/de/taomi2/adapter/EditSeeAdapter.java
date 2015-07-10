package com.example.de.taomi2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.de.taomi2.R;
import com.example.de.taomi2.Util.Bimp;
import com.example.de.taomi2.obj.Label_edit;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 真爱de仙 on 2015/5/13.
 */
public class EditSeeAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    EditSeeAdapter editAdapter;
    public List<Label_edit> mItems;
    private DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory().cacheOnDisc().build();
    protected ImageLoader imageLoader = ImageLoader.getInstance();


    private Context context;

    public EditSeeAdapter(Context context, List<Label_edit> data) {
        editAdapter=this;
        this.mInflater = LayoutInflater.from(context);
        this.mItems = data;
        this.context=context;

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Label_edit getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Label_edit item = getItem(position);
        convertView = mInflater.inflate(R.layout.label_see_edit, null);
        final TextView content=((TextView)convertView.findViewById(R.id.textView79));
        final ImageView tupian=((ImageView)convertView.findViewById(R.id.imageView81));
        if(item.getType()==0){
            content.setVisibility(View.VISIBLE);
            tupian.setVisibility(View.GONE);

            if(item.getWenzi()!=null){
                content.setText(item.getWenzi());
            }
            /*
            convertView.findViewById(R.id.editLayout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    content.requestFocus();
                    InputMethodManager imm6 = (InputMethodManager) content.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm6.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                }
            });
*/
        }else{
            content.setVisibility(View.GONE);
            tupian.setVisibility(View.VISIBLE);
            if(item.getBitmapPath()!=null) {
                Bitmap bm = Bimp.getimage(item.getBitmapPath());
                tupian.setImageBitmap(bm);
            }
        }
        return convertView;
    }

}
