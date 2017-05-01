package pw.cdmi.wechat.store;

import java.io.InputStream;
import java.util.List;

import pw.cdmi.wechat.store.model.Store;
import pw.cdmi.wechat.store.model.NewStoreRequest;
import pw.cdmi.wechat.store.model.UpdateStoreRequest;

public interface StoreClient {	
	// 查询门店列表
	static final String LIST_STORES_API = "https://api.weixin.qq.com/cgi-bin/poi/getpoilist";

	// 上传门店图片
	static final String UPLOAD_STORE_PHOTOES_API = "https://api.weixin.qq.com/cgi-bin/media/uploadimg";

	// 创建门店
	static final String CREATE_STORE_API = "http://api.weixin.qq.com/cgi-bin/poi/addpoi";

	// 查询门店信息
	static final String GET_STORE_API = "http://api.weixin.qq.com/cgi-bin/poi/getpoi";

	// 修改门店信息
	static final String UPDATE_STORE_API = "https://api.weixin.qq.com/cgi-bin/poi/updatepoi";

	// 删除门店信息
	static final String DELETE_STORE_API = "https://api.weixin.qq.com/cgi-bin/poi/delpoi";

	// 获得门店类型
	static final String STORE_CATEGORIES_API = "http://api.weixin.qq.com/cgi-bin/poi/getwxcategory";
	
	public List<Store> listStores(String begin, String limit);
	
	public void createStore(NewStoreRequest store);
	
	public String uploadStorePhotoes(InputStream inputStream);
	
	public Store getStore(String poi_id);
	
	public void updateStore(UpdateStoreRequest store);
	
	public void deleteStore(String poi_id);
	
	public List<String> getStoreCategory();//全局的
	
}
