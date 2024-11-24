package net.hwyz.iov.cloud.tsp.account.service.application.service;

import cn.hutool.core.util.PhoneUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.account.api.contract.AccountInfo;
import net.hwyz.iov.cloud.tsp.account.api.contract.response.LoginMpResponse;
import net.hwyz.iov.cloud.tsp.account.service.domain.account.model.AccountDo;
import net.hwyz.iov.cloud.tsp.account.service.domain.account.repository.AccountRepository;
import net.hwyz.iov.cloud.tsp.account.service.domain.account.service.AccountService;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.model.ClientDo;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.repository.ClientRepository;
import net.hwyz.iov.cloud.tsp.account.service.domain.client.service.ClientService;
import net.hwyz.iov.cloud.tsp.account.service.domain.contract.enums.ClientOperation;
import net.hwyz.iov.cloud.tsp.account.service.domain.contract.enums.RegSource;
import net.hwyz.iov.cloud.tsp.account.service.domain.external.service.ExWeixinMiniProgramService;
import net.hwyz.iov.cloud.tsp.account.service.domain.login.service.LoginService;
import net.hwyz.iov.cloud.tsp.account.service.domain.token.model.TokenDo;
import net.hwyz.iov.cloud.tsp.account.service.domain.token.service.TokenService;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception.AccountNotEnableException;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception.MobileInvalidException;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception.MobileLoginVerifyCodeIncorrectException;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception.WeixinMiniProgramException;
import net.hwyz.iov.cloud.tsp.framework.commons.domain.DoState;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.ClientType;
import net.hwyz.iov.cloud.tsp.framework.commons.enums.CountryRegion;
import org.springframework.stereotype.Service;

/**
 * 登录应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginAppService {

    private final LoginService loginService;
    private final TokenService tokenService;
    private final ClientService clientService;
    private final AccountService accountService;
    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final ExWeixinMiniProgramService exWeixinMiniProgramService;

    /**
     * 发送手机登录验证码
     *
     * @param clientId      客户端ID
     * @param countryRegion 国家或地区
     * @param mobile        手机号
     */
    public void sendMobileVerifyCode(String clientId, CountryRegion countryRegion, String mobile) {
        checkMobile(countryRegion, mobile);
        ClientDo clientDo = clientService.getOrCreate(clientId, ClientType.MP);
        clientDo.checkOperation(ClientOperation.SEND_LOGIN_VERIFY_CODE);
        clientRepository.save(clientDo);
        loginService.sendMobileVerifyCode(clientId, countryRegion, mobile);
    }

    /**
     * 手机验证码登录
     *
     * @param clientId      客户端ID
     * @param countryRegion 国家或地区
     * @param mobile        手机号
     * @param verifyCode    验证码
     * @return 登录响应
     */
    public LoginMpResponse mobileVerifyCodeLogin(String clientId, CountryRegion countryRegion, String mobile, String verifyCode) {
        checkMobile(countryRegion, mobile);
        boolean verifySuccess = loginService.verifyMobileVerifyCode(countryRegion, mobile, verifyCode);
        if (verifySuccess) {
            AccountDo accountDo = accountService.getOrCreate(countryRegion, mobile);
            if (!accountDo.getEnable()) {
                throw new AccountNotEnableException(accountDo.getAccountId());
            }
            if (accountDo.getState() == DoState.NEW) {
                accountDo.markRegSource(RegSource.APP);
            }
            accountRepository.save(accountDo);
            clientService.login(clientId, ClientType.MP, accountDo.getAccountId());
            TokenDo tokenDo = tokenService.createMpToken(accountDo.getAccountId(), clientId);
            return LoginMpResponse.builder()
                    .mobile(mobile)
                    .nickname(accountDo.getNickname())
                    .avatar(accountDo.getAvatar())
                    .token(tokenDo.getAccessToken())
                    .tokenExpires(tokenDo.getAccessTokenExpires())
                    .refreshToken(tokenDo.getRefreshToken())
                    .refreshTokenExpires(tokenDo.getRefreshTokenExpires())
                    .build();
        }
        throw new MobileLoginVerifyCodeIncorrectException(countryRegion, mobile);
    }

    /**
     * 微信小程序登录
     *
     * @param clientId   客户端ID
     * @param mobileCode 微信小程序手机号授权码
     * @return 登录响应
     */
    public LoginMpResponse weixinMiniProgramLogin(String clientId, String mobileCode) {
        AccountInfo accountInfo = exWeixinMiniProgramService.getMobileByCode(mobileCode);
        if (accountInfo != null) {
            CountryRegion countryRegion = CountryRegion.valOf(accountInfo.getCountryRegionCode());
            AccountDo accountDo = accountService.getOrCreate(countryRegion, accountInfo.getMobile());
            if (accountDo.getState() == DoState.NEW) {
                accountDo.markRegSource(RegSource.WEIXIN_MINI_PROGRAM);
            }
            accountRepository.save(accountDo);
            clientService.login(clientId, ClientType.MP, accountDo.getAccountId());
            TokenDo tokenDo = tokenService.createMpToken(accountDo.getAccountId(), clientId);
            return LoginMpResponse.builder()
                    .mobile(accountInfo.getMobile())
                    .nickname(accountDo.getNickname())
                    .avatar(accountDo.getAvatar())
                    .token(tokenDo.getAccessToken())
                    .tokenExpires(tokenDo.getAccessTokenExpires())
                    .refreshToken(tokenDo.getRefreshToken())
                    .refreshTokenExpires(tokenDo.getRefreshTokenExpires())
                    .build();
        }
        throw new WeixinMiniProgramException("微信小程序登录失败");
    }

    /**
     * 检查手机号格式
     *
     * @param countryRegion 国家或地区
     * @param mobile        手机号
     */
    private void checkMobile(CountryRegion countryRegion, String mobile) {
        logger.info("检查国家或地区[{}]手机号[{}]有效性", countryRegion.name, mobile);
        boolean mobileInvalid = switch (countryRegion) {
            case CHINESE_MAINLAND -> !PhoneUtil.isMobile(mobile);
            case CHINESE_HONG_KONG -> !PhoneUtil.isMobileHk(mobile);
            case CHINESE_MACAU -> !PhoneUtil.isMobileMo(mobile);
            case CHINESE_TAIWAN -> !PhoneUtil.isMobileTw(mobile);
        };
        if (mobileInvalid) {
            throw new MobileInvalidException(mobile, countryRegion);
        }
    }

}
