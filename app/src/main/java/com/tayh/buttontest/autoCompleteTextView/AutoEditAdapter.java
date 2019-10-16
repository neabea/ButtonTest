package com.tayh.buttontest.autoCompleteTextView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tayh.buttontest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZY
 * @time 2019/10/8
 */
public class AutoEditAdapter extends BaseAdapter implements Filterable {
    private ArrayFilter mFilter;
    private List<String> mList;
    private Context context;
    private List<String> mUnfilteredData = new ArrayList<>();
    /**
     * 输入的关键字
     **/
    private String indexStr;

    public AutoEditAdapter(List<String> mList, Context context) {
        this.mList = mList;
        mUnfilteredData.clear();
        mUnfilteredData.addAll(mList);
        this.context = context;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_auto_edit, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = view.findViewById(R.id.tv_title);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        String itemStr = mList.get(position);
        if (!TextUtils.isEmpty(indexStr) && itemStr.contains(indexStr)) {
            String strBefore = itemStr.substring(0, itemStr.indexOf(indexStr));
            String strAfter = itemStr.substring(itemStr.indexOf(indexStr) + indexStr.length());
            itemStr = strBefore + "<u><font color= 'red' type='bold'>" + indexStr + "</font></u>" + strAfter;
        }
        viewHolder.textView.setText(Html.fromHtml(itemStr));
        return view;
    }

    static class ViewHolder {
        public TextView textView;
    }

    private class ArrayFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults results = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                return results;
            }
            indexStr = constraint.toString();
            ArrayList<String> newData = new ArrayList<>();
            for (String data : mUnfilteredData) {
                if (data.contains(constraint)) {
                    newData.add(data);
                }
            }
            results.values = newData;
            results.count = newData.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList = (ArrayList) results.values;
            notifyDataSetChanged();
        }
    }

    public List<String> getFilterData() {
        return mList;
    }
}
