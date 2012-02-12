package jp.co.dol.rss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ItemDetailActivity extends Activity {

	private TextView mNo;
	private TextView mPostDate;
	private TextView mReleaseDate;
	private TextView mDiscoveryEmp;
	private TextView mTroubleSpots;
	private TextView mFailureBody;
	private TextView mTroubleCause;
	private TextView mRepairBody;
	private TextView mResults;
	private TextView mRepairSrc;
	private TextView mRemarks;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_detail);

		Intent intent = getIntent();
		
		Item item = (Item)intent.getSerializableExtra("ITEM");
		
//		String pjNo = item.getPjNo().toString();
		String no = item.getNo().toString();
		String postDate = item.getPostDate().toString();
//		String title = item.getTitle().toString();
//		String supportKbn = item.getSupportKbn().toString();
//		String priorityLv = item.getPriorityLv().toString();
//		String importantLv = item.getImportantLv().toString();
//		String startDate = item.getStartDate().toString();
//		String endDate = item.getEndDate().toString();
		String releaseDate = item.getReleaseDate().toString();
		String discoveryEmp = item.getDiscoveryEmp().toString();
//		String supportEmp = item.getSupportEmp().toString();
		String troubleSpots = item.getTroubleSpots().toString();
		String failureBody = item.getFailureBody().toString();
		String troubleCause = item.getTroubleCause().toString();
//		String troubleCauseKbn = item.getTroubleCauseKbn().toString();
		String repairBody = item.getRepairBody().toString();
		String results = item.getResults().toString();
		String repairSrc = item.getRepairSrc().toString();
		String remarks = item.getRemarks().toString();
//		String browser = item.getBrowser().toString();
		
		mNo = (TextView) findViewById(R.id.tvNo);
		mNo.setText(no);
		mPostDate = (TextView) findViewById(R.id.tvPostDate);
		mPostDate.setText(postDate);
		mReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
		mReleaseDate.setText(releaseDate);
		mDiscoveryEmp = (TextView) findViewById(R.id.tvDiscoveryEmp);
		mDiscoveryEmp.setText(discoveryEmp);
		
		mTroubleSpots = (TextView) findViewById(R.id.tvTroubleSpots);
		mTroubleSpots.setText(troubleSpots);
		mFailureBody = (TextView) findViewById(R.id.tvFailureBody);
		mFailureBody.setText(failureBody);
		mTroubleCause = (TextView) findViewById(R.id.tvTroubleCause);
		mTroubleCause.setText(troubleCause);
		
		
	}
}
