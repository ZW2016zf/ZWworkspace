package com.base.xtgl.util;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils{
	private static final int[] allChineseScope = { 1601, 1637, 1833, 2078,
			2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730,
			3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600,
			Integer.MAX_VALUE };
	public static final char unknowChar = '*';
	private static final char[] allEnglishLetter = { 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'W', 'X', 'Y', 'Z', unknowChar };

	private StringUtil() {

	}

	/**
	 * �ִ��Ƿ�Ϊ��
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else if (str.length() == 0) {
			return true;
		} else if ("NULL".equals(str.toUpperCase())) {
			return true;
		}
		return false;
	}

	/**
	 * ���ɲ�ѯ�ִ�Map
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, String> getMapFromQueryParamString(String str) {
		Map<String, String> param = new HashMap<String, String>();
		String keyValues[] = str.split("`");
		for (int i = 0; i < keyValues.length; i++) {

		}
		return param;
	}

	/**
	 * ȫ�滻
	 * 
	 * @param src
	 *            �滻�ִ�
	 * @param tar
	 *            �滻Ŀ��
	 * @param str
	 *            ���ִ�
	 * @return
	 */
	public static String replaceAll(String src, String tar, String str) {
		StringBuilder sb = new StringBuilder();
		byte bytesSrc[] = src.getBytes();

		byte bytes[] = str.getBytes();
		int point = 0;
		for (int i = 0; i < bytes.length; i++) {

			if (isStartWith(bytes, i, bytesSrc, 0)) {

				sb.append(new String(bytes, point, i));
				sb.append(tar);
				i += bytesSrc.length;
				point = i;
			}

		}
		sb.append(new String(bytes, point, bytes.length));
		return sb.toString();
	}

	/**
	 * 
	 * @param bytesSrc
	 * @param bytesTar
	 * @return
	 */
	private static boolean isStartWith(byte bytesSrc[], int startSrc,
			byte bytesTar[], int startTar) {
		StringBuilder sb = new StringBuilder();
		for (int j = startTar; j < bytesTar.length; j++) {
			if (bytesSrc[startSrc + j] != bytesTar[j]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * ȡ����ƴ�����ַ�
	 * 
	 * @param str
	 * @return
	 */
	public static char getFirstLetterFromChinessWord(String str) {
		char result = '*';
		String temp = str.toUpperCase();
		try {
			byte[] bytes = temp.getBytes("gbk");
			if (bytes[0] < 128 && bytes[0] > 0) {
				return (char) bytes[0];
			}

			int gbkIndex = 0;

			for (int i = 0; i < bytes.length; i++) {
				bytes[i] -= 160;
			}
			gbkIndex = bytes[0] * 100 + bytes[1];
			for (int i = 0; i < allEnglishLetter.length; i++) {
				if (i == 22) {
					// System.out.println(allEnglishLetter.length
					// +" "+allChineseScope.length);
				}
				if (gbkIndex >= allChineseScope[i]
						&& gbkIndex < allChineseScope[i + 1]) {
					result = allEnglishLetter[i];
					break;
				}
			}

		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * �ִ��ָ�
	 * 
	 * @param src
	 * @param letter
	 * @return
	 */
	public static String[] split(String src, char letter) {
		if (src == null) {
			return new String[0];
		}
		List<String> ret = new ArrayList();
		byte bytes[] = src.getBytes();
		int curPoint = 0;
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == letter) {
				String s = new String(bytes, curPoint, i - curPoint);
				ret.add(s);
				curPoint=i+1;
			}
		}
		if (ret.size() == 0) {
			return new String[] { src };
		}
		//ret.add(new String(bytes, curPoint, src.length() - curPoint));
		String[] retStr = new String[ret.size()];
		for (int i = 0; i < ret.size(); i++) {
			retStr[i] = ret.get(i);
		}
		return retStr;
	}
	
	public static String[] split(String src ,String letter){
		String retStr[] = new String[0];
		try{
			 retStr = StringUtils.split(src, letter);
			
		}catch(Exception e){
			return retStr;
		}
		return retStr;
	}
	
	/**
	 * ȥ�����һ���ַ�
	 * @param str
	 * @return
	 */
	public static String removeLast(String str){
		if(str == null){
			return null;
		}
		return str.substring(0,str.length()-1);
		
	} 
	
	
	
	public static void main(String argv[]) throws UnsupportedEncodingException {
		
		//System.out.println(split("aa&dd_dd&dd","&"));
		String[] split = StringUtils.split("aa&dd_dd&dd", "323");
		//for(String a:split){
			System.out.println(split[0]);
		//}

		
		//String bmdm_id = "102120";String bmdm_bjdm_id = "102120_1";
		//System.out.println(bmdm_id.matches(bmdm_bjdm_id));
		
		// System.out.println(StringUtil.getFirstLetterFromChinessWord("��"));
		// int b = 0;
		// for (int i = 0; i < 50; i++) {
		// for (int j = 0; j < 50; j++) {
		// byte bytes[] = new byte[] { (byte) (56 + 160 + i),
		// (byte) (00 + 160 + j) };
		// String aa = new String(bytes, "gbk");
		// if (!isEmpty(aa)) {
		// b++;
		// System.out.println(aa + " " + i + " " + j + " "
		// + (i * 50 + j));
		// }
		// }
		// }
		// bytes="��".getBytes("gbk");
		// System.out.println(new String(bytes,"gbk"));
		// System.out.println(StringUtil.getFirstLetterFromChinessWord("��"));
		//String[] ret = split("S||||a&r", 's');
		//System.out.println(ret.length);
//		for (int i = 0; i < ret.length; i++) {
//			System.out.println(ret[i]);
//		}
		
//		String filterField = "bmdm_id,zydm_id,njdm_id";
//		List<String> list = new ArrayList<String>();
//		list.add("bmdm_id=100001,njdm_id=1");
//		list.add("bmdm_id=102120,njdm_id=1");
//		list.add("bmdm_id=2");
//		list.add("bmdm_id=3,njdm_id=1");
//		list.add("zydm_id=1,zydm_id=1");
//		list.add("zydm_id=2,zydm_id=1");
//		list.add("zydm_id=3");
//		
//		
//		StringBuilder builder = new StringBuilder();
//		String f1 = "", f2 = "", reSql = "";
//		String filterFields[] = StringUtil.split(filterField, ',');
//		for (int i = 0; i < filterFields.length; i++) {
//			for (int j = i + 1; j < filterFields.length; j++) {
//				f1 = filterFields[i];
//				f2 = filterFields[j];
//				for (String temp : list) {
//					if (temp.contains(",")) {
//						temp = temp.replace(",", " and ");
//						if (temp.contains(f1) && temp.contains(f2)) {
// 
//								builder.append("(" + temp + ")" + " or ") ;
//							 
//						}
//
//					}
//				}
//			}
//			for (String temp : list) {
//				if(!temp.contains(",")){
//					if(temp.contains(filterFields[i])){
//	 
//							builder.append(temp + " or ");
//						 					
//					}
//				}
//			}
//		}
//		
//		reSql = builder.toString();
//		reSql = reSql.substring(0,reSql.lastIndexOf(" or "));
//		System.out.println(reSql.substring(0,reSql.lastIndexOf(" or ")));

	}
	
	  /**
     * ���ַ�������ĸСд
     *
     * @param s �ַ���
     * @return ����ĸСд������ַ���
     */
    public static String lowerFirst(CharSequence s) {
        int len = s.length();
        if (len == 0) return "";
        char c = s.charAt(0);
        if (Character.isLowerCase(c)) return s.toString();
        return new StringBuilder(len).append(Character.toLowerCase(c))
                .append(s.subSequence(1, len)).toString();
    }

    /**
     * ���ַ�������ĸ��д
     *
     * @param s
     * @return
     */
    public static String upperFirst(CharSequence s) {
        int len = s.length();
        if (len == 0) return "";
        char c = s.charAt(0);
        if (Character.isUpperCase(c)) return s.toString();
        return new StringBuilder(len).append(Character.toUpperCase(c))
                .append(s.subSequence(1, len)).toString();
    }
    
    /**
     * ��������ַ����ĺ��Դ�Сд���Ƿ����.
     *
     * @param s1 �ַ���A
     * @param s2 �ַ���B
     * @return true ��������ַ������Դ�Сд�����,�������ַ�������Ϊnull
     */
    public static boolean equalsIgnoreCase(String s1, String s2) {
        return s1 == null ? s2 == null : s1.equalsIgnoreCase(s2);
    }

    /**
     * ��������ַ����Ƿ����.
     *
     * @param s1 �ַ���A
     * @param s2 �ַ���B
     * @return true ��������ַ������,�������ַ�������Ϊnull
     */
    public static boolean equals(CharSequence s1, CharSequence s2) {
        return s1 == null ? s2 == null : s1.equals(s2);
    }

    /**
     * �ж��ַ����Ƿ��������ַ���ͷ
     *
     * @param s �ַ���
     * @param c �����ַ�
     * @return �Ƿ��������ַ���ͷ
     */
    public static boolean startsWithChar(CharSequence s, char c) {
        return null != s ? (s.length() == 0 ? false : s.charAt(0) == c) : false;
    }

    /**
     * �ж��ַ����Ƿ��������ַ���β
     *
     * @param s �ַ���
     * @param c �����ַ�
     * @return �Ƿ��������ַ���β
     */
    public static boolean endsWithChar(CharSequence s, char c) {
        return null != s ? (s.length() == 0 ? false : s.charAt(s.length() - 1) == c) : false;
    }

    public static boolean isEmpty(CharSequence... css) {
        for (CharSequence cs : css) {
            if (isEmpty(cs)) return true;
        }
        return false;
    }

    /**
     * @param cs �ַ���
     * @return �ǲ���Ϊ���ַ���
     */
    public static boolean isEmpty(CharSequence cs) {
        return null == cs || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return null != cs && cs.length() > 0;
    }

    public static boolean isNotEmpty(CharSequence... css) {
        for (CharSequence cs : css) {
            if (isEmpty(cs)) return false;
        }
        return true;
    }

    /**
     * @param cs �ַ���
     * @return �ǲ���Ϊ�հ��ַ���
     */
    public static boolean isBlank(CharSequence cs) {
        int L;
        if (cs == null || (L = cs.length()) == 0)
            return true;
        for (int i = 0; i < L; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * ȥ���ַ���ǰ��հ�
     *
     * @param cs �ַ���
     * @return ���ַ���
     */
    public static String trim(CharSequence cs) {
        if (null == cs)
            return null;
        if (cs instanceof String)
            return ((String) cs).trim();
        int length = cs.length();
        if (length == 0)
            return cs.toString();
        int l = 0;
        int last = length - 1;
        int r = last;
        for (; l < length; l++) {
            if (!Character.isWhitespace(cs.charAt(l)))
                break;
        }
        for (; r > l; r--) {
            if (!Character.isWhitespace(cs.charAt(r)))
                break;
        }
        if (l > r)
            return "";
        else if (l == 0 && r == last)
            return cs.toString();
        return cs.subSequence(l, r + 1).toString();
    }

    public static String valueOf(Object o, String defaultVal) {
        return o == null ? defaultVal : o.toString();
    }

    /**
     * ��String�е�����patternƥ����ִ��滻��
     *
     * @param sequence    ���滻���ַ���
     * @param regex       �滻���ҵ�������ʽ����
     * @param replacement �滻����
     * @return �滻������ַ���
     */
    public static String replace(CharSequence sequence, String regex, IReplaceCallback replacement) {
        if (sequence == null) return null;
        Matcher m = Pattern.compile(regex).matcher(sequence.toString());
        if (m.find()) {
            StringBuffer sb = new StringBuffer();
            int index = 0;
            while (true) {
                m.appendReplacement(sb, Matcher.quoteReplacement(replacement.replace(m.group(0), index++, m)));
                if (m.find()) continue;
                else break;
            }
            m.appendTail(sb);
            return sb.toString();
        }
        return sequence.toString();
    }

    /**
     * ��String�е�regex��һ��ƥ����ִ��滻��
     *
     * @param string      ���滻���ַ���
     * @param regex       �滻���ҵ�������ʽ
     * @param replacement �滻����
     * @return �滻������ַ���
     */
    public static String replaceFirst(String string, String regex, IReplaceCallback replacement) {
        if (string == null) return null;
        Matcher m = Pattern.compile(regex).matcher(string);
        StringBuffer sb = new StringBuffer();
        if (m.find()) {
            m.appendReplacement(sb, Matcher.quoteReplacement(replacement.replace(m.group(0), 0, m)));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * �ַ����滻�Ļص��ӿ�
     */
    public interface IReplaceCallback {
        /**
         * ��textת��Ϊ�ض����ִ�����
         *
         * @param text    ָ�����ַ���
         * @param index   �滻�Ĵ���
         * @param matcher Matcher����
         * @return
         */
        public String replace(String text, int index, Matcher matcher);
    }

    /**
     * ������ַ����滻�ӿ�
     * <p>��Ҫ�������$(group)���������matcher.group(group)
     */
    public static abstract class ReplaceCallback implements IReplaceCallback {
        protected Matcher matcher;

        public abstract String replace(String text, int index);

        /**
         * ��textת��Ϊ�ض����ִ�����
         *
         * @param text    ָ�����ַ���
         * @param index   �滻�Ĵ���
         * @param matcher Matcher����
         * @return �滻������ַ���
         */
        public final String replace(String text, int index, Matcher matcher) {
            this.matcher = matcher;
            try {
                return replace(text, index);
            } finally {
                this.matcher = null;
            }
        }

        /**
         * ���matcher�е�������
         * <p>��ͬ��matcher.group(group)
         * <p> �ú���ֻ����{@link #replace(String, int)} �е���
         *
         * @param group ��
         * @return ������
         */
        protected final String $(int group) {
            String data = matcher.group(group);
            return data == null ? "" : data;
        }
    }
}