package com.myself.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myself.latte.app.AccountManager;
import com.myself.latte.ec.database.DatabaseManager;
import com.myself.latte.ec.database.UserProfile;

/**
 * Created by Kamh on 2018/2/7.
 */

public class SignHandler {

    public static void onSignUp(String response, ISignListener listener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功
        AccountManager.setSignState(true);
        listener.onSignInSuccess();
    }

    public static void onSignIn(String response, ISignListener listener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功
        AccountManager.setSignState(true);
        listener.onSignInSuccess();
    }
}
