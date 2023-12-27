package president.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CustomListAdapter extends BaseAdapter {

    Context context;
    String[] nameArray;
    String[] imageArray;
    String[] descArray;
    SharedPreferences sp;

    public CustomListAdapter(Context context, String[] nameArray, String[] imageArray, String[] descArray) {
        this.context = context;
        this.nameArray = nameArray;
        this.imageArray = imageArray;
        this.descArray = descArray;
        sp = context.getSharedPreferences(ConstantSp.PREF,Context.MODE_PRIVATE);
    }

    @Override
    public int getCount() {
        return nameArray.length;
    }

    @Override
    public Object getItem(int i) {
        return nameArray[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view =  layoutInflater.inflate(R.layout.item_list,null);

        ImageView image = view.findViewById(R.id.item_list_image);
        TextView name = view.findViewById(R.id.item_list_name);

        name.setText(nameArray[i]);
        //image.setImageResource(imageArray[i]);
        Glide.with(context).load(imageArray[i]).placeholder(R.mipmap.ic_launcher).into(image);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new CommonMethod(context,nameArray[i]);
                sp.edit().putString(ConstantSp.PRODUCT_NAME,nameArray[i]).commit();
                sp.edit().putString(ConstantSp.PRODUCT_IMAGE,imageArray[i]).commit();
                sp.edit().putString(ConstantSp.PRODUCT_DESC,descArray[i]).commit();
                new CommonMethod(context, ProductDetailActivity.class);
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CommonMethod(context,"Image Clicked : "+nameArray[i]);
            }
        });

        return view;
    }
}
