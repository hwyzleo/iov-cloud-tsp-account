package net.hwyz.iov.cloud.tsp.account.service.infrastructure.external;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.api.contract.Account;
import net.hwyz.iov.cloud.tsp.account.service.domain.external.service.ExWeixinMiniProgramService;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception.WeixinMiniProgramException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 外部微信小程序服务实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
public class ExWeixinMiniProgramServiceImpl implements ExWeixinMiniProgramService {

    /**
     * APP ID
     */
    @Value("${weixin.mini-program.app-id}")
    private String appId;
    /**
     * APP SECRET
     */
    @Value("${weixin.mini-program.app-secret}")
    private String appSecret;
    /**
     * 基础地址
     */
    @Value("${weixin.mini-program.base-url:https://api.weixin.qq.com/}")
    private String baseUrl;
    /**
     * 获取访问令牌路径
     */
    @Value("${weixin.access-token-path:cgi-bin/token}")
    private String getAccessTokenPath;
    /**
     * 获取手机号路径
     */
    @Value("${weixin.mobile-path:wxa/business/getuserphonenumber}")
    private String getMobilePath;

    @Override
    public Account getMobileByCode(String mobileCode) {
        String accessToken = getAccessToken();
        String getMobileUrl = baseUrl + getMobilePath + "?access_token=" + accessToken;
        String jsonStr;
        Account accountInfo;
        try {
            String body = "{\"code\": \"" + mobileCode + "\"}";
            jsonStr = HttpUtil.post(getMobileUrl, body);
            JSONObject phoneInfo = JSONUtil.parseObj(jsonStr).getJSONObject("phone_info");
            accountInfo = new Account();
            String countryCode = phoneInfo.getStr("countryCode");
            if (StrUtil.isNotBlank(countryCode) && !countryCode.startsWith("+")) {
                countryCode = "+" + countryCode;
            }
            accountInfo.setCountryRegionCode(countryCode);
            accountInfo.setMobile(phoneInfo.getStr("purePhoneNumber"));
        } catch (Exception e) {
            throw new WeixinMiniProgramException("获取小程序手机号失败，错误原因：" + e.getMessage());
        }
        return accountInfo;
    }

    /**
     * 获取微信小程序的访问令牌
     *
     * @return accessToken
     */
    private String getAccessToken() {
        String getAccessTokenUrl = baseUrl + getAccessTokenPath +
                "?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        String jsonStr;
        String accessToken;
        try {
            jsonStr = HttpUtil.get(getAccessTokenUrl);
            accessToken = JSONUtil.parseObj(jsonStr).getStr("access_token");
        } catch (Exception e) {
            throw new WeixinMiniProgramException("获取小程序访问令牌失败，错误原因：" + e.getMessage());
        }
        return accessToken;
    }

}
