package com.nttdata.emea.devschool.vehicleordering.network;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



import android.accounts.NetworkErrorException;
import android.os.AsyncTask;
import android.util.Log;

public class RestClient {

	public static final int GET = 0;
	public static final int POST = 1;

    private ArrayList <NameValuePair> headers;
    private String server;
    private String rpc;
    
    private boolean responseReceived=false;
    private String response="";


    public RestClient(String server)
    {
    	this.server = server;

        headers = new ArrayList<NameValuePair>();
    }

    public void addHeader(String name, String value)
    {
    	headers.add(new BasicNameValuePair(name, value));
    }
    public boolean responseReceived()
    {
    	return this.responseReceived;
    }
    public String getResponse()
    {
    	return this.response;
    }
    
    public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}

	public String getRpc() {
		return rpc != null ? rpc : "rpc";
	}
	public void setRpc(String rpc) {
		this.rpc = rpc;
	}

	
	public void callGet(String url, List<NameValuePair> parameters, OnRestResponse onReponse)
	{
		
	}
	

	public void get(String url, OnRestResponse onReponse) {
    	
		HttpGet request = new HttpGet(getServer() + "/" + url);
    	buildAndSend(request, onReponse);    	
    }

	public void post(String url, String body, OnRestResponse onReponse) throws NetworkErrorException {
    	
		HttpPost request = new HttpPost(getServer() + "/" + url);

        
    	try {
    		
    		request.setEntity(new StringEntity(body));
    		
    		
    	} catch (UnsupportedEncodingException e) {
			onReponse.onError(501, e.getLocalizedMessage());
		}
    	
    	buildAndSend(request, onReponse);    	
    }

	public void put(String url, List<NameValuePair> parameters, OnRestResponse onReponse) throws NetworkErrorException {
    	
		HttpPut request = new HttpPut(getServer() + "/" + url);
        
    	try {
    		
    		request.setEntity(new UrlEncodedFormEntity(parameters));
    		
    	} catch (UnsupportedEncodingException e) {
			onReponse.onError(501, e.getLocalizedMessage());
		}
    	
    	buildAndSend(request, onReponse);    	
    }

	private void buildAndSend(HttpUriRequest request, OnRestResponse onReponse) {
    	
    	for(NameValuePair h : headers) {
            request.addHeader(h.getName(), h.getValue());
        }

		RestRequest restRequest = new RestRequest();
    	restRequest.setOnReponse(onReponse);
    	restRequest.setRequest(request);
    	restRequest.execute();

	}

	private class RestRequest extends AsyncTask<String, Integer, String> {

		private HttpUriRequest request;
		private OnRestResponse onReponse;
		private Integer errorCode;
		private String errorMessage;

		public HttpUriRequest getRequest() {
			return request;
		}
		public void setRequest(HttpUriRequest request) {
			this.request = request;
		}

		public OnRestResponse getOnReponse() {
			return onReponse;
		}
		public void setOnReponse(OnRestResponse onReponse) {
			this.onReponse = onReponse;
		}

		public Integer getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(Integer errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		@Override
		protected String doInBackground(String... params) {

			HttpClient client = new DefaultHttpClient();
	        HttpResponse httpResponse;
	        String response = null;

	        try {

	        	httpResponse = client.execute(getRequest());

	        	setErrorCode(httpResponse.getStatusLine().getStatusCode());
	            setErrorMessage(httpResponse.getStatusLine().getReasonPhrase());

	            HttpEntity entity = httpResponse.getEntity();
	            if (entity != null) {
	            	response = EntityUtils.toString(entity);
	            }	            

	        } catch (Exception e)  {

	        	setErrorCode(e.hashCode());
	        	setErrorMessage(e.getLocalizedMessage());

	        }

			return response;
		}

		@Override
		protected void onPostExecute(String result){
			if(getOnReponse() != null){
				if(result != null){
					getOnReponse().onResponse(result);
					response=result;
					responseReceived=true;
				}else{
					response=result;
					responseReceived=true;
					getOnReponse().onError(getErrorCode(), getErrorMessage());
				}
			}
		}

	}
    
}