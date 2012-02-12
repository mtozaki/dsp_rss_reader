package jp.co.dol.rss;

import java.util.ArrayList;
import java.util.List;

import jp.co.dol.common.HttpConnect;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.EditText;

public class ItemInsertTask extends AsyncTask<String,Void,String> {

	private ItemInsertActivity mActivity;
	private ProgressDialog mProgressDialog;
	
	private String msg = "";
	public String getMsg() { return this.msg; }
	
	// �R���X�g���N�^
	public ItemInsertTask (ItemInsertActivity mActivity ) {
		this.mActivity = mActivity;
	}
	
	// �^�X�N�����s��������ɃR�[�������
	@Override
	protected void onPreExecute() {
		// �v���O���X�o�[��\������
	//	mProgressDialog = new ProgressDialog(mActivity);
	//	mProgressDialog.setMessage("Now Adding...");
	//	mProgressDialog.show();
	}
	
	// ���C���X���b�h��Ŏ��s�����
	@Override
	protected void onPostExecute(String str) {
	//	mProgressDialog.dismiss();
	//	mProgressDialog = null;
	}

	@Override
	protected String doInBackground(String... params) {
			
		EditText mNo = (EditText) this.mActivity.findViewById(R.id.editText1);
	    String no = mNo.getText().toString();
	    EditText mPostDate = (EditText) this.mActivity.findViewById(R.id.editText2);
	    String postDate = mPostDate.getText().toString();
	    
	    // Http�p�����[�^�쐬
	    List<NameValuePair> param = new ArrayList<NameValuePair>();
	    param.add(new BasicNameValuePair("no", no));
	    param.add(new BasicNameValuePair("postDate", postDate));
	
	    String res = HttpConnect.post(params[0], param);
	    this.msg = res;
		
		return null;
	}
}
