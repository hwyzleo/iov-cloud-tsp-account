package net.hwyz.iov.cloud.tsp.account.service.facade.mp;

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
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @PostMapping("/action/modifyAvatar")
    public Response<Void> modifyAvatar(@RequestHeader ClientAccount clientAccount, @RequestBody Map<String, String> avatar) {
        logger.info("手机客户端[{}]修改账号[{}]头像", clientAccount.getClientId(), clientAccount.getAccountId());
        accountAppService.modifyMpAccountInfo(clientAccount.getAccountId(), avatar);
        return new Response<>();
    }

    @Override
    @PostMapping("/action/modifyNickname")
    public Response<Void> modifyNickname(@RequestHeader ClientAccount clientAccount, @RequestBody Map<String, String> nickname) {
        logger.info("手机客户端[{}]修改账号[{}]昵称", clientAccount.getClientId(), clientAccount.getAccountId());
        accountAppService.modifyMpAccountInfo(clientAccount.getAccountId(), nickname);
        return new Response<>();
    }

    @Override
    @PostMapping("/action/modifyBio")
    public Response<Void> modifyBio(@RequestHeader ClientAccount clientAccount, @RequestBody Map<String, String> bio) {
        logger.info("手机客户端[{}]修改账号[{}]签名简介", clientAccount.getClientId(), clientAccount.getAccountId());
        accountAppService.modifyMpAccountInfo(clientAccount.getAccountId(), bio);
        return new Response<>();
    }

    @Override
    @PostMapping("/action/modifyGender")
    public Response<Void> modifyGender(@RequestHeader ClientAccount clientAccount, @RequestBody Map<String, String> gender) {
        logger.info("手机客户端[{}]修改账号[{}]性别", clientAccount.getClientId(), clientAccount.getAccountId());
        accountAppService.modifyMpAccountInfo(clientAccount.getAccountId(), gender);
        return new Response<>();
    }

    @Override
    @PostMapping("/action/modifyBirthday")
    public Response<Void> modifyBirthday(@RequestHeader ClientAccount clientAccount, @RequestBody Map<String, String> birthday) {
        logger.info("手机客户端[{}]修改账号[{}]生日", clientAccount.getClientId(), clientAccount.getAccountId());
        accountAppService.modifyMpAccountInfo(clientAccount.getAccountId(), birthday);
        return new Response<>();
    }

    @Override
    @PostMapping("/action/modifyCity")
    public Response<Void> modifyCity(@RequestHeader ClientAccount clientAccount, @RequestBody Map<String, String> city) {
        logger.info("手机客户端[{}]修改账号[{}]用车城市", clientAccount.getClientId(), clientAccount.getAccountId());
        accountAppService.modifyMpAccountInfo(clientAccount.getAccountId(), city);
        return new Response<>();
    }
}
