package jp.co.dol.rss;

import jp.co.dol.common.ConstMaster;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ItemInsertActivity extends Activity implements OnClickListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_insert);
		
		Button btn = (Button) this.findViewById(R.id.button1);
		btn.setOnClickListener(this);
		
		
	}
	
    // ìoò^É{É^Éì
    public void onClick(View v) {
    	
    //	ProgressDialog mProgressDialog = new ProgressDialog(this);
	//	mProgressDialog.setMessage("Now Loading...");
	//	mProgressDialog.show();
    	
    	// ÉfÅ[É^ÇÃìoò^
    	ItemInsertTask task = new ItemInsertTask(this);
		task.execute(ConstMaster.SERVER_URL);
		String msg = task.getMsg();
		
		while(true) {
			if (msg.equals("")) {
				try {
					Thread.sleep(10);
				} catch (Exception e) {	
				}
				msg = task.getMsg();
			} else {
				break;
			}
		}
		
	//	mProgressDialog.dismiss();
	//	mProgressDialog = null;
    	
		Intent intent = new Intent();
        intent.putExtra("msg", msg);
        setResult(RESULT_OK, intent);
        
		finish();
    }
}
