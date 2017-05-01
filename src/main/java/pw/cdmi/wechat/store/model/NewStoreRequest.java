package pw.cdmi.wechat.store.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class NewStoreRequest {
	private String sid;							//商户自己的id，用于后续审核通过收到poi_id 的通知时，做对应关系。请商户自己保证唯一识别性
	private String business_name;				//门店名称,15个汉字或30个英文字符内
	private String branch_name;					//分店名称,不超过10个字，不能含有括号和特殊字符
	private String province;					//不超过10个字
	private String city;						//不超过30个字
	private String district;					//不超过10个字
	private String address;						//门店所在的详细街道地址（不要填写省市信息）：不超过80个字
	private String telephone;					//不超53个字符（不可以出现文字）,门店的电话（纯数字，区号、分机号均由“-”隔开）
	private List<String> categories;			//门店的类型,格式：["美食,小吃快餐"]
	private int offset_type;					//坐标类型：
	private double longitude;					//门店所在地理位置的经度
	private double latitude;					//门店所在地理位置的纬度
	
	//ext info 门店服务信息字段
	private List<String> photo_list;			//图片列表，url 形式，可以有多张图片，尺寸为640*340px。
	private String recommend;					//推荐品
	private String special;						//特色服务
	private String introduction;				//商户简介,300字内
	private String open_time;					//营业时间
	private int avg_price; 						//人均价格，大于0 的整数
}
