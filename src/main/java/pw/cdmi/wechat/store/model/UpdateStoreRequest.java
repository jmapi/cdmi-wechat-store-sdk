package pw.cdmi.wechat.store.model;

public class UpdateStoreRequest {
	private String poi_id;						//门店在微信中的id
	private String sid;							//商户自己的id，用于后续审核通过收到poi_id 的通知时，做对应关系。请商户自己保证唯一识别性
	private String telephone;					//不超53个字符（不可以出现文字）,门店的电话（纯数字，区号、分机号均由“-”隔开）
	
	//ext info
	private String photo_list;
	private String recommend;
	private String special;
	private String introduction;
	private String open_time;
	private float avg_price; 
}
