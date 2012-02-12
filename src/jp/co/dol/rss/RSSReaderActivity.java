package jp.co.dol.rss;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class RSSReaderActivity extends Activity implements OnClickListener, OnItemClickListener {
	
//	public static final String RSS_FEED_URL = "http://andante.in/i/feed/";
//	public static final String RSS_FEED_URL = "http://192.168.3.93/sample_rss.xml";
	public static final String RSS_FEED_URL = "http://sysdolphin.appspot.com/failure/xml";
	
	private ArrayList<Item> mItems;
	private RssListAdapter mAdapter;
	
	private final int REQUEST_CODE = 111;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		Button btn = (Button) this.findViewById(R.id.button1);
		btn.setOnClickListener(this);
		
		
		
		// �f�[�^�ǂݍ���
		readData();
    }
    
    // ���I��
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    	// TODO Auto-generated method stub
    	Item item = (Item)mItems.get(arg2);
    	Intent intent = new Intent(this, ItemDetailActivity.class);
    	intent.putExtra("ITEM", item);
    	startActivity(intent);
    }
    
    // �o�^�{�^��
    public void onClick(View v) {
    	Intent intent = new Intent(this, ItemInsertActivity.class);
    //	startActivity(intent);
    	startActivityForResult(intent, REQUEST_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	if (requestCode == REQUEST_CODE) {
    		
    	}
    	
    	if (resultCode == RESULT_OK) {
    //		recreate();
    		readData();
        }
    }
    
    // �f�[�^�ǂݍ���
    private void readData() {
    	
        mItems = new ArrayList<Item>();
		mAdapter = new RssListAdapter(this, mItems);
		
    	ListView listview = (ListView)findViewById(R.id.listView1);
		RssParserTask task = new RssParserTask(this, mAdapter,listview);
		task.execute(RSS_FEED_URL);
		listview.setOnItemClickListener(this);
		
		
    }
}