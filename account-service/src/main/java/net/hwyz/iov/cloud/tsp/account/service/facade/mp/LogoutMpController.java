package net.hwyz.iov.cloud.tsp.account.service.facade.mp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.bean.ClientAccount;
import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.tsp.account.api.feign.mp.LogoutMpApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 退出登录相关手机接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mp/logout")
public class LogoutMpController implements LogoutMpApi {

    @Override
    @PostMapping(value = "")
    public Response<Void> logout(@RequestHeader ClientAccount clientAccount) {
        logger.info("手机客户端[{}]账号[{}]退出登录", clientAccount.getClientId(), clientAccount.getAccountId());
        return new Response<>();
    }
}
