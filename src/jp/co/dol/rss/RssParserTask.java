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
	
	// コンストラクタ
	public RssParserTask(RSSReaderActivity activity, RssListAdapter adapter,ListView _listView) {
		mActivity = activity;
		mListView = _listView;
		mAdapter = adapter;
	}
	
	// タスクを実行した直後にコールされる
	@Override
	protected void onPreExecute() {
		// プログレスバーを表示する
		mProgressDialog = new ProgressDialog(mActivity);
		mProgressDialog.setMessage("Now Loading...");
		mProgressDialog.show();
	}
	
	// バックグラウンドにおける処理を担う。タスク実行時に渡された値を引数とする
	@Override
	protected RssListAdapter doInBackground(String... params) {
		RssListAdapter result = null;
		try {
	//		System.setProperty("proxySet", "true");
	//		System.setProperty("http.proxyHost", "192.168.1.1");
	//		System.setProperty("http.proxyPort", "8080");
			
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ConstMaster.PROXY_SERVER, ConstMaster.PROXY_PORT));
			
			// HTTP経由でアクセスし、InputStreamを取得する
			URL url = new URL(params[0]);
			InputStream is = url.openConnection(proxy).getInputStream();
			result = parseXml(is);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ここで返した値は、onPostExecuteメソッドの引数として渡される
		return result;
	}
	
	// メインスレッド上で実行される
	@Override
	protected void onPostExecute(RssListAdapter result) {
		mProgressDialog.dismiss();
		mProgressDialog = null;
		mListView.setAdapter(result);
	}
	
	// XMLをパースする
	public RssListAdapter parseXml(InputStream is) throws IOException, XmlPullParserException {
		//パーサーを作成する
		XmlPullParser parser = Xml.newPullParser();
		try {
			//パーサーにinputStraemをセットする。これはURLを読み込んでいるStream
			parser.setInput(is, null);
			
			//読み込みの進捗をここから取得ができる。（値はSTARTなどが存在する。）
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

						// 案件No.
						if (tag.equals("pjno")) {
							currentItem.setPjNo(parser.nextText());
						// 障害No.
						} else if (tag.equals("no")) {
							currentItem.setNo(parser.nextText());
						// 発生日
						} else if (tag.equals("postdate")) {
							currentItem.setPostDate(parser.nextText());
						// 概要
						} else if (tag.equals("title")) {
							currentItem.setTitle(parser.nextText());
						// 対応可否
						} else if (tag.equals("supportkbn")) {
							currentItem.setSupportKbn(parser.nextText());
						// 優先度
						} else if (tag.equals("prioritylv")) {
							currentItem.setPriorityLv(parser.nextText());
						// 重要度
						} else if (tag.equals("importantlv")) {
							currentItem.setImportantLv(parser.nextText());
						// 対応開始日
						} else if (tag.equals("startdate")) {
							currentItem.setStartDate(parser.nextText());
						// 対応終了日
						} else if (tag.equals("enddate")) {
							currentItem.setEndDate(parser.nextText());
						// リリース日
						} else if (tag.equals("releasedate")) {
							currentItem.setReleaseDate(parser.nextText());
						// 指摘担当
						} else if (tag.equals("discoveryemp")) {
							currentItem.setDiscoveryEmp(parser.nextText());
						// 対応担当
						} else if (tag.equals("supportemp")) {
							currentItem.setSupportEmp(parser.nextText());
						// 障害発生箇所
						} else if (tag.equals("troublespots")) {
							currentItem.setTroubleSpots(parser.nextText());
						// 内容
						} else if (tag.equals("failurebody")) {
							currentItem.setFailureBody(parser.nextText());
						// 障害原因
						} else if (tag.equals("troublecause")) {
							currentItem.setTroubleCause(parser.nextText());
						// 障害原因区分
						} else if (tag.equals("troublecausekbn")) {
							currentItem.setTroubleCauseKbn(parser.nextText());
						// 修正内容
						} else if (tag.equals("repairbody")) {
							currentItem.setRepairBody(parser.nextText());
						// 対応結果
						} else if (tag.equals("results")) {
							currentItem.setResults(parser.nextText());
						// 修正資産
						} else if (tag.equals("repairsrc")) {
							currentItem.setRepairSrc(parser.nextText());
						// 備考
						} else if (tag.equals("remarks")) {
							currentItem.setRemarks(parser.nextText());
						// ブラウザー
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
