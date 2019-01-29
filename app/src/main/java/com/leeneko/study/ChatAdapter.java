package com.leeneko.study;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class ChatAdapter extends BaseAdapter {
    private Context mContext;
    private int mLayout;
    private List<ChatData> mItems;
    private LayoutInflater mInflater;

    public ChatAdapter(Context context, int layout, List<ChatData> list) {
        this.mContext = context;
        this.mLayout = layout;
        this.mItems = list;
        this.mInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }

    public int getCount() { return mItems.size(); }
    public Object getItem(int position) { return mItems.get(position); }
    public long getItemId(int position) { return position; }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(mLayout, parent, false);
        }
        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvOther = convertView.findViewById(R.id.tvOther);
        TextView tvMy = convertView.findViewById(R.id.tvMy);

        // ChatData item = mitems.get(position);
        // tvTitle.setText( item.getTitle() );
        // tvId.setText(mItems.get(position).getTitle());

        tvId.setText( mItems.get(position).getId() );
        tvOther.setText( mItems.get(position).getOtherMsg() );
        tvMy.setText( mItems.get(position).getMyMsg() );

        return convertView;
    }
}
