package com.example.auth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<User> {

    private final Context context;
    private final List<User> userList;

    public CustomArrayAdapter(Context context, List<User> userList) {
        super(context, R.layout.list_item_layout, userList);
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_layout, parent, false);

        TextView textViewName = rowView.findViewById(R.id.txt);
        ImageView imageView = rowView.findViewById(R.id.img);

        // Set user's name
        textViewName.setText(userList.get(position).name);


        switch (userList.get(position).pic) {
            case 1:
                imageView.setImageResource(R.drawable.img1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.img2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.img3);
                break;
        }

        return rowView;
    }
}

