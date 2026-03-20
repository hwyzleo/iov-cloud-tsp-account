package net.hwyz.iov.cloud.tsp.account.service.facade.mp;

import cn.hutool.core.map.MapUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.bean.ClientAccount;
import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.tsp.account.api.contract.AccountMp;
import net.hwyz.iov.cloud.tsp.account.api.contract.AccountQrcodeMp;
import net.hwyz.iov.cloud.tsp.account.api.feign.mp.AccountMpApi;
import net.hwyz.iov.cloud.tsp.account.service.application.service.AccountAppService;
import net.hwyz.iov.cloud.tsp.account.service.domain.account.model.AccountDo;
import net.hwyz.iov.cloud.tsp.account.service.facade.assembler.AccountMpAssembler;
import net.hwyz.iov.cloud.tsp.account.service.infrastructure.exception.AccountNotExistException;
import net.hwyz.iov.cloud.tsp.oss.api.contract.PreSignedUrl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 账户相关手机接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/mp/account")
public class AccountMpController implements AccountMpApi {

    final AccountAppService accountAppService;

    @Override
    @GetMapping(value = "/info")
    public Response<AccountMp> getAccountInfo(@RequestHeader ClientAccount clientAccount) {
        logger.info("手机客户端[{}]获取账号[{}]信息", clientAccount.getClientId(), clientAccount.getAccountId());
        AccountDo accountDo = accountAppService.getAccountInfo(clientAccount.getAccountId())
                .orElseThrow(() -> new AccountNotExistException(clientAccount.getAccountId()));
        return new Response<>(AccountMpAssembler.INSTANCE.fromDo(accountDo));
    }

    @Override
    @GetMapping(value = "/qrcode")
    public Response<AccountQrcodeMp> getAccountQrcode(@RequestHeader ClientAccount clientAccount) {
        logger.info("手机客户端[{}]获取账号[{}]二维码信息", clientAccount.getClientId(), clientAccount.getAccountId());
        AccountDo accountDo = accountAppService.getAccountInfo(clientAccount.getAccountId())
                .orElseThrow(() -> new AccountNotExistException(clientAccount.getAccountId()));
        return new Response<>(AccountQrcodeMp.builder().qrcode(accountDo.getAccountId()).build());
    }

    @Override
    @PostMapping("/action/generateAvatarUrl")
    public Response<PreSignedUrl> generateAvatarUrl(@RequestHeader ClientAccount clientAccount) {
        logger.info("手机客户端[{}]生成账号[{}]头像上传地址", clientAccount.getClientId(), clientAccount.getAccountId());
        return new Response<>(accountAppService.generateAvatarUrl(clientAccount.getAccountId()));
    }

    @Override
    @PostMapping(
            value = "/action/modifyAvatar",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<Void> modifyAvatar(@RequestHeader ClientAccount clientAccount, @RequestParam String avatar) {
        logger.info("手机客户端[{}]修改账号[{}]头像", clientAccount.getClientId(), clientAccount.getAccountId());
        accountAppService.modifyMpAccountInfo(clientAccount.getAccountId(), MapUtil.of("avatar", avatar));
        return new Response<>();
    }

    @Override
    @PostMapping(
            value = "/action/modifyNickname",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<Void> modifyNickname(@RequestHeader ClientAccount clientAccount, @RequestParam String nickname) {
        logger.info("手机客户端[{}]修改账号[{}]昵称[{}]", clientAccount.getClientId(), clientAccount.getAccountId(), nickname);
        accountAppService.modifyMpAccountInfo(clientAccount.getAccountId(), MapUtil.of("nickname", nickname));
        return new Response<>();
    }

    @Override
    @PostMapping(
            value = "/action/modifyBio",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<Void> modifyBio(@RequestHeader ClientAccount clientAccount, @RequestParam String bio) {
        logger.info("手机客户端[{}]修改账号[{}]签名简介[{}]", clientAccount.getClientId(), clientAccount.getAccountId(), bio);
        accountAppService.modifyMpAccountInfo(clientAccount.getAccountId(), MapUtil.of("bio", bio));
        return new Response<>();
    }

    @Override
    @PostMapping(
            value = "/action/modifyGender",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<Void> modifyGender(@RequestHeader ClientAccount clientAccount, @RequestParam String gender) {
        logger.info("手机客户端[{}]修改账号[{}]性别[{}]", clientAccount.getClientId(), clientAccount.getAccountId(), gender);
        accountAppService.modifyMpAccountInfo(clientAccount.getAccountId(), MapUtil.of("gender", gender));
        return new Response<>();
    }

    @Override
    @PostMapping(
            value = "/action/modifyBirthday",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<Void> modifyBirthday(@RequestHeader ClientAccount clientAccount, @RequestParam String birthday) {
        logger.info("手机客户端[{}]修改账号[{}]生日[{}]", clientAccount.getClientId(), clientAccount.getAccountId(), birthday);
        accountAppService.modifyMpAccountInfo(clientAccount.getAccountId(), MapUtil.of("birthday", birthday));
        return new Response<>();
    }

    @Override
    @PostMapping(
            value = "/action/modifyCity",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<Void> modifyCity(@RequestHeader ClientAccount clientAccount, @RequestParam String city) {
        logger.info("手机客户端[{}]修改账号[{}]用车城市[{}]", clientAccount.getClientId(), clientAccount.getAccountId(), city);
        accountAppService.modifyMpAccountInfo(clientAccount.getAccountId(), MapUtil.of("city", city));
        return new Response<>();
    }
}
