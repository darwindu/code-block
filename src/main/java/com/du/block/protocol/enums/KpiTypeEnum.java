package com.du.block.protocol.enums;

/**
 * KPI结果
 * @author darwindu
 * @date 2019/3/14
 **/
public enum KpiTypeEnum {

	KPI_S("S", "传奇"),
	KPI_A("A", "卓越"),
	KPI_B("B", "优秀"),
	KPI_C("C", "普通"),
    ;
	
	/**类型**/
	private String key;
	/**描述**/
	private String value;
    
	private KpiTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getValue(String key) {
        for (KpiTypeEnum o : KpiTypeEnum.values()) {
            if (o.getKey().equals(key)) {
                return o.getValue();
            }
        }
        return key;
    }

}
