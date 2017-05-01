package pw.cdmi.wechat.store;

import java.io.InputStream;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import pw.cdmi.core.http.HttpMethod;
import pw.cdmi.core.http.client.ClientException;
import pw.cdmi.core.http.client.RequestMessage;
import pw.cdmi.core.http.client.ResponseMessage;
import pw.cdmi.core.http.client.parser.ResponseParseException;
import pw.cdmi.wechat.WeChatClient;
import pw.cdmi.wechat.store.model.Store;
import pw.cdmi.wechat.store.model.NewStoreRequest;
import pw.cdmi.wechat.store.model.UpdateStoreRequest;
import pw.cdmi.wechat.store.parser.ResponseParsers;

public class StoreClientImpl implements StoreClient {

	protected WeChatClient client;

	public StoreClientImpl(String accessKey, String secretAccessKey) {
		this.client = new WeChatClient(accessKey, secretAccessKey);
	}

	@Override
	public List<Store> listStores(String begin, String limit) {
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("begin", begin);
		params.put("limit", limit);
		JSONObject content = JSONObject.fromObject(params);
		RequestMessage request = new RequestMessage();
		request.setEndpoint(URI.create(LIST_STORES_API));
		request.setMethod(HttpMethod.POST);
		request.setContent(content.toString());
		ResponseMessage response = this.client.sendRequest(request, true);
		try {
			return ResponseParsers.listStoreResponseParser.parse(response);
		} catch (ResponseParseException rpe) {
			throw new ClientException(rpe.getMessage(), null, response.getRequestId(), rpe);
		}
	}

	@Override
	public void createStore(NewStoreRequest store) {
		JSONObject content = JSONObject.fromObject(store);
		JSONObject submit_content = new JSONObject();
		submit_content.put("base_info", content);
		JSONObject all_content = new JSONObject();
		all_content.put("business", submit_content);

		RequestMessage request = new RequestMessage();
		request.setEndpoint(URI.create(CREATE_STORE_API));
		request.setMethod(HttpMethod.POST);
		request.setContent(all_content.toString());
		this.client.sendRequest(request);
	}

	@Override
	public String uploadStorePhotoes(InputStream inputStream) {
		return null;
	}

	@Override
	public Store getStore(String poi_id) {
		JSONObject poi_json = new JSONObject();
		poi_json.put("poi_id", poi_id);

		RequestMessage request = new RequestMessage();
		request.setEndpoint(URI.create(GET_STORE_API));
		request.setMethod(HttpMethod.POST);
		request.setContent(poi_json.toString());
		ResponseMessage response = this.client.sendRequest(request, true);
		try {
			return ResponseParsers.getStoreResponseParser.parse(response);
		} catch (ResponseParseException rpe) {
			throw new ClientException(rpe.getMessage(), null, response.getRequestId(), rpe);
		}
	}

	@Override
	public void updateStore(UpdateStoreRequest store) {
		JSONObject content = JSONObject.fromObject(store);
		JSONObject submit_content = new JSONObject();
		submit_content.put("base_info", content);
		JSONObject all_content = new JSONObject();
		all_content.put("business", submit_content);

		RequestMessage request = new RequestMessage();
		request.setEndpoint(URI.create(UPDATE_STORE_API));
		request.setMethod(HttpMethod.POST);
		request.setContent(all_content.toString());
		this.client.sendRequest(request);
	}

	@Override
	public void deleteStore(String poi_id) {
		JSONObject poi_json = new JSONObject();
		poi_json.put("poi_id", poi_id);

		RequestMessage request = new RequestMessage();
		request.setEndpoint(URI.create(DELETE_STORE_API));
		request.setMethod(HttpMethod.POST);
		request.setContent(poi_json.toString());
		this.client.sendRequest(request);
	}

	@Override
	public List<String> getStoreCategory() {
		RequestMessage request = new RequestMessage();
		request.setEndpoint(URI.create(STORE_CATEGORIES_API));
		request.setMethod(HttpMethod.GET);
		ResponseMessage response = this.client.sendRequest(request);
		try {
			return ResponseParsers.getStoreCategoriesResponseParser.parse(response);
		} catch (ResponseParseException rpe) {
			throw new ClientException(rpe.getMessage(), null, response.getRequestId(), rpe);
		}
	}

}
