package net.hwyz.iov.cloud.tsp.account.service.facade.mp;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.SendSmsLoginVerifyCodeRequest;
import net.hwyz.iov.cloud.tsp.account.api.contract.request.SmsVerifyCodeLoginRequest;
import net.hwyz.iov.cloud.tsp.account.api.contract.response.LoginMpResponse;
import net.hwyz.iov.cloud.tsp.account.api.feign.mp.LoginMpApi;
import net.hwyz.iov.cloud.tsp.account.service.application.service.LoginAppService;
import net.hwyz.iov.cloud.tsp.account.service.domain.contract.enums.CountryRegion;
import net.hwyz.iov.cloud.tsp.framework.commons.bean.Response;
import org.springframework.web.bind.annotation.*;

/**
 * 登录相关手机接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mp/login")
public class LoginMpController implements LoginMpApi {

    final LoginAppService loginAppService;

    @Override
    @PostMapping(value = "/action/sendSmsVerifyCode")
    public Response<Void> sendSmsVerifyCode(@RequestHeader String clientId, @RequestBody @Valid SendSmsLoginVerifyCodeRequest request) {
        logger.info("手机客户端[{}]向手机[{}:{}]发送短信登录验证码", clientId, request.getCountryRegionCode(), request.getMobile());
        loginAppService.sendMobileVerifyCode(clientId, CountryRegion.valOf(request.getCountryRegionCode()), request.getMobile());
        return new Response<>();
    }

    @Override
    @PostMapping(value = "/action/smsVerifyCodeLogin")
    public Response<LoginMpResponse> smsVerifyCodeLogin(@RequestHeader String clientId, @RequestBody @Valid SmsVerifyCodeLoginRequest request) {
        logger.info("手机客户端[{}]通过验证码[{}]登录手机账号[{}:{}]", clientId, request.getVerifyCode(), request.getCountryRegionCode(),
                request.getMobile());
        LoginMpResponse loginResponse = loginAppService.mobileVerifyCodeLogin(clientId,
                CountryRegion.valOf(request.getCountryRegionCode()), request.getMobile(), request.getVerifyCode());
        return new Response<>(loginResponse);
    }
}
