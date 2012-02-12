package jp.co.dol.rss;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import jp.co.dol.common.ConstMaster;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Xml;
import android.widget.ListView;

public class RssParserTask extends AsyncTask<String, Integer, RssListAdapter> {
	private RSSReaderActivity mActivity;
	private RssListAdapter mAdapter;
	private ProgressDialog mProgressDialog;
	private ListView mListView;
	
	// �R���X�g���N�^
	public RssParserTask(RSSReaderActivity activity, RssListAdapter adapter,ListView _listView) {
		mActivity = activity;
		mListView = _listView;
		mAdapter = adapter;
	}
	
	// �^�X�N�����s��������ɃR�[�������
	@Override
	protected void onPreExecute() {
		// �v���O���X�o�[��\������
		mProgressDialog = new ProgressDialog(mActivity);
		mProgressDialog.setMessage("Now Loading...");
		mProgressDialog.show();
	}
	
	// �o�b�N�O���E���h�ɂ����鏈����S���B�^�X�N���s���ɓn���ꂽ�l�������Ƃ���
	@Override
	protected RssListAdapter doInBackground(String... params) {
		RssListAdapter result = null;
		try {
	//		System.setProperty("proxySet", "true");
	//		System.setProperty("http.proxyHost", "192.168.1.1");
	//		System.setProperty("http.proxyPort", "8080");
			
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ConstMaster.PROXY_SERVER, ConstMaster.PROXY_PORT));
			
			// HTTP�o�R�ŃA�N�Z�X���AInputStream���擾����
			URL url = new URL(params[0]);
			InputStream is = url.openConnection(proxy).getInputStream();
			result = parseXml(is);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// �����ŕԂ����l�́AonPostExecute���\�b�h�̈����Ƃ��ēn�����
		return result;
	}
	
	// ���C���X���b�h��Ŏ��s�����
	@Override
	protected void onPostExecute(RssListAdapter result) {
		mProgressDialog.dismiss();
		mProgressDialog = null;
		mListView.setAdapter(result);
	}
	
	// XML���p�[�X����
	public RssListAdapter parseXml(InputStream is) throws IOException, XmlPullParserException {
		//�p�[�T�[���쐬����
		XmlPullParser parser = Xml.newPullParser();
		try {
			//�p�[�T�[��inputStraem���Z�b�g����B�����URL��ǂݍ���ł���Stream
			parser.setInput(is, null);
			
			//�ǂݍ��݂̐i������������擾���ł���B�i�l��START�Ȃǂ����݂���B�j
			int eventType = parser.getEventType();
			Item currentItem = null;
			
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String tag = null;
				switch (eventType) {
					case XmlPullParser.START_TAG:
					tag = parser.getName().toLowerCase();

					if (tag.equals("item")) {
						currentItem = new Item();
					} else if (currentItem != null) {

						// �Č�No.
						if (tag.equals("pjno")) {
							currentItem.setPjNo(parser.nextText());
						// ��QNo.
						} else if (tag.equals("no")) {
							currentItem.setNo(parser.nextText());
						// ������
						} else if (tag.equals("postdate")) {
							currentItem.setPostDate(parser.nextText());
						// �T�v
						} else if (tag.equals("title")) {
							currentItem.setTitle(parser.nextText());
						// �Ή���
						} else if (tag.equals("supportkbn")) {
							currentItem.setSupportKbn(parser.nextText());
						// �D��x
						} else if (tag.equals("prioritylv")) {
							currentItem.setPriorityLv(parser.nextText());
						// �d�v�x
						} else if (tag.equals("importantlv")) {
							currentItem.setImportantLv(parser.nextText());
						// �Ή��J�n��
						} else if (tag.equals("startdate")) {
							currentItem.setStartDate(parser.nextText());
						// �Ή��I����
						} else if (tag.equals("enddate")) {
							currentItem.setEndDate(parser.nextText());
						// �����[�X��
						} else if (tag.equals("releasedate")) {
							currentItem.setReleaseDate(parser.nextText());
						// �w�E�S��
						} else if (tag.equals("discoveryemp")) {
							currentItem.setDiscoveryEmp(parser.nextText());
						// �Ή��S��
						} else if (tag.equals("supportemp")) {
							currentItem.setSupportEmp(parser.nextText());
						// ��Q�����ӏ�
						} else if (tag.equals("troublespots")) {
							currentItem.setTroubleSpots(parser.nextText());
						// ���e
						} else if (tag.equals("failurebody")) {
							currentItem.setFailureBody(parser.nextText());
						// ��Q����
						} else if (tag.equals("troublecause")) {
							currentItem.setTroubleCause(parser.nextText());
						// ��Q�����敪
						} else if (tag.equals("troublecausekbn")) {
							currentItem.setTroubleCauseKbn(parser.nextText());
						// �C�����e
						} else if (tag.equals("repairbody")) {
							currentItem.setRepairBody(parser.nextText());
						// �Ή�����
						} else if (tag.equals("results")) {
							currentItem.setResults(parser.nextText());
						// �C�����Y
						} else if (tag.equals("repairsrc")) {
							currentItem.setRepairSrc(parser.nextText());
						// ���l
						} else if (tag.equals("remarks")) {
							currentItem.setRemarks(parser.nextText());
						// �u���E�U�[
						} else if (tag.equals("browser")) {
							currentItem.setBrowser(parser.nextText());
						}
					}
					break;
					case XmlPullParser.END_TAG:
					tag = parser.getName();
					
					if (tag.equals("item")) {
						mAdapter.add(currentItem);
					}
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mAdapter;
	}
}
