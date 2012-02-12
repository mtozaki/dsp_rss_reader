package jp.co.dol.rss;

import java.util.List;  

import android.content.Context;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.ArrayAdapter;  
import android.widget.TextView;  

public class RssListAdapter extends ArrayAdapter<Item> {

	private LayoutInflater mInflater;
	private TextView mNo;
	private TextView mPostDate;
	private TextView mImportantLv;
	private TextView mTitle;

	public RssListAdapter(Context context, List<Item> objects) {
		super(context, 0, objects);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// 1�s���Ƃ̃r���[�𐶐�����
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (convertView == null) {
			view = mInflater.inflate(R.layout.itemlayout, null);
		}

		// ���ݎQ�Ƃ��Ă��郊�X�g�̈ʒu����Item���擾����
		Item item = this.getItem(position);
		
		if (item != null) {
			// Item����K�v�ȃf�[�^�����o���A���ꂼ��TextView�ɃZ�b�g����
			
			String no = item.getNo().toString();
			String postDate = item.getPostDate().toString();
			String importantLv = item.getImportantLv().toString();
			String title = item.getTitle().toString();
			
			mNo = (TextView) view.findViewById(R.id.tvNo);
			mNo.setText("No." + no);
			mPostDate = (TextView) view.findViewById(R.id.tvPostDate);
			mPostDate.setText(postDate);
			mImportantLv = (TextView) view.findViewById(R.id.tvImportantLv);
			mImportantLv.setText("�d�v�x:" + importantLv);
			
//			if ( Integer.parseInt(importantLv) > 6 )
	//			mImportantLv.setTextColor(50);
			
			mTitle = (TextView) view.findViewById(R.id.tvTitle);
			mTitle.setText(title);
		}
		
		return view;
	}
}
