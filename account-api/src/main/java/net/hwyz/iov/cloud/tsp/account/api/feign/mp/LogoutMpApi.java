package net.hwyz.iov.cloud.tsp.account.api.feign.mp;


import net.hwyz.iov.cloud.framework.common.bean.ClientAccount;
import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.SendSmsLoginVerifyCodeRequest;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.SmsVerifyCodeLoginRequest;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.WxMobileCodeLoginRequest;
import net.hwyz.iov.cloud.tsp.account.api.contract.response.LoginMpResponse;

/**
 * 退出登录相关手机接口
 *
 * @author hwyz_leo
 */
public interface LogoutMpApi {

    /**
     * 退出登录
     *
     * @param clientAccount 终端用户
     * @return 操作结果
     */
    Response<Void> logout(ClientAccount clientAccount);

}
