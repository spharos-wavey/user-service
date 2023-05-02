package xyz.wavey.userservice.oauth.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.wavey.userservice.oauth.vo.ResponseGetToken;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;


@Service
@Slf4j
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private  String clientId;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private  String redirectUri;
    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private  String grantType;


    public ResponseGetToken getAccessToken(String code){
        ResponseGetToken responseGetToken;
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            String stringBuilder = "grant_type=" + grantType +
                    "&client_id=" + clientId +
                    "&redirect_uri=" + redirectUri +
                    "&code=" + code;

            bw.write(stringBuilder);
            bw.flush();

            int responseCode = connection.getResponseCode();
            log.info("getAccessToken response code = "+ responseCode);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String result = "";
            while((line = br.readLine()) != null){
                result += line;
            }

            log.info("getAccessToken response body = " + result);
            log.info("getAccessToken response body type = " + result.getClass().getName());

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            responseGetToken = ResponseGetToken.builder()
                    .accessToken(element.getAsJsonObject().get("access_token").getAsString())
                    .refreshToken(element.getAsJsonObject().get("refresh_token").getAsString())
                    .idToken(element.getAsJsonObject().get("id_token").getAsString())
                    .build();

            br.close();
            bw.close();

            return responseGetToken;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String,Object> getUserInfo(String accessToken){
        HashMap<String,Object> userInfo = new HashMap<>();
        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        try{
            URL url  =new URL(reqUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization","Bearer " + accessToken);
            int responseCode = connection.getResponseCode();
            log.info("resposneCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null){
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

            userInfo.put("nickName",nickname);
            userInfo.put("email",email);

        } catch (Exception e){
            e.printStackTrace();
        }
        return userInfo;
    }

}
