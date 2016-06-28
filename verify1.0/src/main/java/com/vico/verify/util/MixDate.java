package com.vico.verify.util;

/**
 * 
 * @ClassName: MixDate
 * @Description: 混淆/解混淆，混淆方式：所有字符ascii码偏移64位
 * @author: Liu.Dun
 * @date: 2016年6月27日 下午8:59:09
 */
public class MixDate {

	static final int offset = 64; // 偏移量

	/**
	 * @param:
	 * @return: String
	 * @Title: mixDate
	 * @Description: 前20位ascii码向右偏移offset位,写入数据库前操作，返回结果为不带日期序列号
	 * @param sourceNumber
	 * @return
	 *
	 */
	public static String mixDate(String sourceNumber) {
		// 序列号总长度为56个字符，前20位为日期，
		String str1 = sourceNumber.substring(0, 20);
		String str2 = sourceNumber.substring(20);

		StringBuffer sb = new StringBuffer();
		char[] oricode = str1.toCharArray();
		int leng = oricode.length;
		int[] afcode = new int[leng];
		for (int i = 0; i < leng; i++) {
			afcode[i] = oricode[i] + offset; // 所有字符的ascii码向右偏移64位
			char lastcode = (char) afcode[i];
			sb.append(lastcode);
		}
		sb.append(str2);
		return sb.toString();
	}

	public static String demixDate(String sourceNumber) {
		String str1 = sourceNumber.substring(0, 20);
		String str2 = sourceNumber.substring(20);
		StringBuffer sb = new StringBuffer();
		char[] oricode = str1.toCharArray();
		int leng = oricode.length;
		int[] afcode = new int[leng];
		for (int m = 0; m < leng; m++) {
			afcode[m] = (int) oricode[m] + (-offset);
			char lastcode = (char) afcode[m];
			sb.append(lastcode);
		}
		sb.append(str2);
		return sb.toString();
	}

}
