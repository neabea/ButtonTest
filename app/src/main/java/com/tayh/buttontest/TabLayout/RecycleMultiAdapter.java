package com.tayh.buttontest.TabLayout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

/**
 * @author LZY
 * @time 2019/4/24
 */
public class RecycleMultiAdapter extends BaseExpandableListAdapter {

    private String[] grouplist = new String[]{
            "神族","虫族","人族"
    };
    private String[][] childlist = new String[][]{
            {"狂战士","龙骑士","黑暗圣堂"},
            {"小狗","飞龙","自爆妃子"},
            {"步兵","伞兵","护士mm"}
    };

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
