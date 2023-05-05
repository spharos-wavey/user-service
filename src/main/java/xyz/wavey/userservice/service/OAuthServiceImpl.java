package xyz.wavey.userservice.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.wavey.userservice.vo.ResponseGetToken;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
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

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            String result = "";
            while((line = br.readLine()) != null){
                result += line;
            }

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

    public String decodeToken(String jwt) {
        // https://developers.kakao.com/docs/latest/ko/kakaologin/common#oidc
        // 헤더, 페이로드, 서명이 .(온점)으로 구분되어 있음
        // 우리가 필요한 정보 sub 는 payload에 속해있으므로 .으로 나눈 문자열 배열중 두번째 값을 복호화하여 사용
        String payload = jwt.split("[.]")[1];
        Base64.Decoder decoder = Base64.getDecoder();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(new String(decoder.decode(payload.getBytes())));

        return element.getAsJsonObject().get("sub").getAsString();
    }

}
