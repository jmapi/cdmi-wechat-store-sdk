package pw.cdmi.wechat.store.model;

public enum ErrorMessage {
    System_Error(-1, "system error", "系统错误，请稍后重试"),
    Invalid_Image_Size(40009, "Invalid image size", "图片大小为0或者超过1M"),
    Invalid_Args(40097, "invalid args", "参数不正确，请参考字段要求检查json 字段"),
    Invalid_Category(65104, "invalid category", "门店的类型不合法，必须严格按照附表的分类填写"),
    Invalid_Photo_Url(65105, "invalid photo url", "图片url 不合法，必须使用接口1 的图片上传接口所获取的url"),
    Poi_Audit_State_Must_Be_Approved(65106, "poi audit state must be approved", "门店状态必须未审核通过"),
    Not_Allow_Modify(65107, "not allow modify", "扩展字段为不允许修改的状态"),
    Invalid_Business(65109, "invalid business", "门店名为空"),
    Invalid_Address(65110, "invalid address", "门店所在详细街道地址为空"),
    Invalid_Telephone(65111, "invalid telephone", "门店的电话为空"),
    Invalid_City(65112, "invalid city", "门店所在的城市为空"),
    Invalid_Province(65113, "invalid province", "门店所在的省份为空"),
    Empty_Photo_List(65114, "empty photo list", "图片列表为空"),
    Invalid_Poi_Id(65115, "invalid poi id", "poi_id 不正确");
    		
	
    private final int code;
    private final String message;
    private final String description;
    
    private ErrorMessage(int code, String message, String description){
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return this.code;
    }
    
    public String getMessage() {
        return this.message;
    }

    public String getDescription() {
        return this.description;
    }

}
