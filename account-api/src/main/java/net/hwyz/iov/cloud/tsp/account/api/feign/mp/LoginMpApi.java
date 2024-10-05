package net.hwyz.iov.cloud.tsp.account.api.feign.mp;


import net.hwyz.iov.cloud.tsp.account.api.contract.request.SendSmsLoginVerifyCodeRequest;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.SmsVerifyCodeLoginRequest;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.WxMobileCodeLoginRequest;
import net.hwyz.iov.cloud.tsp.account.api.contract.response.LoginMpResponse;
import net.hwyz.iov.cloud.tsp.framework.commons.bean.Response;

/**
 * 登录相关手机接口
 *
 * @author hwyz_leo
 */
public interface LoginMpApi {

    /**
     * 发送短信登录验证码
     *
     * @param clientId 客户端ID
     * @param request  发送登录验证码请求
     * @return 操作结果
     */
    Response<Void> sendSmsVerifyCode(String clientId, SendSmsLoginVerifyCodeRequest request);

    /**
     * 短信验证码登录
     *
     * @param clientId 客户端ID
     * @param request  短信验证码登录请求
     * @return 手机登录结果
     */
    Response<LoginMpResponse> smsVerifyCodeLogin(String clientId, SmsVerifyCodeLoginRequest request);

    /**
     * 微信手机授权码登录
     *
     * @param clientId 客户端ID
     * @param request  微信手机码登录请求
     * @return 微信手机码登录结果
     */
    Response<LoginMpResponse> weixinMobileCodeLogin(String clientId, WxMobileCodeLoginRequest request);

}
