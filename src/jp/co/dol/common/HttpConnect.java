package jp.co.dol.common;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpConnect {

	
	public static String post(String url, List<NameValuePair> param) {
		
		try {
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			HttpHost proxy = new HttpHost(ConstMaster.PROXY_SERVER, ConstMaster.PROXY_PORT);
			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			httppost.setEntity(new UrlEncodedFormEntity(param));

			HttpResponse response = httpclient.execute(httppost);

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			response.getEntity().writeTo(byteArrayOutputStream);

			return byteArrayOutputStream.toString();
			
		} catch (Exception e) {
		
			return e.getMessage();
		}
	}
}
