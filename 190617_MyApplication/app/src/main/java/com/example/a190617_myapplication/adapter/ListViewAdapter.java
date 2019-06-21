package com.example.a190617_myapplication.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a190617_myapplication.R;
import com.example.a190617_myapplication.activity.ListViewItem;
import com.example.a190617_myapplication.activity.NetworkUtilForJoin;
import com.example.a190617_myapplication.activity.PHPReqForTop;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements Filterable {
    //Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    //필터링 된 결과 데이터를 저장하기 위한 ArrayList. 최초에는 전체 리스트 보유.
    private ArrayList<ListViewItem> filteredItemList = listViewItemList;

   Filter listFilter;

    //ListViewAdapter의 생성자
    public ListViewAdapter(){

    }

    //Adapter에 사용되는 데이터의 개수를 리턴
    @Override
    public int getCount(){
        return listViewItemList.size();
    }

    //position에 위치한 데이터 화면에 출력하는데 사용될 view를 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();

        NetworkUtilForJoin.setNetworkPolicy();

        //"ListViewItem"Layout을 inflate하여 convertView참조 획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_listview, parent, false);
        }
        //화면에 표시될 View(Layout이 inflate된)로부터 위젯에 대한 참조 획득
        ImageButton iconImageView = (ImageButton) convertView.findViewById(R.id.custom_image1) ;
        TextView titleTextView = (TextView) convertView.findViewById(R.id.custom_name1) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.custom_desc1) ;

        //Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = filteredItemList.get(position);

        //아이템 내 각 위젯에 데이터 반영
        iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getTitle());
        descTextView.setText(listViewItem.getDesc());

        //리스트뷰 클릭 이벤트
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    PHPReqForTop request = new PHPReqForTop("http://192.168.23.95/android/url.php");
                    request.PhPtest(String.valueOf(filteredItemList.get(pos).getTitle()),String.valueOf(filteredItemList.get(pos).getDesc()));
                    Toast.makeText(context, (filteredItemList.get(pos).getTitle()) + " 상품이 북마크에 등록되었습니다.", Toast.LENGTH_SHORT).show();
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
        });

        return convertView;
    }
    //지정한 위치(position)에 있는 데이터와 관계된 아이템 (row)의 ID를 리턴
    @Override
    public long getItemId(int position){
        return position;
    }
    //지정한 위치(position)에 있는 데이터를 리턴
    @Override
    public Object getItem(int position){
        return filteredItemList.get(position);
    }
    //아이템 데이터 추가를 위한 함수
    public void addItem(Drawable icon, String title, String desc){
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);

        listViewItemList.add(item);
    }
    @Override
    public Filter getFilter(){
        if(listFilter == null){
            listFilter = new ListFilter();
        }
        return listFilter;
    }
    private  class ListFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint){
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0){
                results.values = listViewItemList;
                results.count = listViewItemList.size();
            }else{
                ArrayList<ListViewItem> itemList = new ArrayList<ListViewItem>();

                for(ListViewItem item : listViewItemList){
                    if (item.getTitle().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                            item.getDesc().toUpperCase().contains(constraint.toString().toUpperCase()))
                    {
                        itemList.add(item) ;
                    }
                }

                results.values = itemList ;
                results.count = itemList.size() ;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, Filter.FilterResults results) {

            // update listview by filtered data list.
            filteredItemList = (ArrayList<ListViewItem>) results.values ;

            // notify
            if (results.count > 0) {
                notifyDataSetChanged() ;
            } else {
                notifyDataSetInvalidated() ;
            }
        }
    }
}
