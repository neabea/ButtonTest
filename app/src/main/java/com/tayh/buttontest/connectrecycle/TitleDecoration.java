package com.tayh.buttontest.connectrecycle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tayh.buttontest.R;

import java.util.List;

/**
 * @author LZY
 * @time 2019/5/5
 */
public class TitleDecoration extends RecyclerView.ItemDecoration {

    private List<RightBean> list;
    private LayoutInflater mInflater;
    Context context;
    private TitleInterface titleInterface;
    boolean leftClick;
    int mTitleHeight;

    public TitleDecoration(Context context, List<RightBean> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
    }

    //判断是否从左边列表框点击过来的
    public void setLeftClick(boolean leftClick) {
        this.leftClick = leftClick;
    }

    public void setListener(TitleInterface titleInterface) {
        this.titleInterface = titleInterface;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        int position = ((RecyclerView.LayoutParams)view.getLayoutParams()).getViewAdapterPosition();
//        if (position > -1) {
//            if (position == 0) {//等于0肯定要有title的
//                outRect.set(0, 100, 0, 0);
//            } else {//其他的通过判断
//                if (0 != list.get(position).getType() && list.get(position).getType()!=list.get(position - 1).getType()) {
//                    outRect.set(0, 200, 0, 0);//不为空 且跟前一个tag不一样了，说明是新的分类，也要title
//                } else {
//                    outRect.set(0, 0, 0, 0);
//                }
//            }
//        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
//        int position = ((RecyclerView.LayoutParams) parent.getLayoutParams()).getViewAdapterPosition();
        int position = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        View child = parent.findViewHolderForLayoutPosition(position).itemView;
        Log.i("zeyu", "position:" + position);
        Paint paint = new Paint();
        Rect outRect = new Rect();
        boolean isTranslate = false;//canvas是否平移的标志
        if (!leftClick) {
            titleInterface.setSelected(position);
        }
//        c.save();
//        int height = child.getHeight();
//        c.translate(0,height);
//        c.restore();
        if ((list.size() > position + 1) && (list.get(position).getType() == 1 && list.get(position + 1).getType() == 0)) {
            Log.i("zeyu", "child.getHeight():" + child.getHeight()+"--:"+ child.getTop()+"--:"+mTitleHeight);
            if (child.getHeight() + child.getTop() < mTitleHeight) {
//                c.save();
                isTranslate = true;
                int height = child.getHeight() + child.getTop() - mTitleHeight;
                Log.i("zeyu", "height:" + height);
                c.translate(0, height);
            }
        }
        drawHeader(parent, position, c);
        if (isTranslate) {
//            c.restore();
        }
//        c.drawRect(0,0,0,0,paint);
//        c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getRight() - parent.getPaddingRight(), parent.getPaddingTop() + 200, paint);
//        if (position > -1) {
//            if (position == 0) {//等于0肯定要有title的
//                outRect.set(0, 100, 0, 0);
//            } else {//其他的通过判断
//                if (0 != list.get(position).getType() && list.get(position).getType()!=list.get(position - 1).getType()) {
//
//                    outRect.set(0, 200, 0, 0);//不为空 且跟前一个tag不一样了，说明是新的分类，也要title
//                } else {
//                    outRect.set(0, 0, 0, 0);
//                }
//            }
//        }
    }


    private void drawHeader(RecyclerView parent, int pos, Canvas canvas) {
        View topTitleView = mInflater.inflate(R.layout.right_item_title, parent, false);
        TextView tvTitle = (TextView) topTitleView.findViewById(R.id.tv_title_right);
        if (list.get(pos).getType() == 0) {
            tvTitle.setText(list.get(pos).getTitleBean().getTitle());
        } else {
            while (pos >= 0) {
                if (list.get(pos).getTitleBean() != null) {
                    tvTitle.setText(list.get(pos).getTitleBean().getTitle());
                    break;
                }
                pos--;
            }
        }
        //绘制title开始
        int toDrawWidthSpec;//用于测量的widthMeasureSpec
        int toDrawHeightSpec;//用于测量的heightMeasureSpec
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) topTitleView.getLayoutParams();
        if (lp == null) {
            lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);//这里是根据复杂布局layout的width height，new一个Lp
            topTitleView.setLayoutParams(lp);
        }
        topTitleView.setLayoutParams(lp);
        if (lp.width == ViewGroup.LayoutParams.MATCH_PARENT) {
            //如果是MATCH_PARENT，则用父控件能分配的最大宽度和EXACTLY构建MeasureSpec
            toDrawWidthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), View.MeasureSpec.EXACTLY);
        } else if (lp.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            //如果是WRAP_CONTENT，则用父控件能分配的最大宽度和AT_MOST构建MeasureSpec
            toDrawWidthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), View.MeasureSpec.AT_MOST);
        } else {
            //否则则是具体的宽度数值，则用这个宽度和EXACTLY构建MeasureSpec
            toDrawWidthSpec = View.MeasureSpec.makeMeasureSpec(lp.width, View.MeasureSpec.EXACTLY);
        }
        //高度同理
        if (lp.height == ViewGroup.LayoutParams.MATCH_PARENT) {
            toDrawHeightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom(), View.MeasureSpec.EXACTLY);
        } else if (lp.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            toDrawHeightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom(), View.MeasureSpec.AT_MOST);
        } else {
            toDrawHeightSpec = View.MeasureSpec.makeMeasureSpec(lp.height, View.MeasureSpec.EXACTLY);
        }
        //依次调用 measure,layout,draw方法，将复杂头部显示在屏幕上
        topTitleView.measure(toDrawWidthSpec, toDrawHeightSpec);
        topTitleView.setBackgroundColor(context.getResources().getColor(R.color.white));
        topTitleView.layout(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getPaddingLeft() + topTitleView.getMeasuredWidth(), parent.getPaddingTop() + topTitleView.getMeasuredHeight());
        topTitleView.draw(canvas);//Canvas默认在视图顶部，无需平移，直接绘制
        //绘制title结束

    }
}
