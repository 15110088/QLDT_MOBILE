package stnmt.ttcntt.qldt_mobile;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 07/07/2016.
 */
public class JsonConvert {
    public static String ConvertQueryThua(String maXa, Integer soTo, String soThua)
    {
        try {
            JSONObject op = new JSONObject();
            op.put("maXa", maXa);
            op.put("soTo", soTo);
            op.put("soThua", soThua);
            return op.toString();
        } catch (JSONException e) {
        }
        return "{}";
    }
    public static String ConvertQueryThuaChu(String key, String maXa, String tenChu, String soGiayTo)
    {
        try {
            JSONObject op = new JSONObject();
            op.put("key", key);
            op.put("maKVHC", maXa);
            if(!tenChu.isEmpty()) op.put("tenChu", tenChu);
            return op.toString();
        } catch (JSONException e) {
        }
        return "{}";
    }

    public static String ConvertQueryThanhVien(String userName, String password, String imei)
    {
        try {
            JSONObject op = new JSONObject();
            op.put("user", userName);
            op.put("matKhau", password);
            op.put("token", imei);
            return op.toString();
        } catch (JSONException e) {
        }
        return "{}";
    }

    public static String ConvertQuerySoNha(String key, String maKVHC)
    {
        try {
            JSONObject op = new JSONObject();
            op.put("key", key);
            op.put("maKVHC", maKVHC);
            return op.toString();
        } catch (JSONException e) {
        }
        return "{}";
    }
}
